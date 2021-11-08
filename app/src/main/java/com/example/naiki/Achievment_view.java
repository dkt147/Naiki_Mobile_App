package com.example.naiki;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

public class Achievment_view extends AppCompatActivity {

    TextView t51,t52,t53, t54,t55;
    ImageView im9;
    Button b1,b2,b3;
    String name, detail, cat, qut, d_id,image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_achievment_view);

        t51 = findViewById(R.id.textView51);
        t52 = findViewById(R.id.textView52);
        t53 = findViewById(R.id.textView53);
        t54 = findViewById(R.id.textView14);
        t55 = findViewById(R.id.textView48);

        im9 = findViewById(R.id.imageView9);

        b1 = findViewById(R.id.button9);
        b2 = findViewById(R.id.button10);
        b3 = findViewById(R.id.button8);



        Intent i = getIntent();
        if (i != null)
        {
            name = i.getStringExtra( "name");
            detail = i.getStringExtra( "item_detail");
            cat = i.getStringExtra( "cat");
            qut = i.getStringExtra( "quantity");
            d_id = i.getStringExtra( "d_id");
            image = i.getStringExtra( "image");


            t51.setText(name);
            t52.setText(cat);
            t53.setText(qut);
            t54.setText(detail);
            t55.setText(d_id);

            Glide.with(getApplicationContext()).load(image).into(im9);

        }
        else
        {
            Toast.makeText(this, "No record", Toast.LENGTH_SHORT).show();
        }

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();

                Intent intent = new Intent(getApplicationContext(), my_edit.class);
                intent.putExtra("name" , name);
                intent.putExtra("item_detail" , detail);
                intent.putExtra("cat" , cat);
                intent.putExtra("quantity" , qut);
                intent.putExtra("image" , image);
                intent.putExtra("d_id" , d_id);


                startActivity(intent);
            }
        });

//        b1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(getApplicationContext(), my_edit.class);
//                startActivity(intent);
//
//            }
//        });
//
//        b1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(getApplicationContext(), my_edit.class);
//                startActivity(intent);
//
//            }
//        });
    }

}