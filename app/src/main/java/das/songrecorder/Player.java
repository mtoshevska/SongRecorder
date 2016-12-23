package das.songrecorder;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import java.io.File;

/**
 * Created by Toni on 23.12.2016.
 */

public class Player {
    private static Player instance;

    private Player(){

    }

    public static Player getInstance(){
        synchronized (Player.class){
            if(instance==null){
                instance=new Player();
            }
        }
        return instance;
    }

    public void playSong(File song, Context context){
        Intent intent=new Intent(Intent.ACTION_VIEW);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setDataAndType(Uri.fromFile(song),"audio/*");
        if (intent.resolveActivity(context.getPackageManager()) != null) {
            context.startActivity(intent);
        }
    }
}
