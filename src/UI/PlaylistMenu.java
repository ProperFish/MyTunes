package UI;

import BE.Playlist;
import BE.Song;
import BLL.PlaylistManager;
import BLL.SongManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * MyTunes, EASV (14/12/2012)
 *
 * @author Lars Vad Sørensen, Jakob Hansen, Klaus Teddy Bøgelund Andresen og Jesper Agerbo Hansen.
 */
public class PlaylistMenu extends Menu
{
    //Instance fields.
    private PlaylistManager mgr;
    private SongManager sMgr;
    private final Playlist playlist;
    private Playlist p;

    /**
     * Constructs a PlaylistMenu with correct options.
     */
    public PlaylistMenu()
    {
        super("Playlist:",
                "Show all playlists",
                "Show all songs in a playlist",
                "Add a playlist",
                "Remove a playlist",
                "Reorder a playlist",
                "Add song to a playlist",
                "Remove song from a playlist",
                "Rename a playlist");
        this.playlist = p;
        try
        {
            mgr = PlaylistManager.getInstance();
            sMgr = SongManager.getInstance();
        }
        catch (Exception ex)
        {
            System.out.println("ERROR - " + ex.getMessage());
            System.exit(2);
        }

    }

    /**
     * Overrides the doAction method to accept the menu-options, and react accordingly.
     * @param option the option to be reacted to.
     */
    @Override
    protected void doAction(int option)
    {
        switch (option)
        {
            case 1:
                showAllPlaylists();
                break;
            case 2:
                showAllSongs();
                break;
            case 3:
                addPlaylist();
                break;
            case 4:
                removePlaylist();
                break;
            case 5:
                reorderPlaylist();
                break;
            case 6:
                addSong();
                break;
            case 7:
                removeSong();
                break;
            case 8:
                renamePlaylist();
                break;
        }
    }

    /**
     * Prints the uniform playlist-header to be used in other print-operations.
     */
    private void printPlaylistHeader()
    {
        System.out.println();
        System.out.println(String.format("%-3s %-15s %-10s",
                "ID", "NAME", "CREATED"));
    }

    /**
     * Shows all playlists in the database.
     */
    private void showAllPlaylists()
    {
        clear();
        System.out.println("Show All Playlists:");
        System.out.println();

        try
        {
            ArrayList<Playlist> playlists = mgr.getAll();

            printPlaylistHeader();
            for (Playlist p : playlists)
            {
                System.out.println(p);
            }
        }
        catch (Exception e)
        {
            System.out.println(" ERROR - " + e.getMessage());
        }
        pause();
    }
    
    /**
     * Shows all songs from a given playlist.
     */
    private void showAllSongs()
    {
        clear();
        System.out.print("Enter playlist ID: ");
        int id = new Scanner(System.in, "iso-8859-1").nextInt();

        try
        {
            ArrayList<Song> results = mgr.getAllSongs(id);

            printSongHeader();
            for (Song s : results)
            {
                System.out.println(s);
            }
        }
        catch (Exception e)
        {
            System.out.println(" ERROR - " + e.getMessage());
        }
        pause();
    }

    /**
     * Adds a playlist to the database, prompting the user for the neccesary input.
     */
    private void addPlaylist()
    {
        clear();
        System.out.println("Add Playlist:");
        System.out.println();

        try
        {
            Scanner sc = new Scanner(System.in, "iso-8859-1");

            System.out.print("Name: ");
            String name = sc.nextLine();

            Playlist playlist = new Playlist(name);

            playlist = mgr.addPlaylist(playlist);

            System.out.println();
            System.out.println("Playlist added!");
        }
        catch (Exception e)
        {
            System.out.println("ERROR - " + e.getMessage());
        }
        pause();
    }

    /**
     * Removes a playlist from the database from a user-input ID.
     */
    private void removePlaylist()
    {
        clear();
        System.out.println("Delete Playlist:");
        System.out.println("");
        try
        {
            System.out.print("Select playlist id: ");
            int id = new Scanner(System.in).nextInt();

            mgr.deletePlaylist(id);
        }
        catch (InputMismatchException ie)
        {
            System.out.println("ERROR - ID must be a number");
        }
        catch (Exception e)
        {
            System.out.println(" ERROR - " + e.getMessage());
            pause();
        }
    }

    /**
     * TBI
     */
    private void reorderPlaylist()
    {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    /**
     *  Adds a song to a database. Both ID's must be known beforehand, as they are prompted for.
     */
    private void addSong()
    {
        clear();
        Playlist plst;
        Song song;
        try
        {
            System.out.print("Enter playlist id: ");
            int id = new Scanner(System.in).nextInt();
            plst = mgr.getByID(id);
            System.out.print("Enter song id: ");
            int sid = new Scanner(System.in).nextInt();
            song = sMgr.getById(sid);
            mgr.addSong(plst, song);
            System.out.println();
            System.out.println("Song added!");
        }
        catch (InputMismatchException ie)
        {
            System.out.println("ERROR - ID must be a number");
        }
        catch (Exception e)
        {
            System.out.println(" ERROR - " + e.getMessage());
        }
        pause();
    }

    /**
     *  Removes a song from a given playlist. Both ID's must be known beforehand.
     */
    private void removeSong()
    {
        clear();

        Playlist pl = null;
        Song song = null;
        System.out.print("Enter playlist id: ");
        int id = new Scanner(System.in).nextInt();
        System.out.print("Enter song id: ");
        int sid = new Scanner(System.in).nextInt();
        try
        {
            pl = mgr.getByID(id);
            song = sMgr.getById(sid);
        }
        catch (InputMismatchException ie)
        {
            System.out.println("ERROR - ID must be a number");
        }
        catch (Exception e)
        {
            System.out.println(" ERROR - " + e.getMessage());
        }
        try
        {

            mgr.removeSong(pl, song);
        }
        catch (SQLException ex)
        {
            System.out.println(" ERROR - " + ex.getMessage());
        }

        System.out.println();
        System.out.println("Song removed!");


        pause();
    }
    
    /**
     * Renames a playlist with a prompted ID by updating the local object, and then uploading it.
     */
    private void renamePlaylist()
    {
        clear();
        Playlist plst;        
        try
        {
            System.out.print("Enter playlist id: ");
            int id = new Scanner(System.in).nextInt();
            System.out.print("Enter new name: ");
            String name = new Scanner(System.in).nextLine();
            
            plst= new Playlist(id,name);
            mgr.updatePlaylist(plst);
            System.out.println();
            System.out.println("playlist renamed!");
        }
        catch (InputMismatchException ie)
        {
            System.out.println("ERROR - ID must be a number");
        }
        catch (Exception e)
        {
            System.out.println(" ERROR - " + e.getMessage());
        }
        pause();
    }
}