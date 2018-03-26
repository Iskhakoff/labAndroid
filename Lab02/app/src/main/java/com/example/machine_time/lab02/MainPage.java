package com.example.machine_time.lab02;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainPage extends AppCompatActivity implements View.OnClickListener {

    Button beginTestBtn, showRatingBtn;
    String loginUser;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch(item.getItemId()){
            case R.id.tryAgain:
//                Intent tryAgain = new Intent(this, MainPage.class);
//                startActivity(tryAgain);

                Toast.makeText(this, "Чтобы пройти тест, нажмите \"Начать тест\"", Toast.LENGTH_LONG).show();
                break;
            case R.id.aboutApp:
                Intent aboutApp = new Intent(this, AboutPage.class);
                startActivity(aboutApp);
                break;
            case R.id.exit:
                Intent exit = new Intent(this, MainActivity.class);
                startActivity(exit);
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);

        beginTestBtn = (Button)findViewById(R.id.beginTestBtn);
        showRatingBtn = (Button)findViewById(R.id.showRatingBtn);

        beginTestBtn.setOnClickListener(this);
        showRatingBtn.setOnClickListener(this);

        Intent intent = getIntent();

        loginUser = (intent.getStringExtra("login"));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.beginTestBtn:
                Intent startPolls = new Intent(this, PollsPage.class);
                startPolls.putExtra("login", loginUser);
                startActivity(startPolls);
                break;
            case R.id.showRatingBtn:
                Intent showRating = new Intent(this, RatingPage.class);
                startActivity(showRating);
                showRating.putExtra("login", loginUser);
                break;
        }
    }
}
