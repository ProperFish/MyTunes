package UI;

//import BE.Playlist;

import BE.Song;
import BLL.PlaylistManager;
import java.util.ArrayList;
import java.util.Scanner;


/**
 *
 * @author drengene
 */
public class PlaylistMenu extends Menu
{

    private PlaylistManager mgr;

    private final Playlist playlist;

    public PlaylistMenu()
    {
        super("Playlist:",
                "Show all playlists",
                "Show all songs in a playlist",
                "Add a playlist",
                "Remove a playlist",
                "Reorder a playlist",
                "Add song to a playlist",
                "Remove song from a playlist");
        this.playlist = p;
        try
        {
            mgr = PlaylistManager.getInstance();
        }
        catch (Exception ex)
        {
            System.out.println("ERROR - " + ex.getMessage());
            System.exit(2);
        }

    }

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
        }
    }

    private void printSongHeader()
    {
        System.out.println();
        System.out.println(String.format("%30s %-30s %-30s %30s",
                "ARTIST", "TITLE", "FILENAME", "CATEGORY"));
    }
    
    private void showAllPlaylists()
    {
        clear();
        System.out.println("Show All Playlists:");
        System.out.println();

        try
        {
            ArrayList<Playlist> playlists = mgr.getAll();

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

    private void showAllSongs()
    {
        clear();
        System.out.println("Enter playlist name");
        String name = new Scanner(System.in, "iso-8859-1").nextLine();
        
        try
        {
            ArrayList<Song> songs = mgr.getSongs(name);

            printSongHeader();
            for (Song s : songs)
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

            Playlist playlists = new Playlist(name);

            playlists = mgr.addPlaylist(playlists);

            System.out.println();
            System.out.println("Playlist added!");
        }
        catch (Exception e)
        {
            System.out.println("ERROR - " + e.getMessage());
        }
        pause();
    }

    private void removePlaylist()
    {
        clear();
        System.out.println("Delete Playlist:");
        System.out.println("");
        try
        {
            System.out.print("Select playlist name: ");
            String name = new Scanner(System.in, "iso-8859-1").nextLine();

            mgr.deletePlaylist(name);
        }
        catch (Exception e)
        {
            System.out.println(" ERROR - " + e.getMessage());
            pause();
        }
    }

    private void reorderPlaylist()
    {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    private void addSong()
    {
        clear();
        System.out.println("Enter playlist id");
        int id = new Scanner(System.in).nextInt();
        
        try
        {
            System.out.print("Id: ");
            int sid = new Scanner(System.in).nextInt();
            
            Song songs = getSong(sid);

            mgr.addSong(id, songs);

            System.out.println();
            System.out.println("Song added!");
        }
        catch (Exception e)
        {
            System.out.println(" ERROR - " + e.getMessage());
        }
        pause();
    }

    private void removeSong()
    {
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
