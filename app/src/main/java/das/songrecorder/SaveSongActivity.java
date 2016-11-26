package das.songrecorder;

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
                String title=songTitle.getText().toString();
                if(title!="") {
                    saveSong(title);
                }
                startActivity(new Intent(getApplication(),HomeActivity.class));
            }
        });
    }

    public void saveSong(String title)
    {
        String location=song.getParentFile().getAbsolutePath();
        song.renameTo(new File(location+"/"+title+".3gp"));
        Information info=new Information(song);
        info.fill();
        Toast.makeText(getApplicationContext(),"Song saved!", Toast.LENGTH_LONG).show();
    }
}
