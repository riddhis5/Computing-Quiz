package com.example.computingquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainQuizActivity extends AppCompatActivity implements View.OnClickListener{

    // call ui elements
    private TextView question, totalQuestions;
    private Button optionOne, optionTwo, optionThree, optionFour, enter;

    // user score
    int score = 0;
    // total number of questions
    int numberOfQuestions = QuestionsAndAnswers.questions.length;
    // current question
    int currentQuestionIndex = 0;
    // user selected answer
    String selectedAnswer = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_quiz);

        // define ui elements
        question = (TextView) findViewById(R.id.question);
        totalQuestions = (TextView) findViewById(R.id.totalQuestions);
        optionOne = (Button) findViewById(R.id.optionOne);
        optionTwo = (Button) findViewById(R.id.optionTwo);
        optionThree = (Button) findViewById(R.id.optionThree);
        optionFour = (Button) findViewById(R.id.optionFour);
        enter = (Button) findViewById(R.id.enter);

        // set click listeners
        optionOne.setOnClickListener(this);
        optionTwo.setOnClickListener(this);
        optionThree.setOnClickListener(this);
        optionFour.setOnClickListener(this);
        enter.setOnClickListener(this);

        // number of questions
        totalQuestions.setText("Number of Questions: " + numberOfQuestions);

        // load next question
        nextQuestion();
    }

    @Override
    public void onClick(View v) {
        optionOne.setBackgroundColor(Color.BLACK);
        optionTwo.setBackgroundColor(Color.BLACK);
        optionThree.setBackgroundColor(Color.BLACK);
        optionFour.setBackgroundColor(Color.BLACK);
        enter.setBackgroundColor(Color.BLACK);

        Button clickedButton = (Button) v;
        if (clickedButton.getId() == R.id.enter) {
            // increase score if right option selected
            if (selectedAnswer.equals(QuestionsAndAnswers.answers[currentQuestionIndex])) {
                score++;
            }
            // add to current question index
            currentQuestionIndex++;
            // load next question
            nextQuestion();
        }
        else {
            // option chosen
            selectedAnswer = clickedButton.getText().toString();
            clickedButton.setBackgroundColor(Color.CYAN);

        }



    }

    void nextQuestion() {
        // ending quiz
        if (currentQuestionIndex == numberOfQuestions) {
            endQuiz();
            return;
        }

        // set relevant questions and options
        question.setText(QuestionsAndAnswers.questions[currentQuestionIndex]);
        optionOne.setText(QuestionsAndAnswers.options[currentQuestionIndex][0]);
        optionTwo.setText(QuestionsAndAnswers.options[currentQuestionIndex][1]);
        optionThree.setText(QuestionsAndAnswers.options[currentQuestionIndex][2]);
        optionFour.setText(QuestionsAndAnswers.options[currentQuestionIndex][3]);


    }

    // ending of quiz
    void endQuiz(){
        String passOrFail = "";
        if (score > numberOfQuestions + 0.7) {
            passOrFail = "Pass";
        }
        else {
            passOrFail = "Fail";
        }

        // create dialog box
        new AlertDialog.Builder(this)
                .setTitle(passOrFail)
                .setMessage("You Achieved " + score+ " out of "+ numberOfQuestions)
                .setPositiveButton("Try Again", (dialogInterface, i) -> tryAgain())
                .setCancelable(false)
                .show();
    }

    // try again option
    void tryAgain() {
        score = 0;
        currentQuestionIndex = 0;
        nextQuestion();

    }
}