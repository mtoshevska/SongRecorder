package das.songrecorder;

import android.os.Environment;

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

    public ArrayList<Song> getSongs(){
        File directory=new File(location);
        File files[]=directory.listFiles();
        int counter=0;
        ArrayList<Song>songs=new ArrayList<Song>();
        for(File f:files){
            String title=f.getName();
            String title1=title.substring(0, title.lastIndexOf('.'));
            String author="Author"+counter;
            String artist="Artist"+counter;
            int duration=counter*10+21;
            counter++;
            Song song=new Song(title1,author,artist,duration,f.getAbsolutePath());
            songs.add(song);
        }
        return songs;
    }
}
