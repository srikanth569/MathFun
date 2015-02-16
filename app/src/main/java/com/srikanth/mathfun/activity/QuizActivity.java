package com.srikanth.mathfun.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.srikanth.mathfun.MathMode.AbstractMode;
import com.srikanth.mathfun.MathMode.AbstractModeFactory;
import com.srikanth.mathfun.R;

import java.util.ArrayList;

/**
 * Created by srikanth on 2/13/15.
 */
public class QuizActivity extends ActionBarActivity implements View.OnClickListener {

    private Handler mHandler = new Handler();
    private ProgressBar mProgressBar;
    private int Quiz_Delay_Timer = 600;
    private int mFastest25ProgressStatus = 0;
    private int mProgressStatus = 0;
    private ArrayList<Integer> mQuizTimings;
    private ArrayList<Integer> mFastest20Timings;
    private Button mOption1;
    private Button mOption2;
    private Button mOption3;
    private Button mOption4;
    // Variables
    private int answer = -1;
    private int mode = -1;
    private int quiz_mode = -1;
    private long passedTimeInMs = 0;
    private long startTimeinMS = 0;
    private long endTimeinMS = 0;
    private ArrayList<Integer> answerChoices = null;
    private AbstractMode mAbstractMode = null;

    // Views
    private TextView mQuestionView = null;
    private Button mBtnNewQuestion = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        mProgressBar = (ProgressBar) findViewById(R.id.quiz_progressbar);
        mOption1 = (Button) findViewById(R.id.quiz_option1);
        mOption2 = (Button) findViewById(R.id.quiz_option2);
        mOption3 = (Button) findViewById(R.id.quiz_option3);
        mOption4 = (Button) findViewById(R.id.quiz_option4);
        mQuestionView = (TextView) findViewById(R.id.quiz_question);
        mBtnNewQuestion = (Button) findViewById(R.id.quiz_new_question);
        mode = getIntent().getIntExtra(LauncherActivity.STRING_EXTRA, -1);
        quiz_mode = getIntent().getIntExtra(HomeScreenActivity.MODE, -1);
        mBtnNewQuestion.setOnClickListener(this);
        setupMode();
        setupUI();
    }

    private void setupMode() {
        if (HomeScreenActivity.mode_quiz == quiz_mode) {
            startProgressBar();
        } else if (HomeScreenActivity.mode_fastest25 == quiz_mode) {
            startTimeinMS = System.currentTimeMillis();
        }
    }


    private void startProgressBar() {
        new Thread(new Runnable() {
            public void run() {

                while (mProgressStatus < 100) {
                    mProgressStatus = doWork();

                    // Update the progress bar
                    mHandler.post(new Runnable() {
                        public void run() {
                            mProgressBar.setProgress(mProgressStatus);
                        }
                    });
                }
            }

        }).start();
    }

    private int doWork() {
        try {
            Thread.sleep(Quiz_Delay_Timer);
            mProgressStatus++;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (mProgressStatus == 100) {
            Intent intent = new Intent(QuizActivity.this, ResultActivity.class);
            startActivity(intent);
        }
        return mProgressStatus;
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
        boolean correctAnswer = false;
        if (v.getId() == R.id.quiz_new_question) {
            setupUI();
        } else {
            disableSelecting();
            Button btn = (Button) v;
            int selectedAnswer = Integer.parseInt(btn.getText().toString());
            if (selectedAnswer == answer) {
                correctAnswer = true;
                v.setBackgroundColor(getResources().getColor(R.color.green));
            } else {
                v.setBackgroundColor(getResources().getColor(R.color.red));
            }
            storeAnswerBasedOnMode(correctAnswer);
        }

    }

    private void storeAnswerBasedOnMode(boolean correctAnswer) {
        if (correctAnswer) {
            if (quiz_mode == HomeScreenActivity.mode_fastest25) {
                mFastest25ProgressStatus++;
                mProgressBar.setProgress(mFastest25ProgressStatus * 5);
                if (mFastest25ProgressStatus == 20) {
                    endTimeinMS = System.currentTimeMillis();
                    Intent intent = new Intent(QuizActivity.this, ResultActivity.class);
                    passedTimeInMs = endTimeinMS - startTimeinMS;
                    passedTimeInMs = passedTimeInMs / 1000;
                    intent.putExtra("BLAH", Long.toString(passedTimeInMs));
                    startActivity(intent);
                }
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
