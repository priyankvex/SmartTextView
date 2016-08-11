package com.wordpress.priyankvex.smarttextviewdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.wordpress.priyankvex.smarttextview.SmartTextCallback;
import com.wordpress.priyankvex.smarttextview.SmartTextView;


public class MainActivity extends AppCompatActivity implements SmartTextCallback{

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
        mSmartTextView.setPhoneNumberColorCode("#ff33aa");
        mSmartTextView.setHashTagColorCode("#f37735");
        mSmartTextView.setUrlColorCode("#ffc425");
        mSmartTextView.setMentionColorCode("#57b884");
        mSmartTextView.setDetectMentions(true);
        mSmartTextView.setDetectHashTags(true);
        mSmartTextView.setText(sampleText);
        mSmartTextView.setSmartTextCallback(this);
    }

    @Override
    public void emailClick(String email) {
        // Define your own email intent. If you don't use the SmartTextCallback, library will use the
        // inbuilt ones.
    }

    @Override
    public void hashTagClick(String hashTag) {
        // Do something with the hash tags
    }

    @Override
    public void webUrlClick(String webUrl) {
        // Define what happens on url click. Library has it own default if you don't implement SmartTextCallback.
    }

    @Override
    public void phoneNumberClick(String phoneNumber) {
        // Do something with the phone number. Library has it own default if you don't implement SmartTextCallback.
    }

    @Override
    public void mentionClick(String mention) {
        // Do something with the mention clicks
    }
}
