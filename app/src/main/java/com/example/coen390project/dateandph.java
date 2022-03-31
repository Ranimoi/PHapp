package com.example.coen390project;

public class dateandph {
    private Float ph;
    private String date;

    public dateandph(Float ph, String date) {
        this.ph = ph;
        this.date = date;
    }

    public dateandph() {

    }

    public void setPH(Float ph) {
        this.ph = ph;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Float getPH() {
        return ph;
    }

    public String getDate() {
        return date;
    }
}
