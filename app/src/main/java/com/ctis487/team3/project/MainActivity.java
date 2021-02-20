package com.ctis487.team3.project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MainActivity extends AppCompatActivity {

    IntentFilter mIntentFilter;

    ArrayList<Book> bookList;
    RecyclerView recyclerViewBooks;
    MediaPlayer mp;
    DatabaseHelper dbHelper;
    Button btnrec, btnMyFavList;

    //To store all distinct categries
    public static ArrayList<String> categoryList = new ArrayList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Hiding title bar using code
        getSupportActionBar().hide();

        dbHelper = new DatabaseHelper(this);

        btnrec = findViewById(R.id.btnrec);
        btnMyFavList = findViewById(R.id.btnMyFavList);

        recyclerViewBooks = findViewById(R.id.recyclerViewBooks);
        mp = MediaPlayer.create(this, R.raw.rolldice);

        //RecyclerView.LayoutManager  layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerViewBooks.setLayoutManager(layoutManager);

        mIntentFilter = new IntentFilter();
        mIntentFilter.addAction("BOOK_JSON_PARSE_ACTION");
        registerReceiver(mIntentReceiver, mIntentFilter);

        Intent intent = new Intent(this, ParseBookJSONService.class);
        intent.putExtra("filename", "books.json");
        startService(intent);

    }

    private BroadcastReceiver mIntentReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            // Storing the received data into a Bundle
            Log.d("Service", "Braodcast message taken");
            Bundle b = intent.getExtras();
            bookList = b.getParcelableArrayList("booklist");
            fillListView();
        }
    };

    void fillListView() {
        MyRecyclerViewAdapter adapter = new MyRecyclerViewAdapter(this, bookList);
        recyclerViewBooks.setAdapter(adapter);
    }

    public void onClick(View view) {
        Intent intent = new Intent();
        if (view.getId() == R.id.btnrec) {
            intent = new Intent(this, SecondActivity.class);
            intent.putParcelableArrayListExtra("bookList", bookList);

        } else if (view.getId() == R.id.btnMyFavList) {
            intent = new Intent(this, ThirdActivity.class);
        }else if(view.getId() == R.id.randomBtn){
            mp.start();
            intent = new Intent(this, RandomActivity.class);
            intent.putParcelableArrayListExtra("bookList", bookList);
        }
        startActivity(intent);
    }

}