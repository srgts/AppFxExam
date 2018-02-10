package com.sample.controllers;

import com.sample.objects.Note;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.stage.Stage;

public class AddDialogController {
    private Note note;

    public void actionClose(ActionEvent actionEvent) {
        Node node = (Node)actionEvent.getSource();
        Stage stage = (Stage)node.getScene().getWindow();
        stage.close();
    }

    public void actionSave(ActionEvent actionEvent) {
        note.setText();
    }
}
