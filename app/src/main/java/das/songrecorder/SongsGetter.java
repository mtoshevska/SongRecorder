package das.songrecorder;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;
import android.util.Log;
import java.io.File;
import java.util.ArrayList;

public class SongsGetter {

    static final String location= Environment.getExternalStorageDirectory()+"/SongRecorder/";
    private static SongsGetter instance;

    /**
     * Private constructor for the class.
     */
    private SongsGetter(){}

    /**
     * Creates and returns instance if is null. Otherwise just returns it.
     * @return unique instance of the class
     */
    public static SongsGetter getInstance(){
        synchronized (SongsGetter.class){
            if(instance==null){
                instance=new SongsGetter();
            }
        }
        return instance;
    }

    /**
     * Method for getting the list of all song files saved locally on phone.
     * @return List of audio files
     */
    private File[] getFiles(){
        File directory=new File(location);
        File files[]=directory.listFiles();
        return files;
    }

    /**
     * Method for getting the list of all songs saved locally on phone.
     * @param context
     * @return List of Songs
     */
    public ArrayList<Song> getSongs(Context context){
        File files[]=getFiles();
        ArrayList<Song>songs=new ArrayList<Song>();
        SongsDBHelper dbHelper=new SongsDBHelper(context);
        SQLiteDatabase db=dbHelper.getReadableDatabase();
        String projection[]={SongDBEntry.COLUMN_NAME,SongDBEntry.COLUMN_AUTHOR,SongDBEntry.COLUMN_ARTIST,
                SongDBEntry.COLUMN_DURATION,SongDBEntry.COLUMN_LOCATION,SongDBEntry.COLUMN_GENRE,
                SongDBEntry.COLUMN_YEAR,SongDBEntry.COLUMN_DATE_RECORDED};
        String selection=SongDBEntry.COLUMN_LOCATION + " = ?";
        String[] selectionArgs=new String[1];
        for(File f:files){
            selectionArgs[0]=f.getAbsolutePath();
            Cursor cursor=db.query(SongDBEntry.TABLE_NAME,projection,selection,selectionArgs,null,null,null);
            cursor.moveToFirst();
            Song song = new Song(cursor.getString(0),cursor.getString(1),cursor.getString(2),cursor.getInt(3),
                    cursor.getString(4),cursor.getString(5),cursor.getInt(6),cursor.getString(7));
            songs.add(song);
            Log.d("SongsGetter",cursor.getString(0)+" "+cursor.getString(1)+" "+cursor.getString(2)+" "+
                    cursor.getInt(3)+" "+cursor.getString(4)+" "+cursor.getString(5)+" "+cursor.getInt(6)+
                    " "+cursor.getString(7));
        }
        return songs;
    }
}
