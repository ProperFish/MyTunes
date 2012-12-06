package BLL;

import BE.Song;
import DAL.SongAccess;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author drengene
 */
public class SongManager
{

    private static SongManager instance = null;
    private SongAccess db = null;

    private SongManager() throws Exception
    {
        db = new SongAccess();
    }

    public static SongManager getInstance() throws Exception
    {
        if (instance == null)
        {
            instance = new SongManager();
        }
        return instance;
    }

    public ArrayList<Song> getAll() throws SQLException
    {
        return db.getAll();
    }

    public Song getById(int songID) throws SQLException
    {
        return db.getByID(songID);
    }

    public ArrayList<Song> getByName(String name) throws SQLException
    {
        return db.getByName(name);
    }

    public Song addSong(Song s) throws SQLException
    {
        return db.insert(s);
    }

    public void updateSong(Song s) throws SQLException
    {
        db.update(s);
    }

    public void deleteSong(int id) throws SQLException
    {
        db.delete(id);
    }
}