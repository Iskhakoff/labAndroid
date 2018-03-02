package com.example.machine_time.lab_01;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.QuickContactBadge;

public class EnterPage extends AppCompatActivity implements View.OnClickListener {

    final String PW = "admin";

    EditText password;
    Button btnEnter;

    SharedPreferences passPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_page);

        password = (EditText)findViewById(R.id.password);
        btnEnter = (Button)findViewById(R.id.btnEnter);

        btnEnter.setOnClickListener(this);

        passPref = getPreferences(0);
        String pass = passPref.getString("savedPassword", "");
        password.setText(pass);
        if(password.getText().toString().equalsIgnoreCase(PW)){
            Intent intent = new Intent(this, QuestionPage.class);
            startActivity(intent);
        }
    }

    void savePw(){
        passPref = getPreferences(0);
        SharedPreferences.Editor editor = passPref.edit();
        editor.putString("savedPassword", password.getText().toString());
        editor.commit();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnEnter:
                if(password.getText().toString() != "" && password.getText().toString().equalsIgnoreCase(PW)){
                    savePw();

                    Intent intent = new Intent(this, QuestionPage.class);
                    startActivity(intent);
                }
        }
    }
}
