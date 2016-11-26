package das.songrecorder;

import java.io.File;

/**
 * Created by Toni on 25.11.2016.
 */

public class Information {

    private File song;

    public Information(File s) {
        song = s;
    }

    public void fill() {
        getDataFromDatabase(song.getName());
    }

    public void getDataFromDatabase(String title) {

    }
}
