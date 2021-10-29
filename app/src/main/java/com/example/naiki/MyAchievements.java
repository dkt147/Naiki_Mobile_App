package com.example.naiki;

import android.app.AlertDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.bumptech.glide.Glide;

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

public class MyAchievements extends Fragment {

    ArrayList<String> alist = new ArrayList<>();
    ListView listView;
    SharedPreferences sharedPreferences;
    AlertDialog alertDialog;


    String rid ;
    public MyAchievements() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my_achievements, container, false);

        listView = view.findViewById(R.id.donation_list);


        getMyDonations();
        return view;
    }

    public void getMyDonations() {


        class bgWorker extends AsyncTask<String, Void, String> {


            @Override
            protected String doInBackground(String... strings) {

//              rid is the vallue we get from sharedpreferences
                String rid = strings[1];
                String type = strings[0];

// working for fetch profile from database for which we are using rid to sent this id to mysql api where we get data
                if (type.equals("myDonation")) {

                    try {
//                        API link
                        String fetch_url = "http://192.168.56.1/naiki/achievements.php";
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
            protected void onPreExecute() {

            }

            @Override
            protected void onPostExecute(String s) {
                if (s != null) {

                    if (s.equals("Failed")) {
                        alertDialog.setMessage("Details not found");
                        alertDialog.show();
                    } else {
                        try {
                            JSONArray jsonArray = new JSONArray(s);
                            JSONObject jsonObject = null;
                            alist.clear();

                            for (int i = 0; i < jsonArray.length(); i++) {
                                jsonObject = jsonArray.getJSONObject(i);
                                String row[];
                                String name = jsonObject.getString("item_name");
                                String quantity = jsonObject.getString("quantity");
                                String image = jsonObject.getString("item_image");
                                String note = jsonObject.getString("note");

//                                Glide.with(getContext()).load(image).into(list_class);


//                                                jsonObject.getString("item_image")+



//                                alist.add(row);
                            }


                            ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, alist);
                            listView.setAdapter(adapter);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }

            }
        }


//        sharedPreferences gettting values
        sharedPreferences = getContext().getSharedPreferences("userr" , Context.MODE_PRIVATE);
        if(sharedPreferences.contains("rid") && sharedPreferences.contains("uphone"))
        {
            rid = sharedPreferences.getString("rid", "0");

        }

//        Calling object
        bgWorker bg = new bgWorker();
        bg.execute("myDonation" , rid);
    }
}