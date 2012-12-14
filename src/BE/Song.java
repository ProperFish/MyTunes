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
     * @param id the id to be assigned.
     * @param s the existing song-object.
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
     * @param title the title-string.
     * @param artist the artist-string.
     * @param category the category-string.
     * @param filename the location of the file.
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
     * @param title the title-string.
     * @param artist the artist-string.
     * @param duration the duration-integer.
     * @param filename the location of the song.
     * @param category the category-string.
     */
    public Song(String title, String artist, int duration, String filename, String category)
    {
        this.title = title;
        this.artist = artist;
        this.duration = duration;
        this.filename = filename;
        this.category = category;
    }
    
    /**
     * Constructor for a song-object, accepting;
     * ID, Title, Artist, Category and Filename.
     * @param id the ID to be assigned.
     * @param title the title to be assigned.
     * @param artist the artist to be assigned.
     * @param category the category to be assigned.
     * @param filename  the filename to be assigned.
     */
    public Song(int id, String title, String artist, String category, String filename)
    {
        this.id = id;
        this.artist = artist;
        this.title = title;
        this.category = category;
        this.filename = filename;
    }
    
    /**
     * Constructor for a song-object, accepting;
     * ID, Title, Artist, Category, Filename and Duration.
     * @param id the id-integer.
     * @param artist the artist-string.
     * @param category the category-string.
     * @param filename the location of the file.
     * @param duration the duration-integer.
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
     * @param filename the filename to set.
     */
    public void setFilename(String filename)
    {
        this.filename = filename;
    }

    /**
     * Overrides the toString method for more practical use in our case.
     * @return the formatted string.
     */
    @Override
    public String toString()
    {
        //int min = duration/60;
        //int sec = duration-min*60;
        return String.format("%-40s %15s %10s %-10d %-3d", getTitle(), getArtist(), getCategory(), getDuration(), getId());
    }
}