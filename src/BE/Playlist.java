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
    
    public Playlist(int id, Playlist p)
    {
        this.id = p.id;
        this.name = p.name;
        this.created = p.created;
    }
    
    public Playlist(int id, String name)
    {
        this.name = name;
        this.id = id;
    }
    
    public Playlist(int id, String name , String created)
    {
        this.id = id;
        this.name = name;
        this.created = created;
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
    public String getName()
    {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name)
    {
        this.name = name;
    }

    /**
     * @return the address
     */
    public String getCreated()
    {
        return created;
    }
//    @Override
//    public String toString()
//    {
//        int min = duration/60;
//        int sec = duration-min*60;
//        return String.format("%3d %-30s %-30s %4d", title, artist, category, min + ":" + sec);
//    }
}