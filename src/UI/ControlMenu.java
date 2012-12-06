package UI;

/**
 * MyTunes, EASV (14/12/2012)
 * @author Lars Vad Sørensen, Jakob Hansen, Klaus Teddy Bøgelund Andresen og Jesper Agerbo Hansen
 */

public class ControlMenu extends Menu
{

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
        }
    }
}