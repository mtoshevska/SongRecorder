package das.songrecorder;

import android.app.Activity;
import java.text.SimpleDateFormat;
import java.util.Calendar;

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

    /**
     * Method that calls other methods in order to fill the song metadata with information.
     * @param song
     * @param title
     * @param activity
     */
    public void fill(Song song,String title,Activity activity) {
        song.setName(title);
        Calendar c = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy");
        String formattedDate = df.format(c.getTime());
        song.setDateRecorded(formattedDate);
        GetInfo getInfo=new GetInfoByName(activity);
        getInfo.getDataFromDatabase(song);
    }
}
