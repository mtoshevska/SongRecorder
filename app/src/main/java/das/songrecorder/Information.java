package das.songrecorder;

import android.app.Activity;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by Toni on 25.11.2016.
 */

public class Information {

    private static Information instance;

    /**
     * Private constructor for the class.
     */
    private Information(){}

    /**
     * Creates and returns instance if is null. Otherwise just returns it.
     * @return unique instance of the class
     */
    public static Information getInstance(){
        synchronized (Information.class){
            if(instance==null){
                instance=new Information();
            }
        }
        return instance;
    }

    public Song fill(Song song,String title,Activity activity) {
        song.setName(title);
        Calendar c = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy");
        String formattedDate = df.format(c.getTime());
        song.setDateRecorded(formattedDate);
        GetInfo getInfo=new GetInfoByName(activity);
        return getInfo.getDataFromDatabase(song);
    }
}
