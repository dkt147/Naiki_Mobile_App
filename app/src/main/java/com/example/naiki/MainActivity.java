package com.example.naiki;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {


    EditText phone , pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        phone = findViewById(R.id.phoneid);
        pass = findViewById(R.id.passwordid);

    }

    public void login(View view){

        if(phone.length()==0)
        {
            phone.requestFocus();
            phone.setError("FIELD CANNOT BE EMPTY");
        }

        else if(pass.length()==0)
        {
            pass.requestFocus();
            pass.setError("FIELD CANNOT BE EMPTY");
        }

        else if (phone.length()!=0 && pass.length()!=0) {

            String user_text = phone.getText().toString();
            String pass_text = pass.getText().toString();
//            String type = "login";

            Background_Worker background_worker = new Background_Worker(this);
            background_worker.execute("login", user_text, pass_text);


        }


    }

   public void signup(View view){
       Intent intent = new Intent( MainActivity.this , Register.class);
       startActivity(intent);
   }
}