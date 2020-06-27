package com.example.surrogateshopper;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class Register extends AppCompatActivity {

    EditText username , name , surname , pass1 , pass2 ;
    Button register;
    RadioGroup type;
    RadioButton selected;
    Context context;
    String regURL = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);


        username = findViewById(R.id.etUserNameReg);
        name = findViewById(R.id.etNameReg);
        surname = findViewById(R.id.etUserNameReg);
        pass1 = findViewById(R.id.etPasswordReg);
        pass2 = findViewById(R.id.etPassConfReg);
        type = findViewById(R.id.rgType);
        register = findViewById(R.id.btnRegReg);
        context = this;

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerMe();
            }
        });
    }

    private void registerMe() {
        String Name = name.getText().toString().trim();
        String uName = username.getText().toString().trim();
        String Surname = surname.getText().toString().trim();
        String pass01 = pass1.getText().toString().trim();
        String pass02 = pass2.getText().toString().trim();
        String Type = "";

        if(uName.equals("") || uName.length() < 5){
            username.setError("Enter Username of length 5 and above");
            return;
        }
        if(Name.equals("")){
            name.setError("Enter Name");
            return;
        }
        if(Surname.equals("")){
            surname.setError("Enter Surname");
            return;
        }
        if(pass01.equals("")){
            pass1.setError("Enter Password");
            return;
        }
        if(pass02.equals("") || !pass01.equals(pass02)){
            pass2.setError("Please enter matching password");
            return;
        }

        selected = findViewById(type.getCheckedRadioButtonId());
        Type = selected.getText().toString();


        ContentValues cv = new ContentValues();
        cv.put("name" , Name);
        cv.put("surname" , Surname);
        cv.put("username" ,uName);
        cv.put("pass" , pass01);
        cv.put("type" , Type);
        @SuppressLint("StaticFieldLeak")
        AsyncHTTPPost asyncHTTPPost = new AsyncHTTPPost(regURL,cv) {

            @Override
            protected void onPostExecute(String output) {
                if(output.equals("1")){
                    Intent intent = new Intent(Register.this , login.class);
                    startActivity(intent);
                    finish();
                }
                else{
                    Toast.makeText(context , "Please try again later" , Toast.LENGTH_SHORT).show();
                }
            }
        };
        asyncHTTPPost.execute();

    }
}
