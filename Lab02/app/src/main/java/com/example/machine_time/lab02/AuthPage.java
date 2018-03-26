package com.example.machine_time.lab02;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class AuthPage extends AppCompatActivity implements View.OnClickListener {

    EditText loginEt, pwEt;
    Button enterBtn;
    TextView errorTv;

    RegistrationPage.DBHelper dbHelper;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth_page);

        loginEt  = (EditText)findViewById(R.id.loginEt);
        pwEt     = (EditText)findViewById(R.id.pwEt);
        enterBtn = (Button)findViewById(R.id.enterBtn);
        errorTv  = (TextView)findViewById(R.id.errorTv);

        enterBtn.setOnClickListener(this);

        dbHelper = new RegistrationPage.DBHelper(this);

    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.enterBtn){
            db = dbHelper.getWritableDatabase();

            String login = loginEt.getText().toString();
            String password = pwEt.getText().toString();

            Cursor c = db.query("users", new String[]{"login", "password"}, "login = ? AND password = ?", new String[]{login, password},
                    null,null,null);

            int loginCol = c.getColumnIndex("login");
            int passwordCol = c.getColumnIndex("password");

            boolean auth = false;

            if(c.moveToFirst()){
                if(login.equals(c.getString(loginCol)) && password.equals(c.getString(passwordCol))){
                    auth = true;
                }else{
                    auth = false;
                }
            }

            if(auth){
                Intent beginTest = new Intent(this, MainPage.class);
                beginTest.putExtra("login", login);
                startActivity(beginTest);
            }else{
                Toast.makeText(this, "Неверный пароль или логин", Toast.LENGTH_SHORT).show();
            }
            c.close();
            db.close();

        }
    }
}
