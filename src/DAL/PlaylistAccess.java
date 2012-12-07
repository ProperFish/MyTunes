package DAL;

import BE.Playlist;
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

public class PlaylistAccess {

    private SQLServerDataSource dataSource;

    public PlaylistAccess() throws Exception {
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
     * @return an arraylist of all playlists.
     * @throws SQLException
     */
    public ArrayList<Playlist> getAll() throws SQLException 
    {
        try (Connection con = dataSource.getConnection()) {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(""
                    + "SELECT Playlist.ID, Playlist.Name"
                    + "FROM Playlist"
                    + "ORDER BY Name");

            ArrayList<Playlist> results = new ArrayList<>();
            while (rs.next()) {
                int ID = rs.getInt("ID");
                String name = rs.getString("Name");

                Playlist result = new Playlist(ID, name);
                results.add(result);
            }
            return results;
        }
    }

    /**
     * Returns a playlist by fetching a matching ID on the server.
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
                    + "SELECT Playlist.ID, Playlist.Name"
                    + "FROM Playlist"
                    + "WHERE ID = " + id
                    + "ORDER BY Name");
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
     * @param name the name to be searched for.
     * @return an arraylist containing all playlists that contains the search-term.
     * @throws SQLException 
     */
    public ArrayList<Playlist> getByName(String name) throws SQLException 
    {
        try (Connection con = dataSource.getConnection()) {
            String sql = (""
                    + "SELECT Playlist.ID, Playlist.Name"
                    + "FROM Playlist"
                    + "where Name like ?"
                    + "ORDER BY Name");
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, name);
            ResultSet rs = ps.executeQuery();

            ArrayList<Playlist> results = new ArrayList<>();
            while (rs.next()) {
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
     * @param p a given playlist-object.
     * @return the newly created playlist object.
     * @throws SQLException 
     */
    public Playlist insert(Playlist p) throws SQLException 
    {
        String sql = ""
                + "INSERT INTO Playlist"
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
     * @param p the playlist to be updated.
     * @throws SQLException 
     */
    public void update(Playlist p) throws SQLException 
    {
        String sql = "UPDATE Playlist SET Name = ?";
        Connection con = dataSource.getConnection();
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, p.getName());
        int affectedRows = ps.executeUpdate();
        if (affectedRows == 0) {
            throw new SQLException("Unable to update playlist.");
        }
    }

    /**
     * Deletes a playlist with a given ID from the server.
     * @param id the ID of the playlist for deletion.
     * @throws SQLException 
     */
    public void delete(int id) throws SQLException 
    {
        String sql = "DELETE FROM Playlist WHERE Id = " + id;

        Connection con = dataSource.getConnection();

        PreparedStatement ps = con.prepareStatement(sql);

        int affectedRows = ps.executeUpdate();
        if (affectedRows == 0) {
            throw new SQLException("Unable to delete playlist");
        }
    }
}
