package UI;

import BE.Playlist;

/**
 *
 * @author drengene
 */
public class PlaylistMenu extends Menu
{

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
            case 7:
                break;
        }
    }
}
