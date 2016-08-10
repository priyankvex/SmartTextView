package com.wordpress.priyankvex.smarttextviewdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.wordpress.priyankvex.smarttextview.SmartTextCallback;
import com.wordpress.priyankvex.smarttextview.SmartTextView;


public class MainActivity extends AppCompatActivity implements SmartTextCallback, View.OnClickListener{

    SmartTextView mSmartTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        String sampleText = getString(R.string.sample_text);

        mSmartTextView = (SmartTextView) findViewById(R.id.textView);
        mSmartTextView.setEmailColorCode("#3cb371");
        mSmartTextView.setDetectMentions(true);
        mSmartTextView.setDetectHashTags(true);
        mSmartTextView.setText(sampleText);
        //mSmartTextView.setSmartTextCallback(this);
    }

    @Override
    public void emailClick(String email) {
        Toast.makeText(getApplicationContext(), email, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void hashTagClick(String hashTag) {

    }

    @Override
    public void webUrlClick(String webUrl) {

    }

    @Override
    public void phoneNumberClick(String phoneNumber) {

    }

    @Override
    public void mentionClick(String mention) {

    }

    @Override
    public void onClick(View v) {

    }
}
