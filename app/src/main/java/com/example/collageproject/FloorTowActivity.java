package com.example.collageproject;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.view.animation.ScaleAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class FloorTowActivity extends AppCompatActivity {

    private float mScale = 1f;
    private ScaleGestureDetector mScaleDetector;
    GestureDetector gestureDetector;
    private FirebaseDatabase database;
    private DatabaseReference myRef;
    private TextView lectsName, roomNumber;

    Button detBtn;
    Button[] buttons = new Button[19];
    ImageView[] imageViews = new ImageView[22];
    ImageView arrwoLect, arrwoRight, arrwoRight2, arrowDown, arrowDown2, arrowUp;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_floors);

        roomNumber = findViewById(R.id.textView_room_number);
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("الطابق الثاني").child("التدرسيين");
        lectsName = findViewById(R.id.lect_names_textview);
        detBtn = findViewById(R.id.details_btn);

        buttons[0] = findViewById(R.id.button1);
        buttons[1] = findViewById(R.id.button2);
        buttons[2] = findViewById(R.id.button3);
        buttons[3] = findViewById(R.id.button4);
        buttons[4] = findViewById(R.id.button5);
        buttons[5] = findViewById(R.id.button6);
        buttons[6] = findViewById(R.id.button7);
        buttons[7] = findViewById(R.id.button8);
        buttons[8] = findViewById(R.id.button9);
        buttons[9] = findViewById(R.id.button10);
        buttons[10] = findViewById(R.id.button11);
        buttons[11] = findViewById(R.id.button12);
        buttons[12] = findViewById(R.id.button13);
        buttons[13] = findViewById(R.id.button14);
        buttons[14] = findViewById(R.id.button15);
        buttons[15] = findViewById(R.id.button16);
        buttons[16] = findViewById(R.id.button17);
        buttons[17] = findViewById(R.id.button18);
        buttons[18] = findViewById(R.id.button19);

        imageViews[0] = findViewById(R.id.imageView);
        imageViews[1] = findViewById(R.id.imageView2);
        imageViews[2] = findViewById(R.id.imageView3);
        imageViews[3] = findViewById(R.id.imageView4);
        imageViews[4] = findViewById(R.id.imageView5);
        imageViews[5] = findViewById(R.id.imageView6);
        imageViews[6] = findViewById(R.id.imageView7);
        imageViews[7] = findViewById(R.id.imageView8);
        imageViews[8] = findViewById(R.id.imageView9);
        imageViews[9] = findViewById(R.id.imageView10);
        imageViews[10] = findViewById(R.id.imageView11);
        imageViews[11] = findViewById(R.id.imageView12);
        imageViews[12] = findViewById(R.id.imageView13);
        imageViews[13] = findViewById(R.id.imageView14);
        imageViews[14] = findViewById(R.id.imageView15);
        imageViews[15] = findViewById(R.id.imageView16);
        imageViews[16] = findViewById(R.id.imageView17);
        imageViews[17] = findViewById(R.id.imageView18);
        imageViews[18] = findViewById(R.id.imageView19);
        imageViews[19] = findViewById(R.id.imageView20);
        imageViews[20] = findViewById(R.id.imageView21);
        imageViews[21] = findViewById(R.id.imageView22);

        arrwoLect = findViewById(R.id.arrow_lects);
        arrowDown = findViewById(R.id.arrow_down);
        arrowUp = findViewById(R.id.arrow_up);
        arrowDown2 = findViewById(R.id.arrow_down2);
        arrwoRight = findViewById(R.id.arrow_right);
        arrwoRight2 = findViewById(R.id.arrow_right2);


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

                ConstraintLayout constraintLayout = (ConstraintLayout) findViewById(R.id.my_layout);
                constraintLayout.startAnimation(scaleAnimation);
                return true;
            }
        });
        detect_place_room();
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void detect_place_room(){
        Intent intent = getIntent();
        String c = intent.getStringExtra("room");
        if (!c.isEmpty()) {
            StringBuilder builder = new StringBuilder();
            myRef.child(c).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    lectsName.setVisibility(View.VISIBLE);
                    roomNumber.setText(snapshot.getKey());
                    for (DataSnapshot n : snapshot.getChildren()) {
                        builder.append(n.getValue(String.class)).append("\n");
                    }
                    lectsName.setText(builder.toString());
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
            switch (c) {
                case "غرفة 1":
                    buttons[0].setVisibility(View.VISIBLE);
                    break;
                case "غرفة 2":
                    buttons[11].setVisibility(View.VISIBLE);
                    break;
                case "غرفة 3":
                    buttons[12].setVisibility(View.VISIBLE);
                    break;
                case "غرفة 4":
                    buttons[1].setVisibility(View.VISIBLE);
                    break;
                case "غرفة 5":
                    buttons[13].setVisibility(View.VISIBLE);
                    break;
                case "غرفة 6":
                    buttons[2].setVisibility(View.VISIBLE);
                    break;
                case "غرفة 7":
                    buttons[3].setVisibility(View.VISIBLE);
                    break;
                case "غرفة 8":
                    buttons[14].setVisibility(View.VISIBLE);
                    break;
                case "غرفة 9":
                    buttons[4].setVisibility(View.VISIBLE);
                    break;
                case "غرفة 10":
                    buttons[15].setVisibility(View.VISIBLE);
                    break;
                case "غرفة 11":
                    buttons[16].setVisibility(View.VISIBLE);
                    break;
                case "غرفة 12":
                    buttons[5].setVisibility(View.VISIBLE);
                    break;
                case "غرفة 13":
                    buttons[6].setVisibility(View.VISIBLE);
                    break;
                case "غرفة 14":
                    buttons[7].setVisibility(View.VISIBLE);
                    break;
                case "غرفة 15":
                    buttons[17].setVisibility(View.VISIBLE);
                    break;
                case "غرفة 16":
                    buttons[18].setVisibility(View.VISIBLE);
                    break;
                case "غرفة 17":
                    buttons[8].setVisibility(View.VISIBLE);
                    break;

                case "lects":
                    arrwoLect.setVisibility(View.VISIBLE);
                    break;
                case "labs":
                    arrwoRight.setVisibility(View.VISIBLE);
                    break;
                case "قسم شبكات المعلومات مختبر 1":
                    imageViews[5].setVisibility(View.VISIBLE);
                    break;
                case "قسم شبكات المعلومات مختبر 2":
                    imageViews[6].setVisibility(View.VISIBLE);
                    break;
                case "قسم شبكات المعلومات مختبر 3":
                    imageViews[7].setVisibility(View.VISIBLE);
                    break;
                case "قسم شبكات المعلومات مختبر 4":
                    imageViews[8].setVisibility(View.VISIBLE);
                    break;
                case "قسم البرامجيات مختبر 1":
                    imageViews[9].setVisibility(View.VISIBLE);
                    break;
                case "قسم البرامجيات مختبر 2":
                    imageViews[10].setVisibility(View.VISIBLE);
                    break;
                case "قسم البرامجيات مختبر 3":
                    imageViews[11].setVisibility(View.VISIBLE);
                    break;
                case "قسم البرامجيات مختبر 4":
                    imageViews[1].setVisibility(View.VISIBLE);
                    break;
                case "قسم الامنية مختبر 1":
                    imageViews[13].setVisibility(View.VISIBLE);
                    break;
                case "قسم الامنية مختبر 2":
                    imageViews[14].setVisibility(View.VISIBLE);
                    break;
                case "قسم الامنية مختبر 3":
                    imageViews[15].setVisibility(View.VISIBLE);
                    break;
                case "قسم الامنية مختبر 4":
                    imageViews[16].setVisibility(View.VISIBLE);
                    break;
                case "شعبة الرقابة والتدقيق الداخلي":
                    imageViews[17].setVisibility(View.VISIBLE);
                    break;
                case "المعاون الاداري":
                    imageViews[18].setVisibility(View.VISIBLE);
                    break;
                case "الحسابات":
                    imageViews[21].setVisibility(View.VISIBLE);
                    break;
                case "القانونية":
                    imageViews[19].setVisibility(View.VISIBLE);
                    break;
                case "الصادرة والواردة":
                    imageViews[20].setVisibility(View.VISIBLE);
                case "manage":
                    arrowDown.setVisibility(View.VISIBLE);
                    break;
                case "غرفة العميد":
                    arrowUp.setVisibility(View.VISIBLE);
                    break;
                case "security":
                    arrwoRight2.setVisibility(View.VISIBLE);
                    break;
                case "inside":
                    arrowDown2.setVisibility(View.VISIBLE);
                    break;
                case "امانة مجلس الكلية":
                    imageViews[0].setVisibility(View.VISIBLE);
                    break;
                case "لجنة الترقيات":
                    imageViews[12].setVisibility(View.VISIBLE);
                    break;
                case "غرفة المعيدات":
                    imageViews[11].setVisibility(View.VISIBLE);
                    break;
                case "ادارة الجودة الشاملة والاعتمادية":
                    imageViews[4].setVisibility(View.VISIBLE);
                    break;
            }
        }
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        super.dispatchTouchEvent(ev);
        mScaleDetector.onTouchEvent(ev);
        gestureDetector.onTouchEvent(ev);
        return gestureDetector.onTouchEvent(ev);

    }

    public void startDetails(View view) {
        startActivity(new Intent(FloorTowActivity.this, QrScannerActivity.class).putExtra("floor", "two"));
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