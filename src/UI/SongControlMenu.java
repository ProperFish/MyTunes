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
                "Play",
                "Stop",
                "Pause",
                "Resume",
                "What's playing?");
        this.song = s;
        try
        {
            p = new MyTunesPlayer(song.getFilename());
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
                playSong();
                break;
            case 2:
                stopSong();
            case 3:
                pauseSong();
                break;
            case 4:
                resumeSong();
                break;
            case 5:
                nowPlaying();
                break;
            case 0:
                resumeSong();
                stopSong();
                break;
        }
    }

    private void playSong()
    {
        p.play();
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
        printSongHeader();
        System.out.println(song);
        System.out.println();
    }

    private void printSongHeader()
    {
        System.out.println();
        System.out.println(String.format("%-40s %15s %10s %-10s %-3s",
                "TITLE", "ARTIST", "CATEGORY", "DURATION", "ID"));
    }
}
