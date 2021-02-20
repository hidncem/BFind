package com.ctis487.team3.project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class SecondActivity extends AppCompatActivity {

    Spinner spinnerCategory;
    RecyclerView recyclerViewRec;
    MyRecyclerViewAdapter adapter;
    SpinnerAdapter spAdapter;
    ArrayList<Book> bookList, recommendList;
    Intent intent = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        // Hiding title bar using code
        getSupportActionBar().hide();

        intent = getIntent();
        bookList = intent.getParcelableArrayListExtra("bookList");

        //Log.d("Service", "LIST" + bookList);

        recyclerViewRec = findViewById(R.id.recyclerViewRec);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerViewRec.setLayoutManager(layoutManager);

        spinnerCategory = findViewById(R.id.spinnerCategory);
        spAdapter = new SpinnerAdapter(getBaseContext(), R.layout.spinner_layout, MainActivity.categoryList);
        spinnerCategory.setAdapter(spAdapter);

        spinnerCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                recommendList = new ArrayList<>();
                String selectedCategory = spinnerCategory.getSelectedItem().toString();
                Toast.makeText(SecondActivity.this, selectedCategory+" Kategorisinde Önerilen Kitaplar", Toast.LENGTH_SHORT).show();

                for (Book b : bookList) {
                    if (b.getCategory().equals(selectedCategory)) {
                        recommendList.add(b);
                    }
                }

                if (!recommendList.isEmpty()) {
                    adapter = new MyRecyclerViewAdapter(SecondActivity.this, recommendList);
                    recyclerViewRec.setAdapter(adapter);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }
}