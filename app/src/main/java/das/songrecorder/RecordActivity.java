package das.songrecorder;

import android.content.Intent;
import android.os.Environment;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;

public class RecordActivity extends AppCompatActivity {

    long timeWhenStopped;
    int fileLength;
    Recorder recorder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record);
        recorder=new Recorder();
        recorder.Start();

        final Button btnSave=(Button)this.findViewById(R.id.save);
        final Button btnContinue=(Button)this.findViewById(R.id.btnContinue);
        final TextView pausedText=(TextView)this.findViewById(R.id.txtPaused);
        final ImageView imgPaused=(ImageView)this.findViewById(R.id.imgPaused);
        final Button stop_pause=(Button)this.findViewById(R.id.stop_pause);
        final Chronometer timer=(Chronometer)this.findViewById(R.id.chronometer2);
        //TextView text1=(TextView)this.findViewById(R.id.textView);
        //text1.setText(Environment.getDataDirectory().getAbsolutePath());
        //File file=new File(Environment.getExternalStorageDirectory()+"/SongRecorder/");
        //file.mkdirs();
        //if(file.exists())
        //{
            //text1.setText("Postoiiiiiiiiiii");
        //}

        timer.start();

        btnContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnContinue.setVisibility(View.GONE);
                btnSave.setVisibility(View.GONE);
                pausedText.setVisibility(View.GONE);
                imgPaused.setVisibility(View.GONE);
                stop_pause.setVisibility(View.VISIBLE);
                timer.setVisibility(View.VISIBLE);
                timer.setBase(SystemClock.elapsedRealtime() + timeWhenStopped);
                timer.start();
            }
        });

        btnContinue.setVisibility(View.GONE);
        btnSave.setVisibility(View.GONE);
        pausedText.setVisibility(View.GONE);
        imgPaused.setVisibility(View.GONE);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), SaveSongActivity.class));
            }
        });

        stop_pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnContinue.setVisibility(View.VISIBLE);
                btnSave.setVisibility(View.VISIBLE);
                pausedText.setVisibility(View.VISIBLE);
                imgPaused.setVisibility(View.VISIBLE);
                stop_pause.setVisibility(View.GONE);
                timer.stop();
                stop(timer.getBase());
                timer.setVisibility(View.GONE);
                recorder.Stop();
            }
        });
    }

    public void stop(long t)
    {
        timeWhenStopped = t - SystemClock.elapsedRealtime();
        fileLength=(int)timeWhenStopped/1000;
    }
}
