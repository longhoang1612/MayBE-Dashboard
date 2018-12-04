package com.hoanglong.junadminstore.data.model.home;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class NewsFeed {
    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("__v")
    @Expose
    private Integer v;
    @SerializedName("accessories")
    @Expose
    private List<Accessories> accessories = null;
    @SerializedName("laptop")
    @Expose
    private List<Laptop> laptop = null;
    @SerializedName("phone")
    @Expose
    private List<Phone> phone = null;
    @SerializedName("km")
    @Expose
    private List<Km> km = null;
    @SerializedName("slideImage")
    @Expose
    private List<String> slideImage = null;

    public NewsFeed() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getV() {
        return v;
    }

    public void setV(Integer v) {
        this.v = v;
    }

    public List<Accessories> getAccessories() {
        return accessories;
    }

    public void setAccessories(List<Accessories> accessories) {
        this.accessories = accessories;
    }

    public List<Laptop> getLaptop() {
        return laptop;
    }

    public void setLaptop(List<Laptop> laptop) {
        this.laptop = laptop;
    }

    public List<Phone> getPhone() {
        return phone;
    }

    public void setPhone(List<Phone> phone) {
        this.phone = phone;
    }

    public List<Km> getKm() {
        return km;
    }

    public void setKm(List<Km> km) {
        this.km = km;
    }

    public List<String> getSlideImage() {
        return slideImage;
    }

    public void setSlideImage(List<String> slideImage) {
        this.slideImage = slideImage;
    }
}
