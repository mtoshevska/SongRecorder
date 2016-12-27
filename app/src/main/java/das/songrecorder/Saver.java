package das.songrecorder;

import java.io.File;

/**
 * Created by Toni on 27.11.2016.
 */

public class Saver {
    private static Saver instance;

    private Saver(){

    }

    public static Saver getInstance(){
        synchronized (Saver.class){
            if(instance==null){
                instance=new Saver();
            }
        }
        return instance;
    }

    public void save(Song song){
        String location=song.getLocation();
        File songFile=new File(location);
        songFile.renameTo(new File(songFile.getParentFile().getAbsolutePath()+"/"+song.getName()+".3gp"));
    }

    public void discard(Song song){
        File forDelete=new File(song.getLocation());
        forDelete.delete();
    }
}
