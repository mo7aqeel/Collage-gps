package com.example.collageproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;


public class Departments extends AppCompatActivity {

    private String[] floors = {"الطابق الأول", "الطابق الثاني"};

    private ArrayList<String> floor_one;

    private ArrayList<String> floor_two;

    private ListView li,
            oneListView, networkListView, softwareListView, mangeOneListView, securityListView,
            towListView, lecListView, labListView, mangeTowListView, floorTowMainListView;

    ArrayAdapter<String> arr, onearr, twoarr;


    private TextView textView, backText;
    private Button mButton;
    private String room;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_departments2);

        findViewById(R.id.searchEdit).setOnClickListener(view -> startActivity(new Intent(Departments.this, SearchActivity.class)));


        floor_one = QrScannerActivity.floorOneList;
        floor_two = QrScannerActivity.floorTowList;

        textView = findViewById(R.id.info_text);
        backText = findViewById(R.id.back_text);
        mButton = findViewById(R.id.detect_btn);

        mangeOneListView = findViewById(R.id.mangeOne_list);
        securityListView = findViewById(R.id.security_list);
        labListView = findViewById(R.id.lab_list);
        mangeTowListView = findViewById(R.id.mangeTow_list);
        floorTowMainListView = findViewById(R.id.floorTowMain_list);
        li = findViewById(R.id.floors_list);
        oneListView = findViewById(R.id.floor_one_list);
        towListView = findViewById(R.id.floor_two_list);
        networkListView = findViewById(R.id.network_list);
        softwareListView = findViewById(R.id.software_list);
        lecListView = findViewById(R.id.lect_list);

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Departments.this, FloorTowActivity.class);
                intent.putExtra("room", room);
                startActivity(intent);
            }
        });
        

        arr = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, floors);

        onearr = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, floor_one);

        twoarr = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, floor_two);



        li.setAdapter(arr);
        oneListView.setAdapter(onearr);
        towListView.setAdapter(twoarr);


        li.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (i == 0){
                    oneListView.setVisibility(View.VISIBLE);
                    towListView.setVisibility(View.GONE);
                    li.setVisibility(View.GONE);
                    networkListView.setVisibility(View.GONE);
                    lecListView.setVisibility(View.GONE);
                    softwareListView.setVisibility(View.GONE);
                    securityListView.setVisibility(View.GONE);
                    mangeOneListView.setVisibility(View.GONE);
                    mangeTowListView.setVisibility(View.GONE);
                    labListView.setVisibility(View.GONE);
                    floorTowMainListView.setVisibility(View.GONE);
                }
                else{
                    oneListView.setVisibility(View.GONE);
                    towListView.setVisibility(View.VISIBLE);
                    networkListView.setVisibility(View.GONE);
                    lecListView.setVisibility(View.GONE);
                    li.setVisibility(View.GONE);
                    softwareListView.setVisibility(View.GONE);
                    securityListView.setVisibility(View.GONE);
                    mangeOneListView.setVisibility(View.GONE);
                    mangeTowListView.setVisibility(View.GONE);
                    labListView.setVisibility(View.GONE);
                    floorTowMainListView.setVisibility(View.GONE);
                }
            }
        });

        oneListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            final Intent intent = new Intent(Departments.this, FloorOneActivity.class);
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (i == floor_one.size()-1){
                    oneListView.setVisibility(View.GONE);
                    li.setVisibility(View.VISIBLE);
                    towListView.setVisibility(View.GONE);
                    networkListView.setVisibility(View.GONE);
                    lecListView.setVisibility(View.GONE);
                    softwareListView.setVisibility(View.GONE);
                    securityListView.setVisibility(View.GONE);
                    mangeOneListView.setVisibility(View.GONE);
                    mangeTowListView.setVisibility(View.GONE);
                    labListView.setVisibility(View.GONE);
                    floorTowMainListView.setVisibility(View.GONE);
                    return;
                }
                else if (i == 0)
                    intent.putExtra("lab", "manage");

                else if (i == 1)
                    intent.putExtra("lab", "security");

                else if (i == 2)
                    intent.putExtra("lab", "software");

                else if (i == 3)
                    intent.putExtra("lab", "network");

                startActivity(intent);
            }
        });

        towListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(Departments.this, FloorTowActivity.class);
                if (i == floor_two.size()-1){
                    oneListView.setVisibility(View.GONE);
                    li.setVisibility(View.VISIBLE);
                    towListView.setVisibility(View.GONE);
                    networkListView.setVisibility(View.GONE);
                    lecListView.setVisibility(View.GONE);
                    softwareListView.setVisibility(View.GONE);
                    securityListView.setVisibility(View.GONE);
                    mangeOneListView.setVisibility(View.GONE);
                    mangeTowListView.setVisibility(View.GONE);
                    labListView.setVisibility(View.GONE);
                    floorTowMainListView.setVisibility(View.GONE);
                }
                else {
                    if (i == 0) {
                        intent.putExtra("room", "lects");
                    } else if (i == 1) {
                        intent.putExtra("room", "manage");
                    } else if (i == 2) {
                        intent.putExtra("room", "main");
                    } else if (i == 3)
                        intent.putExtra("room", "labs");
                    else if (i == 5)
                        intent.putExtra("room", "security");
                    else if (i == 6)
                        intent.putExtra("room", "inside");
                    startActivity(intent);
                }
            }
        });
    }
}