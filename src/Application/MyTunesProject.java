package Application;

import UI.MainMenu;

/**
 * MyTunes, EASV (14/12/2012)
 * @author Lars Vad Sørensen, Jakob Hansen, Klaus Teddy Bøgelund Andresen og Jesper Agerbo Hansen
 */

public class MyTunesProject
{

    /**
     * The main-module for starting the interface.
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        MainMenu employeeMenu = new MainMenu();
        employeeMenu.run();
    }
}