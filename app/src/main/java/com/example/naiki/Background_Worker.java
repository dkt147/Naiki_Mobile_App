package com.example.naiki;


import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.widget.Toast;

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

public class Background_Worker extends AsyncTask<String,Void,String> {

    String type;
    Context context;
    AlertDialog alertDialog;
    SharedPreferences sharedPreferences;
    Background_Worker(Context ctx) {
        context = ctx;
    }

    @Override
    protected String doInBackground(String... params) {
        type = params[0];


//        http://192.168.56.1/naiki/login.php
//        http://lms-php.000webhostapp.com/LMS_PHP/mlogin.php


        if (type.equals("login")) {
            try {
                String login_url = "http://lms-php.000webhostapp.com/naiki/login.php";
                String un = params[1];
                String ps = params[2];

                URL url = new URL(login_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String post_data = URLEncoder.encode("un", "UTF-8") + "=" + URLEncoder.encode(un, "UTF-8") + "&"
                        + URLEncoder.encode("ps", "UTF-8") + "=" + URLEncoder.encode(ps, "UTF-8");
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






        if (type.equals("story")) {
            String reg_url = "http://lms-php.000webhostapp.com/naiki/story.php";
            String rid = params[1];
            String un = params[2];
            String st = params[3];



            try {
                URL url = new URL(reg_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoInput(true);
                httpURLConnection.setDoOutput(true);

                OutputStream outputStream = httpURLConnection.getOutputStream();
                OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream);
                BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);

                String post_data =
                        URLEncoder.encode("rid", "UTF-8") + "=" + URLEncoder.encode(rid, "UTF-8") +
                                "&" + URLEncoder.encode("un", "UTF-8") + "=" + URLEncoder.encode(un, "UTF-8") +
                                "&" + URLEncoder.encode("st", "UTF-8") + "=" + URLEncoder.encode(st, "UTF-8") ;



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

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        if (type.equals("donate")) {
            String reg_url = "http://lms-php.000webhostapp.com/naiki/donate.php";
            String rid = params[1];
            String it = params[2];
            String qt = params[3];
            String ds = params[4];
            String ct = params[5];
            String im = params[6];
            String type = "Donate";



            try {
                URL url = new URL(reg_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoInput(true);
                httpURLConnection.setDoOutput(true);

                OutputStream outputStream = httpURLConnection.getOutputStream();
                OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream);
                BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);

                String post_data =
                        URLEncoder.encode("it", "UTF-8") + "=" + URLEncoder.encode(it, "UTF-8") +
                                "&" + URLEncoder.encode("qt", "UTF-8") + "=" + URLEncoder.encode(qt, "UTF-8") +
                                "&" + URLEncoder.encode("ds", "UTF-8") + "=" + URLEncoder.encode(ds, "UTF-8") +
                                "&" + URLEncoder.encode("im", "UTF-8") + "=" + URLEncoder.encode(im, "UTF-8") +
                                "&" + URLEncoder.encode("type", "UTF-8") + "=" + URLEncoder.encode(type, "UTF-8") +
                                "&" + URLEncoder.encode("rid", "UTF-8") + "=" + URLEncoder.encode(rid, "UTF-8") +

                                "&" + URLEncoder.encode("ct", "UTF-8") + "=" + URLEncoder.encode(ct, "UTF-8") ;


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

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }


        if (type.equals("request")) {
            String reg_url = "http://lms-php.000webhostapp.com/naiki/donate.php";
            String rid = params[1];
            String it = params[2];
            String qt = params[3];
            String ds = params[4];
            String ct = params[5];
            String im = params[6];
            String type = "Request";



            try {
                URL url = new URL(reg_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoInput(true);
                httpURLConnection.setDoOutput(true);

                OutputStream outputStream = httpURLConnection.getOutputStream();
                OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream);
                BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);

                String post_data =
                        URLEncoder.encode("it", "UTF-8") + "=" + URLEncoder.encode(it, "UTF-8") +
                                "&" + URLEncoder.encode("qt", "UTF-8") + "=" + URLEncoder.encode(qt, "UTF-8") +
                                "&" + URLEncoder.encode("ds", "UTF-8") + "=" + URLEncoder.encode(ds, "UTF-8") +
                                "&" + URLEncoder.encode("im", "UTF-8") + "=" + URLEncoder.encode(im, "UTF-8") +
                                "&" + URLEncoder.encode("type", "UTF-8") + "=" + URLEncoder.encode(type, "UTF-8") +
                                "&" + URLEncoder.encode("rid", "UTF-8") + "=" + URLEncoder.encode(rid, "UTF-8") +

                                "&" + URLEncoder.encode("ct", "UTF-8") + "=" + URLEncoder.encode(ct, "UTF-8") ;


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

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }


        //REGISTER CODE
//        REGISTER CODE
        if (type.equals("register")) {
            String reg_url = "http://lms-php.000webhostapp.com/naiki/register.php";
            String un = params[1];
            String ph = params[2];
            String em = params[3];
            String ps = params[4];
            String ad = params[5];
            String tp = params[6];
            String im = params[7];
            String a = params[8];
            String b = params[9];




            try {
                URL url = new URL(reg_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoInput(true);
                httpURLConnection.setDoOutput(true);

                OutputStream outputStream = httpURLConnection.getOutputStream();
                OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream);
                BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);

                String post_data =
                        URLEncoder.encode("un", "UTF-8") + "=" + URLEncoder.encode(un, "UTF-8") +
                                "&" + URLEncoder.encode("ph", "UTF-8") + "=" + URLEncoder.encode(ph, "UTF-8") +
                                "&" + URLEncoder.encode("em", "UTF-8") + "=" + URLEncoder.encode(em, "UTF-8") +
                                "&" + URLEncoder.encode("ad", "UTF-8") + "=" + URLEncoder.encode(ad, "UTF-8") +
                                "&" + URLEncoder.encode("ps", "UTF-8") + "=" + URLEncoder.encode(ps, "UTF-8")+
                                "&" + URLEncoder.encode("tp", "UTF-8") + "=" + URLEncoder.encode(tp, "UTF-8")+
                                "&" + URLEncoder.encode("a", "UTF-8") + "=" + URLEncoder.encode(ad, "UTF-8")+
                                "&" + URLEncoder.encode("a", "UTF-8") + "=" + URLEncoder.encode(b, "UTF-8")+
                                "&" + URLEncoder.encode("im", "UTF-8") + "=" + URLEncoder.encode(im, "UTF-8");



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

                alertDialog.setTitle("Registration Status");
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
        alertDialog = new AlertDialog.Builder(context).create();
        alertDialog.setTitle("Alert Message");

    }


    @Override
    protected void onPostExecute(String s) {
        if (s != null) {

            String tt = type;


            if (type.equals("login")) {

                if (s.equals("Login-Failed")) {
                    alertDialog.setMessage("Invalid username or password");
                    alertDialog.show();
                } else {


                    try {
                        sharedPreferences = context.getSharedPreferences("userr", Context.MODE_PRIVATE);

                        JSONObject jobj = new JSONObject(s);
                        String r_id = jobj.getString("r_id");
                        int uphon = jobj.getInt("uphone");
                        String nam = jobj.getString("uname");
                        String type = jobj.getString("utype");
                        String pas = jobj.getString("upass");

                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putInt("uphone", uphon);
                        editor.putString("rid", r_id);
                        editor.putString("uname", nam);
                        editor.putString("upass", pas);
                        editor.putString("utype", type);
                        editor.commit();

                        Intent intent = new Intent(context, Naviagtion.class);
                        context.startActivity(intent);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }

// Register request Result
            if (type.equals("register")) {

                if (s.equals("Register-Failed")) {
//                    alertDialog.setMessage("Regsiteration Failed Phone Number already Exists '\n' Please Login");
//                    alertDialog.show();
                    Toast.makeText(context, "Regsiteration Failed Phone Number already Exists '\n'  Please Login", Toast.LENGTH_SHORT).show();
                } else {
//                    alertDialog.setMessage("Registered Successfully");
//                    alertDialog.show();
                    Toast.makeText(context, "Registered", Toast.LENGTH_SHORT).show();
//                        Intent intent = new Intent(context, MainActivity.class);
//                        context.startActivity(intent);
                    }
                }

//            Donation purpose result
            if (type.equals("donate"))
            {

                if (s.equals("Failed")) {
                    alertDialog.setMessage("Donate Failed");
                    alertDialog.show();
                } else {
                    alertDialog.setMessage("Submitted Successfully");
                    alertDialog.show();
//                    Intent intent = new Intent(context, home.class);
//                    context.startActivity(intent);
                }
            }


            if (type.equals("request"))
            {

                if (s.equals("Failed")) {
                    Toast.makeText(context, "Request Failed", Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(context, "Submitted", Toast.LENGTH_SHORT).show();
//                    alertDialog.setMessage("Submitted Successfully");
//                    alertDialog.show();
//                    Intent intent = new Intent( context , home.class);
//                    context.startActivity(intent);
                }
            }

            if (type.equals("story"))
            {

                if (s.equals("Register-Failed")) {
                    Toast.makeText(context, "Posted Failed", Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(context, "Posted Succesfully", Toast.LENGTH_SHORT).show();
//                    alertDialog.setMessage("Post Successfully");
//                    alertDialog.show();
//                    Intent intent = new Intent(context, story.class);
//                    context.startActivity(intent);
                }
            }


        }


    }


        }







