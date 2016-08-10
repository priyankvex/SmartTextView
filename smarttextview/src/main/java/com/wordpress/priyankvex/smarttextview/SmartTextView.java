package com.wordpress.priyankvex.smarttextview;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by @priyankvex on 27/1/16.
 * SmartTextView class that is based on TextView.
 * It detects various patterns in text like phone numbers and emails etc
 * and sets clickable intents on them.
 */
public class SmartTextView extends TextView {

    private Context mContext;
    private String mEmailColorCode;
    private String mUrlColorCode;
    private String mPhoneNumberColorCode;
    private String mMentionColorCode;
    private String mHashTagColorCode;
    private SmartTextCallback mSmartTextCallback;
    private boolean detectMentions = false;
    private boolean detectHashTags = false;

    public SmartTextView(Context context){
        super(context);
        this.mContext = context;
    }

    public SmartTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
        this.mEmailColorCode = "#3344ff";
        this.mUrlColorCode = "#3344ff";
        this.mPhoneNumberColorCode = "#3344ff";
        this.mMentionColorCode = "#3344ff";
        this.mHashTagColorCode = "#3344ff";
    }

    public void setText(String text){
        super.setText(text);
        Log.d("test", "Custom set text");

        SpannableString ss = new SpannableString(text);
        // Splitting the words by spaces
        String[] words = text.split(" ");
        for (String word : words){
            Log.d("test", word);
            word = word.replace("\n", "");

            if (word.matches("^([a-zA-Z0-9_\\-\\.]+)@([a-zA-Z0-9_\\-\\.]+)\\.([a-zA-Z]{2,5})$")){
                // the word is email. Set the email span
                int startIndex = text.indexOf(word);
                int endIndex = startIndex + word.length();
                final String finalWord = word;
                ClickableSpan emailClickSpan = new ClickableSpan() {
                    @Override
                    public void onClick(View widget) {
                        if (mSmartTextCallback == null){
                            Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                                    "mailto",finalWord, null));
                            mContext.startActivity(Intent.createChooser(emailIntent, "Send Email"));
                        }
                        else{
                            mSmartTextCallback.emailClick(finalWord);
                        }
                    }
                };
                ForegroundColorSpan emailColorSpan = new ForegroundColorSpan(Color.parseColor(mEmailColorCode));
                ss.setSpan(emailClickSpan, startIndex, endIndex, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                ss.setSpan(emailColorSpan, startIndex, endIndex, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                Log.d("test", "Email matched" + startIndex + " : " + endIndex);
            }
            else if (word.matches("(http:\\/\\/|https:\\/\\/|www.).{3,}")){
                // word is a URL
                int startIndex = text.indexOf(word);
                int endIndex = startIndex + word.length();
                final String finalWord1 = word.startsWith("http://") || word.startsWith("https://") ? word : "http://" + word;
                ClickableSpan urlClickSpan = new ClickableSpan() {
                    @Override
                    public void onClick(View widget) {
                        if (mSmartTextCallback == null){
                            Intent i = new Intent(Intent.ACTION_VIEW);
                            i.setData(Uri.parse(finalWord1));
                            mContext.startActivity(i);
                        }
                        else{
                            mSmartTextCallback.webUrlClick(finalWord1);
                        }
                    }
                };
                ForegroundColorSpan urlColorSpan = new ForegroundColorSpan(Color.parseColor(mUrlColorCode));
                ss.setSpan(urlClickSpan, startIndex, endIndex, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                ss.setSpan(urlColorSpan, startIndex, endIndex, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                Log.d("test", "Url matched" + startIndex + " : " + endIndex);
            }
            else if (word.matches("\\b\\d{3}[-.]?\\d{3}[-.]?\\d{4}\\b")){
                // word is a phone number
                int startIndex = text.indexOf(word);
                int endIndex = startIndex + word.length();
                final String finalWord2 = word;
                ClickableSpan numberClickSpan = new ClickableSpan() {
                    @Override
                    public void onClick(View widget) {
                        if (mSmartTextCallback == null){
                            Intent intent = new Intent(Intent.ACTION_DIAL);
                            intent.setData(Uri.parse("tel:" + finalWord2));
                            mContext.startActivity(intent);
                        }
                        else{
                            mSmartTextCallback.phoneNumberClick(finalWord2);
                        }
                    }
                };
                ForegroundColorSpan numberColorSpan = new ForegroundColorSpan(Color.parseColor(mPhoneNumberColorCode));
                ss.setSpan(numberClickSpan, startIndex, endIndex, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                ss.setSpan(numberColorSpan, startIndex, endIndex, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                Log.d("test", "Phone Number matched" + startIndex + " : " + endIndex);
            }
            else if (detectMentions && word.startsWith("@") && word.length() >= 2){
                // word is a mention
                int startIndex = text.indexOf(word);
                int endIndex = startIndex + word.length();
                final String finalWord3 = word;
                ClickableSpan mentionClickSpan = new ClickableSpan() {
                    @Override
                    public void onClick(View widget) {
                        if (mSmartTextCallback == null){
                            Log.e("test", "Implement and set setSmartTextCallback in your activity/fragment");
                        }
                        else{
                            mSmartTextCallback.mentionClick(finalWord3);
                        }
                        Toast.makeText(mContext, "Mention Clicked", Toast.LENGTH_SHORT).show();
                    }
                };
                ForegroundColorSpan mentionColorSpan = new ForegroundColorSpan(Color.parseColor(mMentionColorCode));
                ss.setSpan(mentionClickSpan, startIndex, endIndex, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                ss.setSpan(mentionColorSpan, startIndex, endIndex, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                Log.d("test", "Mention matched" + startIndex + " : " + endIndex);
            }
            else if (detectHashTags && word.startsWith("#") && word.length() >= 2){
                // word is a hash tag
                int startIndex = text.indexOf(word);
                int endIndex = startIndex + word.length();
                ClickableSpan hashTagClickSpan = new ClickableSpan() {
                    @Override
                    public void onClick(View widget) {
                        Toast.makeText(mContext, "HashTag Clicked", Toast.LENGTH_SHORT).show();
                    }
                };
                ForegroundColorSpan hashTagColorSpan = new ForegroundColorSpan(Color.parseColor(mHashTagColorCode));
                ss.setSpan(hashTagClickSpan, startIndex, endIndex, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                ss.setSpan(hashTagColorSpan, startIndex, endIndex, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                Log.d("test", "Hash tag matched" + startIndex + " : " + endIndex);
            }
        }

        super.setText(ss);
        super.setMovementMethod(LinkMovementMethod.getInstance());
    }


    public void setEmailColorCode(String emailColorCode) {
        this.mEmailColorCode = emailColorCode;
    }

    public void setUrlColorCode(String urlColorCode){
        this.mUrlColorCode = urlColorCode;
    }

    public void setPhoneNumberColorCode(String phoneNumberColorCode){
        this.mPhoneNumberColorCode = phoneNumberColorCode;
    }

    public void setSmartTextCallback(SmartTextCallback mSmartTextCallback) {
        this.mSmartTextCallback = mSmartTextCallback;
    }

    public void setDetectMentions(boolean value){
        this.detectMentions = value;
    }

    public void setDetectHashTags(boolean value){
        this.detectHashTags = value;
    }
}
