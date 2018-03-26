package com.example.machine_time.lab02;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


public class AboutUserPage extends AppCompatActivity {

    TextView fullNameUserTv, ratingUserTv;

    ListView answersTv;
    ArrayList<User> users = new ArrayList<User>();
    AdapterListView adapterListView;

    RegistrationPage.DBHelper dbHelper;
    SQLiteDatabase db;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_user_page);

        fullNameUserTv = (TextView)findViewById(R.id.fullNameUserTv);
        ratingUserTv   = (TextView)findViewById(R.id.ratingUserTv);


        dbHelper = new RegistrationPage.DBHelper(this);

        db = dbHelper.getWritableDatabase();

        fillData();





        String loginUser = getLogin();

        Cursor cursor = db.query("users", null, "login = ?", new String[]{loginUser}, null,null, null);

        if(cursor.moveToFirst()){

            int nameCol = cursor.getColumnIndex("name");
            int surnameCol = cursor.getColumnIndex("surname");
            int lastnameCol = cursor.getColumnIndex("lastname");
            int ratingCol   = cursor.getColumnIndex("rating");

            String name = cursor.getString(nameCol);
            String surname = cursor.getString(surnameCol);
            String lastname = cursor.getString(lastnameCol);
            String rating = String.valueOf(cursor.getDouble(ratingCol) + "%");

            fullNameUserTv.setText(surname + " " + name + " " + lastname);
            ratingUserTv.setText("Рейтинг — " + rating);
        }else
            Log.d("USER", "0 rows");

    }

    String getLogin(){
        Intent intent = getIntent();
        String loginUserFrom = intent.getStringExtra("login");
        return loginUserFrom;
    }

    void fillData(){



        String loginUser = getLogin();
        Cursor c = db.query("users", null, "login = ?", new String[]{loginUser}, null, null, null);

        String sym = "=";
        String questAndAnswer;
        String[] separatelyAnswer;


        if(c.moveToFirst()){

            for (int i = 1; i <= 7; i++) {

                int questionCol = c.getColumnIndex("question" + i);

                questAndAnswer  = c.getString(questionCol);


                try{
                    separatelyAnswer = questAndAnswer.split(sym);
                    users.add(new User(null, null,null, separatelyAnswer[0],"Ответ — " + separatelyAnswer[1]));

                    answersTv       = (ListView)findViewById(R.id.answersTv);
                    adapterListView = new AdapterListView(this, users);
                    answersTv.setAdapter(adapterListView);

                }catch (ArrayIndexOutOfBoundsException e){
                    System.out.println("ERRORRRRRRRRRRRRRRR");
                }




            }

        }else Log.d("error", "0 rows");



    }
}
