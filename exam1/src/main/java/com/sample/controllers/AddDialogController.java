package com.sample.controllers;

import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.stage.Stage;

public class AddDialogController {
    public void actionClose(ActionEvent actionEvent) {
        Node node = (Node)actionEvent.getSource();
        Stage stage = (Stage)node.getScene().getWindow();
        stage.close();
    }
}
