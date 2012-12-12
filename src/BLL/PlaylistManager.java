package BLL;

import BE.Playlist;
import BE.Song;
import DAL.PlaylistAccess;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * MyTunes, EASV (14/12/2012)
 * @author Lars Vad Sørensen, Jakob Hansen, Klaus Teddy Bøgelund Andresen og Jesper Agerbo Hansen
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

    public ArrayList<Song> getAllSongs(int id) throws SQLException
    {
        return db.getAllSongs(id);
    }
    
    public ArrayList<Playlist> getAll() throws SQLException
    {
        return db.getAll();
    }

    public Playlist getByID(int playlistID) throws SQLException
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
    
    public void addSong(Playlist plst, Song song) throws SQLException
    {
        db.addSong(plst, song);
    }
    
    public void removeSong(Playlist plst, Song song) throws SQLException
    {
        db.removeSong(plst, song);
    }
}