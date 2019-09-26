package com.example.team;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.AnimationSet;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private ImageView mIva;
    private ImageView mIvb;
    private CountDownTimer start;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        getSupportActionBar().hide();
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        AlphaAnimation alphaAnimation = new AlphaAnimation(0, 1);
        //动画停留最后一针
        alphaAnimation.setFillAfter(true);
        AnimationSet animationSet = new AnimationSet(false);
        animationSet.addAnimation(alphaAnimation);
        animationSet.setDuration(1500);
        mIva.startAnimation(animationSet);
        mIvb.startAnimation(animationSet);
        start = new CountDownTimer(1000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {
                startActivity(new Intent(MainActivity.this, HomeActivity.class));
                finish();
            }
        }.start();
    }

    private void initView() {
        mIva = findViewById(R.id.iva);
        mIvb = findViewById(R.id.ivb);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (start != null) {
            start.cancel();
        }
    }
}
