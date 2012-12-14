package BLL;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

/**
 * Code by Bent H Pedersen.
 *
 * @author bhp
 * @author revision by Lars Vad Sørensen, Jakob Hansen, Klaus Teddy Bøgelund Andresen og Jesper Agerbo Hansen.
 */
public class MyTunesPlayer
{
    //Instance fields.
    private String soundFile;
    private Player player;
    private Thread playerThread = null;
    private boolean isPlaying;

    public MyTunesPlayer(String soundFile) throws Exception
    {
        this.soundFile = soundFile;
        isPlaying = false;
    }

    /**
     *  Starts playback by creating a new player-object, and starts the thread.
     */
    public void play()
    {
        resume(); //Put here to restart the thread in order to prevent a deadlock.
        stop();
        playerThread = new Thread()
        {
            @Override
            public void run()
            {
                try
                {
                    player = new Player(new FileInputStream(soundFile));
                    player.play();
                    isPlaying = false;
                }
                catch (FileNotFoundException | JavaLayerException ex)
                {
                    throw new RuntimeException(ex.getMessage());
                }
            }
        };
        playerThread.start();
        isPlaying = true;
    }

   /**
    * Stops the current playback-thread by closing the player-object.
    */
    public void stop()
    {
        if (playerThread != null)
        {
            if (!isPlaying)
            {
                playerThread.interrupt();
            }
            player.close();
            isPlaying = false;
        }
    }

    /**
     * Pauses the song that is currently playing by suspending the thread.
     */
    public void pause()
    {
        if (isPlaying)
        {
            playerThread.suspend();
            isPlaying = false;
        }
    }

    /**
     * Edited to reflect its possible use before the start of a thread.
     * Resumes the song that is currently loaded.
     */
    public void resume()
    {
        if (playerThread != null)
        {
            if (!isPlaying)
            {
                playerThread.resume();
                isPlaying = true;
            }
        }
    }
}
