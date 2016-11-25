package das.songrecorder;

import android.media.MediaRecorder;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Toni on 25.11.2016.
 */

public class Recorder {

    private MediaRecorder recorder;
    private String songName;
    private ArrayList<String>tmpFiles;
    private int counter;

    public Recorder() {
        songName=Environment.getExternalStorageDirectory()+"/SongRecorder/song.3gp";
        tmpFiles=new ArrayList<String>();
        counter=0;
    }

    public void Start(){
        Continue();
    }

    public void Stop() {
        if(recorder!=null) {
            recorder.stop();
            recorder.release();
            recorder = null;
        }
        if(!tmpFiles.isEmpty()) {
            createOneFile();
        }
    }

    public void Continue(){
        recorder=new MediaRecorder();
        String tmpName=Environment.getExternalStorageDirectory()+"/SongRecorder/tmp"+counter+".3gp";
        tmpFiles.add(tmpName);
        counter++;
        recorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        recorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        recorder.setOutputFile(tmpName);
        recorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_WB);
        try{
            recorder.prepare();
        }
        catch (Exception e)
        {
            Log.e("AudioRecord", "You cannot record");
        }
        recorder.start();
    }

    public void Pause(){
        recorder.stop();
        recorder.release();
        recorder=null;
    }

    public void createOneFile() {

    }
}
