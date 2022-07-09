package com.example.yard.ParseApplication;
import android.app.Application;

import com.example.yard.Adapter.PostCreation;
import com.parse.Parse;
import com.parse.ParseObject;

public class ParseApplication extends Application {
  @Override
  public void onCreate() {
    super.onCreate();
    ParseObject.registerSubclass(PostCreation.class);
    Parse.initialize(
        new Parse.Configuration.Builder(this)
            .applicationId("1HvuN3fU38oQRUqJtloEorqF1UColFPyvyib7y9v")
            .clientKey("OTG8fYAf7w1aqCJhSSuZyQEsXGevMSub4iyVlNWN")
            .server("https://parseapi.back4app.com")
            .build());
  }
}
