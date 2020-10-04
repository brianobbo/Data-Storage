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
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class InternalStorage extends AppCompatActivity {

    String FileNameInternal = "myFile";
    Button buttonSaveNameInternal;
    Button buttonReadNameInternal;
    EditText editNameInternal;
    TextView readNameInternal;
    String name;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_internal_storage);

        editNameInternal = findViewById(R.id.etNameInternal);
        readNameInternal = findViewById(R.id.tvReadTxtInternal);
        buttonSaveNameInternal =findViewById(R.id.buSaveFileInternal);
        buttonReadNameInternal =findViewById(R.id.buReadFileInternal);

        buttonSaveNameInternal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveFileInternal();
            }
        });

        buttonReadNameInternal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                readFileInternal();
            }
        });

    }

    //saving file to internal
    private void saveFileInternal(){
        try {
            FileOutputStream fileOutputStream = openFileOutput(FileNameInternal, Context.MODE_PRIVATE);
            name = editNameInternal.getText().toString();
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

            FileInputStream fileInputStream = openFileInput(FileNameInternal);
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            StringBuilder stringBuilder = new StringBuilder();
            String line = null;
            while ((line = bufferedReader.readLine())!=null){
                stringBuilder.append(line);
            }
            fileInputStream.close();
            inputStreamReader.close();
            readNameInternal.setText(stringBuilder.toString());
            Toast.makeText(this, "Data retrieved from Internal: "+stringBuilder.toString(), Toast.LENGTH_SHORT).show();

        }catch (Exception e){
            e.printStackTrace();
        }
    }

}