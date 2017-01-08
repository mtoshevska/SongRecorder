package das.songrecorder;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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

            String connectionUrl="jdbc:jtds:sqlserver://songrecorder.database.windows.net:1433/Songs;user=dasproject@songrecorder;password=power!Tgirls;encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;";

            // Declare the JDBC objects.
            Connection con = null;
            PreparedStatement stmt = null;
            ResultSet rs = null;
            try
            {
                // Establish the connection.
                Class.forName("net.sourceforge.jtds.jdbc.Driver").newInstance();
                con = DriverManager.getConnection(connectionUrl);
                Log.d("GetInfoByName","connected");

                // Create and execute an SQL statement that returns some data.
                stmt = con.prepareStatement("SELECT * FROM SongsInfo WHERE Name='"+params[0]+"'");
                Log.d("GetInfoByName","statement created");
                rs = stmt.executeQuery();
                Log.d("GetInfoByName","statement executed");

                // Iterate through the data in the result set and display it.
                while (rs.next()) {
                    Log.d("GetInfoByName",rs.getString("Name")+" "+rs.getString("Author")+" "+rs.getString("Artist")+" "+rs.getString("Genre")+""+rs.getInt("Year"));
                    song.setAuthor(rs.getString("Author"));
                    song.setArtist(rs.getString("Artist"));
                    song.setGenre(rs.getString("Genre"));
                    song.setYear(rs.getInt("Year"));
                }
            }
            // Handle any errors that may have occurred.
            catch(Exception e)
            {
                e.printStackTrace();
            }
            finally
            {
                if (rs != null) try {
                    rs.close();
                } catch (Exception e) {
                }
                if (stmt != null) try {
                    stmt.close();
                } catch (Exception e) {
                }
                if (con != null) try {
                    con.close();
                } catch (Exception e) {
                }
            }

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
