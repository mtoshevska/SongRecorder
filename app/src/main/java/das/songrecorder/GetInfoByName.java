package das.songrecorder;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class GetInfoByName implements GetInfo {

    protected Song song;
    protected Activity activity;

    /**
     * Constructor for class that gets information for the song which is going to be saved locally on phone.
     * @param a
     */
    public GetInfoByName(Activity a){
        activity=a;
    }

    @Override
    public void getDataFromDatabase(Song f) {
        song=f;
        new GetData().execute(f.getName());
    }

    private class GetData extends AsyncTask<String,Void,Void>{

        private ProgressDialog dialog;

        /**
         * Constructor for the class that pulls information for the song from the database.
         */
        public GetData(){}

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
            String connectionUrl="jdbc:jtds:sqlserver://songrecorder.database.windows.net:1433/Songs;user=dasproject@songrecorder;password=power!Tgirls;encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;";
            Connection con = null;
            PreparedStatement stmt = null;
            ResultSet rs = null;
            boolean foundInfo=true;
            boolean databaseError=false;
            try {
                Class.forName("net.sourceforge.jtds.jdbc.Driver").newInstance();
                con = DriverManager.getConnection(connectionUrl);
                stmt = con.prepareStatement("SELECT * FROM SongsInfo WHERE Name='" + params[0] + "'");
                rs = stmt.executeQuery();
                if (rs.next()) {
                    String author = rs.getString("Author");
                    author = author.replaceAll(";", ",");
                    song.setAuthor(author);
                    String artist = rs.getString("Artist");
                    artist = artist.replaceAll(";", ",");
                    song.setArtist(artist);
                    song.setGenre(rs.getString("Genre"));
                    song.setYear(rs.getInt("Year"));
                } else {
                    foundInfo = false;
                    song.setArtist("unknown");
                    song.setAuthor("unknown");
                    song.setGenre("unknown");
                    song.setYear(0);
                }
            }
            catch(Exception e){
                databaseError=true;
                e.printStackTrace();
            }
            finally{
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
            intent.putExtra("FoundInfo",foundInfo);
            intent.putExtra("DatabaseError",databaseError);
            activity.getApplicationContext().sendBroadcast(intent);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            dialog.dismiss();
        }
    }
}
