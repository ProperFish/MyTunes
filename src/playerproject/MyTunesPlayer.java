/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package playerproject;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;


/**
 *
 * @author bhp
 */
public class MyTunesPlayer
{
    private String soundFile;
    private Player player;
    private Thread playerThread = null;
    private boolean isPlaying;

    public MyTunesPlayer(String soundFile) throws Exception
    {
        this.soundFile = soundFile;
        isPlaying = false;
    }

    public void play()
    {
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

    public void pause()
    {
        if (isPlaying)
        {
            playerThread.suspend();
            isPlaying = false;
        }
    }

    public void resume()
    {
        if (!isPlaying)
        {
            playerThread.resume();
            isPlaying = true;
        }
    }
}
