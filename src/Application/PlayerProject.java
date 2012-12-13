/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Application;

import BLL.MyTunesPlayer;
import java.util.Scanner;

/**
 *
 * @author bhp
 */
public class PlayerProject
{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception
    {
        MyTunesPlayer p = new MyTunesPlayer("01 - Thunderstruck.mp3");
        p.play();
        
        System.out.print("Press ENTER to pause...");
        new Scanner(System.in).nextLine();
        
        p.pause();
        
        System.out.print("Press ENTER to resume...");
        new Scanner(System.in).nextLine();
        
        p.resume();
        
        System.out.print("Press ENTER to start over...");
        new Scanner(System.in).nextLine();
        
        p.play();
        
        System.out.print("Press ENTER to stop...");
        new Scanner(System.in).nextLine();
        
        p.stop();
    }
}
