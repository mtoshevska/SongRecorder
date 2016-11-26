package das.songrecorder;

/**
 * Created by Toni on 26.11.2016.
 */

public class Song {

    private String name;
    private String author;
    private String artist;
    private int duration;

    public Song(String n, String au, String ar, int d){
        name=n;
        author=au;
        artist=ar;
        duration=d;
    }

    public String getName(){
        return name;
    }

    public String getAuthor(){
        return author;
    }

    public String getArtist(){
        return artist;
    }

    public int getDuration(){
        return duration;
    }
}
