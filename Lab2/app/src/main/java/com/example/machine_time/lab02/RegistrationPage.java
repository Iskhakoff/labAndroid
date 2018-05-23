package com.example.machine_time.lab02;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class RegistrationPage extends AppCompatActivity implements View.OnClickListener {

    EditText nameEt, surnameEt, lastnameEt, loginEt, pwEt, trypwEt;
    Button registrationBtn;
    TextView errorPwLogTv;

    DBHelper dbHelper;
    SQLiteDatabase db;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_page);

        nameEt          = (EditText)findViewById(R.id.nameEt);
        surnameEt       = (EditText)findViewById(R.id.surnameEt);
        lastnameEt      = (EditText)findViewById(R.id.lastnameEt);
        loginEt         = (EditText)findViewById(R.id.loginEt);
        pwEt            = (EditText)findViewById(R.id.pwEt);
        trypwEt         = (EditText)findViewById(R.id.trypwEt);
        errorPwLogTv    = (TextView)findViewById(R.id.errorPwLogTv);
        registrationBtn = (Button)findViewById(R.id.registrationBtn);

        registrationBtn.setOnClickListener(this);

        dbHelper = new DBHelper(this);
    }

    @Override
    public void onClick(View v) {

        if(v.getId() == R.id.registrationBtn) {

            ContentValues cv = new ContentValues();

            String name = nameEt.getText().toString();
            String surname = surnameEt.getText().toString();
            String lastname = lastnameEt.getText().toString();
            String login = loginEt.getText().toString();
            String password = pwEt.getText().toString();

            boolean repeat = false;

            db = dbHelper.getWritableDatabase();


            if(password.equals(trypwEt.getText().toString())) {

                Cursor c = db.query("users", new String[]{"login"}, "login = ?", new String[]{login}, null, null, null);

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
                    cv.put("name", name);
                    cv.put("surname", surname);
                    cv.put("lastname", lastname);
                    cv.put("login", login);
                    cv.put("password", password);
                    cv.put("rating", 0.0);
                    cv.put("question1", "0");
                    cv.put("question2", "0");
                    cv.put("question3", "0");
                    cv.put("question4", "0");
                    cv.put("question5", "0");
                    cv.put("question6", "0");
                    cv.put("question7", "0");


                    long rowID = db.insert("users", null, cv);

                    Log.d("added user", "ROWID = " + rowID);

                    Intent intent = new Intent(this, AuthPage.class);
                    startActivity(intent);

                }

            }else{
                errorPwLogTv.setText("Пароли не совпадают");
            }



        }

        dbHelper.close();




    }

    static class DBHelper extends SQLiteOpenHelper{

        public DBHelper(Context context) {
            super(context, "myDB", null, 1);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL("create table users (id integer primary key autoincrement," +
                    "name text, surname text, lastname text, login text, password text, rating real, question1 text," +
                    "question2 text, question3 text, question4 text, question5 text, question6 text, question7 text);");

        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        }
    }
}
