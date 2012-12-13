package UI;

import BE.Song;
import BLL.MyTunesPlayer;
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

    private SongManager sMgr;
    //Initialisér eventuelt et 'Song' objekt?

    public ControlMenu()
    {
        super("Controls:",
                "Play song",
                "Start playlist",
                "Stop",
                "Pause",
                "Resume",
                "What's playing?");
        try
        {
            sMgr = SongManager.getInstance();
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
                playSong();
                break;
            case 2:
                playPlaylist();
                break;
            case 3:
                stopPlayer();
                break;
            case 4:
                pausePlayer();
                break;
            case 5:
                resumePlayer();
                break;
            case 6:
                nowPlaying();
                break;
        }
    }

    private void playSong()
    {
        try
        {
            System.out.print("Enter song ID: ");
            int id = new Scanner(System.in).nextInt();
            Song song = sMgr.getById(id);

            if (song != null)
            {
                MyTunesPlayer p = new MyTunesPlayer(song.getFilename());
                p.play();
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

    private void playPlaylist()
    {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    private void stopPlayer()
    {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    private void pausePlayer()
    {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    private void resumePlayer()
    {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    private void nowPlaying()
    {
        throw new UnsupportedOperationException("Not yet implemented");
    }
}