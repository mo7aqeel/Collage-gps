package com.example.collageproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class FloorOneActivity extends AppCompatActivity {

    private ImageView[] images = new ImageView[33];

    private float mScale = 1f;
    private ScaleGestureDetector mScaleDetector;
    GestureDetector gestureDetector;
    private FirebaseDatabase database;
    private DatabaseReference myRef;

    private ArrayList<String> floorOneList ;
    private ArrayList<String> manageOneList;
    private ArrayList<String> securityList ;
    private ArrayList<String> softwareList ;
    private ArrayList<String> networkList ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_floor_two);

        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("الطابق الاول");

        floorOneList = QrScannerActivity.floorOneList;
        manageOneList = QrScannerActivity.manageOneList;
        securityList = QrScannerActivity.securityList;
        softwareList = QrScannerActivity.softwareList;
        networkList = QrScannerActivity.networkList;


        images[0] = findViewById(R.id.imageView27);
        images[1] = findViewById(R.id.imageView28);
        images[2] = findViewById(R.id.imageView29);
        images[3] = findViewById(R.id.imageView30);
        images[4] = findViewById(R.id.imageView31);
        images[5] = findViewById(R.id.imageView32);
        images[6] = findViewById(R.id.imageView33);
        images[7] = findViewById(R.id.imageView34);
        images[8] = findViewById(R.id.imageView35);
        images[9] = findViewById(R.id.imageView36);
        images[11-1] = findViewById(R.id.imageView38);
        images[12-1] = findViewById(R.id.imageView39);
        images[13-1] = findViewById(R.id.imageView40);
        images[14-1] = findViewById(R.id.imageView41);
        images[15-1] = findViewById(R.id.imageView42);
        images[16-1] = findViewById(R.id.imageView43);
        images[17-1] = findViewById(R.id.imageView44);
        images[18-1] = findViewById(R.id.imageView45);
        images[19-1] = findViewById(R.id.imageView46);
        images[20-1] = findViewById(R.id.imageView47);
        images[21-1] = findViewById(R.id.imageView48);
        images[22-1] = findViewById(R.id.imageView49);
        images[23-1] = findViewById(R.id.imageView50);
        images[24-1] = findViewById(R.id.imageView51);
        images[25-1] = findViewById(R.id.imageView52);
        images[26-1] = findViewById(R.id.imageView53);
        images[27-1] = findViewById(R.id.imageView54);
        images[28-1] = findViewById(R.id.imageView55);
        images[29-1] = findViewById(R.id.imageView56);
        images[30-1] = findViewById(R.id.imageView57);
        images[31-1] = findViewById(R.id.imageView58);
        images[32-1] = findViewById(R.id.imageView59);
        images[33-1] = findViewById(R.id.imageView60);

        zooming();

        (findViewById(R.id.button_floor_one)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(FloorOneActivity.this, QrScannerActivity.class).putExtra("floor", "one"));
            }
        });
        detect_place();

    }

    private void detect_place(){
        Intent intent = getIntent();
        ImageView netArrow, softArrow, manArrow;
        netArrow = findViewById(R.id.net_arrow);
        softArrow = findViewById(R.id.sof_arrow);
        manArrow = findViewById(R.id.manage_arrow);
        TextView infoText = findViewById(R.id.info_floor_one);

        if (intent.hasExtra("floor one")) {
            String s = intent.getStringExtra("floor one");
            for (int i=0; i<networkList.size(); i++){
                if (networkList.get(i).equals(s)){
                    infoText.setText(s);
                    infoText.setVisibility(View.VISIBLE);
                    break;
                }
            }
            for (int i=0; i<softwareList.size(); i++){
                if (softwareList.get(i).equals(s)){
                    infoText.setText(s);
                    infoText.setVisibility(View.VISIBLE);
                    break;
                }
            }
            for (int i=0; i<floorOneList.size(); i++){
                if (floorOneList.get(i).equals(s)){
                    infoText.setText(s);
                    infoText.setVisibility(View.VISIBLE);
                    break;
                }
            }
            for (int i=0; i<manageOneList.size(); i++){
                if (manageOneList.get(i).equals(s)){
                    infoText.setText(s);
                    infoText.setVisibility(View.VISIBLE);
                    break;
                }
            }
            for (int i=0; i<securityList.size(); i++){
                if (securityList.get(i).equals(s)){
                    infoText.setText(s);
                    infoText.setVisibility(View.VISIBLE);
                    break;
                }
            }
            if (!s.isEmpty()) {
                switch (s) {
                    case "قسم شبكات المعلومات قاعة 4":
                        images[10].setVisibility(View.VISIBLE);
                        break;
                    case "قسم شبكات المعلومات قاعة 1":
                        images[1].setVisibility(View.VISIBLE);
                        break;
                    case "رئيس قسم شبكات المعلومات و سكرتارية قسم شبكات المعلومات":
                        images[9].setVisibility(View.VISIBLE);
                        break;
                    case "قسم شبكات المعلومات قاعة 3":
                        images[2].setVisibility(View.VISIBLE);
                        break;
                    case "قسم شبكات المعلومات قاعة 2":
                        images[5].setVisibility(View.VISIBLE);
                        break;
                    case "قسم شبكات المعلومات قاعة 5":
                        images[3].setVisibility(View.VISIBLE);
                        break;
                    case "قسم شبكات المعلومات قاعة 6":
                        images[4].setVisibility(View.VISIBLE);
                        break;
                    case "قسم شبكات المعلومات قاعة السيمنار":
                        images[0].setVisibility(View.VISIBLE);
                        break;
                    case "سكرتارية قسم البرامجيات":
                        images[20].setVisibility(View.VISIBLE);
                        break;
                    case "مقرر قسم البرامجيات":
                        images[11].setVisibility(View.VISIBLE);
                        break;
                    case "المكتبة":
                        images[15].setVisibility(View.VISIBLE);
                        break;
                    case "قسم البرامجيات قاعة 1":
                        images[22].setVisibility(View.VISIBLE);
                        break;
                    case "قسم البرامجيات قاعة 2":
                        images[12].setVisibility(View.VISIBLE);
                        break;
                    case "قسم البرامجيات قاعة 3":
                        images[23].setVisibility(View.VISIBLE);
                        break;
                    case "قسم البرامجيات قاعة 4":
                        images[13].setVisibility(View.VISIBLE);
                        break;
                    case "اللجنة الامتحانية":
                        images[14].setVisibility(View.VISIBLE);
                        break;
                    case "قسم امنية المعلومات قاعة 1":
                        images[19].setVisibility(View.VISIBLE);
                        break;
                    case "قسم امنية المعلومات قاعة 2":
                        images[18].setVisibility(View.VISIBLE);
                        break;
                    case "قسم امنية المعلومات قاعة 3":
                        images[17].setVisibility(View.VISIBLE);
                        break;
                    case "قسم امنية المعلومات قاعة 4":
                        images[16].setVisibility(View.VISIBLE);
                        break;
                    case "الشؤون العلمية":
                        images[30].setVisibility(View.VISIBLE);
                        break;
                    case "الدراسات العليا":
                        images[31].setVisibility(View.VISIBLE);
                        break;
                    case "التسجيل":
                        images[28].setVisibility(View.VISIBLE);
                        break;
                    case "قاعة ماجستير 1":
                        images[24].setVisibility(View.VISIBLE);
                        break;
                    case "قاعة ماجستير 2":
                        images[25].setVisibility(View.VISIBLE);
                        break;
                }
            }
        }

        if (intent.hasExtra("lab")){
            switch (intent.getStringExtra("lab")){
                case "manage":
                    manArrow.setVisibility(View.VISIBLE);
                    break;
                case "software":
                    softArrow.setVisibility(View.VISIBLE);
                    break;
                case "network":
                    netArrow.setVisibility(View.VISIBLE);
                    break;
            }
        }
    }

    private void zooming(){
        gestureDetector = new GestureDetector(this, new GestureListener());
        mScaleDetector = new ScaleGestureDetector(this, new ScaleGestureDetector.SimpleOnScaleGestureListener() {
            @Override
            public boolean onScale(ScaleGestureDetector detector) {
                float scale = 1 - detector.getScaleFactor();


                float prevscale = mScale;
                mScale += scale;

                if (mScale < 0.1f)
                    mScale = 0.1f;


                if (mScale > 10f)
                    mScale = 10f;


                ScaleAnimation scaleAnimation = new ScaleAnimation(1f / prevscale, 1f / mScale, 1 / prevscale, 1f / mScale, detector.getFocusX(), detector.getFocusY());
                scaleAnimation.setDuration(0);
                scaleAnimation.setFillAfter(true);

                ConstraintLayout constraintLayout = (ConstraintLayout) findViewById(R.id.floor_two_layout);
                constraintLayout.startAnimation(scaleAnimation);
                return true;
            }
        });
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        super.dispatchTouchEvent(ev);
        mScaleDetector.onTouchEvent(ev);
        gestureDetector.onTouchEvent(ev);
        return gestureDetector.onTouchEvent(ev);

    }

    private class GestureListener extends GestureDetector.SimpleOnGestureListener {
        @Override
        public boolean onDown(MotionEvent e) {
            return true;
        }

        @Override
        public boolean onDoubleTap(MotionEvent e) {
            return true;
        }
    }
}