package com.srikanth.mathfun.activity;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.TextView;

import com.srikanth.mathfun.R;

/**
 * Created by srikanth on 2/14/15.
 */
public class ResultActivity extends ActionBarActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        String blah = getIntent().getStringExtra("BLAH");
        TextView tv = (TextView) findViewById(R.id.result_text);
        tv.setText("The result of this bullshit is " + blah);
    }
}
