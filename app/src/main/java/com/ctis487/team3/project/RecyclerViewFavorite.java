package com.ctis487.team3.project;

import android.app.Dialog;
import android.content.Context;
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

public class RecyclerViewFavorite extends RecyclerView.Adapter<RecyclerViewFavorite.RecyclerViewFavoriteItemHolder> {

    private Context context;
    private ArrayList<Book> recyclerItemValues;
    Dialog customDialog;
    DatabaseHelper dbHelper;

    public RecyclerViewFavorite(Context context, ArrayList<Book> values) {
        this.context = context;
        this.recyclerItemValues = values;
        dbHelper = new DatabaseHelper(context);
    }

    @NonNull
    @Override
    public RecyclerViewFavoriteItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflator = LayoutInflater.from(parent.getContext());

        View itemView = inflator.inflate(R.layout.recycler_item, parent, false);

        RecyclerViewFavoriteItemHolder mViewHolder = new RecyclerViewFavoriteItemHolder(itemView);
        return mViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewFavoriteItemHolder holder, int i) {

        if(recyclerItemValues.isEmpty()){
            Toast.makeText(context, " Favorileriniz Boş.", Toast.LENGTH_SHORT).show();
        }{
            final Book bookItem = recyclerItemValues.get(i);

            holder.tvItemName.setText(bookItem.getName());
            holder.tvItemCategory.setText(bookItem.getCategory());

            String imageNameAddress = bookItem.getImage();

            Picasso.with(context)
                    .load(imageNameAddress)
                    .into(holder.imgItemBookImage);

            holder.parentLayout.setOnLongClickListener(new View.OnLongClickListener() {
                @Override public boolean onLongClick(View v) {
                    displayDialog(bookItem.toString(),i);
                    return true;
                }
            });

        }

    }

    @Override
    public int getItemCount() {
        return recyclerItemValues.size();
    }

    class RecyclerViewFavoriteItemHolder extends RecyclerView.ViewHolder{
        TextView tvItemName, tvItemCategory;
        ImageView imgItemBookImage;
        ConstraintLayout parentLayout;
        public RecyclerViewFavoriteItemHolder(@NonNull View itemView) {
            super(itemView);
            tvItemName = itemView.findViewById(R.id.tvItemBookName);
            tvItemCategory = itemView.findViewById(R.id.tvItemBookCategory);
            imgItemBookImage = itemView.findViewById(R.id.imgItemBookmage);
            parentLayout = itemView.findViewById(R.id.itemConstLayout);
        }
    }

    public void displayDialog(final String msg,int pos) {

        if(!recyclerItemValues.isEmpty()){
            final Book bookItem = recyclerItemValues.get(pos);

            TextView tv1, tv2;
            ImageView imageView;
            Button addFavList;
            customDialog = new Dialog(context);

            customDialog.setContentView(R.layout.dialog_favorite);
            tv1 = customDialog.findViewById(R.id.tv);
            tv2 = customDialog.findViewById(R.id.tv3);
            addFavList = customDialog.findViewById(R.id.deleteButton);
            imageView = customDialog.findViewById(R.id.imageViewFav);

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
                    BookDB.delete(dbHelper, recyclerItemValues.get(pos).getId());
                    refreshMyAdapterAfterDelete(pos);
                    Toast.makeText(context, bookItem.getName()+" favorilerinizden silindi.", Toast.LENGTH_SHORT).show();
                    customDialog.dismiss();
                }
            });
        }
    }

    public void refreshMyAdapterAfterDelete(int position){
        recyclerItemValues.remove(position);
        notifyDataSetChanged();
    }



}
