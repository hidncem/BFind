package com.ctis487.team3.project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.ctis487.team3.project.top_fragment_package.TopFragment;

import java.util.ArrayList;

public class RandomActivity extends AppCompatActivity implements TopFragment.TopFragmentInterface{

    TopFragment topFragment;
    BottomFragment bottomFragment;
    ArrayList<Book> bookList;
    Intent intent = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_random);
        // Hiding title bar using code
        getSupportActionBar().hide();
        intent = getIntent();
        bookList = intent.getParcelableArrayListExtra("bookList");
        Log.d("FIX THIS AND DELETE", "hERE");
        Log.d("FIX THIS AND DELETE", bookList.get(0).getAuthor().toString());
        topFragment = (TopFragment) getSupportFragmentManager().findFragmentById(R.id.fragmentTop);

        bottomFragment = new BottomFragment();

        Bundle b = new Bundle();
        b.putParcelableArrayList("bookList", bookList);
        bottomFragment.setArguments(b);

        FragmentManager fragmentManager = this.getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.dynamicFragmentLayout, bottomFragment);
        fragmentTransaction.commit();
    }

    @Override
    public void setRandomBook(int position) {
        bottomFragment.setRandomBook(position);
    }
}