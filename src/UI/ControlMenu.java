/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

//import BE.Song;

/**
 *
 * @author bhp
 */
public class ControlMenu extends Menu
{

    //private final Song song;

    public ControlMenu()
    {
        super("Controls:",
                "Play song",
                "Start playlist",
                "Stop",
                "Pause",
                "Resume",
                "What's playing?");
       //this.song = s;

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