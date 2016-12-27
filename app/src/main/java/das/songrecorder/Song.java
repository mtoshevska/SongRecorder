package das.songrecorder;

import java.util.Date;
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
    private String genre;
    private int year;
    private String dateRecorded;

    public Song(){

    }

    public Song(String n, String au, String ar, int d, String l, String g, int y, String date){
        name=n;
        author=au;
        artist=ar;
        duration=d;
        location=l;
        genre=g;
        year=y;
        dateRecorded=date;
    }

    public void setName(String n){
        name=n;
    }

    public void setLocation(String l){
        location=l;
    }

    public void setDateRecorded(String d){
        dateRecorded=d;
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
