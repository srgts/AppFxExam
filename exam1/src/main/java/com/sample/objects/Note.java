package com.sample.objects;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Note {

    private SimpleIntegerProperty number;
    private Date date;
    private SimpleStringProperty text = new SimpleStringProperty("");

    public Note() {
    }

    public Note(int number, Date date, String text) {
        this.number = new SimpleIntegerProperty(number);
        this.date = date;
        this.text = new SimpleStringProperty(text);
    }

    public int getNumber() {
        return number.get();
    }

    public void setNumber(int number) {
        this.number.set(number);
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getText() {
        return text.get();
    }

    public void setText(String text) {
        this.text.set(text);
    }

    public SimpleIntegerProperty numberProperty(){
        return number;
    }

    public SimpleStringProperty textProperty(){
        return text;
    }

    public SimpleStringProperty dateProperty(){
        DateFormat df = new SimpleDateFormat("dd:MM:yyyy HH:mm:ss");
        return new SimpleStringProperty(df.format(date));
    }
}
