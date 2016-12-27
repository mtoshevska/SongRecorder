package das.songrecorder;

import java.io.File;

/**
 * Created by Toni on 25.11.2016.
 */

public class Information {

    private static Information instance;

    private Information() {

    }

    public static Information getInstance(){
        synchronized (Information.class){
            if(instance==null){
                instance=new Information();
            }
        }
        return instance;
    }

    public Song fill(File songFile,String title) {
        Song song=new Song();
        song.setName(title);
        song.setLocation(songFile.getAbsolutePath());
        return getDataFromDatabase(song);
    }

    private Song getDataFromDatabase(Song f) {
        return f;
    }
}
