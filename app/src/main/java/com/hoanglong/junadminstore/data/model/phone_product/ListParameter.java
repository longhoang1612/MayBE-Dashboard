package com.hoanglong.junadminstore.data.model.phone_product;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ListParameter implements Parcelable {

    @SerializedName("titlePara")
    @Expose
    private String titlePara;
    @SerializedName("contentPara")
    @Expose
    private String contentPara;
    @SerializedName("_id")
    @Expose
    private String id;

    public static final Creator<ListParameter> CREATOR = new Creator<ListParameter>() {
        @Override
        public ListParameter createFromParcel(Parcel in) {
            return new ListParameter(in);
        }

        @Override
        public ListParameter[] newArray(int size) {
            return new ListParameter[size];
        }
    };

    public ListParameter(String titlePara, String contentPara, String id) {
        this.titlePara = titlePara;
        this.contentPara = contentPara;
        this.id = id;
    }

    private ListParameter(Parcel in) {
        titlePara = in.readString();
        contentPara = in.readString();
        id = in.readString();
    }

    ListParameter(ListParameterBuilder listParameterBuilder) {
        listParameterBuilder.id = id;
        listParameterBuilder.contentPara = contentPara;
        listParameterBuilder.titlePara = titlePara;
    }

    public String getTitlePara() {
        return titlePara;
    }

    public void setTitlePara(String titlePara) {
        this.titlePara = titlePara;
    }

    public String getContentPara() {
        return contentPara;
    }

    public void setContentPara(String contentPara) {
        this.contentPara = contentPara;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "ListParameter{" +
                "titlePara='" + titlePara + '\'' +
                ", contentPara='" + contentPara + '\'' +
                ", id='" + id + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(titlePara);
        dest.writeString(contentPara);
        dest.writeString(id);
    }

    public static class ListParameterBuilder {
        private String titlePara;
        private String contentPara;
        private String id;

        public ListParameterBuilder setTitlePara(String titlePara) {
            this.titlePara = titlePara;
            return this;
        }

        public ListParameterBuilder setContentPara(String contentPara) {
            this.contentPara = contentPara;
            return this;
        }

        public ListParameterBuilder setId(String id) {
            this.id = id;
            return this;
        }

        public ListParameter build() {
            return new ListParameter(this);
        }
    }
}
