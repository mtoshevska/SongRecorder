package das.songrecorder;

import android.provider.BaseColumns;

/**
 * Created by Toni on 27.12.2016.
 */

public class SongDBEntry implements BaseColumns {
    public static final String TABLE_NAME = "SongRecorder_songs";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_AUTHOR = "author";
    public static final String COLUMN_ARTIST = "artist";
    public static final String COLUMN_DURATION = "duration";
    public static final String COLUMN_LOCATION = "location";
    public static final String COLUMN_GENRE = "genre";
    public static final String COLUMN_YEAR = "year";
    public static final String COLUMN_DATE_RECORDED = "dateRecorded";
}
