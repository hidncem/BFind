package com.ctis487.team3.project.top_fragment_package;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.ctis487.team3.project.R;

import java.util.ArrayList;
import java.util.Random;


public class TopFragment extends Fragment {
    Button frgrandomBtn;

    TopFragmentInterface topFragmentInterfaceListener;

    public interface  TopFragmentInterface{
        public void setRandomBook(int position);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if(context instanceof TopFragmentInterface )
            topFragmentInterfaceListener = (TopFragmentInterface) context;
        // here, context is the MainActivity
        // Assign context to TopFragmentInterfaceListener means that MainActivity implements that interface
        // and changeImage() method is definietly implemented in MainActivity
    }


    public TopFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_top, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        frgrandomBtn = view.findViewById(R.id.randomButton);

        frgrandomBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Random random = new Random();
                int randomNumber = random.nextInt(50);

                topFragmentInterfaceListener.setRandomBook(randomNumber);

            }
        });
    }
}