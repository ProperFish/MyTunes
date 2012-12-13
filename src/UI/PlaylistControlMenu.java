/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import BE.Playlist;

/**
 *
 * @author LarsVad
 */
public class PlaylistControlMenu extends Menu
{

    private final Playlist plist;

    public PlaylistControlMenu(Playlist pl)
    {
        super("Controls:",
                "Pause",
                "Resume",
                "What's playing?");
        this.plist = pl;
    }

    @Override
    protected void doAction(int option)
    {
         switch (option)
        {
            case 1:
                pausePlaylist();
                break;
            case 2:
                resumePlaylist();
                break;
            case 3:
                nowPlaying();
                break;
            case 0:
                stopPlaylist();
                break;
        }
    }

    private void pausePlaylist()
    {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    private void resumePlaylist()
    {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    private void nowPlaying()
    {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    private void stopPlaylist()
    {
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
