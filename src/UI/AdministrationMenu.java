package UI;

/**
 *
 * @author drengene
 */
public class AdministrationMenu extends Menu
{
    public AdministrationMenu()
    {
        super("Administration:",
              "Song",
              "Playlist");
    }

    @Override
    protected void doAction(int option)
    {
        switch (option)
        {
            case 1:
                songMenu();
                break;
            case 2:
                playlistMenu();
                break;
        }
    }
    private void songMenu()
    {
        clear();
        try
        {
            new SongMenu().run();
        }
        catch (Exception e)
        {
            System.out.println(" ERROR - " + e.getMessage());
        }
    }
    
    private void playlistMenu()
    {
        clear();
        try
        {
            new PlaylistMenu().run();
        }
        catch (Exception e)
        {
            System.out.println(" ERROR - " + e.getMessage());
        }
    }
}