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
    //Instance fields.
    private static PlaylistManager instance = null;
    private PlaylistAccess db = null;
    
    /**
     * A constructor for a PlaylistAccess-object.
     * @throws Exception 
     */
    private PlaylistManager() throws Exception
    {
        db = new PlaylistAccess();
    }

    /**
     * A getter-class for returning the instance of a PlaylistManager object.
     * @return the instance of a new PlaylistManager object.
     * @throws Exception 
     */
    public static PlaylistManager getInstance() throws Exception
    {
        if (instance == null)
        {
            instance = new PlaylistManager();
        }
        return instance;
    }

    /**
     * Gets all songs from a given playlist.
     * @param id the id of the playlist to be fetched.
     * @return an arraylist containing all the songs from a playlist.
     * @throws SQLException 
     */
    public ArrayList<Song> getAllSongs(int id) throws SQLException
    {
        return db.getAllSongs(id);
    }
    
    /**
     * Gets all playlists from the SQL-Server.
     * @return an arraylist containing all playlists.
     * @throws SQLException 
     */
    public ArrayList<Playlist> getAll() throws SQLException
    {
        return db.getAll();
    }

    /**
     * Gets a playlist by fetching the corresponding ID.
     * @param playlistID the id of the playlist to be fetched.
     * @return a playlist-object containing the details from the server.
     * @throws SQLException 
     */
    public Playlist getByID(int playlistID) throws SQLException
    {
        return db.getByID(playlistID);
    }

    /**
     * Gets a playlist by name from the server.
     * @param name the name to be searched for.
     * @return a playlist object that matches the given name on the server.
     * @throws SQLException 
     */
    public ArrayList<Playlist> getByName(String name) throws SQLException
    {
        return db.getByName(name);
    }

    /**
     * Adds a playlist to the server.
     * @param p the playlist object to be uploaded.
     * @throws SQLException 
     */
    public void addPlaylist(Playlist p) throws SQLException
    {
        db.insert(p);
    }

    /**
     * Updates a playlist on the server.
     * @param p the local playlist-object to be uploaded to the server.
     * @throws SQLException 
     */
    public void updatePlaylist(Playlist p) throws SQLException
    {
        db.update(p);
    }

    /**
     * Deletes a playlist on the server by ID.
     * @param id the ID to be deleted.
     * @throws SQLException 
     */
    public void deletePlaylist(int id) throws SQLException
    {
        db.delete(id);
    }
    
    /**
     * Adds a song to a given playlist.
     * @param plst the playlist object for the song to be added to.
     * @param song the song in question.
     * @throws SQLException 
     */
    public void addSong(Playlist plst, Song song) throws SQLException
    {
        db.addSong(plst, song);
    }
    
    /**
     * Removes a song from a playlist.
     * @param plst the playlist object for the song to be removed from.
     * @param song the song in question.
     * @throws SQLException 
     */
    public void removeSong(Playlist plst, Song song) throws SQLException
    {
        db.removeSong(plst, song);
    }
}