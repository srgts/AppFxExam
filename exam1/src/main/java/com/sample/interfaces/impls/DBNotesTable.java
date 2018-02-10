package com.sample.interfaces.impls;

import com.sample.interfaces.NotesTable;
import com.sample.objects.Note;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DBNotesTable implements NotesTable {

    private ObservableList<Note> notes = FXCollections.observableArrayList();

    public void add(Note note) {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/notes?user=root&password=root"
                    + "&serverTimezone=UTC&useSSL=false");
            Statement st = conn.createStatement();
            st.executeUpdate("INSERT INTO notes_table(noteText) VALUES (text)");

        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
    }

    public ObservableList<Note> getNotes() {
        return notes;
    }
}
