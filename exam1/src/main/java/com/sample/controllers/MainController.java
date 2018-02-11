package com.sample.controllers;

import com.sample.interfaces.impls.DBNotesTable;
import com.sample.objects.Note;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;

import static javafx.stage.Modality.WINDOW_MODAL;

public class MainController {
    private DBNotesTable db = new DBNotesTable();
    private Stage mainStage;

    @FXML
    private TableView tableNotesTable;
    @FXML
    private TableColumn<Note, String> columnDate;
    @FXML
    private TableColumn<Note, String> columnText;

    private Parent fxmlAdd;
    private FXMLLoader fxmlLoader = new FXMLLoader();
    private AddDialogController addDialogController;
    private Stage addDialogStage;

    @FXML
    private void initialize() {
        columnDate.setCellValueFactory(new PropertyValueFactory<Note, String>("date"));
        columnText.setCellValueFactory(new PropertyValueFactory<Note, String>("text"));
        db.getUpdateTable();
        tableNotesTable.setItems(db.getNotes());

        try {
            fxmlLoader.setLocation(getClass().getResource("/fxml/add.fxml"));
            fxmlAdd = fxmlLoader.load();
            addDialogController = fxmlLoader.getController();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showDialog(ActionEvent actionEvent) {
        addDialogController.setNote(new Note());
        if (addDialogStage == null) {
            addDialogStage = new Stage();
            addDialogStage.setTitle("Добавление заметки");
            addDialogStage.setHeight(400);
            addDialogStage.setWidth(600);
            addDialogStage.setResizable(false);
            addDialogStage.setScene(new Scene(fxmlAdd));
            addDialogStage.initModality(WINDOW_MODAL);
            addDialogStage.initOwner(mainStage);
        }
        addDialogStage.showAndWait();
        db.add(addDialogController.getNote());
        db.getUpdateTable();
    }

    public void setMainStage(Stage mainStage) {
        this.mainStage = mainStage;
    }
}
