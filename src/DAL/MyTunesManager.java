/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
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
 *
 * @author bhp
 */
public class MyTunesManager
{

    private SQLServerDataSource dataSource;

    public MyTunesManager() throws Exception
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

    public ArrayList<Song> getAll() throws SQLException
    {
        try (Connection con = dataSource.getConnection())
        {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(""
                    + "SELECT Song.*, Artist.Name'Artist', Category.Category'Category'"
                    + "FROM Song"
                    + "inner join Artist on Song.ArtistID = Artist.ID"
                    + "inner join Category on Song.CategoryID = Category.ID"
                    + "ORDER BY Song.Title");

            ArrayList<Song> songs = new ArrayList<>();
            while (rs.next())
            {
                int ID = rs.getInt("ID");
                String title = rs.getString("Title");
                String artist = rs.getString("Artist");
                String category = rs.getString("Category");
                int duration = rs.getInt("Duration");

                Song son = new Song(ID, title, artist, category, duration);
                songs.add(son);
            }
            return songs;
        }
    }

    public Song getByID(int id) throws SQLException
    {
        try (Connection con = dataSource.getConnection())
        {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(""
                    + "SELECT Song.*, Artist.Name'Artist', Category.Category'Category'"
                    + "FROM Song"
                    + "inner join Artist on Song.ArtistID = Artist.ID"
                    + "inner join Category on Song.CategoryID = Category.ID"
                    + "WHERE Song.ID = " + id
                    + "ORDER BY Song.Title");
            Song song;
            rs.next();
            int ID = rs.getInt("ID");
            String title = rs.getString("Title");
            String artist = rs.getString("Artist");
            String category = rs.getString("Category");
            int duration = rs.getInt("Duration");
            song = new Song(ID, title, artist, category, duration);
            return song;
        }
    }

    public ArrayList<Song> getByName(String name) throws SQLException
    {
        try (Connection con = dataSource.getConnection())
        {
            String sql = (""
                    + "SELECT Song.*, Artist.Name'Artist', Category.Category'Category'"
                    + "FROM Song"
                    + "inner join Artist on Song.ArtistID = Artist.ID"
                    + "inner join Category on Song.CategoryID = Category.ID"
                    + "WHERE Title = ? OR Artist = ?"
                    + "ORDER BY Song.Title");
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, name);
            ps.setString(2, name);
            ResultSet rs = ps.executeQuery();

            ArrayList<Song> songs = new ArrayList<>();
            while (rs.next())
            {
                int ID = rs.getInt("ID");
                String title = rs.getString("Title");
                String artist = rs.getString("Artist");
                String category = rs.getString("Category");
                int duration = rs.getInt("Duration");

                Song son = new Song(ID, title, artist, category, duration);
                songs.add(son);
            }
            return songs;
        }
    }

    public Song insert(Song s) throws SQLException
    {
        String sql = ""
                + "SELECT Artist.ID"
                + "into temp"
                + "WHERE Artist.Name = ?"
                + "SELECT category.ID"
                + "into temp2"
                + "where category.Name = ?"
                + "insert into Song"
                + "(?,temp.ID,temp2.ID,?,?)"
                + "drop temp,temp2";

        Connection con = dataSource.getConnection();
        PreparedStatement ps = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
        ps.setString(1, s.getArtist());
        ps.setString(2, s.getCategory());
        
        ps.setString(3, s.getTitle());
        ps.setString(4, s.getFilename());
        ps.setInt(5, s.getDuration());
        
        String sql1 = ""
                + "SELECT Category.ID"
                + "WHERE Category.Name = ?";
        Connection con1 dataSource.getConnection();
        PreparedStatement ps1 = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

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

    public void update(Song s) throws SQLException
    {
        String sql = "UPDATE Song SET Title = ?, Artist = ?, Category = ? , Duration = ? WHERE Id = ?";

        Connection con = dataSource.getConnection();

        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, s.getTitle());
        ps.setString(2, s.getArtist());
        ps.setString(3, s.getCategory());
        ps.setInt(4, s.getDuration());

        int affectedRows = ps.executeUpdate();
        if (affectedRows == 0)
        {
            throw new SQLException("Unable to update song");
        }
    }

    public void delete(int id) throws SQLException
    {
        String sql = "DELETE FROM Song WHERE Id = ?";

        Connection con = dataSource.getConnection();

        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, id);

        int affectedRows = ps.executeUpdate();
        if (affectedRows == 0)
        {
            throw new SQLException("Unable to delete song");
        }
    }
}
