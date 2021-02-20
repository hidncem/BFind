package com.ctis487.team3.project;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;

import java.util.ArrayList;

public class SpinnerAdapter extends ArrayAdapter<String> {

    private Context context;
    private int layoutResourceId;
    private LayoutInflater inflator;
    private ArrayList<String> categoryArray;

    private boolean flag = false;

    public SpinnerAdapter(@NonNull Context baseContext, int genreLayout, ArrayList<String> categoryArray) {
        super(baseContext, genreLayout, categoryArray);
        this.context = baseContext;
        this.layoutResourceId = genreLayout;
        this.categoryArray = categoryArray;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        return getCustomView(position, convertView, parent);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView,  ViewGroup parent) {
        return getCustomView(position, convertView, parent);
    }
    public View getCustomView(int position,  View convertView,  ViewGroup parent) {
        View rowView = convertView;
        if(rowView == null){
            inflator = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            rowView = inflator.inflate(layoutResourceId,parent,false);
        }

        ConstraintLayout itemLayout = rowView.findViewById(R.id.constraintItemLayout);
        TextView tv_main = rowView.findViewById(R.id.tvItemGenre);

        tv_main.setText(categoryArray.get(position));

        return rowView;
    }
}
