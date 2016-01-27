package com.wordpress.priyankvex.smarttextview;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by @priyankvex on 27/1/16.
 * SmartTextView class that is based on TextView.
 * It detects various patterns in text like phone numbers and emails etc
 * and sets clickable intents on them.
 */
public class SmartTextView extends TextView {

    Context mContext;

    public SmartTextView(Context context){
        super(context);
        this.mContext = context;
    }

    public SmartTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
    }
}
