package com.ctis487.team3.project;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Random;


public class BottomFragment extends Fragment {

    TextView frgName, frgAuthor;
    ImageView frgrandomImage;
    ArrayList<Book> bookArrayList;



    public BottomFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getArguments()!= null){
            bookArrayList = getArguments().getParcelableArrayList("bookList");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_bottom, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        frgName = view.findViewById(R.id.bookNameTxt);
        frgAuthor = view.findViewById(R.id.authorTxt);
        frgrandomImage = view.findViewById(R.id.imgRandom);

        Random random = new Random();
        int randomNumber = random.nextInt(50);
        setRandomBook(randomNumber);

    }

    void setRandomBook(int position){
        Book bookItem = bookArrayList.get(position);
        String imageNameAddress = bookItem.getImage();

        frgName.setText(bookArrayList.get(position).getName());
        frgAuthor.setText(bookArrayList.get(position).getAuthor());
        Picasso.with(getContext())
                .load(imageNameAddress)
                .into(frgrandomImage);
    }
}