package com.example.simpleapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.example.simpleapp.passwordHashing;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Registration extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);


        //Click register button
        Button regis = (Button)findViewById(R.id.button3);
        regis.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                // get information
                EditText email = (EditText) findViewById(R.id.inputEmail);
                EditText username = (EditText) findViewById(R.id.inputUsername);
                EditText password = (EditText) findViewById(R.id.inputPassword);

                String emailString = email.getText().toString();
                String usernameString = username.getText().toString();
                String passwordString = password.getText().toString();


                if (emailString.isEmpty() || usernameString.isEmpty() || passwordString.isEmpty()){
                    String message = "Please fill all information!";
                    Toast.makeText(Registration.this, message, Toast.LENGTH_SHORT).show();
                } else {
                    // Processing....
                    String result;
                    String passHash = passwordHashing.doHashing(passwordString);
                    Log.i("hashhhhhhhh: ", passHash);
                    User user = new User();

                    // Create database
                    SQLiteConnector db = new SQLiteConnector(Registration.this);
                    //Mysqlconnection mysql = new Mysqlconnection();
                    // -----------------------------------------------------
                    if (db.checkUser(usernameString, passHash)) { //--SQLite
                    //if (mysql.checkUser(usernameString, passHash)) {
                        result = "User exist!";
                    }
                    else {
                        user.setId(1);
                        user.setEmail(emailString);
                        user.setName(usernameString);
                        user.setPassword(passHash);

                       db.addUser(user);  //SQLite

                        //mysql.addUser(usernameString, passHash, emailString);

                        //if (mysql.checkUser(usernameString, passHash)){
                        if (db.checkUser(usernameString, passHash)){
                            result = "Create Successfully!";
                            email.setText("");
                            username.setText("");
                            password.setText("");
                            Intent myIntent = new Intent( Registration.this, MainActivity.class);
                            startActivity(myIntent);

                        } else {
                            result = "Something went wrong! Try again!";
                        }

                    }
                    //------------------------------------------------------

                    Toast.makeText(Registration.this, result, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}