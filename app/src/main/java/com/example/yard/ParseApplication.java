package com.example.yard;

import android.app.Application;
import com.parse.Parse;
import com.parse.ParseObject;

public class ParseApplication extends Application{
    @Override
    public void onCreate() {
        super.onCreate();
        ParseObject.registerSubclass(PostCreation.class);
        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("1HvuN3fU38oQRUqJtloEorqF1UColFPyvyib7y9v")
                .clientKey("OTG8fYAf7w1aqCJhSSuZyQEsXGevMSub4iyVlNWN")
                .server("https://parseapi.back4app.com")
                .build()
        );
    }
}