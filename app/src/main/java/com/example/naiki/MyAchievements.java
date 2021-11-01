package com.example.naiki;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;

public class MyAchievements extends Fragment implements AdapterView.OnItemSelectedListener {

    private static final String apiurl="http://lms-php.000webhostapp.com/naiki/achievements.php";
//    private static final String apiurl2="http://lms-php.000webhostapp.com/naiki/request.php";
    ListView listView1;
    AlertDialog alertDialog;
    SharedPreferences sharedPreferences;

    ArrayList<String> donate_list_data = new ArrayList<>();

    private static String item_name[];
    private static String item_detail[];
    private static String category[];
    private static String quantity[];
    private static String points[];

    private static String image[];
    String rid;


    public MyAchievements() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        sharedPreferences = getContext().getSharedPreferences("userr" , Context.MODE_PRIVATE);

        if(sharedPreferences.contains("rid") && sharedPreferences.contains("uphone"))
        {
            rid = sharedPreferences.getString("rid", "0");

        }

        View view = inflater.inflate(R.layout.fragment_my_achievements, container, false);
        listView1 = view.findViewById(R.id.donation_list);
//        Button b1 = view.findViewById(R.id.button4);
//        Button b2 = view.findViewById(R.id.button5);
        fetch_data_into_array(listView1);

        // Inflate the layout for this fragment
        return view;
    }





    public void fetch_data_into_array(View view)
    {

        class  dbManager extends AsyncTask<String,Void,String>
        {
            protected void onPostExecute(String data)
            {
                try {
                    JSONArray ja = new JSONArray(data);
                    JSONObject jo = null;

                    item_name = new String[ja.length()];
                    item_detail = new String[ja.length()];
                    category = new String[ja.length()];
                    quantity = new String[ja.length()];
                    points = new String[ja.length()];
                    image = new String[ja.length()];

                    for (int i = 0; i < ja.length(); i++) {
                        jo = ja.getJSONObject(i);
                        item_name[i] = jo.getString("item_name");;
                        item_detail[i] = jo.getString("note");
                        category[i] = jo.getString("category");
                        quantity[i] = jo.getString("quantity");
                        points[i] = jo.getString("points");


                        image[i] ="http://lms-php.000webhostapp.com/naiki/images/" + jo.getString("item_image");;
                    }


                    myadapter adptr = new myadapter(getActivity(), item_name, item_detail , image  , quantity , category, points );

                    listView1.setAdapter(adptr);

                } catch (Exception ex) {
                    Toast.makeText(getContext(), ex.getMessage(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            protected String doInBackground(String... strings)
            {
                String id= strings[1];

                try {

                    URL url = new URL(strings[0]);
                    HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                    httpURLConnection.setRequestMethod("POST");
                    httpURLConnection.setDoInput(true);
                    httpURLConnection.setDoOutput(true);

                    OutputStream outputStream = httpURLConnection.getOutputStream();
                    OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream);
                    BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);

                    String post_data =
                            URLEncoder.encode("id", "UTF-8") + "=" + URLEncoder.encode(id, "UTF-8");


                    bufferedWriter.write(post_data);
                    bufferedWriter.flush();
                    bufferedWriter.close();
                    outputStreamWriter.close();
                    outputStream.close();


                    InputStream inputStream = httpURLConnection.getInputStream();
                    InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                    BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

                    String line = "";
                    String result = "";

                    while ((line = bufferedReader.readLine()) != null) {
                        result += line;
                    }

                    bufferedReader.close();
                    inputStreamReader.close();
                    inputStream.close();

                    httpURLConnection.disconnect();


                    return result;

                } catch (Exception ex) {
                    return ex.getMessage();
                }

            }

        }
        dbManager obj=new dbManager();
        obj.execute(apiurl , rid);

    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }


    class myadapter extends ArrayAdapter<String>
    {


        Context context;
        String ttl[];
        String dsc[];
        String rimg[];
        String qt[];
        String cat[];
        String points[];


        myadapter(Context c, String ttl[], String dsc[], String rimg[], String qt[] , String cat[] , String points[] )
        {
            super(c,R.layout.list_row,R.id.item_name,ttl);
            context=c;
            this.ttl=ttl;
            this.dsc=dsc;
            this.rimg=rimg;
            this.qt = qt;
            this.cat = cat;
            this.points = points;
        }
        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent)
        {
            LayoutInflater inflater=(LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View row=inflater.inflate(R.layout.list_row,parent,false);

            ImageView img=row.findViewById(R.id.item_image);
            TextView tv1=row.findViewById(R.id.item_name_id);
            TextView tv2=row.findViewById(R.id.item_details);
            TextView tv3=row.findViewById(R.id.textView42);
            TextView tv4=row.findViewById(R.id.textView43);


            tv1.setText(ttl[position]);
            tv2.setText(dsc[position]);
            tv3.setText(cat[position]);
            tv4.setText(points[position]);

            String url=rimg[position];


            class ImageLoadTask extends AsyncTask<Void, Void, Bitmap> {
                private String url;
                private ImageView imageView;

                public ImageLoadTask(String url, ImageView imageView) {
                    this.url = url;
                    this.imageView = imageView;
                }

                @Override
                protected Bitmap doInBackground(Void... params) {
                    try {
                        URL connection = new URL(url);
                        InputStream input = connection.openStream();
                        Bitmap myBitmap = BitmapFactory.decodeStream(input);
                        Bitmap resized = Bitmap.createScaledBitmap(myBitmap, 400, 400, true);
                        return resized;
                    } catch (Exception e) {
                        Toast.makeText(getContext(),e.getMessage(),Toast.LENGTH_LONG).show();
                    }
                    return null;
                }
                @Override
                protected void onPostExecute(Bitmap result) {
                    super.onPostExecute(result);
                    imageView.setImageBitmap(result);
                }
            }
            ImageLoadTask obj=new ImageLoadTask(url,img);
            obj.execute();

            return row;
        }
    }


}