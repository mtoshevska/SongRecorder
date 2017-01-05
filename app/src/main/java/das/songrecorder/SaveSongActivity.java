package das.songrecorder;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;

public class SaveSongActivity extends AppCompatActivity {

    Song song=null;
    Saver saver;
    Information info;

    BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Log.d("SaveSongActivity","Received broadcast");
            song=(Song)intent.getExtras().get("Song");
            saveSong();
            Toast.makeText(getApplicationContext(),"Song saved!", Toast.LENGTH_LONG).show();
            startActivity(new Intent(getApplicationContext(),HomeActivity.class));
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save_song);

        Button btnSave=(Button)this.findViewById(R.id.btnSaveSong);
        final EditText songTitle=(EditText)this.findViewById(R.id.title);

        Bundle extras=getIntent().getExtras();
        if(extras!=null){
            song=(Song)extras.get("Song");
        }

        saver=Saver.getInstance();
        info=Information.getInstance();

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String title=songTitle.getText().toString();
                if(title.compareTo("")!=0) {
                    final android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(SaveSongActivity.this);
                    builder.setMessage("Save song?");
                    builder.setCancelable(false);
                    builder.setPositiveButton(
                            "Yes",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    fillInfo(title);
                                    //saveSong();
                                    //Toast.makeText(getApplicationContext(),"Song saved!", Toast.LENGTH_LONG).show();
                                    //startActivity(new Intent(getApplicationContext(),HomeActivity.class));
                                }
                            });
                    builder.setNegativeButton(
                            "No",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    discardSong();
                                    Toast.makeText(getApplicationContext(),"Song discarded", Toast.LENGTH_LONG).show();
                                    startActivity(new Intent(getApplicationContext(),HomeActivity.class));
                                }
                            });
                    builder.show();
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"Please type song name",Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        IntentFilter filter = new IntentFilter();
        filter.addAction("SongFilledWithInformation");
        registerReceiver(broadcastReceiver, filter);
        Log.d("SaveSongActivity","Broadcast registered");
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(broadcastReceiver);
        Log.d("SaveSongActivity","Broadcast unregistered");
    }

    public void discardSong(){
        saver.discard(song,getApplicationContext());
    }

    public void fillInfo(String title){
        song=info.fill(song,title,this);
    }

    public void saveSong(){
        saver.save(song,getApplicationContext());
    }
}
