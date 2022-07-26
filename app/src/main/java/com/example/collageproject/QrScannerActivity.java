package com.example.collageproject;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.camera.core.CameraSelector;
import androidx.camera.core.ImageAnalysis;
import androidx.camera.core.Preview;
import androidx.camera.lifecycle.ProcessCameraProvider;
import androidx.camera.view.PreviewView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.LifecycleOwner;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Size;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.common.util.concurrent.ListenableFuture;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class QrScannerActivity extends AppCompatActivity {

    private PreviewView previewView;
    private ListenableFuture<ProcessCameraProvider> cameraProviderFuture;
    private String qrCode;
    private static final int PERMISSION_REQUEST_CAMERA = 0;
    private Button qrCodeFoundButton, roomFound, floorOneBtn;

    public static ArrayList<String> floorsList = new ArrayList<>();

    public static ArrayList<String> floorOneList = new ArrayList<>();
    public static ArrayList<String> manageOneList = new ArrayList<>();
    public static ArrayList<String> securityList = new ArrayList<>();
    public static ArrayList<String> softwareList = new ArrayList<>();
    public static ArrayList<String> networkList = new ArrayList<>();

    public static ArrayList<String> mainList = new ArrayList<>();

    public static ArrayList<String> floorTowList = new ArrayList<>();
    public static ArrayList<String> floorTowMainList = new ArrayList<>();
    public static ArrayList<String> lecList = new ArrayList<>();
    public static ArrayList<String> labList = new ArrayList<>();
    public static ArrayList<String> manageTowList = new ArrayList<>();

    private DatabaseReference myRef1;
    private DatabaseReference myRef2;
    private FirebaseDatabase database;


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qr_scanner);

        previewView = findViewById(R.id.activity_main_previewView);
        qrCodeFoundButton = findViewById(R.id.qrCodeFoundButton);
        roomFound = findViewById(R.id.room_found);
        floorOneBtn = findViewById(R.id.floor_one_btn);
        qrCodeFoundButton.setVisibility(View.GONE);
        qrCodeFoundButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(QrScannerActivity.this, Departments.class);
                //intent.putExtra("search", qrCode);
                startActivity(intent);
            }
        });
        cameraProviderFuture = ProcessCameraProvider.getInstance(this);
        requestCamera();
        startCamera();

        database = FirebaseDatabase.getInstance();
        myRef1 = database.getReference("الطابق الاول");
        myRef1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot n : snapshot.getChildren()){
                    if (!n.getKey().equals("يرجوع"))
                        floorOneList.add(n.getKey());
                    else
                        floorOneList.add(n.getValue(String.class));
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        myRef2 = database.getReference("الطابق الثاني");
        myRef2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot n : snapshot.getChildren()){
                    if (!n.getKey().equals("يرجوع"))
                        floorTowList.add(n.getKey());
                    else
                        floorTowList.add(n.getValue(String.class));
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        valueEvent(myRef2, "الرئيسي", floorTowMainList);
        valueEvent(myRef2, "التدرسيين", lecList);
        valueEvent(myRef2, "المختبرات", labList);
        valueEvent(myRef2, "الجناح الاداري", manageTowList);

        valueEvent(myRef1, "الجناح الاداري", manageOneList);
        valueEvent(myRef1, "قسم الامنية", securityList);
        valueEvent(myRef1, "قسم البرامجيات", softwareList);
        valueEvent(myRef1, "قسم الشبكات", networkList);

    }//end onCreate()

    private void valueEvent(DatabaseReference myRef, String key, ArrayList<String> list){
        if (key.equals("التدرسيين")) {
            myRef.child(key).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    for (DataSnapshot n : snapshot.getChildren()) {
                        if (n.hasChildren()) {
                            for (DataSnapshot n2 : n.getChildren()) {
                                if (!n2.getValue(String.class).equals("رجوع")) {
                                    list.add(n2.getValue(String.class));
                                    mainList.add(n2.getValue(String.class));
                                }
                            }
                        }else
                            list.add(n.getValue(String.class));
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }
        else {
            myRef.child(key).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    for (DataSnapshot n : snapshot.getChildren()) {
                        if (!n.getValue(String.class).equals("رجوع")) {
                            list.add(n.getValue(String.class));
                            mainList.add(n.getValue(String.class));
                        }
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }

    }

    private void requestCamera() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
            return;
        } else {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.CAMERA)) {
                ActivityCompat.requestPermissions(QrScannerActivity.this, new String[]{Manifest.permission.CAMERA}, PERMISSION_REQUEST_CAMERA);
            } else {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, PERMISSION_REQUEST_CAMERA);
            }
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void startCamera() {
        cameraProviderFuture.addListener(() -> {
            try {
                ProcessCameraProvider cameraProvider = cameraProviderFuture.get();
                bindCameraPreview(cameraProvider);
            } catch (ExecutionException | InterruptedException e) {
                Toast.makeText(this, "Error starting camera " + e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }, ContextCompat.getMainExecutor(this));
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void bindCameraPreview(@NonNull ProcessCameraProvider cameraProvider) {
        previewView.setPreferredImplementationMode(PreviewView.ImplementationMode.SURFACE_VIEW);

        Preview preview = new Preview.Builder()
                .build();

        CameraSelector cameraSelector = new CameraSelector.Builder()
                .requireLensFacing(CameraSelector.LENS_FACING_BACK)
                .build();

        preview.setSurfaceProvider(previewView.createSurfaceProvider());

        ImageAnalysis imageAnalysis =
                new ImageAnalysis.Builder()
                        .setTargetResolution(new Size(1280, 720))
                        .setBackpressureStrategy(ImageAnalysis.STRATEGY_KEEP_ONLY_LATEST)
                        .build();

        imageAnalysis.setAnalyzer(ContextCompat.getMainExecutor(this), new QRCodeImageAnalyzer(new QRCodeFoundListener() {
            @Override
            public void onQRCodeFound(String _qrCode) {
                Intent intent = getIntent();
                qrCode = _qrCode;
                if (intent.hasExtra("floor")){
                    String floor = intent.getStringExtra("floor");
                    if (floor.equals("one")){
                        floorOneBtn.setVisibility(View.VISIBLE);
                    }
                    else
                        roomFound.setVisibility(View.VISIBLE);
                }
                else
                    qrCodeFoundButton.setVisibility(View.VISIBLE);
            }

            @Override
            public void qrCodeNotFound() {
            }
        }));

        cameraProvider.bindToLifecycle((LifecycleOwner)this, cameraSelector, imageAnalysis, preview);
    }

    public void startFloor(View view) {
        Intent intent = new Intent(QrScannerActivity.this, FloorTowActivity.class);
        intent.putExtra("room", qrCode);
        startActivity(intent);
    }

    public void startFloorOne(View view) {
        Intent intent = new Intent(QrScannerActivity.this, FloorOneActivity.class);
        intent.putExtra("floor one", qrCode);
        startActivity(intent);
    }
}