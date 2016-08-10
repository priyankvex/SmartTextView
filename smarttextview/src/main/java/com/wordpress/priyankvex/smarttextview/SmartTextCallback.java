package com.wordpress.priyankvex.smarttextview;

/**
 * Created by @priyankvex on 10/8/16.
 */

public interface SmartTextCallback {

    void hashTagClick(String hashTag);
    void mentionClick(String mention);
    void emailClick(String email);
    void phoneNumberClick(String phoneNumber);
    void webUrlClick(String webUrl);
}
