/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

//import BE.Song;
import DAL.MyTunesManager;
import java.util.ArrayList;


/**
 *
 * @author bhp
 */
public class SongMenu extends Menu
{
    private MyTunesManager mgr;
    //private final Song song;

    public SongMenu()
    {
        super("Songs:",
                "List all songs",
                "Search",
                "Add song",
                "Update a song",
                "Remove a song",
                "Check");
        //this.song = s;

    }

    @Override
    protected void doAction(int option)
    {
        switch (option)
        {
            case 1:
                break;
            case 2:
                break;
            case 3:
                break;
            case 4:
                break;
            case 5:
                break;
            case 6:
                break;
        }
    }
    
    private void printSongHeader()
    {
        System.out.println();
        System.out.println(String.format("%3s %-30s %-30s %3s",
                "Title", "Artist", "Album", "Duration"));
    }
    
    private void showAllSongs()
    {
        clear();
        System.out.println("Show all songs:");
        System.out.println();

        try
        {
            ArrayList<Song> songs = mgr.getAll();

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
}