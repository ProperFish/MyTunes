package BLL;

import BE.Playlist;
import DAL.PlaylistAccess;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author drengene
 */
public class PlaylistManager
{

    private static PlaylistManager instance = null;
    private PlaylistAccess db = null;

    private PlaylistManager() throws Exception
    {
        db = new PlaylistAccess();
    }

    public static PlaylistManager getInstance() throws Exception
    {
        if (instance == null)
        {
            instance = new PlaylistManager();
        }
        return instance;
    }

    public ArrayList<Playlist> getAll() throws SQLException
    {
        return db.getAll();
    }

    public Playlist getById(int playlistID) throws SQLException
    {
        return db.getByID(playlistID);
    }

    public ArrayList<Playlist> getByName(String name) throws SQLException
    {
        return db.getByName(name);
    }

    public Playlist addPlaylist(Playlist p) throws SQLException
    {
        return db.insert(p);
    }

    public void updatePlaylist(Playlist p) throws SQLException
    {
        db.update(p);
    }

    public void deletePlaylist(int id) throws SQLException
    {
        db.delete(id);
    }
}