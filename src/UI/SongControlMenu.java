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

    /**
     * Gives the visual output.
     * Gets the filename for the song to use for MyTunesPlayer,
     * and initialises the MyTunesPlayer.
     * 
     * @param s Song to be used in the class
     */
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
            case 0:             // When exiting the menu extra actions are needed...
                                // to ensure stable running.
                resumeSong();   // Resumes the song if song is paused, 
                                // as the suspended thread deadlocks the program.
                stopSong();     // Stops the song, as it cannot be stopped...
                                // after the menu has been closed.
                break;
        }
    }

    /**
     * Plays the song.
     */
    private void playSong()
    {
        p.play();
    }

    /**
     * Stops the song.
     */
    private void stopSong()
    {
        p.stop();
    }

    /**
     * Pauses the song.
     */
    private void pauseSong()
    {
        p.pause();
    }

    /**
     * Resumes the song after it has been paused.
     */
    private void resumeSong()
    {
        p.resume();
    }

    /**
     * Shows which song is currently playing.
     */
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
