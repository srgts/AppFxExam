package com.sample.controllers;

import com.sample.objects.Note;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AddDialogController {

    @FXML
    TextField fldDate;
    @FXML
    TextArea textArea;

    private Note note;

    void setNote(Note note){
        this.note = note;
    }

    Note getNote(){
        return note;
    }

    void clearNote(){
        note.setText("");
    }

    public void actionClose(ActionEvent actionEvent) {
        Node node = (Node)actionEvent.getSource();
        Stage stage = (Stage)node.getScene().getWindow();
        stage.hide();
        textArea.clear();
    }

    public void actionSave(ActionEvent actionEvent) {
        note.setText(textArea.getText());
        actionClose(actionEvent);
    }

}
