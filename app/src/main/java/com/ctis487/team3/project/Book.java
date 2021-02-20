package com.ctis487.team3.project;

import android.os.Parcel;
import android.os.Parcelable;

public class Book implements Parcelable {
    private int id;
    private String name;
    private String author;
    private String detail;
    private String category;
    private String image;

    public Book(int id, String name, String author, String detail, String category, String image) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.detail = detail;
        this.category = category;
        this.image = image;
    }

    public Book(String name, String author, String detail, String category, String image) {
        this.name = name;
        this.author = author;
        this.detail = detail;
        this.category = category;
        this.image = image;
    }

    protected Book(Parcel in) {
        id = in.readInt();
        name = in.readString();
        author = in.readString();
        detail = in.readString();
        category = in.readString();
        image = in.readString();
    }

    public static final Creator<Book> CREATOR = new Creator<Book>() {
        @Override
        public Book createFromParcel(Parcel in) {
            return new Book(in);
        }

        @Override
        public Book[] newArray(int size) {
            return new Book[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(name);
        dest.writeString(author);
        dest.writeString(detail);
        dest.writeString(category);
        dest.writeString(image);
    }

    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public String getAuthor() { return author; }

    public void setAuthor(String author) { this.author = author; }

    public String getDetail() { return detail; }

    public void setDetail(String detail) { this.detail = detail; }

    public String getCategory() { return category; }

    public void setCategory(String category) { this.category = category; }

    public String getImage() { return image; }

    public void setImage(String image) { this.image = image; }

    @Override
    public String toString() {
        return "Book{" +
                "name='" + name + '\'' +
                ", author='" + author + '\'' +
                ", detail='" + detail + '\'' +
                ", category='" + category + '\'' +
                ", image='" + image + '\'' +
                '}';
    }
}
