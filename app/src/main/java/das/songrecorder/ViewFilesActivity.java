package das.songrecorder;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

public class ViewFilesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_files);

        ListView songList=(ListView)this.findViewById(R.id.songsList);
        SongAdapter adapter=new SongAdapter(getApplicationContext());
        SongsGetter songsGetter=SongsGetter.getInstance();
        adapter.addSongs(songsGetter.getSongs());
        adapter.notifyDataSetChanged();
        songList.setAdapter(adapter);
    }
}
