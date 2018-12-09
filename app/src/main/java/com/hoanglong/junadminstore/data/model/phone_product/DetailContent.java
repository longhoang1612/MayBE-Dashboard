package com.hoanglong.junadminstore.data.model.phone_product;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DetailContent implements Parcelable {

    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("image")
    @Expose
    private String image;

    public DetailContent(String title, String image) {
        this.title = title;
        this.image = image;
    }

    public static final Creator<DetailContent> CREATOR = new Creator<DetailContent>() {
        @Override
        public DetailContent createFromParcel(Parcel in) {
            return new DetailContent(in);
        }

        @Override
        public DetailContent[] newArray(int size) {
            return new DetailContent[size];
        }
    };

    DetailContent(DetailContentBuilder detailContentBuilder) {
        detailContentBuilder.id = id;
        detailContentBuilder.image = image;
        detailContentBuilder.title = title;
    }

    private DetailContent(Parcel in) {
        title = in.readString();
        id = in.readString();
        image = in.readString();
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "DetailContent{" +
                "title='" + title + '\'' +
                ", id='" + id + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(id);
        dest.writeString(image);
    }

    public static class DetailContentBuilder {
        private String title;
        private String id;
        private String image;

        public DetailContentBuilder setTitle(String title) {
            this.title = title;
            return this;
        }

        public DetailContentBuilder setId(String id) {
            this.id = id;
            return this;
        }

        public DetailContentBuilder setImage(String image) {
            this.image = image;
            return this;
        }

        public DetailContent build() {
            return new DetailContent(this);
        }
    }
}
