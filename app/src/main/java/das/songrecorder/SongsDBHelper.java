package das.songrecorder;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class SongsDBHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION=1;
    public static final String DATABASE_NAME="SongRecorder_Songs.db";

    /**
     * Class that helps in manipulating with the data from the local database.
     */

    private static final String SQL_DELETE_ENTRIES="DROP TABLE IF EXISTS " + SongDBEntry.TABLE_NAME;

    private static final String SQL_CREATE_ENTRIES="CREATE TABLE "+SongDBEntry.TABLE_NAME+" ("+
            SongDBEntry.COLUMN_NAME+" TEXT NOT NULL,"+
            SongDBEntry.COLUMN_AUTHOR+" TEXT,"+
            SongDBEntry.COLUMN_ARTIST+" TEXT NOT NULL,"+
            SongDBEntry.COLUMN_DURATION+" INTEGER,"+
            SongDBEntry.COLUMN_LOCATION+" TEXT,"+
            SongDBEntry.COLUMN_GENRE+" TEXT,"+
            SongDBEntry.COLUMN_YEAR+" INTEGER,"+
            SongDBEntry.COLUMN_DATE_RECORDED+" TEXT,"+
            "PRIMARY KEY ("+SongDBEntry.COLUMN_NAME+","+SongDBEntry.COLUMN_ARTIST+"))";

    public SongsDBHelper(Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
        Log.d("SongDBHelper",SongDBEntry.TABLE_NAME+" created!");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }
}
