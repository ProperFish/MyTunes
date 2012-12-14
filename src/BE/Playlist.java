package BE;

/**
 * MyTunes, EASV (14/12/2012)
 * @author Lars Vad Sørensen, Jakob Hansen, Klaus Teddy Bøgelund Andresen og Jesper Agerbo Hansen
 */

public class Playlist
{
    // Instance fields.
    private int id;
    private String name;
    private String created;    
    
    /**
     * Creates a new playlist from a given name.
     * @param name the name to be assigned to the playlist.
     */
    public Playlist(String name)
    {
        this.name = name;
    }
    
    /**
     * Constructor for a playlist-object, accepting;
     * ID and an already existing playlist-object..
     * @param id and playlist object.
     */
    public Playlist(int id, Playlist p)
    {
        this.id = p.id;
        this.name = p.name;
        this.created = p.created;
    }
    
    /**
     * A constructor a playlist-object, that accepts:
     * @param id the id to be assigned to the object.
     * @param name the name to be assigned to the object.
     * @param timestamp the timestamp to be assigned to the object.
     */
    public Playlist(int id, String name, java.sql.Timestamp timestamp)
    {
        this.name = name;
        this.id = id;
        this.created = timestamp.toString();
    }

    /**
     * A constructor for a playlist-object, that accepts:
     * @param id the id to be assigned to the object.
     * @param name  the name to be assigned to the object.
    */
    public Playlist(int id, String name)
    {
        this.name = name;
        this.id = id;
    }
    
    /**
     * Constructor for a playlist-object, accepting;
     * ID, Name and Creation-date.
     * @param id, name and creation-date.
     */
    public Playlist(int id, String name , String created)
    {
        this.id = id;
        this.name = name;
        this.created = created;
    }

    /**
     * Returns the ID of a playlist.
     * @return the ID.
     */
    public int getId()
    {
        return id;
    }

    /**
     * Gets the name of the playlist.
     * @return the name.
     */
    public String getName()
    {
        return name;
    }

    /**
     * Sets the name of the playlist.
     * @param name the name to set.
     */
    public void setName(String name)
    {
        this.name = name;
    }

    /**
     * Returns the creation-time of the playlist.
     * @return the time of creation.
     */
    public String getCreated()
    {
        return created;
    }
    
    /**
     * Overrides the toString-method for header-formatting.
     * @return a formatted string.
     */
    @Override
    public String toString()
    {
        return String.format("%-3d %-10s %15s", getId(), getName(), getCreated());
    }
}