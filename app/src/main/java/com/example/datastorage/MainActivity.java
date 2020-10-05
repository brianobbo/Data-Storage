package com.example.datastorage;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.solver.Cache;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class MainActivity extends AppCompatActivity {
    Button SharedPref;
    Button Internal;
    Button External;
    Button ExtPublic;
    Button Cache;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPref = findViewById(R.id.buSharedPreference);
        Internal = findViewById(R.id.buInternalStorage);
        External = findViewById(R.id.buExternalStorage);
        ExtPublic = findViewById(R.id.buExternalStoragePublicDirectory);
        Cache = findViewById(R.id.buCacheStorage);

        SharedPref.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),sharedPreference.class);
                startActivity(intent);
            }
        });

        Internal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),InternalStorage.class);
                startActivity(intent);
            }
        });

        External.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),ExternalStorage.class);
                startActivity(intent);
            }
        });

    }
}