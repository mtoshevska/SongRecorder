package das.songrecorder;

import android.media.MediaRecorder;
import android.os.Environment;
import android.util.Log;

import java.io.File;

/**
 * Created by Toni on 25.11.2016.
 */

public class Recorder {

    private MediaRecorder recorder;
    private String songName;

    public Recorder() {
        songName=Environment.getExternalStorageDirectory()+"/SongRecorder/song.3gp";
    }

    public void Start(){
        recorder=new MediaRecorder();
        recorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        recorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        recorder.setOutputFile(songName);
        recorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
        try{
            recorder.prepare();
        }
        catch (Exception e)
        {
            Log.e("AudioRecord", "You cannot record");
        }
        recorder.start();
    }

    public void Stop(){
        recorder.stop();
        recorder.release();
        recorder=null;
    }
}
