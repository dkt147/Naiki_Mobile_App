package com.example.naiki;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.github.dhaval2404.imagepicker.ImagePicker;
import com.google.android.material.textfield.TextInputEditText;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class donate extends Fragment implements AdapterView.OnItemSelectedListener {

    private Bitmap bmp;

    public donate() {
        // Required empty public constructor
    }

// Initializing params
    ImageView im;
    Button bt1 , bt2 , bt3;
    TextView t1 , t2, t3;
    TextInputEditText ed1 , ed2 , ed3;
    Spinner spinner;

    String item_name;
    String note;
    String quantity;
    String r_id;
    String category;
    Bitmap bitmap;
    String image_path;
    Uri uri;
    String type;



    SharedPreferences sharedPreferences;

    ArrayList<String> spinner_data = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_donate, container, false);

        im = view.findViewById(R.id.imageView2);
        t2 = view.findViewById(R.id.textView20);
        bt2 = view.findViewById(R.id.button2);
        bt1 = view.findViewById(R.id.request_btn);
        bt3 = view.findViewById(R.id.online);

        ed1 = view.findViewById(R.id.item_name);
        ed2 = view.findViewById(R.id.quantity);
        ed3 = view.findViewById(R.id.description_id);

        t3 = view.findViewById(R.id.textView22);
        spinner = view.findViewById(R.id.spinner);

// Array adapter for spinner to shoe list
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource ( getContext(), R.array.category , android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);



        sharedPreferences = getContext().getSharedPreferences("userr" , Context.MODE_PRIVATE);

        if(sharedPreferences.contains("rid") && sharedPreferences.contains("uphone"))
        {
            r_id = sharedPreferences.getString("rid", "0");
            type = sharedPreferences.getString("utype", "0");

        }


        im.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImagePicker.with(donate.this)
//                        .crop()	    			//Crop image(Optional), Check Customization for more option
//                        .compress(1024)			//Final image size will be less than 1 MB(Optional)
//                        .maxResultSize(1080, 1080)	//Final image resolution will be less than 1080 x 1080(Optional)
                        .start();



            }
        });



//         onclick listeneer on button to take picture from gallery or camera ,  then we are using onActivity result to get data of image

//        Donate button click functionas
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

                    if (uri == null)
                    {
                        Toast.makeText(getContext(), "Please upoad Image", Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        image_path = encodedbitmap(bitmap);
                        Background_Worker bgworker = new Background_Worker(getContext());
                        bgworker.execute("donate" ,r_id, item_name , quantity , note, category, image_path);
                    }




                }
            }
        });

//        Request Button click
        bt1.setOnClickListener(new View.OnClickListener() {
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


                    if (uri == null)
                    {
                        Toast.makeText(getContext(), "Please upoad Image", Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        image_path = encodedbitmap(bitmap);
                        Background_Worker bgworker = new Background_Worker(getContext());
                        bgworker.execute("request" ,r_id, item_name , quantity , note, category, image_path);
                    }

                }
            }
        });
        return view;


    }


    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        uri = data.getData();
        if (uri == null)
        {
            Toast.makeText(getContext(), "Please upoad Image", Toast.LENGTH_SHORT).show();
        }
        else
        {
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), uri);
                im.setImageBitmap(bitmap);
//        t2.setText(uri1.toString());

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }

    public String encodedbitmap(Bitmap bitmap)
    {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,100, byteArrayOutputStream);

        byte[] byteofimage = byteArrayOutputStream.toByteArray();
        image_path = android.util.Base64.encodeToString(byteofimage , Base64.DEFAULT);
//        t2.setText(image_path);
        return image_path;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}



