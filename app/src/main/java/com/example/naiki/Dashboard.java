package com.example.naiki;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

public class Dashboard extends AppCompatActivity {

    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        Intent intent = new Intent(this, Naviagtion.class);
        startActivity(intent);
        sharedPreferences = getSharedPreferences("user", Context.MODE_PRIVATE);
    }


    public void onBackPressed()
    {

        if(!(sharedPreferences.contains("uphone") && sharedPreferences.contains("upass")))
        {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
    }
}