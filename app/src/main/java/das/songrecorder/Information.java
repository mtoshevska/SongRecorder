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

    public Song fill(File songFile,String title,int d) {
        Song song=new Song();
        song.setName(title);
        song.setLocation(songFile.getAbsolutePath());
        song.setDuration(d);
        Calendar c = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy");
        String formattedDate = df.format(c.getTime());
        song.setDateRecorded(formattedDate);
        return getDataFromDatabase(song);
    }

    private Song getDataFromDatabase(Song f) {
        //fill attr with info from database
        int num=new Random().nextInt(20);
        f.setAuthor("Author"+num);
        f.setArtist("Artist"+num);
        f.setGenre("Genre"+num);
        f.setYear(2017);
        return f;
    }
}
