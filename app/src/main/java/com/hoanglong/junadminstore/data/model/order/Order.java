package com.hoanglong.junadminstore.data.model.order;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.hoanglong.junadminstore.data.model.cart.CartItem;

import java.util.List;


public class Order implements Parcelable {
    @SerializedName("_id")
    @Expose
    private String idOrder;
    @SerializedName("idUser")
    @Expose
    private String mIdUser;
    @SerializedName("nameUser")
    private String mNameUser;
    @SerializedName("typeOrder")
    @Expose
    private String mTypeOrder;
    @SerializedName("dateOrder")
    @Expose
    private String mDateOrder;
    @SerializedName("statusOrder")
    @Expose
    private String mStatusOrder;
    @SerializedName("phoneNumber")
    @Expose
    private String mPhoneNumber;
    @SerializedName("typePayment")
    @Expose
    private String mTypePayment;
    @SerializedName("cart")
    @Expose
    private List<CartItem> mCartItems = null;
    @SerializedName("addressUser")
    @Expose
    private String mAddressUser;

    public Order(String idUser, String typeOrder, String dateOrder, String statusOrder, String nameUser, String phoneNumber, String typePayment, List<CartItem> cartItems, String addressUser) {
        mIdUser = idUser;
        mTypeOrder = typeOrder;
        mDateOrder = dateOrder;
        mStatusOrder = statusOrder;
        mNameUser = nameUser;
        mPhoneNumber = phoneNumber;
        mTypePayment = typePayment;
        mCartItems = cartItems;
        mAddressUser = addressUser;
    }

    private Order(Parcel in) {
        idOrder = in.readString();
        mIdUser = in.readString();
        mNameUser = in.readString();
        mTypeOrder = in.readString();
        mDateOrder = in.readString();
        mStatusOrder = in.readString();
        mPhoneNumber = in.readString();
        mTypePayment = in.readString();
        mAddressUser = in.readString();
    }

    public static final Creator<Order> CREATOR = new Creator<Order>() {
        @Override
        public Order createFromParcel(Parcel in) {
            return new Order(in);
        }

        @Override
        public Order[] newArray(int size) {
            return new Order[size];
        }
    };

    public String getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(String idOrder) {
        this.idOrder = idOrder;
    }

    public String getIdUser() {
        return mIdUser;
    }

    public void setIdUser(String idUser) {
        mIdUser = idUser;
    }

    public String getTypeOrder() {
        return mTypeOrder;
    }

    public void setTypeOrder(String typeOrder) {
        mTypeOrder = typeOrder;
    }

    public String getDateOrder() {
        return mDateOrder;
    }

    public void setDateOrder(String dateOrder) {
        mDateOrder = dateOrder;
    }

    public String getStatusOrder() {
        return mStatusOrder;
    }

    public void setStatusOrder(String statusOrder) {
        mStatusOrder = statusOrder;
    }

    public String getNameUser() {
        return mNameUser;
    }

    public void setNameUser(String nameUser) {
        mNameUser = nameUser;
    }

    public String getPhoneNumber() {
        return mPhoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        mPhoneNumber = phoneNumber;
    }

    public String getTypePayment() {
        return mTypePayment;
    }

    public void setTypePayment(String typePayment) {
        mTypePayment = typePayment;
    }

    public List<CartItem> getCartItems() {
        return mCartItems;
    }

    public void setCartItems(List<CartItem> cartItems) {
        mCartItems = cartItems;
    }

    public String getAddressUser() {
        return mAddressUser;
    }

    public void setAddressUser(String addressUser) {
        mAddressUser = addressUser;
    }

    @Override
    public String toString() {
        return "Order{" +
                "mIdUser='" + mIdUser + '\'' +
                ", mTypeOrder='" + mTypeOrder + '\'' +
                ", mDateOrder='" + mDateOrder + '\'' +
                ", mStatusOrder='" + mStatusOrder + '\'' +
                ", mNameUser='" + mNameUser + '\'' +
                ", mPhoneNumber='" + mPhoneNumber + '\'' +
                ", mTypePayment='" + mTypePayment + '\'' +
                ", mCartItems=" + mCartItems +
                ", mAddressUser='" + mAddressUser + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(idOrder);
        dest.writeString(mIdUser);
        dest.writeString(mNameUser);
        dest.writeString(mTypeOrder);
        dest.writeString(mDateOrder);
        dest.writeString(mStatusOrder);
        dest.writeString(mPhoneNumber);
        dest.writeString(mTypePayment);
        dest.writeString(mAddressUser);
    }
}
