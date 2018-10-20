package com.example.lalalas.myapp.model;

import com.example.lalalas.myapp.R;

import java.io.Serializable;
import java.util.ArrayList;

public class Restoran implements Serializable{
    private int imageID;
    private String title;
    private String description;
    private String city;
    private String address;
    private String phone;
    private String mail;

    public Restoran() {

    }


    public Restoran(int imageID, String title, String description, String city, String address, String phone, String mail) {
        this.imageID = imageID;
        this.title = title;
        this.description = description;
        this.city = city;
        this.address = address;
        this.phone = phone;
        this.mail = mail;
    }

    public int getImageID() {
        return imageID;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getCity() {
        return city;
    }

    public String getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
    }

    public String getMail() {
        return mail;
    }

    public void setImageID(int imageID) {
        this.imageID = imageID;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }





}
