package com.example.collageproject;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity {

    private static final int PERMISSION_REQUEST_CAMERA = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        ImageView img = findViewById(R.id.main_image);
        TextView text = findViewById(R.id.textView_splash);

        Animation top = AnimationUtils.loadAnimation(this, R.anim.img_anim);
        Animation bottom = AnimationUtils.loadAnimation(this, R.anim.text_anim);

        img.setAnimation(top);
        text.setAnimation(bottom);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashActivity.this, QrScannerActivity.class);
                startActivity(intent);
                finish();
            }
        }, 5000);
    }
}