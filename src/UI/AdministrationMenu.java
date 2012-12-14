package UI;

/**
 * MyTunes, EASV (14/12/2012)
 * @author Lars Vad Sørensen, Jakob Hansen, Klaus Teddy Bøgelund Andresen og Jesper Agerbo Hansen
 */

public class AdministrationMenu extends Menu
{
    /**
     * Creates the options for our Administration menu.
     */
    public AdministrationMenu()
    {
        super("Administration:",
              "Song",
              "Playlist");
    }

    /**
     * Overrides the doAction-method for more practical use in our case.
     * @param option 
     */
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
    
    /**
     * Creates a new instance of the "SongMenu"-menu, and goes there.
     */
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
    
    /**
     * Creates a new instance of the "PlaylistMenu"-menu, and goes there.
     */
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