package com.example.machine_time.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class ProfileFragment extends Fragment implements View.OnClickListener {

    TextView fioTv, birthTv, loginTv;
    Button exitBtn;
    SharedPreferences cashData;
    DBHelper dbHelper;
    SQLiteDatabase db;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        getActivity().setTitle("Профиль");

        View rootView = inflater.inflate(R.layout.profile_fragment, container, false);

        fioTv = (TextView)rootView.findViewById(R.id.fioTv);
        birthTv = (TextView)rootView.findViewById(R.id.birthTv);
        loginTv = (TextView)rootView.findViewById(R.id.loginTv);
        exitBtn = (Button)rootView.findViewById(R.id.exitBtn);

        exitBtn.setOnClickListener(this);

        cashData = getActivity().getSharedPreferences("savedData",0);
        String login = cashData.getString("login", "");
        System.out.println(login);


        dbHelper = new DBHelper(getActivity());

        db = dbHelper.getWritableDatabase();

        Cursor cursor = db.query("users", null, "login = ?", new String[]{login}, null, null,null);



        if(cursor.moveToFirst()){
            int loginCol = cursor.getColumnIndex("login");
            int fioCol = cursor.getColumnIndex("name");
            int birthCol = cursor.getColumnIndex("birth");

            fioTv.setText(cursor.getString(fioCol));
            birthTv.setText(cursor.getString(birthCol));
            loginTv.setText(cursor.getString(loginCol));

            System.out.println(cursor.getString(fioCol));
            System.out.println(cursor.getString(birthCol));
            System.out.println(cursor.getString(loginCol));
        }
        return rootView;
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.exitBtn){
            cashData = getActivity().getSharedPreferences("savedData", 0);
            cashData.edit().remove("login").apply();
            cashData.edit().remove("password").apply();

            Intent exit = new Intent(getActivity(), MainPage.class);
            startActivity(exit);
        }
    }
}
