package com.example.reminderapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    ProgressBar simpleProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        simpleProgressBar = (ProgressBar) findViewById(R.id.progressBar);

        simpleProgressBar.setMax(100);
        simpleProgressBar.setScaleY(2f);
        progressAnimation();
    }

    private void progressAnimation() {
        ProgressAnimation animation = new ProgressAnimation(this,simpleProgressBar,0f,100f);
        animation.setDuration(8000);
        simpleProgressBar.setAnimation(animation);
    }
}