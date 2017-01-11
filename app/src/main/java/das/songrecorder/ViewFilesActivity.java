package das.songrecorder;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import java.util.ArrayList;

public class ViewFilesActivity extends AppCompatActivity {

    /**
     * The class is used for interaction between user and the application in order to view the recorded songs.
     */

    SongAdapter adapter;
    SongsGetter songsGetter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_files);
        ListView songList=(ListView)this.findViewById(R.id.songsList);
        adapter=new SongAdapter(this);
        songsGetter=SongsGetter.getInstance();
        ArrayList<Song>songs=songsGetter.getSongs(getApplicationContext());
        adapter.addSongs(songs);
        adapter.notifyDataSetChanged();
        songList.setAdapter(adapter);
    }
}
