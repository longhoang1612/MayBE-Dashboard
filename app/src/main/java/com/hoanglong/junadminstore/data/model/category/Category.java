package com.hoanglong.junadminstore.data.model.category;

import android.os.Parcel;
import android.os.Parcelable;

public class Category implements Parcelable {
    private String mImage;
    private String mTitle;
    private String mType;

    public Category(String image, String title, String type) {
        mImage = image;
        mTitle = title;
        mType = type;
    }

    protected Category(Parcel in) {
        mImage = in.readString();
        mTitle = in.readString();
        mType = in.readString();
    }

    public static final Creator<Category> CREATOR = new Creator<Category>() {
        @Override
        public Category createFromParcel(Parcel in) {
            return new Category(in);
        }

        @Override
        public Category[] newArray(int size) {
            return new Category[size];
        }
    };

    public String getImage() {
        return mImage;
    }

    public void setImage(String image) {
        mImage = image;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public String getType() {
        return mType;
    }

    public void setType(String type) {
        mType = type;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mImage);
        dest.writeString(mTitle);
        dest.writeString(mType);
    }
}
