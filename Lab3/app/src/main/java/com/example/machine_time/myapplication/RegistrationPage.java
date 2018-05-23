package com.example.machine_time.myapplication;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegistrationPage extends AppCompatActivity implements View.OnClickListener {

    EditText loginEt, passwordEt, tryPasswordEt, nameEt, dateBirthEt;
    Button submitRegBtn;

    DBHelper dbHelper;
    SQLiteDatabase db;

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
        setContentView(R.layout.activity_registration_page);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        loginEt = (EditText)findViewById(R.id.loginEt);
        passwordEt = (EditText)findViewById(R.id.passwordEt);
        tryPasswordEt = (EditText)findViewById(R.id.tryPasswordEt);
        nameEt = (EditText)findViewById(R.id.nameEt);
        dateBirthEt = (EditText)findViewById(R.id.dateBirthEt);
        submitRegBtn = (Button)findViewById(R.id.submitRegBtn);

        submitRegBtn.setOnClickListener(this);
        dbHelper = new DBHelper(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.submitRegBtn){
            ContentValues cv = new ContentValues();

            String login = loginEt.getText().toString();
            String password = passwordEt.getText().toString();
            String name = nameEt.getText().toString();
            String date = dateBirthEt.getText().toString();

            boolean repeat = false;

            db = dbHelper.getWritableDatabase();

            if(password.equals(tryPasswordEt.getText().toString())){

                Cursor c = db.query("users", new String[]{"login"}, "login = ?", new String[]{login}, null, null,null);

                if(c.moveToFirst()){
                    int loginCol = c.getColumnIndex("login");

                    if(login.equals(c.getString(loginCol))){
                        Toast.makeText(this, "Такой логин уже существует!", Toast.LENGTH_SHORT).show();
                        repeat = true;
                    }else{
                        repeat = false;
                    }
                }

                if(!repeat){
                    cv.put("login", login);
                    cv.put("password", password);
                    cv.put("name", name);
                    cv.put("birth", date);

                    long rowID = db.insert("users", null, cv);

                    Log.d("added user", "ROWID = " + rowID);

                    Intent intent = new Intent(this, AuthPage.class);
                    startActivity(intent);
                }

            }else{
                Toast.makeText(this, "Пароли не совпадают", Toast.LENGTH_SHORT).show();
            }

        }
        dbHelper.close();
    }
}
