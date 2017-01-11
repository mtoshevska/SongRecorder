package das.songrecorder;

import android.media.MediaRecorder;
import android.os.Environment;
import android.util.Log;

public class Recorder {

    private MediaRecorder recorder;
    private static String songName=Environment.getExternalStorageDirectory()+"/SongRecorder/song.3gp";
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
        recorder=new MediaRecorder();
        recorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        recorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        recorder.setOutputFile(songName);
        recorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_WB);
        try{
            recorder.prepare();
        }
        catch (Exception e){
            Log.e("AudioRecord", "You cannot record");
            e.printStackTrace();
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
        return song;
    }

    public void Pause(){
        if(recorder!=null) {
            recorder.stop();
            recorder.release();
            recorder = null;
        }
    }
}
