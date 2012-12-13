/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import BE.Song;
import BLL.MyTunesPlayer;

/**
 * MyTunes, EASV (14/12/2012)
 *
 * @author Lars Vad Sørensen, Jakob Hansen, Klaus Teddy Bøgelund Andresen og
 * Jesper Agerbo Hansen
 */
public class SongControlMenu extends Menu
{

    private final Song song;
    private MyTunesPlayer p;

    public SongControlMenu(Song s)
    {
        super("Controls:",
                "Pause",
                "Resume",
                "What's playing?");
        this.song = s;
        try
        {
            p = new MyTunesPlayer(song.getFilename());
            p.play();
        }
        catch (Exception ex)
        {
            System.out.println("ERROR - " + ex.getMessage());
        }
    }

    @Override
    protected void doAction(int option)
    {

        switch (option)
        {
            case 1:
                pauseSong();
                break;
            case 2:
                resumeSong();
                break;
            case 3:
                nowPlaying();
                break;
            case 0:
                stopSong();
                break;
        }
    }

    private void stopSong()
    {
        p.stop();
    }

    private void pauseSong()
    {
        p.pause();
    }

    private void resumeSong()
    {
        p.resume();
    }

    private void nowPlaying()
    {
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
