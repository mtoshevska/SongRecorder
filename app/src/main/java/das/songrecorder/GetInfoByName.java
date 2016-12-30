package das.songrecorder;

import java.util.Random;

/**
 * Created by Toni on 30.12.2016.
 */

public class GetInfoByName implements GetInfo {
    @Override
    public Song getDataFromDatabase(Song f) {
        int num=new Random().nextInt(20);
        f.setAuthor("Author"+num);
        f.setArtist("Artist"+num);
        f.setGenre("Genre"+num);
        f.setYear(2017);
        return f;
    }
}
