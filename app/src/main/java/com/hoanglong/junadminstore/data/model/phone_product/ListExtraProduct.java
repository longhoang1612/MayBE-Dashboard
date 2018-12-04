package com.hoanglong.junadminstore.data.model.phone_product;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ListExtraProduct implements Parcelable{
    @SerializedName("imageExtra")
    @Expose
    private String imageExtra;
    @SerializedName("titleExtra")
    @Expose
    private String titleExtra;
    @SerializedName("_id")
    @Expose
    private String id;

    public ListExtraProduct(String imageExtra, String titleExtra, String id) {
        this.imageExtra = imageExtra;
        this.titleExtra = titleExtra;
        this.id = id;
    }

    private ListExtraProduct(Parcel in) {
        imageExtra = in.readString();
        titleExtra = in.readString();
        id = in.readString();
    }

    ListExtraProduct(ListExtraProductBuilder listExtraProductBuilder) {
        listExtraProductBuilder.id = id;
        listExtraProductBuilder.titleExtra = titleExtra;
        listExtraProductBuilder.imageExtra = imageExtra;
    }

    public static final Creator<ListExtraProduct> CREATOR = new Creator<ListExtraProduct>() {
        @Override
        public ListExtraProduct createFromParcel(Parcel in) {
            return new ListExtraProduct(in);
        }

        @Override
        public ListExtraProduct[] newArray(int size) {
            return new ListExtraProduct[size];
        }
    };

    public String getImageExtra() {
        return imageExtra;
    }

    public void setImageExtra(String imageExtra) {
        this.imageExtra = imageExtra;
    }

    public String getTitleExtra() {
        return titleExtra;
    }

    public void setTitleExtra(String titleExtra) {
        this.titleExtra = titleExtra;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "ListExtraProduct{" +
                "imageExtra='" + imageExtra + '\'' +
                ", titleExtra='" + titleExtra + '\'' +
                ", id='" + id + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static class ListExtraProductBuilder{
        private String imageExtra;
        private String titleExtra;
        private String id;

        public ListExtraProductBuilder setImageExtra(String imageExtra) {
            this.imageExtra = imageExtra;
            return this;
        }

        public ListExtraProductBuilder setTitleExtra(String titleExtra) {
            this.titleExtra = titleExtra;
            return this;
        }

        public ListExtraProductBuilder setId(String id) {
            this.id = id;
            return this;
        }

        public ListExtraProduct build() {
            return new ListExtraProduct(this);
        }
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(imageExtra);
        dest.writeString(titleExtra);
        dest.writeString(id);
    }
}
