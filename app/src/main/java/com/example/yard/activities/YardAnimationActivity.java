package com.example.yard.activities;

import android.animation.Animator;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.airbnb.lottie.LottieAnimationView;
import com.example.yard.R;

public class YardAnimationActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    Boolean animationFirstRun =
        getSharedPreferences("PREFERENCE", MODE_PRIVATE).getBoolean("isFirstRun", true);
    if (animationFirstRun) {
      setContentView(R.layout.yard_animation);
      Intent i = new Intent();
      i.setClass(getApplicationContext(), LoginActivity.class);
      LottieAnimationView lottieView = findViewById(R.id.YardAnimation);
      lottieView.addAnimatorListener(
          new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {}

            @Override
            public void onAnimationEnd(Animator animator) {
              // do here the action you want
              if (animationFirstRun) {
                // show start activity
                finish();
                startActivity(new Intent(YardAnimationActivity.this, LoginActivity.class));
              }
            }

            @Override
            public void onAnimationCancel(Animator animator) {}

            @Override
            public void onAnimationRepeat(Animator animator) {}
          });
    } else {
      startActivity(new Intent(YardAnimationActivity.this, LoginActivity.class));
    }
    getSharedPreferences("PREFERENCE", MODE_PRIVATE)
        .edit()
        .putBoolean("isFirstRun", false)
        .commit();
  }
}
