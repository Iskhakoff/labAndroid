package com.example.machine_time.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AuthPage extends AppCompatActivity implements View.OnClickListener {

    EditText authLoginEt, authPasswordEt;
    Button submitAuthData;

    DBHelper dbHelper;
    SQLiteDatabase db;

    SharedPreferences saveLogAndPass;

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth_page);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        authLoginEt = (EditText)findViewById(R.id.authLoginEt);
        authPasswordEt = (EditText)findViewById(R.id.authPasswordEt);
        submitAuthData = (Button)findViewById(R.id.submitAuthData);

        submitAuthData.setOnClickListener(this);

        dbHelper = new DBHelper(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.submitAuthData){


            db = dbHelper.getWritableDatabase();

            String login = authLoginEt.getText().toString();
            String password = authPasswordEt.getText().toString();

            Cursor c = db.query("users", new String[]{"login", "password"}, "login = ? AND password = ?", new String[]{login, password},
                    null, null,null);

            int loginCol = c.getColumnIndex("login");
            int passwordCol = c.getColumnIndex("password");

            boolean auth = false;

            if(c.moveToFirst()){
                if(login.equals(c.getString(loginCol)) && password.equals(c.getString(passwordCol))){
                    auth = true;
                }else auth = false;
            }

            if(auth){
                saveLogAndPass = getSharedPreferences("savedData", 0);
                SharedPreferences.Editor editor = saveLogAndPass.edit();
                editor.putString("login", login);
                editor.putString("password", password);
                editor.commit();

                Intent authSucсess = new Intent(this, MainActivity.class);
                startActivity(authSucсess);
            }else{
                Toast.makeText(this, "Неверный пароль или логин", Toast.LENGTH_SHORT).show();
            }

            c.close();
            db.close();
        }
    }
}
