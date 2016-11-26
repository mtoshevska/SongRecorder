package das.songrecorder;

import android.os.Environment;

import java.io.File;

/**
 * Created by Toni on 26.11.2016.
 */

public class SongsGetter {
    static final String location= Environment.getExternalStorageDirectory()+"/SongRecorder/";

    public SongsGetter(){

    }

    public void getSongs(SongAdapter adapter){
        File directory=new File(location);
        File files[]=directory.listFiles();
        int counter=0;
        for(File f:files){
            String title=f.getName();
            String author="Author"+counter;
            String artist="Artist"+counter;
            int duration=counter*10+21;
            counter++;
            Song song=new Song(title,author,artist,duration,f.getAbsolutePath());
            adapter.addSong(song);
        }
    }
}
