package com.example.machine_time.lab02;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button authBtn, regBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        authBtn = (Button)findViewById(R.id.authBtn);
        regBtn = (Button)findViewById(R.id.regBtn);

        authBtn.setOnClickListener(this);
        regBtn.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.authBtn:
                Intent auth = new Intent(this, AuthPage.class);
                startActivity(auth);
                break;
            case R.id.regBtn:
                Intent reg = new Intent(this, RegistrationPage.class);
                startActivity(reg);
                break;
        }
    }
}
