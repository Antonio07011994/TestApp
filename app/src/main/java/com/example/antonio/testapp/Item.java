
package com.example.antonio.testapp;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Item implements Parcelable {

    @SerializedName("about")
    @Expose
    private String about;
    @SerializedName("address")
    @Expose
    private String address;
    @SerializedName("company")
    @Expose
    private String company;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("first_name")
    @Expose
    private String firstName;
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("last_name")
    @Expose
    private String lastName;
    @SerializedName("phone")
    @Expose
    private String phone;
    @SerializedName("photo")
    @Expose
    private String photo;

    protected Item(Parcel in) {
        about = in.readString();
        address = in.readString();
        company = in.readString();
        email = in.readString();
        firstName = in.readString();
        lastName = in.readString();
        phone = in.readString();
        photo = in.readString();
    }

    public static final Creator<Item> CREATOR = new Creator<Item>() {
        @Override
        public Item createFromParcel(Parcel in) {
            return new Item(in);
        }

        @Override
        public Item[] newArray(int size) {
            return new Item[size];
        }
    };

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(firstName);
        dest.writeString(lastName);
        dest.writeString(photo);
        dest.writeString(phone);
        dest.writeString(email);
        dest.writeString(address);
        dest.writeString(company);
        dest.writeString(about);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public String getAbout() {
        return about;
    }
    public void setAbout(String about) {
        this.about = about;
    }

    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }

    public String getCompany() {
        return company;
    }
    public void setCompany(String company) {
        this.company = company;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPhoto() {
        return photo;
    }
    public void setPhoto(String photo) {
        this.photo = photo;
    }
}
