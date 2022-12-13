package com.madapps.yogastudio.useractivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.madapps.yogastudio.R;

public class UserDashboard extends AppCompatActivity {

    Button userhome;
    Button addmissionpage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_dashboard);

        userhome = (Button) findViewById(R.id.userhome);
        addmissionpage = (Button) findViewById(R.id.addmissionpage);

        addmissionpage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(UserDashboard.this,AddmissionActivity.class);
                startActivity(i);
                finish();
            }
        });


    }
}