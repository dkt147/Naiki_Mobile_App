package com.example.naiki;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.dhaval2404.imagepicker.ImagePicker;

public class donate extends Fragment {


    public donate() {
        // Required empty public constructor
    }

// Initializing params
    ImageView im;
    Button bt1;
    TextView t1 , t2;
    EditText ed1 , ed2 , ed3;

    String item_name , note, quantity , r_id;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_donate, container, false);

        im = view.findViewById(R.id.imageView2);
        t2 = view.findViewById(R.id.textView20);
        bt1 = view.findViewById(R.id.image_up);

        ed1 = view.findViewById(R.id.item_name_id);
        ed2 = view.findViewById(R.id.quantity_id);
        ed3 = view.findViewById(R.id.description_id);



//         onclick listeneer on button to take picture from gallery or camera ,  then we are using onActivity result to get data of image
        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImagePicker.Companion.with(donate.this)
                .crop()	    			//Crop image(Optional), Check Customization for more option
//                .compress(1024)			//Final image size will be less than 1 MB(Optional)
//                .maxResultSize(1080, 1080)	//Final image resolution will be less than 1080 x 1080(Optional)
                        .start();
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


    public void donate_btn(View view){
        item_name = ed1.getText().toString();
        quantity = ed2.getText().toString();
        note = ed3.getText().toString();

        Background_Worker bgworker = new Background_Worker(getContext());
        bgworker.execute("donate" , item_name , quantity , note);
    }


}



