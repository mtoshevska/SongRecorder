package das.songrecorder;

import android.media.MediaRecorder;
import android.os.Environment;

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

    /**
     * Starts recording the song.
     */
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
            e.printStackTrace();
        }
        recorder.start();
    }

    /**
     * Stops recording the song.
     * @return Recorded song
     */
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

    /**
     * Pauses recording the song.
     */
    public void Pause(){
        if(recorder!=null) {
            recorder.stop();
            recorder.release();
            recorder = null;
        }
    }
}
