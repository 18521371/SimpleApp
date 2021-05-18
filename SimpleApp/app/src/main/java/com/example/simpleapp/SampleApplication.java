package com.example.simpleapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class SampleApplication extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sample_application);

        TextView text = (TextView)findViewById(R.id.textView2);

        Bundle extras = getIntent().getExtras();
        String username = null;
        if (extras != null)
        {
            username = extras.getString("username");
            text.setText("Hello " + username); // welcome username
        }

    }
}