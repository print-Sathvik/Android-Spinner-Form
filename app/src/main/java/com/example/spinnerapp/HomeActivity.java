package com.example.spinnerapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class HomeActivity extends Activity {
    TextView t1, t2, t3, t4;
    @Override
    protected void onCreate(Bundle SavedInstanceState) {
        super.onCreate(SavedInstanceState);
        setContentView(R.layout.homelayout);
        t1=findViewById(R.id.email);
        t2=findViewById(R.id.name);
        t3=findViewById(R.id.address);
        t4=findViewById(R.id.state);
        Intent i = getIntent();
        String s1 = i.getStringExtra("email");
        String s2 = i.getStringExtra("name");
        String s3 = i.getStringExtra("address");
        String s4 = i.getStringExtra("stateCountry");
        t1.setText(s1);
        t2.setText(s2);
        t3.setText(s3);
        t4.setText(s4);
    }
}
