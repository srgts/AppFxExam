package com.sample.controllers;

import com.sample.interfaces.impls.DBNotesTable;
import com.sample.objects.Note;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

public class AddDialogController {
    @FXML
    public TextArea textArea;

    public void actionClose(ActionEvent actionEvent) {
        Node node = (Node)actionEvent.getSource();
        Stage stage = (Stage)node.getScene().getWindow();
        stage.close();
    }

    public void actionSave(ActionEvent actionEvent) {
        DBNotesTable db= new DBNotesTable();
        Note note = new Note();
        note.setText(textArea.getText());
        db.add(note);
        db.getNotes();
    }
}
