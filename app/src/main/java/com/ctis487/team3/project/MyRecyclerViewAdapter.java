package com.ctis487.team3.project;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.MyRecyclerViewItemHolder>  {
    private Context context;
    private ArrayList<Book> recyclerItemValues;
    Dialog customDialog;
    ArrayList<Book> checkExist;

    DatabaseHelper dbHelper;

    public MyRecyclerViewAdapter(Context context, ArrayList<Book> values){
        this.context = context;
        this.recyclerItemValues = values;
        dbHelper = new DatabaseHelper(context);

        /* To add all json data to db
        for (Book my: recyclerItemValues) {
            Log.d("bookssItem",":my"+my );
            BookDB.insertBook(dbHelper, my);
        }
         */

    }

    public MyRecyclerViewItemHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflator = LayoutInflater.from(viewGroup.getContext());

        View itemView = inflator.inflate(R.layout.recycler_item, viewGroup, false);

        MyRecyclerViewItemHolder mViewHolder = new MyRecyclerViewItemHolder(itemView);
        return mViewHolder;
    }

    public void onBindViewHolder(@NonNull MyRecyclerViewItemHolder myRecyclerViewItemHolder, int i) {

        final Book bookItem = recyclerItemValues.get(i);

        myRecyclerViewItemHolder.tvItemName.setText(bookItem.getName());
        myRecyclerViewItemHolder.tvItemCategory.setText(bookItem.getCategory());

        String imageNameAddress = bookItem.getImage();

        Picasso.with(context)
                .load(imageNameAddress)
                .into(myRecyclerViewItemHolder.imgItemBookImage);

        myRecyclerViewItemHolder.parentLayout.setOnLongClickListener(new View.OnLongClickListener() {
            @Override public boolean onLongClick(View v) {
                displayDialog(bookItem.toString(),i);
                return true;
            }
        });

        Log.d("MB","MY  :"+bookItem );



    }

    public int getItemCount() {
        return recyclerItemValues.size();
    }

    class MyRecyclerViewItemHolder extends  RecyclerView.ViewHolder{
        TextView tvItemName, tvItemCategory;
        ImageView imgItemBookImage;
        ConstraintLayout parentLayout;
        public MyRecyclerViewItemHolder(@NonNull View itemView) {
            super(itemView);
            tvItemName = itemView.findViewById(R.id.tvItemBookName);
            tvItemCategory = itemView.findViewById(R.id.tvItemBookCategory);
            imgItemBookImage = itemView.findViewById(R.id.imgItemBookmage);
            parentLayout = itemView.findViewById(R.id.itemConstLayout);
        }
    }

    public void displayDialog(final String msg,int pos) {
        final Book bookItem = recyclerItemValues.get(pos);

        TextView tv1, tv2;
        ImageView imageView;
        Button addFavList;
        customDialog = new Dialog(context);

        customDialog.setContentView(R.layout.dialog);
        tv1 = customDialog.findViewById(R.id.tv1);
        tv2 = customDialog.findViewById(R.id.tv2);
        addFavList = customDialog.findViewById(R.id.addFavList);
        imageView = customDialog.findViewById(R.id.imageView);

        tv1.setText(bookItem.getName()+"\n"+bookItem.getAuthor());
        tv2.setText(bookItem.getDetail());

        String imageNameAddress = bookItem.getImage();

        Picasso.with(context)
                .load(imageNameAddress)
                .into(imageView);
        customDialog.show();

        addFavList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                checkExist = new ArrayList<>();
                checkExist = BookDB.findBook(dbHelper, bookItem.getName());

                if (!checkExist.isEmpty()){  //to check if book is already exist in favorite list or not
                    Toast.makeText(context, bookItem.getName()+" favorilerinizde mevcut.", Toast.LENGTH_SHORT).show();
                } else {
                    // To add selected json data to db to store favorite books
                    BookDB.insertBook(dbHelper, bookItem);
                    Toast.makeText(context, bookItem.getName()+" favori listesine eklenmiştir.", Toast.LENGTH_SHORT).show();
                }
                
            }
        });

    }

}
