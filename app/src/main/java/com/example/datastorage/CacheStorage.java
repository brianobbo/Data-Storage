package com.example.datastorage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class CacheStorage extends AppCompatActivity {

    String FileNameCache = "myFileCache";
    Button buttonSaveNameCache;
    Button buttonReadNameCache;
    EditText editNameCache;
    TextView readNameCache;
    String name;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cache_storage);

        editNameCache = findViewById(R.id.etNameCache);
        readNameCache = findViewById(R.id.tvReadTxtCache);
        buttonSaveNameCache =findViewById(R.id.buSaveFileCache);
        buttonReadNameCache =findViewById(R.id.buReadFileCache);

        buttonSaveNameCache.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveFileCache();
            }
        });

        buttonReadNameCache.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                readFileCache();
            }
        });

    }

    //saving file to internal
    private void saveFileCache(){
        try {
            //append data to the file
            File file = new File(getCacheDir(), FileNameCache);
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            name = editNameCache.getText().toString();
            fileOutputStream.write(name.getBytes());
            fileOutputStream.close();
            Toast.makeText(this, "Data saved to Cache", Toast.LENGTH_SHORT).show();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    //reading file from internal
    private  void readFileCache(){
        try {

            File file = new File(getCacheDir(), FileNameCache);
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
            readNameCache.setText(stringBuilder.toString());
            Toast.makeText(this, "Data retrieved from Internal: "+stringBuilder.toString(), Toast.LENGTH_SHORT).show();

        }catch (Exception e){
            e.printStackTrace();
        }
    }

}