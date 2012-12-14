package UI;

/**
 * MyTunes, EASV (14/12/2012)
 *
 * @author Lars Vad Sørensen, Jakob Hansen, Klaus Teddy Bøgelund Andresen og Jesper Agerbo Hansen.
 */

public class MainMenu extends Menu {

    /**
     * Creates the options for our Main menu.
     */
    public MainMenu() {
        super("Welcome to MyTunes!",
                "Administration",
                "Control");
    }

    /**
     * Overrides the doAction method for more practical use in our case.
     *
     * @param option the option to be performed.
     */
    @Override
    protected void doAction(int option) {
        switch (option) {
            case 1:
                administrationMenu();
                break;
            case 2:
                controlMenu();
                break;
        }
    }

    /**
     * Creates a new controlMenu, and goes there.
     */
    private void controlMenu() {
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

    /**
     * Creates a new administrationMenu, and goes there.
     */
    private void administrationMenu() {
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