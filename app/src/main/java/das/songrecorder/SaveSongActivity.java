package das.songrecorder;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;

public class SaveSongActivity extends AppCompatActivity {

    File song=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save_song);

        Button btnSave=(Button)this.findViewById(R.id.btnSaveSong);
        final EditText songTitle=(EditText)this.findViewById(R.id.title);

        Bundle extras=getIntent().getExtras();
        if(extras!=null){
            song=(File)extras.get("Song");
        }

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
                                    fillInfo();
                                    saveSong(title);
                                    Toast.makeText(getApplicationContext(),"Song saved!", Toast.LENGTH_LONG).show();
                                    startActivity(new Intent(getApplicationContext(),HomeActivity.class));
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

    public void discardSong()
    {
        Saver saver=new Saver(song);
        saver.discard();
    }

    public void fillInfo()
    {
        Information info=Information.getInstance();
        info.fill(song);
    }

    public void saveSong(String title)
    {
        Saver saver=new Saver(song);
        saver.save(title);
    }
}
