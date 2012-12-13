package UI;

import BE.Playlist;
import BE.Song;
import BLL.PlaylistManager;
import BLL.SongManager;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * MyTunes, EASV (14/12/2012)
 *
 * @author Lars Vad Sørensen, Jakob Hansen, Klaus Teddy Bøgelund Andresen og
 * Jesper Agerbo Hansen
 */
public class ControlMenu extends Menu
{

    private SongManager sMgr;       //Creates a SongManager
    private PlaylistManager pMgr;   //Creates a PlaylistManager

    /**
     * Gives the visual output, and creates a new instance of the BLL managers.
     */
    public ControlMenu()
    {
        super("Controls: ",
                "Play song",
                "Start playlist");
        try
        {
            sMgr = SongManager.getInstance();
            pMgr = PlaylistManager.getInstance();
        }
        catch (Exception ex)
        {
            System.out.println("ERROR - " + ex.getMessage());
            System.exit(2);
        }
    }

    /**
     * Tells what is to be executed when an option is selected.
     *
     * @param option The option selected in the menu
     */
    @Override
    protected void doAction(int option)
    {
        switch (option)
        {
            case 1:
                playSong();
                break;
            case 2:
                playPlaylist();
                break;
        }
    }

    /**
     * Takes a song ID, and gets a song from it.
     * Opens a menu with options for playing the song.
     */
    private void playSong()
    {
        try
        {
            System.out.print("Enter song ID: ");
            int id = new Scanner(System.in).nextInt();
            Song song = sMgr.getById(id);

            if (song != null)
            {
                new SongControlMenu(song).run();
            }
            else
            {
                System.out.println("ERROR - Song not found.");
            }
        }
        catch (InputMismatchException ie)
        {
            System.out.println("ERROR - ID must be number");
        }
        catch (Exception e)
        {
            System.out.println(" ERROR - " + e.getMessage());
        }
    }

    /**
     * Takes a playlist ID, and gets a playlist from it.
     * Opens a menu with options for playing the playlist.
     */
    private void playPlaylist()
    {
        try
        {
            System.out.print("Enter playlist ID: ");
            int id = new Scanner(System.in).nextInt();
            Playlist plist = pMgr.getByID(id);

            if (plist != null)
            {
                new PlaylistControlMenu(plist).run();
            }
            else
            {
                System.out.println("ERROR - Playlist not found.");
            }
        }
        catch (InputMismatchException ie)
        {
            System.out.println("ERROR - ID must be number");
        }
        catch (Exception e)
        {
            System.out.println(" ERROR - " + e.getMessage());
        }
    }
}