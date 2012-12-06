/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
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
 *
 * @author bhp
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

    public ArrayList<Playlist> getAll() throws SQLException {
        try (Connection con = dataSource.getConnection()) {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(""
                    + "SELECT Song.*, Artist.Name'Artist', Category.Category'Category'"
                    + "FROM Song"
                    + "inner join Artist on Song.ArtistID = Artist.ID"
                    + "inner join Category on Song.CategoryID = Category.ID"
                    + "ORDER BY Song.Title");

            ArrayList<Playlist> playlists = new ArrayList<>();
            while (rs.next()) {
                int ID = rs.getInt("ID");
                String name = rs.getString("Name");
                String created = rs.getString("Created");

                Playlist plst = new Playlist(ID, name, created);
                playlists.add(plst);
            }
            return playlists;
        }
    }

    public Playlist getByID(int id) throws SQLException {
        try (Connection con = dataSource.getConnection()) {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(""
                    + "SELECT Song.*, Artist.Name'Artist', Category.Category'Category'"
                    + "FROM Song"
                    + "inner join Artist on Song.ArtistID = Artist.ID"
                    + "inner join Category on Song.CategoryID = Category.ID"
                    + "WHERE Song.ID = " + id
                    + "ORDER BY Song.Title");
            Playlist plst;
            rs.next();
            int ID = rs.getInt("ID");
            String name = rs.getString("Name");
            String created = rs.getString("Created");
            plst = new Playlist(ID, name, created);
            return plst;
        }
    }

    public ArrayList<Playlist> getByName(String name) throws SQLException {
        try (Connection con = dataSource.getConnection()) {
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

            ArrayList<Playlist> playlists = new ArrayList<>();
            while (rs.next()) {
                int ID = rs.getInt("ID");
                String plstname = rs.getString("Name");
                String created = rs.getString("Created");

                Playlist plst = new Playlist(ID, plstname, created);
                playlists.add(plst);
            }
            return playlists;
        }
    }

    public Playlist insert(Playlist p) throws SQLException {
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
        ps.setString(1, p.getArtist());
        ps.setString(2, p.getCategory());

        ps.setString(3, p.getTitle());
        ps.setString(4, p.getFilename());
        ps.setInt(5, p.getDuration());

        String sql1 = ""
                + "SELECT Category.ID"
                + "WHERE Category.Name = ?";
        //Connection con1 dataSource.getConnection();
        PreparedStatement ps1 = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

        int affectedRows = ps.executeUpdate();
        if (affectedRows == 0) {
            throw new SQLException("Unable to insert playlist");
        }

        ResultSet keys = ps.getGeneratedKeys();
        keys.next();
        int id = keys.getInt(1);

        return new Playlist(id, p);
    }

    public void update(Playlist p) throws SQLException {
        String sql = "UPDATE Song SET Title = ?, Artist = ?, Category = ? , Duration = ? WHERE Id = ?";

        Connection con = dataSource.getConnection();

        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, p.getTitle());
        ps.setString(2, p.getArtist());
        ps.setString(3, p.getCategory());
        ps.setInt(4, p.getDuration());

        int affectedRows = ps.executeUpdate();
        if (affectedRows == 0) {
            throw new SQLException("Unable to update playlist");
        }
    }

    public void delete(int id) throws SQLException {
        String sql = "DELETE FROM Song WHERE Id = ?";

        Connection con = dataSource.getConnection();

        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, id);

        int affectedRows = ps.executeUpdate();
        if (affectedRows == 0) {
            throw new SQLException("Unable to delete playlist");
        }
    }
}
