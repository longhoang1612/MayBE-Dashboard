package com.hoanglong.junadminstore.data.model.phone_product;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Parameter implements Parcelable {

    @SerializedName("titlePara")
    @Expose
    private String titlePara;
    @SerializedName("contentPara")
    @Expose
    private String contentPara;
    @SerializedName("_id")
    @Expose
    private String id;

    public static final Creator<Parameter> CREATOR = new Creator<Parameter>() {
        @Override
        public Parameter createFromParcel(Parcel in) {
            return new Parameter(in);
        }

        @Override
        public Parameter[] newArray(int size) {
            return new Parameter[size];
        }
    };

    public Parameter(String titlePara, String contentPara, String id) {
        this.titlePara = titlePara;
        this.contentPara = contentPara;
        this.id = id;
    }

    public Parameter(String titlePara, String contentPara) {
        this.titlePara = titlePara;
        this.contentPara = contentPara;
    }

    private Parameter(Parcel in) {
        titlePara = in.readString();
        contentPara = in.readString();
        id = in.readString();
    }

    Parameter(ListParameterBuilder listParameterBuilder) {
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
        return "Parameter{" +
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

        public Parameter build() {
            return new Parameter(this);
        }
    }
}
