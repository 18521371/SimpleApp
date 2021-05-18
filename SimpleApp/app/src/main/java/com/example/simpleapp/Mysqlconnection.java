package com.example.simpleapp;

import android.annotation.SuppressLint;
import android.os.StrictMode;
import android.util.Log;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Mysqlconnection {
    String classs = "com.mysql.jdbc.Driver";

    String url = "jdbc:mysql://192.168.1.3:3306/simpleapp";
    String un = "root";
    String password = "123456";


    @SuppressLint("NewApi")
    public Connection CONN() {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                .permitAll().build();
        StrictMode.setThreadPolicy(policy);
        Connection conn = null;
        //String ConnURL = null;
        try {
            Class.forName(classs);
            conn = DriverManager.getConnection(url, un, password);
            if (conn != null) {
                Log.d("MYSQL:", "CONNECTED!" );
            }
            //conn = DriverManager.getConnection(ConnURL);

        } catch (SQLException se) {
            Log.e("ERRO", se.getMessage());
        } catch (ClassNotFoundException e) {
            Log.e("ERRO", e.getMessage());
        } catch (Exception e) {
            Log.e("ERRO", e.getMessage());
        }
        return conn;
    }

    public boolean checkUser(String username, String password){
        Connection conn = new Mysqlconnection().CONN();
        String query = "SELECT username,password from users WHERE username=" + "'" + username + "' and " + "password=" + "'" + password + "'";
        Log.d("QUERY: ", query);
        Statement statement = null;
        try {
            statement = conn.createStatement();
            ResultSet result = statement.executeQuery(query);
            if(result.next()) {
                while (result.next()){
                    String re = result.getString(1) + "/" + result.getString(2);
                    Log.d("RESULT: ", re);
                }
                statement.close();
                try { conn.close(); Log.d("MYSQL:", "DISCONNECTED!");} catch (Exception e) { /* Ignored */ }
                return true;
            } else {
                conn.close();
                return false;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    public boolean addUser(String username, String password, String email) {
        Connection conn = new Mysqlconnection().CONN();
        String query = "INSERT INTO users (username, password, email) values(" + "'" + username + "','" + password + "','" + email + "')";
        Log.d("QUERY: ", query);
        Statement statement = null;
        try {
            statement = conn.createStatement();
            statement.executeUpdate(query);
            statement.close();
            try { conn.close(); Log.d("MYSQL:", "DISCONNECTED!");} catch (Exception e) { /* Ignored */ }
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

    }
}

