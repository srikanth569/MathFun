package com.srikanth.mathfun;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by srikanth on 2/7/15.
 */
public class LauncherActivity extends ActionBarActivity implements View.OnClickListener {

    private Button mAddition;
    private Button mSubtraction;
    private Button mDivision;
    private Button mMultiplication;
    public static int ADDITION = 0;
    public static int SUBTRACTION = 1;
    public static int DIVISION = 2;
    public static int MULTIPLICATION = 3;
    public static String STRING_EXTRA = "extra";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launcher);
        mAddition = (Button) findViewById(R.id.addition);
        mSubtraction = (Button) findViewById(R.id.subtraction);
        //mDivision = (Button) findViewById(R.id.division);
        mMultiplication = (Button) findViewById(R.id.multiplication);
        mAddition.setOnClickListener(this);
        mSubtraction.setOnClickListener(this);
//        mDivision.setOnClickListener(this);
        mMultiplication.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this, MainActivity.class);
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
