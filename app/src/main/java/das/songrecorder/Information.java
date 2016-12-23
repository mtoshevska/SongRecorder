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

    public void fill(File song) {
        getDataFromDatabase(song.getName());
    }

    public void getDataFromDatabase(String title) {

    }
}
