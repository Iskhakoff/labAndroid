package com.example.machine_time.myapplication;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    FilmsFragment filmsFragment;
    SerialsFragment serialsFragment;
    ProfileFragment profileFragment;
    FragmentTransaction fTrans;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        filmsFragment = new FilmsFragment();
        serialsFragment = new SerialsFragment();
        profileFragment = new ProfileFragment();

        fTrans = getSupportFragmentManager().beginTransaction();
        fTrans.add(R.id.mainLinear, filmsFragment);
        fTrans.commit();
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {


        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            fTrans = getSupportFragmentManager().beginTransaction();
            boolean result = false;
            switch (item.getItemId()) {
                case R.id.films:
                    fTrans.replace(R.id.mainLinear, filmsFragment);
                    result = true;
                    break;
                case R.id.serials:
                    fTrans.replace(R.id.mainLinear, serialsFragment);
                    result = true;
                    break;
                case R.id.profile:
                    fTrans.replace(R.id.mainLinear, profileFragment);
                    result = true;
                    break;
            }
            fTrans.commit();
            return result;

        }
    };

}
