package com.srikanth.mathfun.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.srikanth.mathfun.MathMode.AbstractMode;
import com.srikanth.mathfun.MathMode.AbstractModeFactory;
import com.srikanth.mathfun.R;
import com.srikanth.mathfun.activity.LauncherActivity;

import java.util.ArrayList;


public class MainActivity extends ActionBarActivity implements View.OnClickListener {

    // Variables
    private int answer = -1;
    private int mode = -1;
    private ArrayList<Integer> answerChoices = null;
    private AbstractMode mAbstractMode = null;

    // Views
    private TextView mQuestionView = null;
    private Button mBtnNewQuestion = null;
    private Button mOption1 = null;
    private Button mOption2 = null;
    private Button mOption3 = null;
    private Button mOption4 = null;
    private ProgressBar mProgressBar = null;

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
        mAbstractMode = AbstractModeFactory.getMathMode(mode);

    }

    @Override
    protected void onResume() {
        super.onResume();
        setupUI();
    }

    private void setupUI() {
        if (mAbstractMode == null) {
            mAbstractMode = AbstractModeFactory.getMathMode(mode);
        }
        setupQuestion();
        setupAnswers();
        enableSelecting();
    }

    private void setupQuestion() {
        if (mAbstractMode != null) {
            mAbstractMode.setupQuestion();
            mQuestionView.setText(mAbstractMode.getmQuestionString());
        }
    }

    private void setupAnswers() {
        if (mAbstractMode != null) {
            mAbstractMode.setupAnswers();
            if (answerChoices == null) {
                answerChoices = new ArrayList<>(4);
            }
            answerChoices.clear();
            answerChoices.addAll(mAbstractMode.getAnswerList());
            answer = mAbstractMode.getmCorrectAnswer();
            mOption1.setText(answerChoices.get(0) + "");
            mOption2.setText(answerChoices.get(1) + "");
            mOption3.setText(answerChoices.get(2) + "");
            mOption4.setText(answerChoices.get(3) + "");
        }
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.new_question) {
            setupUI();
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
