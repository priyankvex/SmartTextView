package com.wordpress.priyankvex.smarttextviewdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.wordpress.priyankvex.smarttextview.SmartTextView;


public class MainActivity extends AppCompatActivity {

    SmartTextView mSmartTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mSmartTextView = (SmartTextView) findViewById(R.id.textView);
        mSmartTextView.setText(R.string.sample_text);
    }
}
