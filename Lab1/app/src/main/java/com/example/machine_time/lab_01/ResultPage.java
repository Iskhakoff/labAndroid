package com.example.machine_time.lab_01;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class ResultPage extends AppCompatActivity implements View.OnClickListener {

    TextView titlePageTv, correctAnswersTv, incorrectAnswersTv, allAnswersTv;
    Button btnTryAgain;
    double checkResult;
    String correctAnswers, allAnswers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_page);

        titlePageTv        = (TextView) findViewById(R.id.titlePageTv);
        correctAnswersTv   = (TextView) findViewById(R.id.correctAnswersTv);
        incorrectAnswersTv = (TextView) findViewById(R.id.incorrectAnswersTv);
        allAnswersTv       = (TextView)findViewById(R.id.allAnswersTv);
        btnTryAgain        = (Button)findViewById(R.id.btnTryAgain);



        btnTryAgain.setOnClickListener(this);

        Intent intent = getIntent();

        checkResult = 0.71;

        correctAnswers = (intent.getStringExtra("resultTest"));
        allAnswers     = (intent.getStringExtra("allAnswers"));

        if ((Double.parseDouble(correctAnswers) / Double.parseDouble(allAnswers)) >= checkResult) {
            titlePageTv.setText("Тест пройден!");
            showStat();
        }else{
            titlePageTv.setText("Тест не пройден!");
            showStat();
        }
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

    void showStat(){
        correctAnswersTv.setText("Количество правильных ответов: " + correctAnswers);
        incorrectAnswersTv.setText("Количество неправиьных ответов: " + (Integer.parseInt(allAnswers) - Integer.parseInt(correctAnswers)));
        allAnswersTv.setText("Всего вопросов: " + allAnswers);
    }


    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.btnTryAgain){
            Intent intent = new Intent(this, QuestionPage.class);
            startActivity(intent);

        }
    }
}

