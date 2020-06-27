package com.example.surrogateshopper;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.hardware.camera2.TotalCaptureResult;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class login extends AppCompatActivity {

    EditText username , password;
    Button login , register;
    String loginURL = "";
    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        username = findViewById(R.id.etUserNameLog);
        password = findViewById(R.id.etPasswordLog);
        login = findViewById(R.id.btnLogin);
        register = findViewById(R.id.btnRegLog);
        context = this;

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(login.this , Register.class));
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logMeIn();
            }
        });


    }

    public void logMeIn(){

        String uName = username.getText().toString().trim();
        String pass = password.getText().toString().trim();

        if(uName.equals("")) {
            username.setError("Please Enter Username");
            return;
        }
        if(pass.equals("")){
            password.setError("Please enter your password");
            return;
        }

        ContentValues cv = new ContentValues();
        cv.put("username" , uName);
        cv.put("pass", pass);


        @SuppressLint("StaticFieldLeak")
        AsyncHTTPPost asyncHTTPPost = new AsyncHTTPPost(loginURL,cv) {

            @Override
            protected void onPostExecute(String output) {
                if(output.equals("exists")){

                }
                else{
                    Toast.makeText(context , "Please Make sure your credentials are correct or try again later" , Toast.LENGTH_SHORT).show();
                }
            }
        };
        asyncHTTPPost.execute();

    }
}
