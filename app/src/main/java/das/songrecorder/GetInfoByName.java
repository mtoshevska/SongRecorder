package das.songrecorder;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;

import java.util.Random;

/**
 * Created by Toni on 30.12.2016.
 */

public class GetInfoByName implements GetInfo {

    protected Song song;
    protected Activity activity;

    public GetInfoByName(Activity a){
        activity=a;
    }

    @Override
    public Song getDataFromDatabase(Song f) {
        song=f;
        /*int num=new Random().nextInt(20);
        f.setAuthor("Author"+num);
        f.setArtist("Artist"+num);
        f.setGenre("Genre"+num);
        f.setYear(2017);*/
        new GetData().execute(f.getName());
        Log.d("GetInfoByName","continued");
        return f;
    }

    private class GetData extends AsyncTask<String,Void,Void>{

        //private Activity activity;
        private ProgressDialog dialog;

        public GetData(){
            //activity=a;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog=new ProgressDialog(activity);
            dialog.setIndeterminate(true);
            dialog.setTitle("Getting data");
            dialog.setMessage("Getting data...please wait");
            dialog.show();
        }

        @Override
        protected Void doInBackground(String... params) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Log.d("GetInfoByName",params[0]);
            int num=new Random().nextInt(20);
            song.setAuthor("Author"+num);
            song.setArtist("Artist"+num);
            song.setGenre("Genre"+num);
            song.setYear(2017);
            Intent intent = new Intent();
            intent.setAction("SongFilledWithInformation");
            intent.putExtra("Song",song);
            activity.getApplicationContext().sendBroadcast(intent);
            Log.d("GetInfoByName","Broadcast sent");
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            dialog.dismiss();
        }
    }
}
