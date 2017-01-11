package das.songrecorder;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.app.AlertDialog.Builder;


public class SaveSongActivity extends AppCompatActivity {

    Song song=null;
    Saver saver;
    Information info;

    BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Log.d("SaveSongActivity","Received broadcast");
            song=(Song)intent.getExtras().get("Song");
            boolean foundInfo=(boolean)intent.getExtras().get("FoundInfo");
            boolean databaseError=(boolean)intent.getExtras().get("DatabaseError");
            if(foundInfo) {
                saveSong();
            }
            else {
                Builder builder = new Builder(SaveSongActivity.this);
                if(databaseError) {
                    builder.setTitle("Error trying to connect to database");
                    builder.setMessage("Something wrong happened while trying to connect to database. "+
                            "Click \"Save\" in order to save the song without information or click " +
                            "\"Try again\" to try again.");
                }
                else {
                    builder.setTitle("Song with that name does not exist in database");
                    builder.setMessage("Click \"Save\" to save the song without information. Or click " +
                            "\"Try again\" to check the name again.");
                }
                builder.setCancelable(false);
                builder.setPositiveButton(
                        "Save",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                saveSong();
                            }
                        });
                builder.setNegativeButton(
                        "Try again",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        });
                builder.show();
            }
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
                    final Builder builder = new Builder(SaveSongActivity.this);
                    builder.setMessage("Clicking on \"Yes\" will save the song with its information in the device. "+
                            "Clicking on \"No\" will discard the song.");
                    builder.setTitle("Save song?");
                    builder.setCancelable(false);
                    builder.setPositiveButton(
                            "Yes",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    ConnectivityManager cm=(ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
                                    NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
                                    boolean isConnected = activeNetwork != null && activeNetwork.isConnectedOrConnecting();
                                    if(isConnected){
                                        Log.d("SaveSongActivity","network connected");
                                        fillInfo(title);
                                    }
                                    else {
                                        Log.d("SaveSongActivity", "network not connected");
                                        Toast.makeText(getApplicationContext(),"Device is not connected to internet. "+
                                                "Internet connection is needed in order to get the info. "+
                                                "Please connect your device to internet.", Toast.LENGTH_LONG).show();

                                    }
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
                else {
                    Toast.makeText(getApplicationContext(), "Please type song name", Toast.LENGTH_LONG).show();
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

    /**
     * Method for discarding song, actually not saving it.
     */
    public void discardSong(){
        saver.discard(song,getApplicationContext());
    }

    /**
     * Method which according to the title of the song, pulls the other information.
     * @param title
     */
    public void fillInfo(String title){
        info.fill(song,title,this);
    }

    /**
     * Method for saving the song all together with the pulled information.
     */
    public void saveSong(){
        saver.save(song,getApplicationContext());
        Toast.makeText(getApplicationContext(), "Song saved!", Toast.LENGTH_LONG).show();
        startActivity(new Intent(getApplicationContext(), HomeActivity.class));
    }
}
