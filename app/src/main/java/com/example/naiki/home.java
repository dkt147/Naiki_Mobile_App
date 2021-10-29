package com.example.naiki;

import android.app.AlertDialog;
import android.content.Context;
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

public class home extends Fragment implements AdapterView.OnItemSelectedListener {

    private static final String apiurl="http://192.168.56.1/naiki/donate_list.php";
    ListView listView1;
    AlertDialog alertDialog;
    SharedPreferences sharedPreferences;

    ArrayList<String> donate_list_data = new ArrayList<>();

    private static String item_name[];
    private static String item_detail[];
    private static String image[];

    Spinner sp1, sp2;


    public home() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home, container, false);
        listView1 = view.findViewById(R.id.doante_list);
        sp1 = view.findViewById(R.id.spinner2);
        sp2 = view.findViewById(R.id.spinner3);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource ( getContext(), R.array.category , android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp1.setAdapter(adapter);
        sp1.setOnItemSelectedListener(this);

        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource ( getContext(), R.array.type , android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp2.setAdapter(adapter);
        sp2.setOnItemSelectedListener(this);

        fetch_data_into_array(listView1);

        listView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String s = listView1.getItemAtPosition(position).toString();

                Toast.makeText(getContext(), s, Toast.LENGTH_LONG).show();
            }
        });
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
                    image = new String[ja.length()];

                    for (int i = 0; i < ja.length(); i++) {
                        jo = ja.getJSONObject(i);
                        item_name[i] = jo.getString("item_name");;
                        item_detail[i] = jo.getString("note");
                        image[i] ="http://192.168.56.1/naiki/images/" + jo.getString("item_image");;
                    }

                    myadapter adptr = new myadapter(getActivity(), item_name, item_detail, image);
                    listView1.setAdapter(adptr);

                } catch (Exception ex) {
                    Toast.makeText(getContext(), ex.getMessage(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            protected String doInBackground(String... strings)
            {
                try {
                    URL url = new URL(strings[0]);
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));

                    StringBuffer data = new StringBuffer();
                    String line;

                    while ((line = br.readLine()) != null) {
                        data.append(line + "\n");
                    }
                    br.close();

                    return data.toString();

                } catch (Exception ex) {
                    return ex.getMessage();
                }

            }

        }
        dbManager obj=new dbManager();
        obj.execute(apiurl);

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

        myadapter(Context c, String ttl[], String dsc[], String rimg[])
        {
            super(c,R.layout.list_row,R.id.item_name,ttl);
            context=c;
            this.ttl=ttl;
            this.dsc=dsc;
            this.rimg=rimg;
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


            tv1.setText(ttl[position]);
            tv2.setText(dsc[position]);

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