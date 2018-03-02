package com.example.machine_time.lab_01;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class QuestionPage extends AppCompatActivity implements View.OnClickListener{

    String[] questions = {"Сколько вершин в треугольнике?", "Со скольки лет продают сигареты?",
            "Кто открыл Америку?", "Примерное население людей в мире?", "Сколько пальцев на РУКАХ у человека?"};

    String[] answer1 = {"1", "16", "Колумб", "10 млрд", "5"};
    String[] answer2 = {"5", "18", "Навальный", "7 млрд", "20"};
    String[] answer3 = {"3", "21", "Обама", "14 млрд", "10"};
    String[] correctAnswer = {"3", "18", "Колумб", "7 млрд", "10"};

    TextView questionTv;
    RadioButton ansTv1, ansTv2, ansTv3;
    Button btnReply;
    RadioGroup answers;


    Question[] allQuestions = {new Question(), new Question(), new Question(), new Question(), new Question()};

    int count = 0;
    int j = 0;

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

        showQuest(j);

    }
    void showQuest(int j){
        questionTv.setText(allQuestions[j].getQuestion());
        ansTv1.setText(allQuestions[j].getAnswer1());
        ansTv2.setText(allQuestions[j].getAnswer2());
        ansTv3.setText(allQuestions[j].getAnswer3());
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.btnReply){
            switch (answers.getCheckedRadioButtonId()){
                case R.id.ansTv1:
                    if(ansTv1.getText().toString().equalsIgnoreCase(correctAnswer[j])){
                        count++;
                    }
                    answers.clearCheck();
                    j++;
                    showQuest(j);
                    Log.d("count", String.valueOf(count));
                    break;
                case R.id. ansTv2:
                    if(ansTv2.getText().toString().equalsIgnoreCase(correctAnswer[j])){
                        count++;
                    }
                    answers.clearCheck();
                    j++;
                    showQuest(j);
                    Log.d("count", String.valueOf(count));
                    break;
                case R.id.ansTv3:
                    if(ansTv3.getText().toString().equalsIgnoreCase(correctAnswer[j])){
                        count++;
                    }
                    answers.clearCheck();
                    j++;
                    showQuest(j);
                    Log.d("count", String.valueOf(count));
                    break;
                default:
                    Toast.makeText(this, "Выберите ответ", Toast.LENGTH_SHORT).show();
            }

        }
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
