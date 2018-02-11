package com.sample.interfaces.impls;

import com.sample.interfaces.NotesTable;
import com.sample.objects.Note;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

public class DBNotesTable implements NotesTable {
    private Connection conn = null;
    private Statement st = null;
    private ResultSet rs = null;
    private final String URL = "jdbc:mysql://localhost:3306/notes?serverTimezone=UTC&useSSL=false";
    private final String USER_NAME = "root";
    private final String PASSWORD = "root";

    private ObservableList<Note> notes = FXCollections.observableArrayList();

    public void add(Note note) {
        try {
            conn = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
            Statement st = conn.createStatement();
            if (!note.getText().equals("")) {
                st.executeUpdate(String.format("INSERT INTO notes_table(createDate, noteText) VALUES (CURTIME(), '%s')", note.getText()));
            }
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        } finally {
            if (st != null) {
                try {
                    st.close();
                } catch (SQLException sqlEx) {
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException sqlEx) {
                }
            }
        }
    }

    public void getUpdateTable() {
        notes.clear();
        Thread t = new Thread(new Runnable() {
            public void run() {
                try {
                    conn = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
                    st = conn.createStatement();
                    rs = st.executeQuery("SELECT createDate, noteText FROM notes_table");
                    while (rs.next()) {
                        notes.add(new Note(rs.getTimestamp("createDate"), rs.getString("noteText")));
                    }
                    rs.close();
                    st.close();
                    conn.close();
                } catch (SQLException ex) {
                    System.out.println("SQLException: " + ex.getMessage());
                    System.out.println("SQLState: " + ex.getSQLState());
                    System.out.println("VendorError: " + ex.getErrorCode());
                } finally {
                    if (rs != null) {
                        try {
                            rs.close();
                        } catch (SQLException sqlEx) {
                        }
                    }
                    if (st != null) {
                        try {
                            st.close();
                        } catch (SQLException sqlEx) {
                        }
                    }
                    if (conn != null) {
                        try {
                            conn.close();
                        } catch (SQLException sqlEx) {
                        }
                    }
                }
            }
        });
        t.start();
    }

    public ObservableList<Note> getNotes() {
        return notes;
    }

}
