package com.example.naiki;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

public class Item_Details extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item__details);

        Intent i = getIntent();
        if (i != null)
        {
            String name = i.getStringExtra( "name");
            String detail = i.getStringExtra( "item_detail");
            String cat = i.getStringExtra( "cat");
            String qut = i.getStringExtra( "quantity");
            String phone = i.getStringExtra( "phone");
            String image = i.getStringExtra( "image");

            TextView t1 = findViewById(R.id.textView25);
            t1.setText(name);

            TextView t2 = findViewById(R.id.textView26);
            t2.setText(detail);

            TextView t3 = findViewById(R.id.textView27);
            t3.setText(cat);

            TextView t4 = findViewById(R.id.textView28);
            t4.setText(qut);

            TextView t5 = findViewById(R.id.textView29);
            t5.setText(phone);

            ImageView im = findViewById(R.id.imageView4);

            Glide.with(getApplicationContext()).load(image).into(im);

        }
        else
        {
        }
    }
}