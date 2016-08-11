# SmartTextView
A smart TextView for android that detects #hashtags, @mentions, URLs, emails, phone numbers and more.


![device-2016-08-11-181532.png](https://s10.postimg.org/wvsifbftl/device_2016_08_11_181532.png)

<h1>Features</h1>

<ul>
  <li>Detect emails, mobile numbers, URLs</li>
  <li>Detect #hash_tags and @mentions</li>
  <li>Use default intents or set custom callbacks.</li>
  <li>Set different colors for each pattern.</li>
</ul>


<h1>Usage</h1>

In layout xml file 

```xml
  <com.wordpress.priyankvex.smarttextview.SmartTextView
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:textSize="9pt"
        android:id="@+id/textView"
        />
```

In java Activity or Fragment

```java
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
```

To use custom callbacks

```java
implements SmartTextCallback
```
