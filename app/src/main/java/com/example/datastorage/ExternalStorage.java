package com.example.datastorage;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;

public class ExternalStorage extends AppCompatActivity {

    public static final int PERMISION_WRITE_EXTERNAL_STORAGE = 123;
    String FileNameExternal = "myFile";
    Button buttonSaveNameFileNameExternal;
    Button buttonReadNameFileNameExternal;
    EditText editNameFileNameExternal;
    TextView readNameFileNameExternal;
    String name;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_external_storage);

        editNameFileNameExternal = findViewById(R.id.etNameExternal);
        readNameFileNameExternal = findViewById(R.id.tvReadTxtExternal);
        buttonSaveNameFileNameExternal =findViewById(R.id.buSaveFileExternal);
        buttonReadNameFileNameExternal =findViewById(R.id.buReadFileExternal);

        buttonSaveNameFileNameExternal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //check if permision is granted to save to external
                int permisionCheck = ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE);
                if (permisionCheck == PackageManager.PERMISSION_GRANTED){
                    saveFileExternal();
                }else {                                     //this is activity
                    ActivityCompat.requestPermissions(ExternalStorage.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, PERMISION_WRITE_EXTERNAL_STORAGE);
                }
            }
        });

        buttonReadNameFileNameExternal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                readFileInternal();
            }
        });

    }

    //permision
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode){
            case  PERMISION_WRITE_EXTERNAL_STORAGE:
                //permision to read External Storage
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    saveFileExternal();
                }else{
                    Toast.makeText(this, "please grant permission to save File", Toast.LENGTH_SHORT).show();
                }return;
        }
    }


    //saving file to internal
    private void saveFileExternal(){
        try {

            //to save file n the directory called  myData in the rooot
            /**File myDir = new File(Environment.getExternalStorageDirectory() + "MyData");
            myDir.mkdirs();
            File file = new File(myDir, FileNameExternal); **/

            //To save file directly in the root
            File file = new File(Environment.getExternalStorageDirectory(), FileNameExternal);

            //below code the same as internal storage
            //FileOutputStream fileOutputStream = openFileOutput(FileNameExternal, Context.MODE_PRIVATE);
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            name = editNameFileNameExternal.getText().toString();
            fileOutputStream.write(name.getBytes());
            fileOutputStream.close();
            Toast.makeText(this, "Data saved to internal", Toast.LENGTH_SHORT).show();
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    //reading file from internal
    private  void readFileInternal(){
        try {

            //to save file n the directory called  myData in the rooot
            /**File myDir = new File(Environment.getExternalStorageDirectory() + "MyData");
             myDir.mkdirs();
             File file = new File(myDir, FileNameExternal); **/

            //To save file directly in the root
            File file = new File(Environment.getExternalStorageDirectory(), FileNameExternal);

            //below code the same as internal storage
            //FileInputStream fileInputStream = openFileInput(FileNameExternal);
            FileInputStream fileInputStream = new FileInputStream(file);
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            StringBuilder stringBuilder = new StringBuilder();
            String line = null;
            while ((line = bufferedReader.readLine())!=null){
                stringBuilder.append(line);
            }
            fileInputStream.close();
            inputStreamReader.close();
            readNameFileNameExternal.setText(stringBuilder.toString());
            Toast.makeText(this, "Data retrieved from Internal: "+stringBuilder.toString(), Toast.LENGTH_SHORT).show();

        }catch (Exception e){
            e.printStackTrace();
        }
    }



}