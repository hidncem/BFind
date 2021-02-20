package com.ctis487.team3.project;

import android.app.IntentService;
import android.content.Intent;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class ParseBookJSONService extends IntentService {
    JSONArray books;
    ArrayList<Book> bookList;
    DatabaseHelper dbHelper;

    public static String TAG_BOOKS="books";
    public static String FIELD_NAME = "name";
    public static String FIELD_AUTHOR = "author";
    public static String FIELD_DETAILS = "detail";
    public static String FIELD_CATEGORY = "category";
    public static String FIELD_IMAGE = "imgName";

    public ParseBookJSONService() {
        super("ParseBookJSONService");
    }


    @Override
    protected void onHandleIntent(Intent intent) {

        bookList = new  ArrayList<Book>();

        String filename = intent.getStringExtra("filename");

        String jsonfileContent = loadFileFromAsset(filename);

        Log.d("Response: ", "> " + jsonfileContent);

        if (jsonfileContent != null) {

            try {
                JSONObject jsonObj = new JSONObject(jsonfileContent);
                Log.d("Response: ", "GİRDİ> " + jsonfileContent);


                // Getting JSON Array node
                books = jsonObj.getJSONArray(TAG_BOOKS);
                Log.d("BOOKs: ", "> " + books);

                // looping through all Contacts
                for (int i = 0; i < books.length(); i++) {
                    JSONObject book = books.getJSONObject(i);

                    String name = book.getString(FIELD_NAME);
                    String author = book.getString(FIELD_AUTHOR);
                    String details = book.getString(FIELD_DETAILS);
                    String category = book.getString(FIELD_CATEGORY);
                    String imgName = "https://i.dr.com.tr/cache/600x600-0/originals/" + book.getString(FIELD_IMAGE);

                    Book a = new Book(name, author, details, category, imgName);
                    bookList.add(a);
                    Log.d("a BOOK: ", "> " + a);

                    if(!MainActivity.categoryList.contains(category))
                        MainActivity.categoryList.add(category);
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        Intent broascastIntent = new Intent();
        Bundle b = new Bundle();
        b.putParcelableArrayList("booklist", bookList);
        broascastIntent.putExtras(b);
        broascastIntent.setAction("BOOK_JSON_PARSE_ACTION");
        getBaseContext().sendBroadcast(broascastIntent);

        Log.d("Service",":servis END" );

    }

    private String loadFileFromAsset(String fileName) {
        String jsonfileContent = null;
        try {
            InputStream is = getBaseContext().getAssets().open(fileName);
            int size = is.available();
            byte[] buffer = new byte[size];

            is.read(buffer);
            is.close();

            jsonfileContent = new String(buffer, "UTF-8");

        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return jsonfileContent;
    }

}