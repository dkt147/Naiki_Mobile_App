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
import android.widget.TextView;

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


public class profile extends Fragment {


    TextView t19, t22, t24 , t20, t16;

    SharedPreferences sharedPreferences;

    String r_id;

    AlertDialog alertDialog;

    public profile() {

    }
    

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        t19 = view.findViewById(R.id.textView19);
        t20 = view.findViewById(R.id.textView20);
        t22 = view.findViewById(R.id.textView22);
        t24 = view.findViewById(R.id.textView24);
        t16 = view.findViewById(R.id.textView16);

        getProfile();


        return view;
    }


    public void getProfile(){


        class bgWorker extends AsyncTask<String, Void, String> {


            @Override
            protected String doInBackground(String... strings) {


                String rid = strings[1];
                String type = strings[0];


                if (type.equals("profile")) {

                    try {
                        String fetch_url = "http://192.168.56.1/naiki/profile.php";
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
                        try
                        {
                            JSONArray jsonArray = new JSONArray(s);
                            JSONObject jsonObject = null;
//                            sdata.clear();

                            for (int i=0; i<jsonArray.length(); i++)
                            {
                                jsonObject = jsonArray.getJSONObject(i);

                                String name = jsonObject.getString("uname");
                                String uphone = jsonObject.getString("uphone");
                                String uaddress = jsonObject.getString("uaddress");
                                String uemail = jsonObject.getString("uemail");




                                t19.setText(name);
                                t20.setText(uphone);
                                t22.setText(uaddress);
                                t24.setText(uemail);
                                t16.setText(name);


                            }

                        }
                        catch (JSONException e)
                        {
                            e.printStackTrace();
                        }

                    }

                }
            }




        }

        sharedPreferences = getContext().getSharedPreferences("userr" , Context.MODE_PRIVATE);

        if(sharedPreferences.contains("rid") && sharedPreferences.contains("uphone"))
        {
            r_id = sharedPreferences.getString("rid", "0");

        }

        bgWorker bg = new bgWorker();
        bg.execute("profile" , r_id);
    }
}