package das.songrecorder;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.nfc.tech.NfcBarcode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

public class ViewFilesActivity extends AppCompatActivity {

    SongAdapter adapter;
    SongsGetter songsGetter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_files);

        /*SongsDBHelper dbHelper=new SongsDBHelper(getApplicationContext());
        SQLiteDatabase db=dbHelper.getReadableDatabase();
        //db.delete(SongDBEntry.TABLE_NAME, null, null);
        String projection[]={SongDBEntry.COLUMN_NAME,SongDBEntry.COLUMN_AUTHOR,SongDBEntry.COLUMN_ARTIST,
                SongDBEntry.COLUMN_DURATION,SongDBEntry.COLUMN_LOCATION,SongDBEntry.COLUMN_GENRE,
                SongDBEntry.COLUMN_YEAR,SongDBEntry.COLUMN_DATE_RECORDED};
        String sortOrder = SongDBEntry.COLUMN_NAME + " DESC";
        Cursor c = db.query(
                SongDBEntry.TABLE_NAME,                     // The table to query
                projection,                               // The columns to return
                null,                                // The columns for the WHERE clause
                null,                            // The values for the WHERE clause
                null,                                     // don't group the rows
                null,                                     // don't filter by row groups
                null                                 // The sort order
        );
        Log.d("ViewFilesActivity",c.getCount()+"");
        for(int i=0;i<c.getCount();i++)
        {
            c.moveToPosition(i);
            Log.d("ViewFilesActivity",c.getString(0));
            Log.d("ViewFilesActivity",c.getString(1));
            Log.d("ViewFilesActivity",c.getString(2));
            Log.d("ViewFilesActivity",c.getInt(3)+"");
            Log.d("ViewFilesActivity",c.getString(4));
            Log.d("ViewFilesActivity",c.getString(5));
            Log.d("ViewFilesActivity",c.getInt(6)+"");
            Log.d("ViewFilesActivity",c.getString(7));
        }*/


        ListView songList=(ListView)this.findViewById(R.id.songsList);
        adapter=new SongAdapter(getApplicationContext());
        songsGetter=SongsGetter.getInstance();
        adapter.addSongs(songsGetter.getSongs(getApplicationContext()));
        adapter.notifyDataSetChanged();
        songList.setAdapter(adapter);
    }
}
