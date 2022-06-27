package com.example.yard;

import com.parse.ParseClassName;
import com.parse.ParseObject;

@ParseClassName("User")
public class AccountCreation extends ParseObject {

    public static final String KEY_EMAIL = "email";
    public static final String KEY_PASSWORD = "password";
    public static final String KEY_CONFIRM = "confirm";

    public String getKeyEmail() {
        return getString(KEY_EMAIL);
    }
    public void setKeyEmail(String email){
        put(KEY_EMAIL, email);
    }


    public String getKeyPassword() {
        return getString(KEY_PASSWORD);
    }
    public void setKeyPassword(String password){
        put(KEY_PASSWORD, password);
    }


    public String getKeyConfirm() {
        return getString(KEY_CONFIRM);
    }
    public void setKeyConfirm(String confirm){
        put(KEY_PASSWORD, confirm);
    }

}
