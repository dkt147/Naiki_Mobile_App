package com.example.naiki;

import android.app.AlertDialog;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

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

public class home extends Fragment {


    ListView listView;
    AlertDialog alertDialog;
    SharedPreferences sharedPreferences;

    ArrayList<String> donate_list_data = new ArrayList<>();

    public home() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home, container, false);
        listView = view.findViewById(R.id.donation_list);
        donate_list();

        // Inflate the layout for this fragment
        return view;
    }


    private void donate_list() {
        class bgWorker extends AsyncTask<String, Void, String> {

            @Override
            protected String doInBackground(String... strings) {


                String type = strings[0];
//                String location = strings[1];


                if (type.equals("list")) {


                    try {


                        String fetch_url = "http://192.168.56.1/naiki/donate_list.php";
                        URL url = new URL(fetch_url);
                        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                        httpURLConnection.setRequestMethod("POST");
                        httpURLConnection.setDoOutput(true);
                        httpURLConnection.setDoInput(true);
                        OutputStream outputStream = httpURLConnection.getOutputStream();
                        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
//                        String post_data = URLEncoder.encode("d_id", "UTF-8") + "=" + URLEncoder.encode(employeeid, "UTF-8");
//                        bufferedWriter.write(post_data);
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

//                alertDialog = new AlertDialog.Builder(context).create();
//                alertDialog.setTitle("Apply Leave");
//                sharedPreferences = context.getSharedPreferences("Mypref", Context.MODE_PRIVATE);
            }

            @Override
            protected void onPostExecute(String s) {
                if (s != null) {

//                if (s.equals("Registered")) {
//                    alertDialog.setMessage("Employee Registered Succesfully");
//                    alertDialog.show();}
//                else {
//                    alertDialog.setMessage("Register Failed");
//                    alertDialog.show();
//                }

                    if (s.equals("Failed")) {
                        alertDialog.setMessage("Details not found");
                        alertDialog.show();
                    } else {
                        try {
                            JSONArray jsonArray = new JSONArray(s);
                            JSONObject jsonObject = null;
                            donate_list_data.clear();

                            for (int i = 0; i < jsonArray.length(); i++) {
                                jsonObject = jsonArray.getJSONObject(i);
                                String row = //jsonObject.getInt("uid")+" "+

                                        "Item Name" + "\t \t \t \t" +
                                                jsonObject.getString("item_name") + "\n \n" +
                                                "Category" + "\t \t \t \t" +
                                                jsonObject.getString("category") + "\n \n" +
                                                "Quantity" + "\t \t \t \t" +
                                                //jsonObject.getString("upassword")+" "+
                                                jsonObject.getString("quantity") + "\n \n" +
                                                "Note" + "\t \t \t \t" +
                                                jsonObject.getString("note") + "\n ---------------------------------------------------------------------- \n";


                                donate_list_data.add(row);
                            }


                            ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, donate_list_data);
                            listView.setAdapter(adapter);


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


//                    if (s.equals("inserted")) {
//                        alertDialog.setMessage("Applied Successfully");
//                        alertDialog.show();
//                    } else if (s.equals("Failed")) {
//                        alertDialog.setMessage("Invalid username or password");
//                        alertDialog.show();
//                    }


                    }
                }


            }

        }
        bgWorker bg = new bgWorker();
        bg.execute("list");


    }
}