package com.example.naiki;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

public class Register extends AppCompatActivity implements AdapterView.OnItemSelectedListener {


    EditText uname , reg_pass , address, email , reg_phone ;

    Spinner spinner;
    ArrayList<String> data = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        uname = (EditText) findViewById(R.id.username_reg);
        reg_phone = (EditText) findViewById(R.id.phone_reg);
        address =(EditText)  findViewById(R.id.address_reg);
        email =(EditText)  findViewById(R.id.email_reg);
        reg_pass =(EditText)  findViewById(R.id.password_reg);
        spinner =  findViewById(R.id.type_reg);


        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource ( this, R.array.type , android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }




    public void reg(View view){



        if(uname.length()==0)
        {
            uname.requestFocus();
            uname.setError("FIELD CANNOT BE EMPTY");
        }

        else if(address.length()==0)
        {
            address.requestFocus();
            address.setError("FIELD CANNOT BE EMPTY");
        }
        else if(email.length()==0)
        {
            email.requestFocus();
            email.setError("FIELD CANNOT BE EMPTY");
        }
        else if(reg_pass.length()==0)
        {
            reg_pass.requestFocus();
            reg_pass.setError("FIELD CANNOT BE EMPTY");
        }
        else if(reg_phone.length()==0)
        {
            reg_phone.requestFocus();
            reg_phone.setError("FIELD CANNOT BE EMPTY");
        }

        else if (reg_phone.length()!=0 && reg_pass.length()!=0 && email.length()!=0 && uname.length()!=0 && address.length()!=0) {

            String user_text = uname.getText().toString();
            String pass_text = reg_pass.getText().toString();
            String address_text = address.getText().toString();
            String email_text = email.getText().toString();
            String tp = spinner.getSelectedItem().toString();
            String phone_text = reg_phone.getText().toString();


            Background_Worker background_worker = new Background_Worker(this);
            background_worker.execute("register", user_text, pass_text , address_text  , phone_text , email_text , tp);
        }
    }
}
