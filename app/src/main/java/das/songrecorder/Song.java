package das.songrecorder;

import java.util.Locale;

/**
 * Created by Toni on 26.11.2016.
 */

public class Song {

    private String name;
    private String author;
    private String artist;
    private int duration;
    private String location;

    public Song(String n, String au, String ar, int d, String l){
        name=n;
        author=au;
        artist=ar;
        duration=d;
        location=l;
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

    public String getLocation(){
        return location;
    }
}
