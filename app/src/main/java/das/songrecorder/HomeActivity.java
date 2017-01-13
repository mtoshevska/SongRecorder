package das.songrecorder;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.File;

public class HomeActivity extends AppCompatActivity {

    /**
     * This class is used to show the HomeScreen (Start recording, View files, Exit).
     */

    Button startButton;
    Button viewFiles;
    int permissionsNum;
    static final int APP_REQUEST_CODE=4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        permissionsNum=0;
        String location=Environment.getExternalStorageDirectory()+"/SongRecorder/";
        File directory=new File(location);
        if(!directory.exists()){
            directory.mkdirs();
        }
        startButton=(Button)this.findViewById(R.id.start);
        viewFiles=(Button)this.findViewById(R.id.view);
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            checkPermissions();
        }
        else {
            setListeners();
        }

        Button exit=(Button)this.findViewById(R.id.exit);
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_MAIN);
                intent.addCategory(Intent.CATEGORY_HOME);
                startActivity(intent);
            }
        });
    }

    private void setListeners(){
        viewFiles.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplication(), ViewFilesActivity.class));
            }
        });
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), RecordActivity.class));
            }
        });
    }

    private void checkPermissions(){
        permissionsNum=0;

        int recordAudio=ContextCompat.checkSelfPermission(HomeActivity.this,Manifest.permission.RECORD_AUDIO);
        if(recordAudio==PackageManager.PERMISSION_GRANTED){
            permissionsNum++;
        }

        int writeExternalStorage=ContextCompat.checkSelfPermission(HomeActivity.this,Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if(writeExternalStorage==PackageManager.PERMISSION_GRANTED){
            permissionsNum++;
        }

        if(permissionsNum==2){
            setListeners();
        }
        else{
            ActivityCompat.requestPermissions(HomeActivity.this,new String[]{Manifest.permission.RECORD_AUDIO,Manifest.permission.WRITE_EXTERNAL_STORAGE},APP_REQUEST_CODE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode){
            case APP_REQUEST_CODE:{
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    setListeners();
                }
            }
            default:
        }
    }
}
