package com.example.machine_time.lab_01;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Map;

public class QuestionPage extends AppCompatActivity implements View.OnClickListener{

    String[] questions = {"Сколько вершин в треугольнике?", "Начальный уровень компетенции разработчика?",
            "Кто открыл Америку?", "Примерное население людей в мире?", "Сколько пальцев на РУКАХ у человека?"};

    String[] answer1 = {"1", "Junior", "Христофор Колумб", "10 млрд", "5"};
    String[] answer2 = {"5", "Middle/Regular", "Алексей Навальный", "7 млрд", "20"};
    String[] answer3 = {"3", "Senior", "Барак Обама", "14 млрд", "10"};
    String[] correctAnswer = {"3", "Junior", "Христофор Колумб", "7 млрд", "10"};

    TextView questionTv;
    RadioButton ansTv1, ansTv2, ansTv3;
    Button btnReply;
    RadioGroup answers;




    Question[] allQuestions = {new Question(), new Question(), new Question(), new Question(), new Question()};

    int countCorrect = 0;
    int countPageQuest = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_page);

        questionTv = (TextView)findViewById(R.id.questionTv);
        ansTv1 = (RadioButton) findViewById(R.id.ansTv1);
        ansTv2 = (RadioButton) findViewById(R.id.ansTv2);
        ansTv3 = (RadioButton) findViewById(R.id.ansTv3);
        btnReply = (Button)findViewById(R.id.btnReply);
        answers = (RadioGroup)findViewById(R.id.answers);

        btnReply.setOnClickListener(this);

        for (int i = 0; i < allQuestions.length; i++) {
            allQuestions[i].setQuestion(questions[i]);
            allQuestions[i].setAnswer1(answer1[i]);
            allQuestions[i].setAnswer2(answer2[i]);
            allQuestions[i].setAnswer3(answer3[i]);
        }

        showQuest(countPageQuest);
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

    void showQuest(int countPageQuest){
        questionTv.setText(allQuestions[countPageQuest - 1].getQuestion());
        ansTv1.setText(allQuestions[countPageQuest - 1].getAnswer1());
        ansTv2.setText(allQuestions[countPageQuest - 1].getAnswer2());
        ansTv3.setText(allQuestions[countPageQuest - 1].getAnswer3());
    }



    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.btnReply){

            if(countPageQuest != allQuestions.length){
                switch (answers.getCheckedRadioButtonId()){
                    case R.id.ansTv1:
                        if(ansTv1.getText().toString().equalsIgnoreCase(correctAnswer[countPageQuest - 1])){
                            countCorrect++;
                        }
                        answers.clearCheck();
                        countPageQuest++;
                        showQuest(countPageQuest);
                        Log.d("count", String.valueOf(countCorrect));
                        Log.d("page", String.valueOf(countPageQuest));
                        break;
                    case R.id. ansTv2:
                        if(ansTv2.getText().toString().equalsIgnoreCase(correctAnswer[countPageQuest - 1])){
                            countCorrect++;
                        }
                        answers.clearCheck();
                        countPageQuest++;
                        showQuest(countPageQuest);
                        Log.d("count", String.valueOf(countCorrect));
                        Log.d("page", String.valueOf(countPageQuest));
                        break;
                    case R.id.ansTv3:
                        if(ansTv3.getText().toString().equalsIgnoreCase(correctAnswer[countPageQuest - 1])){
                            countCorrect++;
                        }
                        answers.clearCheck();
                        countPageQuest++;
                        showQuest(countPageQuest);
                        Log.d("count", String.valueOf(countCorrect));
                        Log.d("page", String.valueOf(countPageQuest));
                        break;
                    default:
                        Toast.makeText(this, "Выберите ответ", Toast.LENGTH_SHORT).show();

                }
            }else{
                switch (answers.getCheckedRadioButtonId()){
                    case R.id.ansTv1:
                        if(ansTv1.getText().toString().equalsIgnoreCase(correctAnswer[countPageQuest - 1])){
                            countCorrect++;
                        }
                        answers.clearCheck();
                        showResult();
                        Log.d("count", String.valueOf(countCorrect));
                        Log.d("page", String.valueOf(countPageQuest));
                        break;
                    case R.id. ansTv2:
                        if(ansTv2.getText().toString().equalsIgnoreCase(correctAnswer[countPageQuest - 1])){
                            countCorrect++;
                        }
                        answers.clearCheck();
                        showResult();
                        Log.d("count", String.valueOf(countCorrect));
                        Log.d("page", String.valueOf(countPageQuest));
                        break;
                    case R.id.ansTv3:
                        if(ansTv3.getText().toString().equalsIgnoreCase(correctAnswer[countPageQuest - 1])){
                            countCorrect++;
                        }
                        answers.clearCheck();
                        showResult();
                        Log.d("count", String.valueOf(countCorrect));
                        Log.d("page", String.valueOf(countPageQuest));
                        break;
                    default:
                        Toast.makeText(this, "Выберите ответ", Toast.LENGTH_SHORT).show();

                }
            }
        }
    }
    void showResult(){
        Intent intent = new Intent(this, ResultPage.class);
        intent.putExtra("allAnswers", String.valueOf(allQuestions.length));
        intent.putExtra("resultTest", String.valueOf(countCorrect));
        startActivity(intent);
    }
}

class Question{

    private String question;
    private String answer1;
    private String answer2;
    private String answer3;

    public Question() {

    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer1() {
        return answer1;
    }

    public void setAnswer1(String answer1) {
        this.answer1 = answer1;
    }

    public String getAnswer2() {
        return answer2;
    }

    public void setAnswer2(String answer2) {
        this.answer2 = answer2;
    }

    public String getAnswer3() {
        return answer3;
    }

    public void setAnswer3(String answer3) {
        this.answer3 = answer3;
    }
}
