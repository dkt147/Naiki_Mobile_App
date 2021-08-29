package com.example.naiki;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.github.dhaval2404.imagepicker.ImagePicker;

import java.util.ArrayList;

public class donate extends Fragment implements AdapterView.OnItemSelectedListener {


    public donate() {
        // Required empty public constructor
    }

// Initializing params
    ImageView im;
    Button bt1 , bt2;
    TextView t1 , t2, t3;
    EditText ed1 , ed2 , ed3;
    Spinner spinner;

    String item_name;
    String note;
    String quantity;
    String r_id;
    String category;


    SharedPreferences sharedPreferences;

    ArrayList<String> spinner_data = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_donate, container, false);

        im = view.findViewById(R.id.imageView2);
        t2 = view.findViewById(R.id.textView20);
        bt1 = view.findViewById(R.id.image_up);
        bt2 = view.findViewById(R.id.button2);

        ed1 = view.findViewById(R.id.item_name);
        ed2 = view.findViewById(R.id.quantity);
        ed3 = view.findViewById(R.id.description_id);

        t3 = view.findViewById(R.id.textView22);
        spinner = view.findViewById(R.id.spinner);


        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource ( getContext(), R.array.category , android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);




        sharedPreferences = getContext().getSharedPreferences("userr" , Context.MODE_PRIVATE);

        if(sharedPreferences.contains("rid") && sharedPreferences.contains("uphone"))
        {
            r_id = sharedPreferences.getString("rid", "0");
            t3.setText(r_id);


        }



//         onclick listeneer on button to take picture from gallery or camera ,  then we are using onActivity result to get data of image

        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ed1.length()==0)
                {
                    ed1.requestFocus();
                    ed1.setError("FIELD CANNOT BE EMPTY");
                }

                else if(ed2.length()==0)
                {
                    ed2.requestFocus();
                    ed2.setError("FIELD CANNOT BE EMPTY");
                }

                else if(ed3.length()==0)
                {
                    ed3.requestFocus();
                    ed3.setError("FIELD CANNOT BE EMPTY");
                }

                else if (ed1.length()!=0 && ed2.length()!=0 && ed3.length()!=0) {

                    item_name = ed1.getText().toString();
                    quantity = ed2.getText().toString();
                    note = ed3.getText().toString();
                    category = spinner.getSelectedItem().toString();

                    Background_Worker bgworker = new Background_Worker(getContext());
                    bgworker.execute("donate" ,r_id, item_name , quantity , note, category);


                }
            }
        });
        return view;


    }

// get data of image
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        Uri uri = data.getData();

        im.setImageURI(uri);
        t2.setText(uri.toString());
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}



