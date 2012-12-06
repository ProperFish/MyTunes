package BE;

/**
 * MyTunes, EASV (14/12/2012)
 * @author Lars Vad Sørensen, Jakob Hansen, Klaus Teddy Bøgelund Andresen og Jesper Agerbo Hansen
 */

public class Song
{
    // Instance fields.
    private int id;
    private String title;
    private String artist;
    private String category;
    private String filename;
    private int duration;
    
    /**
     * Constructor for a song-object, accepting;
     * ID and an already existing song-object..
     * @param id and song object.
     */
    public Song(int id, Song s)
    {
        this.id = id;
        this.title = s.getTitle();
        this.artist = s.getArtist();
        this.category = s.getCategory();
        this.duration = s.getDuration();
        this.filename = s.getFilename();
    }
    
    /**
     * Constructor for a song-object, accepting;
     * Title, Artist, Category and Filename.
     * @param title, artist, category and filename.
     */
    public Song(String title, String artist, String category, String filename)
    {
        this.title = title;
        this.artist = artist;
        this.category = category;
        this.filename = filename;
    }
    
    /**
     * Constructor for a song-object, accepting;
     * ID, Title, Artist, Category, Filename and Duration.
     * @param id, title, artist, category, filename and duration.
     */
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
     * Returns the ID of a song.
     * @return the id.
     */
    public int getId()
    {
        return id;
    }

    /**
     * Returns the title of a song.
     * @return the title.
     */
    public String getTitle()
    {
        return title;
    }

    /**
     * Sets the title of a song.
     * @param name the title to set.
     */
    public void setTitle(String title)
    {
        this.title = title;
    }

    /**
     * Returns the artist linked to a song.
     * @return the artist.
     */
    public String getArtist()
    {
        return artist;
    }

    /**
     * Set the artist of a song.
     * @param address the artist.
     */
    public void setArtist(String artist)
    {
        this.artist = artist;
    }

    /**
     * Returns the category of a song.
     * @return the category.
     */
    public String getCategory()
    {
        return category;
    }

    /**
     * Sets the category of a song.
     * @param depNum the category to set.
     */
    public void setCategory(String category)
    {
        this.category = category;
    }
    
     /**
     * Returns the duration of a song.
     * @return the duration.
     */
    public int getDuration()
    {
        return duration;
    }
    
    /**
     * Sets the duration of a song.
     * @param depNum the duration to set.
     */
    public void setDuration(int duration)
    {
        this.duration = duration;
    }
    
    /**
     * Returns the filename of a song.
     * @return the filename.
     */
    public String getFilename()
    {
        return filename;
    }
    
    /**
     * Sets the filename of a song.
     * @param depNum the filename to set.
     */
    public void setFilename(String filename)
    {
        this.filename = filename;
    }

//    @Override
//    public String toString()
//    {
//        //int min = duration/60;
//        //int sec = duration-min*60;
//        return String.format("%3d %-30s %-30s %4d %2d", title, artist, category, duration, id);
//    }
}