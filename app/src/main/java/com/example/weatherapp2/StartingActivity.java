package com.example.weatherapp2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.airbnb.lottie.LottieAnimationView;
import com.airbnb.lottie.LottieDrawable;

public class StartingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_starting);

        LottieAnimationView animationView = findViewById(R.id.animationView);
        animationView.setAnimation(R.raw.day_night);

        animationView.setRepeatCount(LottieDrawable.INFINITE);
        animationView.playAnimation();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(StartingActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        }, 5000);

    }
}