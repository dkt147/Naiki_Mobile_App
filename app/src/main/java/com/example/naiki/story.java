package com.example.naiki;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;

public class story extends AppCompatActivity {


    private static final String apiurl="http://lms-php.000webhostapp.com/naiki/fetch_story.php";
//    private static final String apiurl2="http://lms-php.000webhostapp.com/naiki/request.php";
    ListView listView1;
    AlertDialog alertDialog;
    SharedPreferences sharedPreferences;

    ArrayList<String> donate_list_data = new ArrayList<>();

    private static String user_name[];
    private static String story[];

    private static String image[];
    String rid;
    Bitmap result;

    ImageView im;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_story);


        sharedPreferences = getSharedPreferences("userr" , Context.MODE_PRIVATE);

        if(sharedPreferences.contains("rid") && sharedPreferences.contains("uphone"))
        {
            rid = sharedPreferences.getString("rid", "0");

        }

        listView1 = findViewById(R.id.story_list);
        im = findViewById(R.id.imageView6);

        fetch_data_into_array2(listView1);

        im.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), write_story.class);
                startActivity(intent);

            }
        });



//        listView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//
//
////                String s = listView1.getChildAt(position);
//                Bundle bundle = new Bundle();
//
//                Intent intent = new Intent(getContext(), Item_Details.class);
//                intent.putExtra("name" , item_name[position]);
//                intent.putExtra("item_detail" , item_detail[position]);
//                intent.putExtra("cat" , category[position]);
//                intent.putExtra("quantity" , quantity[position]);
//                intent.putExtra("phone" , phone[position]);
//                intent.putExtra("image" , result);
//
//                startActivity(intent);            }
//        });
        // Inflate the layout for this fragment

    }


    public void fetch_data_into_array2(View view)
    {
        class  dbManager extends AsyncTask<String,Void,String>
        {
            protected void onPostExecute(String data)
            {
                try {
                    JSONArray ja = new JSONArray(data);
                    JSONObject jo = null;

                    user_name = new String[ja.length()];
                    story = new String[ja.length()];

//                    image = new String[ja.length()];

                    for (int i = 0; i < ja.length(); i++) {
                        jo = ja.getJSONObject(i);
                        user_name[i] = jo.getString("user_name");;
                        story[i] = jo.getString("story");

//                        image[i] ="http://lms-php.000webhostapp.com/naiki/profile/" + jo.getString("item_image");;
                    }


                    myadapter adptr = new myadapter(getApplication(), user_name, story );

                    listView1.setAdapter(adptr);

                } catch (Exception ex) {
                    Toast.makeText(getApplication(), ex.getMessage(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            protected String doInBackground(String... strings)
            {
                try {
                    String id= strings[1];
                    URL url = new URL(strings[0]);
                    HttpURLConnection httpURLConnection  = (HttpURLConnection) url.openConnection();
                    BufferedReader br = new BufferedReader(new InputStreamReader(httpURLConnection .getInputStream()));

                    httpURLConnection.setRequestMethod("POST");
                    httpURLConnection.setDoOutput(true);
                    httpURLConnection.setDoInput(true);
                    OutputStream outputStream = httpURLConnection.getOutputStream();

                    outputStream.close();

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




    class myadapter extends ArrayAdapter<String>
    {


        Context context;
        String un[];
        String st[];
        String rimg[];


        myadapter(Context c, String un[], String st[])
        {
            super(c,R.layout.list_row,R.id.textView43,un);
            context=c;
            this.un=un;



        }
        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent)
        {
            LayoutInflater inflater=(LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View row=inflater.inflate(R.layout.story_row,parent,false);

            ImageView img=row.findViewById(R.id.user_image);
            TextView tv1=row.findViewById(R.id.textView42);
            TextView tv2=row.findViewById(R.id.textView45);


            tv1.setText(un[position]);
            tv2.setText(story[position]);

//            String url=rimg[position];


//            class ImageLoadTask extends AsyncTask<Void, Void, Bitmap> {
//                private String url;
//                private ImageView imageView;
//
//                public ImageLoadTask(String url, ImageView imageView) {
//                    this.url = url;
//                    this.imageView = imageView;
//                }
//
//                @Override
//                protected Bitmap doInBackground(Void... params) {
//                    try {
//                        URL connection = new URL(url);
//                        InputStream input = connection.openStream();
//                        Bitmap myBitmap = BitmapFactory.decodeStream(input);
//                        Bitmap resized = Bitmap.createScaledBitmap(myBitmap, 400, 400, true);
//                        return resized;
//                    } catch (Exception e) {
//                        Toast.makeText(getContext(),e.getMessage(),Toast.LENGTH_LONG).show();
//                    }
//                    return null;
//                }
//                @Override
//                protected void onPostExecute(Bitmap result) {
//                    super.onPostExecute(result);
//                    imageView.setImageBitmap(result);
//
//                }
//            }
//            ImageLoadTask obj=new ImageLoadTask(url,img);
//            obj.execute();

            return row;
        }
    }

}