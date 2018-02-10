package com.sample.objects;

import java.util.Date;

public class Note {

    private int number;
    private Date date;
    private String text;

    public Note(){}

    public Note(int number, Date date, String text) {
        this.number = number;
        this.date = date;
        this.text = text;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
