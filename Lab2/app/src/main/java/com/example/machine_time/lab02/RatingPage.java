package com.example.machine_time.lab02;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;


public class RatingPage extends AppCompatActivity {

    ArrayList<User> users = new ArrayList<User>();
    AdapterListView adapterListView;

    RegistrationPage.DBHelper dbHelper;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rating_page);

        dbHelper = new RegistrationPage.DBHelper(this);

        db = dbHelper.getWritableDatabase();


        fillData();
        adapterListView = new AdapterListView(this, users);

        ListView ratingLv = (ListView)findViewById(R.id.ratingLv);
        ratingLv.setAdapter(adapterListView);

        ratingLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                LinearLayout linLayout = (LinearLayout)view;
                TextView loginUserTv = (TextView) linLayout.getChildAt(0);

                String loginUser = loginUserTv.getText().toString();
                Intent intent = new Intent(getApplicationContext(), AboutUserPage.class);
                intent.putExtra("login", loginUser);
                startActivity(intent);
            }
        });


    }

    void fillData() {

        Cursor cursor = db.query("users", null, null,null,null,null,"rating DESC");

        if(cursor.moveToFirst()){
            int loginCol = cursor.getColumnIndex("login");
            int ratingCol = cursor.getColumnIndex("rating");

            do{
                users.add(new User(null, null, null, cursor.getString(loginCol),
                        String.valueOf(cursor.getString(ratingCol) + "%")));
            }while(cursor.moveToNext());
        }else{
            Log.d("ratingListView", "0 rows");
        }

    }

}
