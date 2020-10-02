package com.example.datastorage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class sharedPreference extends AppCompatActivity {

String FileName = "myFile";
Button buttonSaveName;
Button buttonReadName;
EditText editName;
TextView readName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shared_preference);

        editName = findViewById(R.id.etName);
        readName = findViewById(R.id.tvReadTxt);
        buttonSaveName =findViewById(R.id.buSaveFile);
        buttonReadName =findViewById(R.id.buReadFile);


        buttonSaveName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveFile();
            }
        });

        buttonReadName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                readFile();
            }
        });
    }

    private void readFile(){
        SharedPreferences sharedPreferences = getSharedPreferences(FileName, Context.MODE_PRIVATE);
        String defaultValue = "DefaultValue";
        String name = sharedPreferences.getString("name",defaultValue);
        readName.setText(name);
        Toast.makeText(this, "Data: "+name, Toast.LENGTH_LONG).show();
    }

    private void saveFile(){
        String strName = editName.getText().toString();
        SharedPreferences sharedPreferences = getSharedPreferences(FileName,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("name",strName);
        editor.commit();
        Toast.makeText(this, "Data saved succesfully", Toast.LENGTH_SHORT).show();
    }
}