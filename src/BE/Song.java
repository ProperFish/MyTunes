package BE;

import java.util.Calendar;
/**
 *
 * @author drengene
 */
public class Song
{
    // instance fields
    private int id;
    private String title;
    private String artist;
    private String category;
    private String filename;
    private int duration;
    
    public Song(int id, Song s)
    {
        this.id = id;
        this.title = s.getTitle();
        this.artist = s.getArtist();
        this.category = s.getCategory();
        this.duration = s.getDuration();
        this.filename = s.getFilename();
    }
    
    public Song(int id, String title , String artist, String category, String filename, int duration)
    {
        this.id = id;
        this.title = title;
        this.artist = artist;
        this.category = category;
        this.duration = duration;
        this.filename = filename;
    }

    /**
     * @return the id
     */
    public int getId()
    {
        return id;
    }

    /**
     * @return the name
     */
    public String getTitle()
    {
        return title;
    }

    /**
     * @param name the name to set
     */
    public void setTitle(String title)
    {
        this.title = title;
    }

    /**
     * @return the address
     */
    public String getArtist()
    {
        return artist;
    }

    /**
     * @param address the address to set
     */
    public void setArtist(String artist)
    {
        this.artist = artist;
    }

    /**
     * @return the depNum
     */
    public String getCategory()
    {
        return category;
    }

    /**
     * @param depNum the depNum to set
     */
    public void setCategory(String category)
    {
        this.category = category;
    }
    
    public int getDuration()
    {
        return duration;
    }
    
    public void setDuration(int duration)
    {
        this.duration = duration;
    }
    
    public String getFilename()
    {
        return filename;
    }
    
    public void setFilename(String filename)
    {
        this.filename = filename;
    }
    

    @Override
    public String toString()
    {
        //int min = duration/60;
        //int sec = duration-min*60;
        return String.format("%3d %-30s %-30s %4d %2d", title, artist, category, duration, id);
    }
}