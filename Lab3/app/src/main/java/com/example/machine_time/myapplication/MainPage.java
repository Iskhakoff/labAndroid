package com.example.machine_time.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainPage extends AppCompatActivity implements View.OnClickListener {

    Button registrationBtn, authBtn;

    SharedPreferences cashData;

    DBHelper dbHelper;

    SQLiteDatabase db;

    String loginCash, passwordCash;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);


        registrationBtn = (Button)findViewById(R.id.registrationBtn);
        authBtn         = (Button)findViewById(R.id.authBtn);

        registrationBtn.setOnClickListener(this);
        authBtn.setOnClickListener(this);

        dbHelper = new DBHelper(this);
        db = dbHelper.getWritableDatabase();

        cashData = getSharedPreferences("savedData", 0);
        loginCash = cashData.getString("login", "");
        passwordCash = cashData.getString("password", "");

        Cursor cursor = db.query("users", null, "login = ?", new String[]{loginCash}, null,null,null);

        if(cursor.moveToFirst()){

            int passCol = cursor.getColumnIndex("password");

            if(passwordCash.equals(cursor.getString(passCol))){
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
            }else Log.d("warning", "0 rows, user don't found");

        }




    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.registrationBtn:
                Intent registration = new Intent(this, RegistrationPage.class);
                startActivity(registration);
                break;
            case R.id.authBtn:
                Intent auth = new Intent(this, AuthPage.class);
                startActivity(auth);
                break;
            default:
                break;
        }
    }
}
