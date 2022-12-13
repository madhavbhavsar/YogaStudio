package com.madapps.yogastudio;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.madapps.yogastudio.adminactivity.AdminDashboard;
import com.madapps.yogastudio.useractivity.UserDashboard;

public class LoginActivity extends AppCompatActivity {

    RadioButton radiouser,radioadmin;
    RadioGroup radiogroup;
    Button loginbtn,signupbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        radiogroup = (RadioGroup) findViewById(R.id.radiogroup);
        radiouser =(RadioButton) findViewById(R.id.radiouser);
        radioadmin =(RadioButton) findViewById(R.id.radioadmin);
        loginbtn = (Button) findViewById(R.id.loginbtn);
        signupbtn = (Button) findViewById(R.id.signupbtn);

        radioadmin.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    signupbtn.setEnabled(false);
                } else {
                    signupbtn.setEnabled(true);
                }


            }
        });

        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(radiouser.isChecked()){
                    Intent i = new Intent(LoginActivity.this, UserDashboard.class);
                    startActivity(i);
                    finish();
                }
                if(radioadmin.isChecked()){
                    Intent i = new Intent(LoginActivity.this, AdminDashboard.class);
                    startActivity(i);
                    finish();
                }

            }
        });




    }
}