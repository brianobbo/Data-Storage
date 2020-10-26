package com.example.datastorage.SQL;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.datastorage.R;

public class SQLiteInsertReadUpdateDelete extends AppCompatActivity {

EditText etName, etSurName, etmarks;
Button buInsert;
TextView tvRead;
Button buRead;

DataBaseHelper myDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sqlite_insert_read_update_delete);

        etName = findViewById(R.id.etName);
        etSurName = findViewById(R.id.etSurName);
        etmarks = findViewById(R.id.etMarks);
        buInsert = findViewById(R.id.buInsert);
        tvRead = findViewById(R.id.tvRead);
        buRead = findViewById(R.id.buRead);

        myDb = new DataBaseHelper(this);

        //button insert
        buInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertDataSQL();
            }
        });

        //buttonRead
        buRead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                readData();
            }
        });

    }


    //Insert data
    private void insertDataSQL(){
        String name = etName.getText().toString();
        String surName = etSurName.getText().toString();
        String marks = etmarks.getText().toString();

        //use the object of DataBaseHelper and call the method of inserting data
        Boolean results = myDb.insertData(name,surName,marks);
        if (results == true){
            Toast.makeText(this, "Data Inserted successfully", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(this, "Failed", Toast.LENGTH_SHORT).show();
        }
    }


    //read data
    private void readData(){
        //get result by moving cursor to the next row and appending the result in StringBuffer
        //pass param of  columnIndex to  in getString method to get result from result set .
        Cursor res  = myDb.getAllData();
        StringBuffer stringBuffer = new StringBuffer();
        if (res!=null && res.getCount()>0){
            while (res.moveToNext()){
                stringBuffer.append("Id: "+res.getString(0) +"\n");
                stringBuffer.append("Name: "+res.getString(1) +"\n");
                stringBuffer.append("Surname: "+res.getString(2) +"\n");
                stringBuffer.append("Marks: "+res.getString(3) +"\n"+"\n");
            }
            tvRead.setText(stringBuffer.toString());
            Toast.makeText(this, "Data retrieved succesfully", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(this, "failed", Toast.LENGTH_SHORT).show();
        }
    }
}