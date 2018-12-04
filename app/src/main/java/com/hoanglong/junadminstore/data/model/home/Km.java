package com.hoanglong.junadminstore.data.model.home;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Km {

    @SerializedName("href")
    @Expose
    private String href;
    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("titleItem")
    @Expose
    private String titleItem;
    @SerializedName("newPrice")
    @Expose
    private String newPrice;
    @SerializedName("shockprice")
    @Expose
    private String shockprice;
    @SerializedName("discount")
    @Expose
    private String discount;
    @SerializedName("installment")
    @Expose
    private String installment;
    @SerializedName("pre")
    @Expose
    private String pre;
    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("promo")
    @Expose
    private String promo;
    @SerializedName("imagePromo")
    @Expose
    private String imagePromo;
    @SerializedName("newItem")
    @Expose
    private String newItem;

    public Km() {
    }

    public String getNewItem() {
        return newItem;
    }

    public void setNewItem(String newItem) {
        this.newItem = newItem;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTitleItem() {
        return titleItem;
    }

    public void setTitleItem(String titleItem) {
        this.titleItem = titleItem;
    }

    public String getNewPrice() {
        return newPrice;
    }

    public void setNewPrice(String newPrice) {
        this.newPrice = newPrice;
    }

    public String getShockprice() {
        return shockprice;
    }

    public void setShockprice(String shockprice) {
        this.shockprice = shockprice;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public String getInstallment() {
        return installment;
    }

    public void setInstallment(String installment) {
        this.installment = installment;
    }

    public String getPre() {
        return pre;
    }

    public void setPre(String pre) {
        this.pre = pre;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPromo() {
        return promo;
    }

    public void setPromo(String promo) {
        this.promo = promo;
    }

    public String getImagePromo() {
        return imagePromo;
    }

    public void setImagePromo(String imagePromo) {
        this.imagePromo = imagePromo;
    }
}
