package com.sample.objects;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Note {

    private StringProperty date = new SimpleStringProperty("");
    private StringProperty text = new SimpleStringProperty("");

    private DateFormat df = new SimpleDateFormat("dd:MM:yyyy HH:mm:ss");

    public Note() {
    }

    public Note(Date date, String text) {
        this.date = new SimpleStringProperty(df.format(date));
        this.text = new SimpleStringProperty(text);
    }

    public void setDate(Date date) {
        this.date = new SimpleStringProperty(df.format(date));
    }

    public String getText() {
        return text.get();
    }

    public void setText(String text) {
        this.text.set(text);
    }

    public StringProperty textProperty(){
        return text;
    }

    public StringProperty dateProperty(){
        return date;
    }
}
