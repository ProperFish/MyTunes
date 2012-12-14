package UI;

import BE.Song;
import BLL.SongManager;
import java.util.Scanner;

/**
 * MyTunes, EASV (14/12/2012)
 *
 * @author Lars Vad Sørensen, Jakob Hansen, Klaus Teddy Bøgelund Andresen og Jesper Agerbo Hansen.
 */
public class SongUpdateMenu extends Menu
{

    private final Song song;
    /**
 * The visual output.
 * @param s the song-object to be used for modification. 
 */
    public SongUpdateMenu(Song s)
    {
        super("Update song:",
                "Edit title",
                "Edit artist",
                "Edit category");
        this.song = s;
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
                editTitle();
                break;
            case 2:
                editArtist();
                break;
            case 3:
                editCategory();
                break;
            case 0:
                saveUpdate();
                break;
        }
    }

    /**
     * Edits the title of a song on the server.
     */
    private void editTitle()
    {
        System.out.println();
        System.out.print("New Title:");
        String title = new Scanner(System.in, "iso-8859-1").nextLine();
        song.setTitle(title);
    }

    /**
     * Edits the artist of a song on the server.
     */
    private void editArtist()
    {
        System.out.println();
        System.out.print("New Artist:");
        String artist = new Scanner(System.in, "iso-8859-1").nextLine();
        song.setArtist(artist);
    }

    /**
     * Edits the category of a song on the server.
     */
    private void editCategory()
    {
        System.out.println();
        System.out.print("New Category:");
        String category = new Scanner(System.in, "iso-8859-1").nextLine();
        song.setCategory(category);
    }

    /**
     * Saves all local song-updates to the server.
     */
    private void saveUpdate()
    {
        System.out.println("Do you wish to save changes?");
        System.out.println("Y/N");

        String answer = new Scanner(System.in).nextLine();
        if (answer.equalsIgnoreCase("Y"))
        {
            try
            {
                SongManager.getInstance().updateSong(song);
                System.out.println("Changes saved.");
            }
            catch (Exception ex)
            {
//                ex.printStackTrace();
                System.out.println("ERROR - Unable to update song.");
            }
        }
        else
        {
            System.out.println("Changes will not be saved");
        }
        pause();
    }

    @Override
    protected void headerSection()
    {
        printSongHeader();
        System.out.println(song);
        System.out.println();
        pause();
    }
}