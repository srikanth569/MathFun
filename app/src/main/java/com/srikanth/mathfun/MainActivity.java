package com.srikanth.mathfun;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;


public class MainActivity extends ActionBarActivity implements View.OnClickListener {

    private Random rand = new Random();
    private int m = -1;
    private int n = -1;
    private int max = 100;
    private int min = 1;
    private int answer = -1;
    private ArrayList<Integer> answerChoices = null;
    private TextView mQuestionView = null;
    private Button mBtnNewQuestion = null;
    private Button mOption1 = null;
    private Button mOption2 = null;
    private Button mOption3 = null;
    private Button mOption4 = null;
    private int mode = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mQuestionView = (TextView) findViewById(R.id.question);
        mBtnNewQuestion = (Button) findViewById(R.id.new_question);
        mBtnNewQuestion.setOnClickListener(this);
        mOption1 = (Button) findViewById(R.id.option1);
        mOption2 = (Button) findViewById(R.id.option2);
        mOption3 = (Button) findViewById(R.id.option3);
        mOption4 = (Button) findViewById(R.id.option4);
        mOption1.setOnClickListener(this);
        mOption2.setOnClickListener(this);
        mOption3.setOnClickListener(this);
        mOption4.setOnClickListener(this);
        Intent intent = getIntent();
        mode = intent.getIntExtra(LauncherActivity.STRING_EXTRA, -1);

    }

    @Override
    protected void onResume() {
        super.onResume();
        setupQuestion();
        enableSelecting();
        setupAnswers();
    }

    private void setupQuestion() {
        generateTwoRandomNumbers();
        if (mode == LauncherActivity.ADDITION) {
            mQuestionView.setText(m + " + " + n);
        } else if (mode == LauncherActivity.DIVISION) {
            if (m > n) {
                mQuestionView.setText(m + " / " + n);
            } else {
                mQuestionView.setText(n + " / " + m);
            }

        } else if (mode == LauncherActivity.MULTIPLICATION) {
            mQuestionView.setText(m + " * " + n);
        } else if (mode == LauncherActivity.SUBTRACTION) {

            if (m > n) {
                mQuestionView.setText(m + " - " + n);
            } else {
                mQuestionView.setText(n + " - " + m);
            }

        }
    }

    private void generateTwoRandomNumbers() {
        m = rand.nextInt((max - min) + 1) + min;
        n = rand.nextInt((max - min) + 1) + min;
        if (m == 0 || n == 0) {
            generateTwoRandomNumbers();
        }
        if (mode == LauncherActivity.ADDITION) {
            answer = m + n;
        } else if (mode == LauncherActivity.DIVISION) {
            if (m > n) {
                answer = m / n;
            } else {
                answer = n / m;
            }
        } else if (mode == LauncherActivity.MULTIPLICATION) {
            generateTwoRandomNumbersMul();
            answer = m * n;
        } else if (mode == LauncherActivity.SUBTRACTION) {
            if (m > n) {
                answer = m - n;
            } else {
                answer = n - m;
            }

        }

        setupAnswers();
    }

    private void generateTwoRandomNumbersMul() {
        m = rand.nextInt((19) + 1) + 1;
        n = rand.nextInt((19) + 1) + 1;
    }

    private void setupAnswers() {
        if (answerChoices == null) {
            answerChoices = new ArrayList<>();
        }
        answerChoices.clear();
        if (mode == LauncherActivity.ADDITION) {
            answerChoices.add(Math.abs(m + n));
        } else if (mode == LauncherActivity.DIVISION) {
            answerChoices.add(Math.abs(m / n));
        } else if (mode == LauncherActivity.MULTIPLICATION) {
            answerChoices.add(Math.abs(m * n));
        } else if (mode == LauncherActivity.SUBTRACTION) {
            answerChoices.add(Math.abs(m - n));
        }

        answerChoices.add(Math.abs(answerChoices.get(0) - 1));
        answerChoices.add(Math.abs(answerChoices.get(0) + 1));
        answerChoices.add(Math.abs(answerChoices.get(0) + rand.nextInt((max - min) + 1) + min));
        Collections.shuffle(answerChoices);
        mOption1.setText(answerChoices.get(0) + "");
        mOption2.setText(answerChoices.get(1) + "");
        mOption3.setText(answerChoices.get(2) + "");
        mOption4.setText(answerChoices.get(3) + "");

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.new_question) {
            setupQuestion();
            enableSelecting();
        } else {
            disableSelecting();
            Button btn = (Button) v;
            int selectedAnswer = Integer.parseInt(btn.getText().toString());
            if (selectedAnswer == answer) {
                v.setBackgroundColor(getResources().getColor(R.color.green));
            } else {
                v.setBackgroundColor(getResources().getColor(R.color.red));
            }
        }

    }

    private void disableSelecting() {
        mOption1.setOnClickListener(null);
        mOption2.setOnClickListener(null);
        mOption3.setOnClickListener(null);
        mOption4.setOnClickListener(null);
    }

    private void enableSelecting() {
        mOption1.setOnClickListener(this);
        mOption1.setBackground(getResources().getDrawable(R.color.blue_1));
        mOption2.setOnClickListener(this);
        mOption2.setBackground(getResources().getDrawable(R.color.blue_2));
        mOption3.setOnClickListener(this);
        mOption3.setBackground(getResources().getDrawable(R.color.blue_3));
        mOption4.setOnClickListener(this);
        mOption4.setBackground(getResources().getDrawable(R.color.blue_4));
    }

}
