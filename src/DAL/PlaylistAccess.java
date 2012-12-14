package DAL;

import BE.Playlist;
import BE.Song;
import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Properties;

/**
 * MyTunes, EASV (14/12/2012)
 *
 * @author Lars Vad Sørensen, Jakob Hansen, Klaus Teddy Bøgelund Andresen og Jesper Agerbo Hansen.
 */
public class PlaylistAccess
{
    // Instance fields.
    private SQLServerDataSource dataSource;
    
    /**
     * Loads the configuration from the MyTunes.cfg file in the source directory.
     * Sets all the necessary parameters for the dataSource.
     * @throws Exception 
     */
    public PlaylistAccess() throws Exception
    {
        Properties props = new Properties();
        props.load(new FileReader("MyTunes.cfg"));
        dataSource = new SQLServerDataSource();
        dataSource.setServerName(props.getProperty("SERVER"));
        dataSource.setPortNumber(Integer.parseInt(props.getProperty("PORT")));
        dataSource.setDatabaseName(props.getProperty("DATABASE"));
        dataSource.setUser(props.getProperty("USER"));
        dataSource.setPassword(props.getProperty("PASSWORD"));
    }

    /**
     * Returns an arraylist containing all playlists on the server.
     *
     * @return an arraylist of all playlists.
     * @throws SQLException
     */
    public ArrayList<Playlist> getAll() throws SQLException
    {
        try (Connection con = dataSource.getConnection())
        {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(""
                    + "SELECT Playlist.ID, Playlist.Name, Playlist.Created "
                    + "FROM Playlist "
                    + "ORDER BY Name");

            ArrayList<Playlist> results = new ArrayList<>();
            while (rs.next())
            {
                int ID = rs.getInt("ID");
                String name = rs.getString("Name");
                String timestamp = new SimpleDateFormat("dd/MM/yyyy").format(rs.getDate("Created"));

                Playlist result = new Playlist(ID, name, timestamp);
                results.add(result);
            }
            return results;
        }
    }

    /**
     * Returns all songs from a given playlist, specified by ID.
     *
     * @param id the id of the playlist to be returned.
     * @return the contents of the playlist that shares the ID parameter.
     * @throws SQLException throws an SQLException and a custom error.
     */
    public ArrayList<Song> getAllSongs(int id) throws SQLException
    {
        try (Connection con = dataSource.getConnection())
        {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(""
                    + "SELECT PlaylistSong.*, Song.*,Artist.Name'Artist', Category.Category'Category' "
                    + "FROM PlaylistSong INNER JOIN Song "
                    + "ON PlaylistID = " + id +" AND Song.ID = PlaylistSong.SongID "
                    + "INNER JOIN Artist on Song.ArtistID = Artist.ID "
                    + "INNER JOIN Category on Song.CategoryID = Category.ID "
                    + "ORDER BY seqNo");
            ArrayList<Song> results = new ArrayList<>();
            while (rs.next())
            {
                int ID = rs.getInt("ID");
                String title = rs.getString("Title");
                String artist = rs.getString("Artist");
                String category = rs.getString("Category");
                String filename = rs.getString("Filename");
                int duration = rs.getInt("Duration");

                Song result = new Song(ID, title, artist, category, filename,duration);
                results.add(result);
            }
            return results;
        }
    }

    /**
     * Returns a playlist by fetching a matching ID on the server.
     *
     * @param id the ID of the playlist to be searched for.
     * @return a playlist-object.
     * @throws SQLException
     */
    public Playlist getByID(int id) throws SQLException
    {
        try (Connection con = dataSource.getConnection())
        {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(""
                    + "SELECT Playlist.ID, Playlist.Name "
                    + "FROM Playlist "
                    + "WHERE Playlist.ID = " + id
                    + " ORDER BY Name");
            Playlist result;
            rs.next();
            int ID = rs.getInt("ID");
            String name = rs.getString("Name");
            result = new Playlist(ID, name);
            return result;
        }
    }

    /**
     * Gets all playlists matching the search-term.
     *
     * @param name the name to be searched for.
     * @return an arraylist containing all playlists that contains the
     * search-term.
     * @throws SQLException
     */
    public ArrayList<Playlist> getByName(String name) throws SQLException
    {
        try (Connection con = dataSource.getConnection())
        {
            String sql = (""
                    + "SELECT Playlist.ID, Playlist.Name"
                    + "FROM Playlist"
                    + "WHERE Name LIKE ?"
                    + "ORDER BY Name");
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, name);
            ResultSet rs = ps.executeQuery();

            ArrayList<Playlist> results = new ArrayList<>();
            while (rs.next())
            {
                int ID = rs.getInt("ID");
                String pname = rs.getString("Name");

                Playlist playlist = new Playlist(ID, pname);
                results.add(playlist);
            }
            return results;
        }
    }

    /**
     * Puts a new playlist-object on the server.
     *
     * @param p a given playlist-object.
     * @return the newly created playlist object.
     * @throws SQLException
     */
    public Playlist insert(Playlist p) throws SQLException
    {
        String sql = ""
                + "INSERT INTO Playlist (Name)"
                + "VALUES(?)";

        Connection con = dataSource.getConnection();
        PreparedStatement ps = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
        ps.setString(1, p.getName());

        int affectedRows = ps.executeUpdate();
        if (affectedRows == 0)
        {
            throw new SQLException("Unable to insert playlist.");
        }

        ResultSet keys = ps.getGeneratedKeys();
        keys.next();
        int id = keys.getInt(1);

        return new Playlist(id, p);
    }

    /**
     * Updates a playlist that is selected by ID.
     *
     * @param p the playlist to be updated.
     * @throws SQLException
     */
    public void update(Playlist p) throws SQLException
    {
        String sql = ""
                + "UPDATE Playlist "
                + "SET Name = ? "
                + "WHERE ID ="+ p.getId() ;
        Connection con = dataSource.getConnection();
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, p.getName());
        int affectedRows = ps.executeUpdate();
        if (affectedRows == 0)
        {
            throw new SQLException("Unable to update playlist.");
        }
    }

    /**
     * Deletes a playlist with a given ID from the server.
     *
     * @param id the ID of the playlist for deletion.
     * @throws SQLException
     */
    public void delete(int id) throws SQLException
    {
        String sql = "DELETE FROM Playlist WHERE Id = " + id;

        Connection con = dataSource.getConnection();

        PreparedStatement ps = con.prepareStatement(sql);

        int affectedRows = ps.executeUpdate();
        if (affectedRows == 0)
        {
            throw new SQLException("Unable to delete playlist");
        }
    }

    /**
     * Adds a song-object to a playlist-object in the database.
     * @param p the playlist-object for the song to be added to.
     * @param s the song-object to be added to the playlist.
     * @throws SQLException 
     */
    public void addSong(Playlist p, Song s) throws SQLException
    {
        Connection con = dataSource.getConnection();
        
        String sql1 =""
                + "select MAX(seqNo)'top' "
                + "From PlaylistSong "
                + "where PlaylistID = "+p.getId();
        PreparedStatement ps1 = con.prepareStatement(sql1);
        ResultSet rs = ps1.executeQuery();        
        rs.next();
        int seqNo = rs.getInt("top")+1;
              
                
        String sql = ""
                + "INSERT INTO PlayListSong "
                + "VALUES(?,?,?)";
        
        PreparedStatement ps = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
        ps.setInt(1, p.getId());
        ps.setInt(2, s.getId());
        ps.setInt(3, seqNo);

        int affectedRows = ps.executeUpdate();
        if (affectedRows == 0)
        {
            throw new SQLException("Unable to add song to playlist.");
        }

        ResultSet keys = ps.getGeneratedKeys();
        keys.next();
    }

    /**
     * Removes a song from a given playlist on the server.
     * @param p the playlist-object for the song to be removed from.
     * @param s the song-object to be removed.
     * @throws SQLException 
     */
    public void removeSong(Playlist p, Song s) throws SQLException
    {
        String sql = "DELETE from PlayListSong"
                + "VALUES(?,?)";
        Connection con = dataSource.getConnection();
        PreparedStatement ps = con.prepareStatement(sql);

        ps.setInt(1, p.getId());
        ps.setInt(2, s.getId());

        int affectedRows = ps.executeUpdate();
        if (affectedRows == 0)
        {
            throw new SQLException("Unable to delete song from playlist");
        }
    }
}
