package com.example.machine_time.lab02;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class ResultPage extends AppCompatActivity {

    TextView titlePageTv, ratingPercentTv;
    double checkResult;
    String correctAnswers, allAnswers, loginUser;

    RegistrationPage.DBHelper dbHelper;
    SQLiteDatabase db;


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch(item.getItemId()){
            case R.id.tryAgain:
                Intent tryAgain = new Intent(this, MainPage.class);
                startActivity(tryAgain);
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
        setContentView(R.layout.activity_result_page);

        dbHelper = new RegistrationPage.DBHelper(this);

        db = dbHelper.getWritableDatabase();

        titlePageTv   = (TextView) findViewById(R.id.titlePageTv);
        ratingPercentTv = (TextView)findViewById(R.id.ratingPercentTv);


        Intent intent = getIntent();

        checkResult = 0.71;

        correctAnswers = (intent.getStringExtra("resultTest"));
        allAnswers     = (intent.getStringExtra("allAnswers"));
        loginUser      = (intent.getStringExtra("login"));


        double ratingPercent = (Double.parseDouble(correctAnswers)* 100) / Double.parseDouble(allAnswers);

        double ratingPercentRound = new BigDecimal(ratingPercent).setScale(1, RoundingMode.UP).doubleValue();

        if (ratingPercent >= checkResult * 100) {
            titlePageTv.setText("Тест пройден!");
            ratingPercentTv.setText("Результат  — " + String.valueOf(ratingPercentRound) + "%");
        }else{
            titlePageTv.setText("Тест не пройден!");
            ratingPercentTv.setText("Результат  — " + String.valueOf(ratingPercentRound) + "%");
        }


        ContentValues cv = new ContentValues();
        cv.put("rating", ratingPercentRound);
        db.update("users", cv, "login = ?", new String[]{loginUser});


    }
}
