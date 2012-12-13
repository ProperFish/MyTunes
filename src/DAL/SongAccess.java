package DAL;

import BE.Song;
import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;

/**
 * MyTunes, EASV (14/12/2012)
 * @author Lars Vad Sørensen, Jakob Hansen, Klaus Teddy Bøgelund Andresen og Jesper Agerbo Hansen
 */

public class SongAccess
{
    // Instance fields.
    private SQLServerDataSource dataSource;

    /**
     * Establishes a connection to the server from the "MyTunes.cfg" file.
     * @throws Exception throws SQLExceptions and a custom error.
     */
    public SongAccess() throws Exception
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
     * Returns an ArrayList containing all songs from the server.
     * @return ArrayList containing all songs.
     * @throws SQLException throws SQLExceptions and a custom error.
     */
    public ArrayList<Song> getAll() throws SQLException
    {
        try (Connection con = dataSource.getConnection())
        {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(""
                    + "SELECT Song.*, Artist.Name'Artist', Category.Category'Category' "
                    + "FROM Song "
                    + "inner join Artist on Song.ArtistID = Artist.ID "
                    + "inner join Category on Song.CategoryID = Category.ID "
                    + "ORDER BY Song.Title");

            ArrayList<Song> songs = new ArrayList<>();
            while (rs.next())
            {
                int ID = rs.getInt("ID");
                String title = rs.getString("Title");
                String artist = rs.getString("Artist");
                String category = rs.getString("Category");
                String filename = rs.getString("Filename");
                int duration = rs.getInt("Duration");

                Song son = new Song(ID, title, artist, category, filename, duration);
                songs.add(son);
            }
            return songs;
        }
    }

    /**
     * Gets information from a song with a given ID.
     * @param id the id of the song to be "downloaded".
     * @return returns all the information of the given song-id.
     * @throws SQLException throws SQLExceptions and a custom error.
     */
    public Song getByID(int id) throws SQLException
    {
        try (Connection con = dataSource.getConnection())
        {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(""
                    + "SELECT Song.*, Artist.Name'Artist', Category.Category'Category' "
                    + "FROM Song "
                    + "inner join Artist on Song.ArtistID = Artist.ID " 
                    + "inner join Category on Song.CategoryID = Category.ID "
                    + "WHERE Song.ID = " + id
                    + " ORDER BY Song.Title");
            Song song;
            rs.next();
            int ID = rs.getInt("ID");
            String title = rs.getString("Title");
            String artist = rs.getString("Artist");
            String category = rs.getString("Category");
            String filename = rs.getString("Filename");
            int duration = rs.getInt("Duration");
            song = new Song(ID, title, artist, category, filename, duration);
            return song;
        }
    }

    /**
     * Gets all song-objects matching the search-string.
     * @param query the search-string to be matched on the server.
     * @return all songs matching the search-string.
     * @throws SQLException throws SQLExceptions and a custom error.
     */
    public ArrayList<Song> getByName(String query) throws SQLException
    {
        try (Connection con = dataSource.getConnection())
        {
            String sql = (""
                    + "SELECT Song.*, Artist.Name'Artist', Category.Category'Category' "
                    + "FROM Song "
                    + "INNER JOIN Artist on Song.ArtistID = Artist.ID "
                    + "INNER JOIN Category on Song.CategoryID = Category.ID "
                    + "WHERE Title LIKE ? OR Artist.Name LIKE ? "
                    + "ORDER BY Song.Title");
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, query);
            ps.setString(2, query);
            ResultSet rs = ps.executeQuery();
            ArrayList<Song> results = new ArrayList<>();
            while (rs.next())
            {
                int ID = rs.getInt("ID");
                String title = rs.getString("Title");
                String artist = rs.getString("Artist");
                String category = rs.getString("Category");
                String filename = rs.getString("Filename");
                int duration = rs.getInt("Duration");
                Song result = new Song(ID, title, artist, category, filename, duration);
                results.add(result);
            }
            return results;
        }
    }

    /**
     * Creates a new song on the server from a given song-object locally.
     * @param s the song object to be uploaded.
     * @return returns the new song object with a correct ID from the server.
     * @throws SQLException throws SQLExceptions and a custom error.
     */
    public Song insert(Song s) throws SQLException
    {
        String sql = ""
                + "INSERT INTO Song (title,artistID,categoryID,filename,duration)"
                + "SELECT ?, artist.ID'AID',category.ID'CID',?,?"
                + "FROM artist,category"
                + "WHERE artist.name like ? and category.category like ?";
                
        Connection con = dataSource.getConnection();
        PreparedStatement ps = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
        ps.setString(1, s.getTitle());
        ps.setString(2, s.getFilename());        
        ps.setInt(3, s.getDuration());
        ps.setString(4, s.getArtist());
        ps.setString(5, s.getCategory());
        
        

        int affectedRows = ps.executeUpdate();
        if (affectedRows == 0)
        {
            throw new SQLException("Unable to insert song");
        }

        ResultSet keys = ps.getGeneratedKeys();
        keys.next();
        int id = keys.getInt(1);

        return new Song(id, s);
    }

    /**
     * Updates a song-object on the server by handling changes locally first.
     * @param s the local song-object.
     * @throws SQLException throws SQLExceptions and a custom error.
     */
    public void update(Song s) throws SQLException
    {
        Connection con = dataSource.getConnection();
        
        String ttsql = "SELECT Artis.ID "
                + "where Name like ?";
        
        PreparedStatement ttps = con.prepareStatement(ttsql);
        
        ttps.setString(1,s.getArtist());
        ResultSet trs = ttps.executeQuery();
        trs.next();
        
                
        
        String tsql = "Select Artist.ID'artist', Category.ID'cat'"
                + "From Category, Artist "
                + "where Artist.Name = ? and Category.Category = ?";
        
        
        PreparedStatement tps = con.prepareStatement(tsql);
        
        tps.setString(1, s.getArtist());
        tps.setString(2, s.getCategory());
        
        ResultSet rs = tps.executeQuery();
        rs.next();
        int artistID = rs.getInt("artist");
        int catID = rs.getInt("cat");
        
        String sql = "UPDATE Song SET Title = ?, ArtistID = ?, CategoryID = ? , Duration = ? WHERE Id = ?";

        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, s.getTitle());
        ps.setInt(2, artistID);
        ps.setInt(3, catID);
        ps.setInt(4, s.getDuration());

        int affectedRows = ps.executeUpdate();
        if (affectedRows == 0)
        {
            throw new SQLException("Unable to update song");
        }
    }

    /**
     * Deletes a song on the server, specified by ID.
     * @param id the ID of a song to be deleted.
     * @throws SQLException throws SQLExceptions and a custom error.
     */
    public void delete(int id) throws SQLException
    {
        String sql = " DELETE FROM PlaylistSong WHERE SongID = ?"
                + " DELETE FROM Song WHERE Id = ?";

        Connection con = dataSource.getConnection();

        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, id);
        ps.setInt(2, id);
        int affectedRows = ps.executeUpdate();
        if (affectedRows == 0)
        {
            throw new SQLException("Unable to delete song");
        }
    }
}