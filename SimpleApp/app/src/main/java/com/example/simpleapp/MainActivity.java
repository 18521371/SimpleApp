package com.example.simpleapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.mysql.jdbc.MySQLConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Button regisActivity = (Button) findViewById(R.id.button2);
        regisActivity.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent myIntent = new Intent(MainActivity.this, Registration.class);
                startActivity(myIntent);
            }
        });


        //----------------------------MYSQL----------------------------
//        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.INTERNET}, PackageManager.PERMISSION_GRANTED);
//        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
//        StrictMode.setThreadPolicy(policy);
//        Button signupActivity = (Button) findViewById(R.id.button);
//        signupActivity.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View view) {
//
//                EditText inputUsername = (EditText) findViewById(R.id.inputCheckUsername);
//                EditText inputPassword = (EditText) findViewById(R.id.inputCheckPassword);
//
//                String username = inputUsername.getText().toString();
//                String password = inputPassword.getText().toString();
//                String passHash = passwordHashing.doHashing(password);
//
//                Mysqlconnection mysql = new Mysqlconnection();
//
//                if (mysql.checkUser(username, passHash)) {
//                    inputUsername.setText("");
//                    inputPassword.setText("");
//                    Intent intent = new Intent(MainActivity.this, SampleApplication.class);
//                    intent.putExtra("username",username);
//                    startActivity(intent);
//                } else {
//                    String status = "Login failed! Try again!";
//                    Toast.makeText(MainActivity.this, status, Toast.LENGTH_SHORT).show();
//                }
//            }
//        });

        //-----------------------------SQLite----------------------
        SQLiteConnector db = new SQLiteConnector(MainActivity.this);


        Button signupActivity = (Button)findViewById(R.id.button);
        signupActivity.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {

                EditText inputUsername = (EditText)findViewById(R.id.inputCheckUsername);
                EditText inputPassword = (EditText)findViewById(R.id.inputCheckPassword);

                String username = inputUsername.getText().toString();
                String password = inputPassword.getText().toString();
                String passHash = passwordHashing.doHashing(password);

                if (db.checkUser(username, passHash)) {
                    Intent intent = new Intent(MainActivity.this, SampleApplication.class);
                    intent.putExtra("username",username);
                    startActivity(intent);
                }
                else {
                    String status = "Login failed! Try again!";
                    Toast.makeText(MainActivity.this, status, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}

