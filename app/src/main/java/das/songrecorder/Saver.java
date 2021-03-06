package das.songrecorder;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import java.io.File;

public class Saver {

    private static Saver instance;

    /**
     * Private constructor for the class.
     */
    private Saver(){}

    /**
     * Creates and returns instance if is null. Otherwise just returns it.
     * @return unique instance of the class
     */
    public static Saver getInstance(){
        synchronized (Saver.class){
            if(instance==null){
                instance=new Saver();
            }
        }
        return instance;
    }

    /**
     * Saves the song with its information locally on the device.
     * @param song
     * @param context
     */
    public void save(Song song, Context context){
        String location=song.getLocation();
        File songFile=new File(location);
        if(song.getArtist().compareTo("unknown")!=0) {
            songFile.renameTo(new File(songFile.getParentFile().getAbsolutePath()+"/"+ song.getName()+"-"+song.getArtist()+".3gp"));
            song.setLocation(songFile.getParentFile().getAbsolutePath()+"/"+song.getName()+"-"+song.getArtist()+".3gp");
        }
        else{
            songFile.renameTo(new File(songFile.getParentFile().getAbsolutePath()+"/"+song.getName()+".3gp"));
            song.setLocation(songFile.getParentFile().getAbsolutePath()+"/"+song.getName()+".3gp");
        }
        SongsDBHelper dbHelper=new SongsDBHelper(context);
        SQLiteDatabase db=dbHelper.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(SongDBEntry.COLUMN_NAME,song.getName());
        values.put(SongDBEntry.COLUMN_AUTHOR,song.getAuthor());
        values.put(SongDBEntry.COLUMN_ARTIST,song.getArtist());
        values.put(SongDBEntry.COLUMN_DURATION,song.getDuration());
        values.put(SongDBEntry.COLUMN_LOCATION,song.getLocation());
        values.put(SongDBEntry.COLUMN_GENRE,song.getGenre());
        values.put(SongDBEntry.COLUMN_YEAR,song.getYear());
        values.put(SongDBEntry.COLUMN_DATE_RECORDED,song.getDateRecorded());
        db.insert(SongDBEntry.TABLE_NAME,null,values);
    }

    /**
     * Deletes song from the device.
     * @param song
     * @param context
     */
    public void discard(Song song,Context context){
        String location=song.getLocation();
        File forDelete=new File(location);
        forDelete.delete();
        SongsDBHelper dbHelper=new SongsDBHelper(context);
        SQLiteDatabase db=dbHelper.getWritableDatabase();
        try {
            db.delete(SongDBEntry.TABLE_NAME, SongDBEntry.COLUMN_LOCATION + "='" + location + "'", null);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
