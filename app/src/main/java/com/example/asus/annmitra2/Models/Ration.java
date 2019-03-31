package com.example.asus.annmitra2.Models;

/**
 * Created by asus on 9/21/2018.
 */

public class Ration {
    private String name, shop_code, shop_number, address,mobileno;
    private double lat,lng;

    public Ration(String name, String shop_code, String shop_number, String address,String mobileno,double lat,double lng) {
        this.name = name;
        this.shop_code = shop_code;
        this.shop_number = shop_number;
        this.address = address;
        this.mobileno=mobileno;
        this.lat=lat;
        this.lng=lng;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShop_code() {
        return shop_code;
    }

    public void setShop_code(String shop_code) {
        this.shop_code = shop_code;
    }

    public String getShop_number() {
        return shop_number;
    }

    public void setShop_number(String shop_number) {
        this.shop_number = shop_number;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMobileno() {
        return mobileno;
    }

    public void setMobileno(String mobileno) {
        this.mobileno = mobileno;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }
}
