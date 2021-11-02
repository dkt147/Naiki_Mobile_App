package com.example.naiki;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;

public class write_story extends AppCompatActivity {

    TextInputEditText t1;
    Button b1;

    SharedPreferences sharedPreferences;
    String rid, uname;
    String story;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write_story);

        b1 = findViewById(R.id.button3);
        t1 = findViewById(R.id.story_id);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                story = t1.getText().toString();

                sharedPreferences = getSharedPreferences("userr" , Context.MODE_PRIVATE);

                if(sharedPreferences.contains("rid") && sharedPreferences.contains("uphone"))
                {
                    rid = sharedPreferences.getString("rid", "0");
                    uname = sharedPreferences.getString("uname", "0");

                }

                Background_Worker background_worker = new Background_Worker(getApplication());
                background_worker.execute("story", rid , uname , story );


            }
        });
    }
}