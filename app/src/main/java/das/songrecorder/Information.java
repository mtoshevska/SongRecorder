package das.songrecorder;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Random;

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

    public Song fill(Song song,String title) {
        song.setName(title);
        Calendar c = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy");
        String formattedDate = df.format(c.getTime());
        song.setDateRecorded(formattedDate);
        GetInfo getInfo=new GetInfoByName();
        return getInfo.getDataFromDatabase(song);
    }
}
