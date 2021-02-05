package com.example.braintrainer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Button goButton;
    Button button0;
    Button button1;
    Button button2;
    Button button3;
    Button playAgainButton;
    int locationOfCorrectAnswer;
    TextView resultTextView;
    TextView scoreTextView;
    TextView questionTextView;
    TextView timerTextView;
    ConstraintLayout gameLayout;
    ArrayList<Integer> answers = new ArrayList<Integer>();
    int score=0;
    int numberOfQuestions=0;
    boolean playeractive=false;

    public void PlayAgain(View view){
        playeractive=true;
        playAgainButton.setVisibility(View.INVISIBLE);
        score =0;
        numberOfQuestions = 0;
        timerTextView.setText("30s");
        resultTextView.setText("");
        scoreTextView.setText(Integer.toString(score) + "/" + Integer.toString(numberOfQuestions));
        newQuestions();
        new CountDownTimer(30100,1000){
            @Override
            public void onTick(long millisUntilFinished) {
                timerTextView.setText(String.valueOf(millisUntilFinished/1000) + "s");
            }

            @Override
            public void onFinish() {
                resultTextView.setText("Done");
                playAgainButton.setVisibility(View.VISIBLE);
                playeractive=false;

            }
        }.start();
    }

    public void chooseAnswer(View view){
        if(playeractive) {
            resultTextView.setVisibility(View.VISIBLE);
            if (Integer.toString(locationOfCorrectAnswer).equals(view.getTag().toString())) {
                resultTextView.setText("Correct :)");
                score++;
            } else {
                resultTextView.setText("Wrong :(");
            }
            numberOfQuestions++;
            scoreTextView.setText(Integer.toString(score) + "/" + Integer.toString(numberOfQuestions));
            newQuestions();
        }
    }

    public void start(View view){
        playeractive=true;
        goButton.setVisibility(View.INVISIBLE);
        gameLayout.setVisibility(View.VISIBLE);
        PlayAgain(findViewById(R.id.counterTextView));
    }

    public void newQuestions(){
        Random random = new Random();
        int a= random.nextInt(21);
        int b= random.nextInt(21);

        questionTextView.setText(Integer.toString(a)+ " + " +Integer.toString(b));
        locationOfCorrectAnswer = random.nextInt(4);
        answers.clear();
        for (int i=0;i<4;i++){
            if(i==locationOfCorrectAnswer){
                answers.add(a+b);
            } else{
                int wrongAnswer = random.nextInt(41);
                while (wrongAnswer== a+b) {
                    wrongAnswer=random.nextInt(41);
                }
                answers.add(wrongAnswer);
            }
        }
        button0.setText(Integer.toString(answers.get(0)));
        button1.setText(Integer.toString(answers.get(1)));
        button2.setText(Integer.toString(answers.get(2)));
        button3.setText(Integer.toString(answers.get(3)));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       ///////////////////////////////////code here//////////////////////////////////////////////
        questionTextView = findViewById(R.id.questionTextView);
        button0 = findViewById(R.id.button0);
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        goButton = findViewById(R.id.goButton);
        resultTextView = findViewById(R.id.resultTextView);
        scoreTextView = findViewById(R.id.scoreTextView);
        timerTextView = findViewById(R.id.counterTextView);
        playAgainButton = findViewById(R.id.playAgainButton);
        gameLayout = findViewById(R.id.gamelayout);

        goButton.setVisibility(View.VISIBLE);
        gameLayout.setVisibility(View.INVISIBLE);

    }
}