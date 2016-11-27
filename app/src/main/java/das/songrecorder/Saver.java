package das.songrecorder;

import java.io.File;

/**
 * Created by Toni on 27.11.2016.
 */

public class Saver {
    private File song;

    public Saver(File f){
        song=f;
    }

    public void save(String title){
        String location=song.getParentFile().getAbsolutePath();
        song.renameTo(new File(location+"/"+title+".3gp"));
    }
}
