package das.songrecorder;

import android.os.Parcel;
import android.os.Parcelable;

public class Song implements Parcelable {

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

    /**
     * The constructor creates object that represents the song with the appropriate parameters.
     * @param n
     * @param au
     * @param ar
     * @param d
     * @param l
     * @param g
     * @param y
     * @param date
     */
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

    protected Song(Parcel in) {
        name=in.readString();
        author=in.readString();
        artist=in.readString();
        duration=in.readInt();
        location=in.readString();
        genre=in.readString();
        year=in.readInt();
        dateRecorded=in.readString();
    }

    public static final Creator<Song> CREATOR = new Creator<Song>() {
        @Override
        public Song createFromParcel(Parcel in) {
            return new Song(in);
        }

        @Override
        public Song[] newArray(int size) {
            return new Song[size];
        }
    };

    /**
     * Sets the name of the song.
     * @param n
     */
    public void setName(String n){
        name=n;
    }

    /**
     * Sets the location of the song.
     * @param l
     */
    public void setLocation(String l){
        location=l;
    }

    /**
     * Sets the date when the song is recorded.
     * @param d
     */
    public void setDateRecorded(String d){
        dateRecorded=d;
    }

    /**
     * Sets the author of the song.
     * @param a
     */
    public void setAuthor(String a){
        author=a;
    }

    /**
     * Sets the artist of the song.
     * @param a
     */
    public void setArtist(String a){
        artist=a;
    }

    /**
     * Sets the duration of the song.
     * @param d
     */
    public void setDuration(int d){
        duration=d;
    }

    /**
     * Sets the genre of the song.
     * @param g
     */
    public void setGenre(String g){
        genre=g;
    }

    /**
     * Sets the year when the song is published.
     * @param y
     */
    public void setYear(int y){
        year=y;
    }

    /**
     *
     * @return The name of the song.
     */
    public String getName(){
        return name;
    }

    /**
     *
     * @return The  of the song.
     */
    public String getAuthor(){
        return author;
    }

    /**
     *
     * @return The artist of the song.
     */
    public String getArtist(){
        return artist;
    }

    /**
     *
     * @return The duration of the song.
     */
    public int getDuration(){
        return duration;
    }

    /**
     *
     * @return The location of the song.
     */
    public String getLocation(){
        return location;
    }

    /**
     *
     * @return The genre of the song.
     */
    public String getGenre(){
        return genre;
    }

    /**
     *
     * @return The year when the song is published.
     */
    public int getYear(){
        return year;
    }

    /**
     * The date when the song is recorded.
     * @return
     */
    public String getDateRecorded(){
        return dateRecorded;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(author);
        dest.writeString(artist);
        dest.writeInt(duration);
        dest.writeString(location);
        dest.writeString(genre);
        dest.writeInt(year);
        dest.writeString(dateRecorded);
    }
}
