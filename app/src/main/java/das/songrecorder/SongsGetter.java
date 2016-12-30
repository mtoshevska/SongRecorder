package das.songrecorder;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by Toni on 26.11.2016.
 */

public class SongsGetter {
    static final String location= Environment.getExternalStorageDirectory()+"/SongRecorder/";
    private static SongsGetter instance;

    private SongsGetter(){

    }

    public static SongsGetter getInstance(){
        synchronized (SongsGetter.class){
            if(instance==null){
                instance=new SongsGetter();
            }
        }
        return instance;
    }

    private File[] getFiles(){
        File directory=new File(location);
        File files[]=directory.listFiles();
        return files;
    }

    public ArrayList<Song> getSongs(Context context){
        File files[]=getFiles();
        int counter=0;
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
            if(cursor.getCount()==0) {
                Log.d("SongsGetter",counter+"");
                String title = f.getName();
                String title1 = title.substring(0, title.lastIndexOf('.'));
                String author = "Author" + counter;
                String artist = "Artist" + counter;
                String genre = "Genre" + counter;
                int duration = counter * 10 + 21;
                counter++;
                Song song = new Song(title1,author,artist,duration,f.getAbsolutePath(),genre,2016,"27.12.2016");
                songs.add(song);
            }
            else
            {
                cursor.moveToFirst();
                Song song = new Song(cursor.getString(0),cursor.getString(1),cursor.getString(2),cursor.getInt(3),
                        cursor.getString(4),cursor.getString(5),cursor.getInt(6),cursor.getString(7));
                songs.add(song);
                Log.d("SongsGetter","yayyyyyyyyyy");
            }
        }
        return songs;
    }
}
