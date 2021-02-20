package com.ctis487.team3.project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

public class ThirdActivity extends AppCompatActivity {

    TextView tvFavList;
    DatabaseHelper dbHelper;
    ArrayList<Book> favbookList;
    RecyclerView favRecycler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        String res = "";
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);
        // Hiding title bar using code
        getSupportActionBar().hide();

        tvFavList = findViewById(R.id.txtFavoritesHeaders);

        tvFavList.setVisibility(View.INVISIBLE);

        favRecycler = findViewById(R.id.favoriteRecycler);

        dbHelper = new DatabaseHelper(this);

        favbookList = BookDB.getAllBook(dbHelper);

        if(favbookList.isEmpty()){
            tvFavList.setVisibility(View.VISIBLE);
            tvFavList.setText("Favori listeniz boş.");
        }else{
            tvFavList.setVisibility(View.INVISIBLE);
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
            favRecycler.setLayoutManager(layoutManager);

            RecyclerViewFavorite favAdapter = new RecyclerViewFavorite(this, favbookList);
            favRecycler.setAdapter(favAdapter);
        }
    }
}