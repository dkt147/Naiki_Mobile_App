package com.example.naiki;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.material.textfield.TextInputEditText;

public class my_edit extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    TextInputEditText t1,t2,t3,t4;
    Spinner sp;
    Button b1;
    String d_id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_edit);

        t1 = findViewById(R.id.item_edit_name);
        sp = findViewById(R.id.spinner2);
        t3 = findViewById(R.id.item_quantity_edit);
        t4 = findViewById(R.id.item_details_edit);
        b1 = findViewById(R.id.button11);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource ( getApplicationContext(), R.array.category , android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp.setAdapter(adapter);
        sp.setOnItemSelectedListener(this);

        Intent i = getIntent();
        if (i != null)
        {


            String name = i.getStringExtra( "name");
            String detail = i.getStringExtra( "item_detail");
            String cat = i.getStringExtra( "cat");
            String qut = i.getStringExtra( "quantity");
            d_id = i.getStringExtra( "d_id");
            String image = i.getStringExtra( "image");


            t1.setText(name);
            t3.setText(qut);
            t4.setText(detail);


        }
        else
        {
            Toast.makeText(this, "No record", Toast.LENGTH_SHORT).show();
        }

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(t1.length()==0)
                {
                    t1.requestFocus();
                    t1.setError("FIELD CANNOT BE EMPTY");
                }

                else if(t3.length()==0)
                {
                    t3.requestFocus();
                    t3.setError("FIELD CANNOT BE EMPTY");
                }

                else if(t3.length()==0)
                {
                    t4.requestFocus();
                    t4.setError("FIELD CANNOT BE EMPTY");
                }


                else if (t1.length()!=0 && t3.length()!=0 && t4.length()!=0) {

                    String name1 = t1.getText().toString();
                    String cat1 = sp.getSelectedItem().toString();
                    String note1 = t4.getText().toString();
                    String qut1 = t3.getText().toString();


                        Background_Worker bgworker = new Background_Worker(getApplication());
                        bgworker.execute("update" ,d_id, name1, cat1 , qut1, note1);




                }
            }
        });


    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}