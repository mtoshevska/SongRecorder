package das.songrecorder;

import android.content.Intent;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.ImageView;
import android.widget.TextView;

public class RecordActivity extends AppCompatActivity {

    /**
     * The class is used for interaction between user and the application in order to record the song.
     */

    long timeWhenStopped;
    int fileLength;
    Recorder recorder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record);
        recorder=Recorder.getInstance();

        final Button btnSave=(Button)this.findViewById(R.id.save);
        final Button btnContinue=(Button)this.findViewById(R.id.btnContinue);
        final TextView pausedText=(TextView)this.findViewById(R.id.txtPaused);
        final ImageView imgPaused=(ImageView)this.findViewById(R.id.imgPaused);
        final Button stop_pause=(Button)this.findViewById(R.id.stop_pause);
        final Chronometer timer=(Chronometer)this.findViewById(R.id.chronometer2);
        final Button startAgain=(Button)this.findViewById(R.id.startAgain);
        timer.start();
        recorder.Start();

        btnContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnContinue.setVisibility(View.GONE);
                btnSave.setVisibility(View.GONE);
                pausedText.setVisibility(View.GONE);
                imgPaused.setVisibility(View.GONE);
                startAgain.setVisibility(View.GONE);
                stop_pause.setVisibility(View.VISIBLE);
                timer.setVisibility(View.VISIBLE);
                long time=SystemClock.elapsedRealtime()+timeWhenStopped;
                Log.d("RecordActivity",time+"");
                timer.setBase(time);
                timer.start();
            }
        });

        btnContinue.setVisibility(View.GONE);
        btnSave.setVisibility(View.GONE);
        pausedText.setVisibility(View.GONE);
        startAgain.setVisibility(View.GONE);
        imgPaused.setVisibility(View.GONE);

        startAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnContinue.setVisibility(View.GONE);
                btnSave.setVisibility(View.GONE);
                pausedText.setVisibility(View.GONE);
                imgPaused.setVisibility(View.GONE);
                startAgain.setVisibility(View.GONE);
                stop_pause.setVisibility(View.VISIBLE);
                timer.setVisibility(View.VISIBLE);
                startNewRecording();
                timer.setBase(SystemClock.elapsedRealtime()+timeWhenStopped);
                timer.start();
            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fillSongInformation();
            }
        });

        stop_pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnContinue.setVisibility(View.VISIBLE);
                btnSave.setVisibility(View.VISIBLE);
                pausedText.setVisibility(View.VISIBLE);
                imgPaused.setVisibility(View.VISIBLE);
                startAgain.setVisibility(View.VISIBLE);
                stop_pause.setVisibility(View.GONE);
                timer.stop();
                stop(timer.getBase());
                timer.setVisibility(View.GONE);
            }
        });
    }

    /**
     * Method used in order to stop recording the song when user clicks the button.
     * @param t
     */
    public void stop(long t){
        Log.d("RecordActivity",t+"");
        timeWhenStopped=t-SystemClock.elapsedRealtime();
        fileLength=(int)(SystemClock.elapsedRealtime()-t)/1000;
        recorder.Pause();
    }

    /**
     * Method used to get the information for the song.
     */
    public void fillSongInformation(){
        Song song=recorder.Stop();
        song.setDuration(fileLength);
        Intent intent=new Intent(getApplicationContext(),SaveSongActivity.class);
        intent.putExtra("Song",song);
        startActivity(intent);
    }

    /**
     * Method used to start new recording if user click it.
     */
    public void startNewRecording(){
        timeWhenStopped=0;
        fileLength=0;
        recorder.Start();
    }
}
