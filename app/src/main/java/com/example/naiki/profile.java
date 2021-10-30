package com.example.naiki;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;


public class profile extends Fragment {


//    Inititializing Labels and textboxes
    TextView t19, t22, t24 , t20, t16;
    ImageView pf;

    TextView lg;
    SharedPreferences sharedPreferences;

    String r_id;

    AlertDialog alertDialog;
    ImageView pr;


//    Fragment Constructor
    public profile() {

    }
    

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

//        sharedPreferences gettting values
                sharedPreferences = getContext().getSharedPreferences("userr" , Context.MODE_PRIVATE);

        if(sharedPreferences.contains("rid") && sharedPreferences.contains("uphone"))
        {
            r_id = sharedPreferences.getString("rid", "0");

        }
//        Initializing
        t19 = view.findViewById(R.id.textView19);
        t20 = view.findViewById(R.id.textView20);
        t22 = view.findViewById(R.id.textView22);
        t24 = view.findViewById(R.id.textView24);
        t16 = view.findViewById(R.id.textView16);
        pf = view.findViewById(R.id.profile_image);
        lg = view.findViewById(R.id.logout);

        lg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.remove("rid");
                editor.remove("uphone");
                editor.clear();
                editor.commit();
                Intent intent5 = new Intent(getContext(),MainActivity.class);
                startActivity(intent5);

            }
        });

//        Calling Function
        getProfile();


        return view;
    }


    public void getProfile(){


        class bgWorker extends AsyncTask<String, Void, String> {


            @Override
            protected String doInBackground(String... strings) {

//              rid is the vallue we get from sharedpreferences
                String rid = strings[1];
                String type = strings[0];

// working for fetch profile from database for which we are using rid to sent this id to mysql api where we get data
                if (type.equals("profile")) {

                    try {
//                        API link
                        String fetch_url = "http://lms-php.000webhostapp.com/naiki/profile.php";
                        URL url = new URL(fetch_url);
                        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                        httpURLConnection.setRequestMethod("POST");
                        httpURLConnection.setDoOutput(true);
                        httpURLConnection.setDoInput(true);
                        OutputStream outputStream = httpURLConnection.getOutputStream();
                        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                        String post_data = URLEncoder.encode("rid", "UTF-8") + "=" + URLEncoder.encode(rid, "UTF-8");
                        bufferedWriter.write(post_data);
                        bufferedWriter.flush();
                        bufferedWriter.close();
                        outputStream.close();
                        InputStream inputStream = httpURLConnection.getInputStream();
                        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
                        String result = "";
                        String line = "";
                        while ((line = bufferedReader.readLine()) != null) {
                            result += line;
                        }
                        bufferedReader.close();
                        inputStream.close();
                        httpURLConnection.disconnect();
                        return result;
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                return null;
            }


            @Override
            protected void onPreExecute()
            {

            }

            @Override
            protected void onPostExecute(String s) {
                if (s != null) {

                    if (s.equals("Failed")) {
                        alertDialog.setMessage("Details not found");
                        alertDialog.show();
                    }
                    else {
//                        Getting data in JSON Array from PHP MYSQL
                        try
                        {
                            JSONArray jsonArray = new JSONArray(s);
                            JSONObject jsonObject = null;

                            String image = "";

                            for (int i=0; i<jsonArray.length(); i++)
                            {
                                jsonObject = jsonArray.getJSONObject(i);

                                String name = jsonObject.getString("uname");
                                String uphone = jsonObject.getString("uphone");
                                String uaddress = jsonObject.getString("uaddress");
                                String uemail = jsonObject.getString("uemail");
                                String url = "http://lms-php.000webhostapp.com/naiki/profiles/";
                                image = url + jsonObject.getString("profile_image");


//  Setting values again to textboxes
                                t19.setText(name);
                                t20.setText(uphone);
                                t22.setText(uaddress);
                                t24.setText(uemail);
                                t16.setText(name);

                                Glide.with(getContext()).load(image).into(pf);


                            }

//                            pf.setImageURI(Uri.parse(image));
//                            byte[] bytes = Base64.decode(image,Base64.DEFAULT);
//                            Bitmap bitmap = BitmapFactory.decodeByteArray(bytes,0, bytes.length);
//                            pf.setImageBitmap(bitmap);

                        }
                        catch (JSONException e)
                        {
                            e.printStackTrace();
                        }

                    }

                }
            }

        }





//

//        Calling object
        bgWorker bg = new bgWorker();
        bg.execute("profile" , r_id);
    }
}