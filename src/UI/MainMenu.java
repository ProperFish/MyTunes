package UI;
/**
 *
 * @author drengene
 */
public class MainMenu extends Menu
{
    public MainMenu()
    {
        super("Welcome to MyTunes!",
                "Administration",
                "Control");
        }
    @Override
    protected void doAction(int option)
    {
        switch (option)
        {
            case 1:
                administrationMenu();                        
                break;
            case 2:
                controlMenu();
                break;
        }
    }
    private void controlMenu()
    {
        clear();
        try
        {
        new ControlMenu().run();
        }
        catch (Exception e)
        {
            System.out.println(" ERROR - " + e.getMessage());
        }
    }
    
    private void administrationMenu()
    {
        clear();
        try
        {
        new AdministrationMenu().run();
        }
        catch (Exception e)
        {
            System.out.println(" ERROR - " + e.getMessage());
        }
    }
}