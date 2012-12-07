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
 * @author Lars Vad Sørensen, Jakob Hansen, Klaus Teddy Bøgelund Andresen og
 * Jesper Agerbo Hansen
 */
public class SongMenu extends Menu
{

    private SongManager mgr;

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

    private void printSongHeader()
    {
        System.out.println();
        System.out.println(String.format("%3d %30s %-30s %-30s %30s",
                "ID", "ARTIST", "TITLE", "FILENAME", "CATEGORY"));
    }

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

    private void search()
    {
        clear();
        System.out.println("Show Songs by Artist:");
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

            System.out.print("Filename: ");
            String filename = sc.nextLine();

            System.out.print("Category: ");
            String category = sc.nextLine();

            Song songs = new Song(title, artist, filename, category);

            songs = mgr.addSong(songs);

            System.out.println();
            System.out.println("Song added!");
        }
        catch (Exception e)
        {
            System.out.println("ERROR - " + e.getMessage());
        }
        pause();
    }

    private void updateSong()
    {
        clear();
        System.out.println("Update Song:");
        System.out.println("");
        try
        {
            System.out.print("Select song title: ");
            int id = new Scanner(System.in).nextInt();
            Song songs = mgr.getById(id);

            if (songs != null)
            {
                new SongUpdateMenu(songs).run();
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

    // needs to be discussed
    private void check()
    {
        throw new UnsupportedOperationException("Not yet implemented");
    }
}