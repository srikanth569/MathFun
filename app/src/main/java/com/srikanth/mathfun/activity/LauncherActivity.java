package com.srikanth.mathfun.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;

import com.srikanth.mathfun.R;

/**
 * Created by srikanth on 2/7/15.
 */
public class LauncherActivity extends ActionBarActivity implements View.OnClickListener {

    private Button mAddition;
    private Button mSubtraction;
    private Button mMultiplication;
    public static int ADDITION = 0;
    public static int SUBTRACTION = 1;
    public static int MULTIPLICATION = 2;
    private int activity_mode = -1;
    public static String STRING_EXTRA = "extra";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launcher);
        mAddition = (Button) findViewById(R.id.addition);
        mSubtraction = (Button) findViewById(R.id.subtraction);
        mMultiplication = (Button) findViewById(R.id.multiplication);
        mAddition.setOnClickListener(this);
        mSubtraction.setOnClickListener(this);
        mMultiplication.setOnClickListener(this);
        activity_mode = getIntent().getIntExtra(HomeScreenActivity.MODE, -1);
    }

    @Override
    public void onClick(View v) {
        Intent intent = null;
        if (activity_mode == HomeScreenActivity.mode_quiz) {
            intent = new Intent(this, QuizActivity.class);
            intent.putExtra(HomeScreenActivity.MODE, HomeScreenActivity.mode_quiz);
        } else if (activity_mode == HomeScreenActivity.mode_fastest25) {
            intent = new Intent(this, QuizActivity.class);
            intent.putExtra(HomeScreenActivity.MODE, HomeScreenActivity.mode_fastest25);
        } else if (activity_mode == HomeScreenActivity.mode_practice) {
            intent = new Intent(this, MainActivity.class);
        }
        if (v.getId() == R.id.addition) {
            intent.putExtra(STRING_EXTRA, ADDITION);
        } else if (v.getId() == R.id.subtraction) {
            intent.putExtra(STRING_EXTRA, SUBTRACTION);
        } else if (v.getId() == R.id.multiplication) {
            intent.putExtra(STRING_EXTRA, MULTIPLICATION);
        }
        startActivity(intent);
    }
}
