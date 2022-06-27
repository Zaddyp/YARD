package com.example.yard;

import android.app.Application;

import com.parse.Parse;

public class ParseApplication extends Application{

    @Override
    public void onCreate() {
        super.onCreate();
        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("1HvuN3fU38oQRUqJtloEorqF1UColFPyvyib7y9v")
                .clientKey("OTG8fYAf7w1aqCJhSSuZyQEsXGevMSub4iyVlNWN")
                .server("https://parseapi.back4app.com")
                .build()
        );
    }
}
