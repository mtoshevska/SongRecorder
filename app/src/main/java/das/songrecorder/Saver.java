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

    public void save(String title, File song){
        String location=song.getParentFile().getAbsolutePath();
        song.renameTo(new File(location+"/"+title+".3gp"));
    }

    public void discard(File song){
        String location=song.getParentFile().getAbsolutePath();
        song.delete();
    }
}
