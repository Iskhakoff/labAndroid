package com.example.machine_time.lab_01;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;


public class AboutPage extends AppCompatActivity {

    TextView aboutApp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_page);

        aboutApp = (TextView)findViewById(R.id.aboutApp);

        aboutApp.setText("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        SharedPreferences cleanPass;
        switch (item.getItemId()){
            case R.id.menu_about:
                Intent intent = new Intent(this, AboutPage.class);
                startActivity(intent);
                break;
            case R.id.menu_exit:
                cleanPass = getSharedPreferences("pass", 0);
                cleanPass.edit().remove("savedPassword").apply();
                Intent forgotPass = new Intent(this, EnterPage.class);
                startActivity(forgotPass);
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
