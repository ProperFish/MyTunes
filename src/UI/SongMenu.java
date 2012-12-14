package UI;

import BE.Song;
import BLL.SongManager;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
//import java.util.InputMismatchException;

/**
 * MyTunes, EASV (14/12/2012)
 *
 * @author Lars Vad Sørensen, Jakob Hansen, Klaus Teddy Bøgelund Andresen og Jesper Agerbo Hansen.
 */
public class SongMenu extends Menu
{
    //Instance fields.
    private SongManager mgr;
    
    /**
     * Gives the visual output.
     */
    public SongMenu()
    {
        super("Songs:",
                "List all songs",
                "Search",
                "Add song",
                "Update a song",
                "Remove a song",
                "Check");
        try
        {
            mgr = SongManager.getInstance();
        }
        catch (Exception ex)
        {
            System.out.println("ERROR - " + ex.getMessage());
            System.exit(2);
        }
    }

    /**
     * Tells what is to be executed when an option is selected.
     *
     * @param option The option selected in the menu
     */
    @Override
    protected void doAction(int option)
    {
        switch (option)
        {
            case 1:
                listAll();
                break;
            case 2:
                search();
                break;
            case 3:
                addSong();
                break;
            case 4:
                updateSong();
                break;
            case 5:
                removeSong();
                break;
            case 6:
                check();
                break;
        }
    }

    /**
     * Prints the song-header to be used in other print-operations.
     */
    private void printSongHeader()
    {
        System.out.println();
        System.out.println(String.format("%-40s %15s %10s %-10s %-3s",
                "TITLE", "ARTIST", "CATEGORY", "DURATION", "ID"));
    }

    /**
     * Lists every song on the database.
     */
    private void listAll()
    {
        clear();
        System.out.println("Show All Songs:");
        System.out.println();

        try
        {
            ArrayList<Song> songs = mgr.getAll();

            printSongHeader();
            for (Song e : songs)
            {
                System.out.println(e);
            }
        }
        catch (Exception e)
        {
            System.out.println(" ERROR - " + e.getMessage());
        }
        pause();
    }

    /**
     * Searches for a song by both artist and title on the database.
     */
    private void search()
    {
        clear();
        System.out.println("Show Songs by Artist or Title:");
        System.out.println();

        System.out.print("Enter search: ");
        String name = new Scanner(System.in, "iso-8859-1").nextLine();

        try
        {
            ArrayList<Song> songs = mgr.getByName(name);

            System.out.println("Matching songs:");
            printSongHeader();
            for (Song e : songs)
            {
                System.out.println(e);
            }
        }
        catch (Exception e)
        {
            System.out.println("ERROR - " + e.getMessage());
        }
        pause();
    }

    /**
     * Adds a song to the database for further use in the program.
     */
    private void addSong()
    {
        clear();
        System.out.println("Add Song:");
        System.out.println();

        try
        {
            Scanner sc = new Scanner(System.in, "iso-8859-1");

            System.out.print("Title: ");
            String title = sc.nextLine();

            System.out.print("Artist: ");
            String artist = sc.nextLine();
            
            System.out.print("Duration: ");
            int duration = sc.nextInt();
            sc.nextLine();

            System.out.print("Filename: ");
            String filename = sc.nextLine();

            System.out.print("Category: ");
            String category = sc.nextLine();

            Song song = new Song(title, artist, duration, filename, category);

            mgr.addSong(song);

            System.out.println();
            System.out.println("Song added!");
        }
        catch (Exception e)
        {
            e.printStackTrace();
//            System.out.println("ERROR - " + e.getMessage());
        }
        pause();
    }

    /**
     * Updates a song-entry in the database by prompting the user for changes.
     */
    private void updateSong()
    {
        clear();
        System.out.println("Update Song:");
        System.out.println("");
        try
        {
            System.out.print("Select song id: ");
            int id = new Scanner(System.in).nextInt();
            Song song = mgr.getById(id);

            if (song != null)
            {
                new SongUpdateMenu(song).run();
            }
            else
            {
                System.out.println("ERROR - Song not found.");
            }
        }
        catch (InputMismatchException ie)
        {
            System.out.println("ERROR - ID must be number");
        }
        catch (Exception e)
        {
            System.out.println(" ERROR - " + e.getMessage());
        }
    }

    /**
     * Removes a song from the database by ID.
     */
    private void removeSong()
    {
        clear();
        System.out.println("Delete Song:");
        System.out.println("");
        try
        {
            System.out.print("Select song ID: ");
            int ID = new Scanner(System.in).nextInt();

            mgr.deleteSong(ID);
        }
        catch (InputMismatchException ie)
        {
            System.out.println("ERROR - ID must be number");
        }
        catch (Exception e)
        {
            System.out.println(" ERROR - " + e.getMessage());
            pause();
        }
    }

    /**
     * TBI
     */
    private void check()
    {
        // needs to be discussed
        throw new UnsupportedOperationException("Not yet implemented");
    }
}