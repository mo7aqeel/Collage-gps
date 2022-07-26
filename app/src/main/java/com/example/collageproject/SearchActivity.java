package com.example.collageproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SearchView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class SearchActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {

    ListView list;
    ListViewAdapter adapter;
    SearchView editsearch;
    ArrayList<String> arraylist = new ArrayList<String>();
    private DatabaseReference myRef2;
    private DatabaseReference myRef;
    private FirebaseDatabase database;
    private boolean isFound = false;
    private StringBuilder room;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        room = new StringBuilder();

        database = FirebaseDatabase.getInstance();
        myRef2 = database.getReference("الطابق الثاني");
        myRef = database.getReference("الطابق الاول");

        list = (ListView) findViewById(R.id.listviewsearch);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                myRef2.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for (DataSnapshot n : snapshot.getChildren()) {
                            if (n.getKey().equals("التدرسيين")) {
                                for (DataSnapshot n2 : n.getChildren()) {
                                    for (DataSnapshot n3 : n2.getChildren()) {
                                        if (n3.getValue(String.class).equals(arraylist.get(i))) {
                                            Intent intent = new Intent(SearchActivity.this, FloorTowActivity.class);
                                            intent.putExtra("room", n2.getKey());
                                            startActivity(intent);
                                            isFound = true;
                                        }
                                    }
                                }
                            }
                            else{
                                for (DataSnapshot n2 : n.getChildren()) {
                                        if (n2.getValue(String.class).equals(arraylist.get(i))) {
                                            Intent intent = new Intent(SearchActivity.this, FloorTowActivity.class);
                                            intent.putExtra("room", n2.getKey());
                                            startActivity(intent);
                                            isFound = true;
                                        }}
                            }
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

                if (!isFound){
                    myRef.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            for (DataSnapshot n : snapshot.getChildren()) {
                                for (DataSnapshot n2 : n.getChildren()) {
                                    if (n2.getValue(String.class).equals(arraylist.get(i))) {
                                        Intent intent = new Intent(SearchActivity.this, FloorOneActivity.class);
                                        intent.putExtra("floor one", n2.getValue(String.class));
                                        startActivity(intent);
                                    }}}
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                }
            }
        });

        arraylist = QrScannerActivity.mainList;

        // Pass results to ListViewAdapter Class.
        adapter = new ListViewAdapter(SearchActivity.this, arraylist);

        // Binds the Adapter to the ListView
        list.setAdapter(adapter);

        // Locate the EditText in listview_main.xml
        editsearch = (SearchView) findViewById(R.id.search);
        editsearch.setOnQueryTextListener(SearchActivity.this);
    }

    @Override
    public boolean onQueryTextSubmit(String query) {

        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        list.setVisibility(View.VISIBLE);
        String text = newText;
        adapter.filter(text);
        return false;
    }

}