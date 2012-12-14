package BLL;

import BE.Song;
import DAL.SongAccess;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * MyTunes, EASV (14/12/2012)
 * @author Lars Vad Sørensen, Jakob Hansen, Klaus Teddy Bøgelund Andresen og Jesper Agerbo Hansen
 */

public class SongManager
{
    //Instance Fields.
    private static SongManager instance = null;
    private SongAccess db = null;
    
    /**
     * A constructor for a SongAcess-object.
     * @throws Exception 
     */
    private SongManager() throws Exception
    {
        db = new SongAccess();
    }
    
    /**
     * A getter-class for returning the instance of a SongManager object.
     *
     * @return the instance of a new PlaylistManager object.
     * @throws Exception
     */
    public static SongManager getInstance() throws Exception
    {
        if (instance == null)
        {
            instance = new SongManager();
        }
        return instance;
    }

    /**
     * Gets all songs from the database.
     * @return an arraylist containing all the songs.
     * @throws SQLException 
     */
    public ArrayList<Song> getAll() throws SQLException
    {
        return db.getAll();
    }

    /**
     * Gets a song from the database by ID.
     * @param songID the ID to be fetched.
     * @return the song-object that was fetched.
     * @throws SQLException 
     */
    public Song getById(int songID) throws SQLException
    {
        return db.getByID(songID);
    }

    /**
     * Gets a song by name from the server.
     * @param name the name to be searched for.
     * @return the new object from the database.
     * @throws SQLException 
     */
    public ArrayList<Song> getByName(String name) throws SQLException
    {
        return db.getByName(name);
    }

    /**
     * Adds a song to the database.
     * @param s the song-object to be added.
     * @throws SQLException 
     */
    public void addSong(Song s) throws SQLException
    {
        db.insert(s);
    }

    /**
     * Updates a song in the database.
     * @param s the new song-object.
     * @throws SQLException 
     */
    public void updateSong(Song s) throws SQLException
    {
        db.update(s);
    }

    /**
     * Deletes a song from the database by ID.
     * @param id the ID to be deleted.
     * @throws SQLException 
     */
    public void deleteSong(int id) throws SQLException
    {
        db.delete(id);
    }
}