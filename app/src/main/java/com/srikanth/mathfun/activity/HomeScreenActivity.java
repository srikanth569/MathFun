package com.srikanth.mathfun.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;

import com.srikanth.mathfun.R;

/**
 * Created by srikanth on 2/13/15.
 */
public class HomeScreenActivity extends ActionBarActivity implements View.OnClickListener {

    public static int mode_quiz = 1;
    public static int mode_fastest25 = 2;
    public static int mode_practice = 3;
    public static final String MODE = "mode";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
        Button button1 = (Button) findViewById(R.id.quiz);
        Button button2 = (Button) findViewById(R.id.fastest25);
        Button button3 = (Button) findViewById(R.id.practice);
        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        Intent intent = null;
        intent = new Intent(this, LauncherActivity.class);
        if (v.getId() == R.id.quiz) {
            intent.putExtra(MODE, mode_quiz);
        } else if (v.getId() == R.id.fastest25) {
            intent.putExtra(MODE, mode_fastest25);
        } else if (v.getId() == R.id.practice) {
            intent.putExtra(MODE, mode_practice);
        }

        startActivity(intent);
    }
}
