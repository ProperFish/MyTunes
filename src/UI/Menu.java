package UI;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * MyTunes, EASV (14/12/2012)
 * @author Lars Vad Sørensen, Jakob Hansen, Klaus Teddy Bøgelund Andresen og Jesper Agerbo Hansen
 */

public abstract class Menu
{
    //Instance fields.
    protected int EXIT_OPTION = 0;
    private final String header;
    private final String[] menuItems;

    /**
     * Creates a new menu with a header-string and the items in the menu.
     * @param header the header-string to be appended to the menu.
     * @param menuItems the items to be added to the menu.
     */
    public Menu(String header, String... menuItems)
    {
        this.header = header;
        this.menuItems = menuItems;
    }

    /**
     * The run-method responsible for showing menus, and accepting option-choices.
     */
    public void run()
    {
        boolean done = false;
        while (!done)
        {
            showMenu();
            int option = getOption();
            doAction(option);
            if (option == EXIT_OPTION)
            {
                done = true;
            }
        }
    }

    /**
     * The showMenu method is responsible for printing the menu and its options.
     */
    private void showMenu()
    {
        clear();
        System.out.println();
        System.out.println(header.toUpperCase());
        System.out.println();
        
        headerSection();

        for (int i = 0; i < menuItems.length; i++)
        {
            System.out.println(
                    String.format("%2d)  %s", (i + 1), menuItems[i]));
        }
        System.out.println(
                String.format("%2d)  %s", EXIT_OPTION, "Exit"));
    }

    /**
     * Accepts an option from the user.
     * @return the option the user input.
     */
    private int getOption()
    {
        while (true)
        {
            try
            {
                System.out.print("\nEnter option: ");
                int option = new Scanner(System.in).nextInt();
                if (option >= 1 && option <= menuItems.length
                        || option == EXIT_OPTION)
                {
                    return option;
                }
                else
                {
                    System.out.println("\nERROR - Invalid option.");
                }
            }
            catch (InputMismatchException e)
            {
                System.out.println("ERROR - Not a number.");
            }
        }
    }
    
    /**
     * "Clears" the screen by printing 50 blank lines to it.
     */
    protected void clear()
    {
        for (int i = 0; i < 50; i++)
        {
            System.out.println();
        }
    }
    
    /**
     * "Pauses" the program, asking the user to press enter to continue execution.
     */
    protected void pause()
    {
        System.out.println("\nPress ENTER to continue...");
        new Scanner(System.in).nextLine();
    }
    
        /**
     * Prints the song-header to be used in other print-operations.
     */
    protected void printSongHeader()
    {
        System.out.println();
        System.out.println(String.format("%-40s %15s %10s %-10s %-3s",
                "TITLE", "ARTIST", "CATEGORY", "DURATION", "ID"));
    }
    
    protected void headerSection()
    {
        // Do nothing
    }
    
    abstract protected void doAction(int option);
}
