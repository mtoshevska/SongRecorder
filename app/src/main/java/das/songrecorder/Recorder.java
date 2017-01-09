package das.songrecorder;

import android.media.MediaRecorder;
import android.os.Environment;
import android.util.Log;
import java.util.ArrayList;

/**
 * Created by Toni on 25.11.2016.
 */

public class Recorder {

    private MediaRecorder recorder;
    private static String songName=Environment.getExternalStorageDirectory()+"/SongRecorder/song.3gp";
    private ArrayList<String>tmpFiles;
    private int counter;
    private static Recorder instance;

    /**
     * Private constructor for the class.
     */
    private Recorder(){}

    /**
     * Creates and returns instance if is null. Otherwise just returns it.
     * @return unique instance of the class
     */
    public static Recorder getInstance(){
        synchronized (Recorder.class){
            if(instance==null){
                instance=new Recorder();
            }
        }
        return instance;
    }

    public void Start(){
        //Continue();
        recorder=new MediaRecorder();
        recorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        recorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        recorder.setOutputFile(songName);
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

    public Song Stop() {
        if(recorder!=null) {
            recorder.stop();
            recorder.release();
            recorder = null;
        }
        Song song=new Song();
        song.setLocation(songName);
        //return new File(songName);
        return song;
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
        if(recorder!=null) {
            recorder.stop();
            recorder.release();
            recorder = null;
        }
    }
}
