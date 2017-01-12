package das.songrecorder;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
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
    BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            ArrayList<Song>songs=(ArrayList<Song>)intent.getExtras().get("Songs");
            adapter.addSongs(songs);
            adapter.notifyDataSetChanged();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_files);
        ListView songList = (ListView) this.findViewById(R.id.songsList);
        adapter = new SongAdapter(this);
        songsGetter = SongsGetter.getInstance();
        songsGetter.getSongs(getApplicationContext());
        songList.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        IntentFilter filter = new IntentFilter();
        filter.addAction("SongsSent");
        registerReceiver(broadcastReceiver, filter);
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(broadcastReceiver);
    }

}
