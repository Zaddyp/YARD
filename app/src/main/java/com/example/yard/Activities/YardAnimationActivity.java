package com.example.yard.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.ImageView;

import com.airbnb.lottie.LottieAnimationView;
import com.example.yard.R;

public class YardAnimationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        Boolean isFirstRun = getSharedPreferences("PREFERENCE", MODE_PRIVATE)
                .getBoolean("isFirstRun", true);




        if (isFirstRun){

            setContentView(R.layout.yard_animation);
            Intent i = new Intent();
            i.setClass(getApplicationContext(), LoginActivity.class);

            LottieAnimationView lottieView=findViewById(R.id.YardAnimation);
            lottieView.addAnimatorListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animator) {
                }

                @Override
                public void onAnimationEnd(Animator animator) {
                    // do here the action you want
                    if (isFirstRun) {
                        //show start activity
                        startActivity(new Intent(YardAnimationActivity.this, LoginActivity.class));
                    }

                }
                @Override
                public void onAnimationCancel(Animator animator) {

                }
                @Override
                public void onAnimationRepeat(Animator animator) {

                }
            });
        }
        else{
            startActivity(new Intent(YardAnimationActivity.this, LoginActivity.class));
        }

        getSharedPreferences("PREFERENCE", MODE_PRIVATE).edit()
                .putBoolean("isFirstRun", false).commit();


    }
    }


//    public void fade() {
//        LottieAnimationView logo = findViewById(R.id.YardAnimation);
//        logo.animate().alpha(0f).setDuration(1700);
//        Intent intent = new Intent(YardAnimationActivity.this, LoginActivity.class);
//        (intent);
//    }

//    CountDownTimer counter = new CountDownTimer(120000, 1000) {
//        @Override
//        public void onTick(long l) {
//
//        }
//
//        @Override
//        public void onFinish() {
//            Intent i = new Intent(YardAnimationActivity.this, LoginActivity.class);
//            startActivity(i);
//
//        }
//    };
