package com.example.naiki;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.Html;
import android.util.Base64;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.github.dhaval2404.imagepicker.ImagePicker;
import com.google.android.gms.internal.location.zzas;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class registration extends AppCompatActivity implements AdapterView.OnItemSelectedListener {


   EditText ed1 ,ed2,ed3,ed4;
   TextView t1, t35, t36;
   Button b1;
    ProgressBar progressBar;
    Button button;
    ImageView pr_up;
    String image_path;
    Bitmap bitmap;

    String a;
    String b;
    Spinner spinner;
    ArrayList<String> data = new ArrayList<>();
    FusedLocationProviderClient fusedLocationProviderClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        progressBar = findViewById(R.id.progressBar);
        button = findViewById(R.id.register);
        ed1 = findViewById(R.id.username);
        ed2 = findViewById(R.id.phone);
        ed3 = findViewById(R.id.email);
        ed4 = findViewById(R.id.password);

        b1 = findViewById(R.id.register);

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);

        getLocation();

        t1 =(TextView)  findViewById(R.id.address);

        spinner =  findViewById(R.id.type_reg);

        pr_up = findViewById(R.id.profile_upload);

        pr_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImagePicker.with(registration.this)
//                        .crop()	    			//Crop image(Optional), Check Customization for more option
//                        .compress(1024)			//Final image size will be less than 1 MB(Optional)
//                        .maxResultSize(1080, 1080)	//Final image resolution will be less than 1080 x 1080(Optional)
                        .start();
            }
        });





        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource ( this, R.array.type , android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);





        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

                if (ActivityCompat.checkSelfPermission(registration.this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                    getLocation();
                } else {
                    ActivityCompat.requestPermissions(registration.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 44);
                }

                if (ed1.length() == 0) {
                    ed1.requestFocus();
                    ed1.setError("FIELD CANNOT BE EMPTY");
                } else if (t1.length() == 0) {
                    t1.requestFocus();
                    t1.setError("FIELD CANNOT BE EMPTY");
                    Toast.makeText(getApplicationContext(), "Please enable Location to proceed", Toast.LENGTH_SHORT).show();
                    ActivityCompat.requestPermissions(registration.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 44);
                }

                else if (!(ed3.getText().toString().trim().matches(emailPattern))) {
                    ed3.requestFocus();
                    Toast.makeText(getApplicationContext(),"Invalid email address",Toast.LENGTH_SHORT).show();
                } else if (ed4.length() == 0) {
                    ed4.requestFocus();
                    ed4.setError("FIELD CANNOT BE EMPTY");
                } else if (ed2.length() != 10) {
                    ed2.requestFocus();
                    ed2.setError("Enter correct mobile number");
                } else if (ed2.length() == 10 && ed4.length() != 0 && ed3.length() != 0 && ed1.length() != 0 && t1.length() != 0 && ed3.getText().toString().trim().matches(emailPattern)) {

                    String username = ed1.getText().toString();
                    String phone = ed2.getText().toString();
                    String email = ed3.getText().toString();
                    String password = ed4.getText().toString();
                    String address_text = t1.getText().toString();
                    String tp = spinner.getSelectedItem().toString();
                    image_path = encodedbitmap(bitmap);

                    if (isValidPassword(password))
                    {

//                        Intent intent = new Intent(registration.this , OTP.class);
//                        intent.putExtra("mobile" , phone);

//                        intent.putExtra("verificationId" , verificationId);

                        Background_Worker background_worker = new Background_Worker(getApplication());
                        background_worker.execute("register", username ,phone , email , password , address_text , tp, image_path , a , b);

//                        intent.putExtra("user_text" , username);
//                        intent.putExtra("pass_text" , password);
//                        intent.putExtra("address_text" , address_text);
//                        intent.putExtra("email_text" , email);
//                        intent.putExtra("tp" , tp);
//                        intent.putExtra("image" , image_path);
//
//                        startActivity(intent);

//                            PhoneAuthProvider.getInstance().verifyPhoneNumber(
//                                    "+92" + phone,
//                                    60,
//                                    TimeUnit.SECONDS,
//                                    registration.this,
//                                    new PhoneAuthProvider.OnVerificationStateChangedCallbacks(){
//                                        @Override
//                                        public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
//                                            progressBar.setVisibility(View.GONE);
//                                            button.setVisibility(View.VISIBLE);
//                                        }
//
//                                        @Override
//                                        public void onVerificationFailed(@NonNull FirebaseException e) {
//                                            progressBar.setVisibility(View.GONE);
//                                            button.setVisibility(View.VISIBLE);
//                                            Toast.makeText(registration.this , e.getMessage(), Toast.LENGTH_SHORT).show();
//
//
//                                        }
//
//                                        @Override
//                                        public void onCodeSent(@NonNull String verificationId, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
//                                            progressBar.setVisibility(View.GONE);
//                                            button.setVisibility(View.VISIBLE);
//                                            Intent intent = new Intent(registration.this , OTP.class);
//                                            intent.putExtra("mobile" , phone);
//
//                                            intent.putExtra("verificationId" , verificationId);
//
//
//                                            intent.putExtra("user_text" , username);
//                                            intent.putExtra("pass_text" , password);
//                                            intent.putExtra("address_text" , address_text);
//                                            intent.putExtra("email_text" , email);
//                                            intent.putExtra("tp" , tp);
//                                            intent.putExtra("image" , image_path);
//
//                                            startActivity(intent);
//                                        }
//                                    }
//                            );



                    }
                    else{
                        Toast.makeText(registration.this, " Password must has atleast 8 characters that include at least 1 lowercase character, 1 uppercase, 1 number, and 1 special character", Toast.LENGTH_SHORT).show();
                    }


//

                }



            }
        });


    }

    public boolean isValidPassword(final String password) {

        Pattern pattern;
        Matcher matcher;

        final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$";

//        val passwordREGEX = Pattern.compile("^" +
//                "(?=.*[0-9])" +         //at least 1 digit
//                "(?=.*[a-z])" +         //at least 1 lower case letter
//                "(?=.*[A-Z])" +         //at least 1 upper case letter
//                "(?=.*[a-zA-Z])" +      //any letter
//                "(?=.*[@#$%^&+=])" +    //at least 1 special character
//                "(?=\\S+$)" +           //no white spaces
//                ".{8,}" +               //at least 8 characters
//                "$");

        pattern = Pattern.compile(PASSWORD_PATTERN);
        matcher = pattern.matcher(password);

        return matcher.matches();

    }


    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        Uri uri1 = data.getData();
        try {
            bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri1);
            pr_up.setImageBitmap(bitmap);
//        t2.setText(uri1.toString());

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String encodedbitmap(Bitmap bitmap)
    {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,100, byteArrayOutputStream);

        byte[] byteofimage = byteArrayOutputStream.toByteArray();
        image_path = android.util.Base64.encodeToString(byteofimage , Base64.DEFAULT);
//        t2.setText(image_path);
        return image_path;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void getLocation() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            return;
        }
        fusedLocationProviderClient.getLastLocation().addOnCompleteListener(new OnCompleteListener<Location>() {
            @Override
            public void onComplete(@NonNull Task<Location> task) {
                Location location = task.getResult();
                if (location != null)
                {

                    try {
                        Geocoder geocoder = new Geocoder(registration.this , Locale.getDefault());
                        List<Address> address1 = geocoder.getFromLocation(location.getLatitude() , location.getLongitude() ,1);

                        t1.setText(address1.get(0).getAddressLine(0));
//                        textView2 = findViewById(R.id.textView6);
//                        textView2.setText(Html.fromHtml("<font> Latitude : </font>"  +address.get(0).getCountryName()));
//                        textView3 = findViewById(R.id.textView7);
                        a = String.valueOf(address1.get(0).getLongitude());
                        b = String.valueOf(address1.get(0).getLatitude());
//                        textView3.setText(Html.fromHtml("<font> Latitude : </font>"  +address.get(0).getLocality()));
//                        t35 = findViewById(R.id.textView35);
//                        t35.setText(Html.fromHtml("<font> Latitude : </font>"  +address1.get(0).getLatitude()));
//                        t36 = findViewById(R.id.textView36);
//                        t36.setText(Html.fromHtml("<font> Latitude : </font>"  +address1.get(0).getLongitude()));

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

            }
        });

    }

}