package com.ctis487.team3.project;

import android.content.ContentValues;
import android.database.Cursor;
import android.util.Log;

import java.util.ArrayList;

public class BookDB {
    public static String TABLE_NAME="books";
    public static String FIELD_ID = "id";
    public static String FIELD_NAME = "name";
    public static String FIELD_AUTHOR = "author";
    public static String FIELD_DETAILS = "details";
    public static String FIELD_CATEGORY = "category";
    public static String FIELD_IMAGE = "image";

    public static String CREATE_TABLE_SQL="CREATE TABLE "+TABLE_NAME+" ( "+FIELD_ID+" INTEGER, "+FIELD_NAME+" TEXT, "+FIELD_AUTHOR+" TEXT, "+FIELD_DETAILS+" TEXT, "+FIELD_CATEGORY+" TEXT, "+FIELD_IMAGE+" TEXT, PRIMARY KEY("+FIELD_ID+" AUTOINCREMENT))";
    public static String DROP_TABLE_SQL = "DROP TABLE if exists "+TABLE_NAME;

    public static ArrayList<Book> getAllBook(DatabaseHelper dbHelper){
        Book anItem;
        ArrayList<Book> data = new ArrayList<>();
        Cursor cursor = dbHelper.getAllRecords(TABLE_NAME, null);
        Log.d("DATABASE OPERATIONS", cursor.getCount()+",  "+cursor.getColumnCount());
        while(cursor.moveToNext()){
            int id=cursor.getInt(0);
            String name = cursor.getString(1);
            String author= cursor.getString(2);
            String detail= cursor.getString(3);
            String category= cursor.getString(4);
            String image= cursor.getString(5);
            anItem = new Book(id, name, author, detail, category, image);
            data.add(anItem);

        }
        Log.d("DATABASE OPERATIONS",data.toString());
        return data;
    }

    public static ArrayList<Book> findBook(DatabaseHelper dbHelper, String key) {
        Book anItem;
        ArrayList<Book> data = new ArrayList<>();
        String where = FIELD_NAME+" like '%"+key+"%'";

        Cursor cursor = dbHelper.getSomeRecords(TABLE_NAME, null, where);
        Log.d("DATABASE OPERATIONS",  where+", "+cursor.getCount()+",  "+cursor.getColumnCount());
        while(cursor.moveToNext()){
            int id=cursor.getInt(0);
            String name = cursor.getString(1);
            String author= cursor.getString(2);
            String detail= cursor.getString(3);
            String category= cursor.getString(4);
            String image= cursor.getString(5);
            anItem = new Book(id, name, author, detail, category, image);
            data.add(anItem);
        }
        Log.d("DATABASE OPERATIONS",data.toString());
        return data;
    }

    public static boolean insertBook(DatabaseHelper dbHelper, int id, String name, String author, String details, String category, String imgName){
        ContentValues contentValues = new ContentValues( );
        contentValues.put(FIELD_NAME, name);
        contentValues.put(FIELD_AUTHOR, author);
        contentValues.put(FIELD_DETAILS, details);
        contentValues.put(FIELD_CATEGORY, category);
        contentValues.put(FIELD_IMAGE, imgName);
        boolean res = dbHelper.insert(TABLE_NAME,contentValues);
        return res;
    }
    public static boolean insertBook(DatabaseHelper dbHelper, Book book){
        ContentValues contentValues = new ContentValues();
        contentValues.put(FIELD_NAME, book.getName());
        contentValues.put(FIELD_AUTHOR, book.getAuthor());
        contentValues.put(FIELD_DETAILS, book.getDetail());
        contentValues.put(FIELD_CATEGORY, book.getCategory());
        contentValues.put(FIELD_IMAGE, book.getImage());
        boolean res = dbHelper.insert(TABLE_NAME,contentValues);
        return res;
    }

    public static boolean delete(DatabaseHelper dbHelper, int id){
        Log.d("DATABASE OPERATIONS", "DELETE DONE");
        String where = FIELD_ID + " = "+id;
        boolean res =  dbHelper.delete(TABLE_NAME, where);
        return  res;
    }

}
