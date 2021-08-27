package com.example.naiki;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

public class OTP extends AppCompatActivity {

    private EditText t1, t2 ,t3 ,t4 ,t5, t6;
    TextView t7;
    ProgressBar progressBar2;
    Button verifybtn;

    private String verificationId;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_o_t_p);

        t1 = findViewById(R.id.text1);
        t2 = findViewById(R.id.text2);
        t3 = findViewById(R.id.text3);
        t4 = findViewById(R.id.text4);
        t5 = findViewById(R.id.text5);
        t6 = findViewById(R.id.text6);
        t7 = findViewById(R.id.textView7);
        progressBar2 = findViewById(R.id.progressBar2);
        verifybtn = findViewById(R.id.verifybtn);

        t7.setText(String.format("+92") + getIntent().getStringExtra("mobile"));




        setupOTPInputs();



    }


    public void verifybtnclick(View view){


        if(t1.getText().toString().trim().isEmpty() ||
                t2.getText().toString().trim().isEmpty() ||
                t3.getText().toString().trim().isEmpty() ||
                t4.getText().toString().trim().isEmpty() ||
                t5.getText().toString().trim().isEmpty() ||
                t6.getText().toString().trim().isEmpty()
        )
        {
            Toast.makeText(this, "Please Enter OTP to Verify", Toast.LENGTH_SHORT).show();
            return;
        }
        String code =
                t1.getText().toString() +
                        t2.getText().toString() +
                        t3.getText().toString() +
                        t4.getText().toString() +
                        t5.getText().toString() +
                        t6.getText().toString();



        verificationId = getIntent().getStringExtra("verificationId");
        String user_text = getIntent().getStringExtra("user_text");
        String pass_text =  getIntent().getStringExtra("pass_text");
        String address_text =  getIntent().getStringExtra("address_text");
        String email_text =  getIntent().getStringExtra("email_text");
        String tp =  getIntent().getStringExtra("tp");
        String phone_text =  getIntent().getStringExtra("phone_text");

        if(verificationId!=null){
            progressBar2.setVisibility(View.VISIBLE);
            verifybtn.setVisibility(View.INVISIBLE);
            PhoneAuthCredential phoneAuthCredential = PhoneAuthProvider.getCredential(
                    verificationId,code
            );
            FirebaseAuth.getInstance().signInWithCredential(phoneAuthCredential).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    progressBar2.setVisibility(View.GONE);
                    verifybtn.setVisibility(View.VISIBLE);
                    if(task.isSuccessful()){
//                        Intent intent = new Intent(getApplicationContext() , Naviagtion.class);
//                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
//                        startActivity(intent);
                        Background_Worker background_worker = new Background_Worker(OTP.this);
                        background_worker.execute("register", user_text, pass_text , address_text  , phone_text , email_text , tp);
                    }
                    else
                    {
                        Toast.makeText(OTP.this, "Verification Code Entered was Invalid", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }



    }

    public void setupOTPInputs(){
        t1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(!s.toString().trim().isEmpty()){
                    t2.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        t2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(!s.toString().trim().isEmpty()){
                    t3.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        t3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(!s.toString().trim().isEmpty()){
                    t4.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        t4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(!s.toString().trim().isEmpty()){
                    t5.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        t5.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(!s.toString().trim().isEmpty()){
                    t6.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        t6.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(!s.toString().trim().isEmpty()){
                    t6.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


    }
}