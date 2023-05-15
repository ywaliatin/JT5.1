package com.example.jt;

import android.os.Parcel;
import android.os.Parcelable;

public class NewsArticle implements Parcelable {
    private String title;
    private String description;
    private int imageResourceId;

    public NewsArticle(String title, String description, int imageResourceId) {
        this.title = title;
        this.description = description;
        this.imageResourceId = imageResourceId;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getImageResourceId() {
        return imageResourceId;
    }

    // Parcelable implementation

    protected NewsArticle(Parcel in) {
        title = in.readString();
        description = in.readString();
        imageResourceId = in.readInt();
    }

    public static final Creator<NewsArticle> CREATOR = new Creator<NewsArticle>() {
        @Override
        public NewsArticle createFromParcel(Parcel in) {
            return new NewsArticle(in);
        }

        @Override
        public NewsArticle[] newArray(int size) {
            return new NewsArticle[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(description);
        dest.writeInt(imageResourceId);
    }



}
