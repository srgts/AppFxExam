package com.sample.controllers;

import com.sample.interfaces.impls.DBNotesTable;
import com.sample.objects.Note;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;

import static javafx.stage.Modality.WINDOW_MODAL;

public class MainController {

    private final int MAX_TEXT_LENGTH = 100;

    private DBNotesTable db = new DBNotesTable();

    private Parent fxmlAdd;
    private FXMLLoader fxmlLoader = new FXMLLoader();
    private AddDialogController addDialogController;
    private Stage addDialogStage;
    private Stage mainStage;

    public void setMainStage(Stage mainStage) {
        this.mainStage = mainStage;
    }

    @FXML
    private TableView tableNotesTable;
    @FXML
    private TableColumn<Note, String> columnDate;
    @FXML
    private TableColumn<Note, String> columnText;

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
        if (addDialogStage == null) {
            addDialogController.setNote(new Note());
            addDialogStage = new Stage();
            addDialogStage.setTitle("Добавление заметки");
            addDialogStage.setHeight(400);
            addDialogStage.setWidth(600);
            addDialogStage.setResizable(false);
            addDialogStage.setScene(new Scene(fxmlAdd));
            addDialogStage.initModality(WINDOW_MODAL);
            addDialogStage.initOwner(mainStage);
            addTextLimiter(addDialogController.textArea, MAX_TEXT_LENGTH);
        }
        addDialogStage.showAndWait();
        db.add(addDialogController.getNote());
        db.getUpdateTable();
        addDialogController.clearNote();
    }

    private void addTextLimiter(final TextArea ta, final int maxLength) {
        ta.textProperty().addListener(new ChangeListener<String>() {
            public void changed(ObservableValue<? extends String> ov, final String oldValue, final String newValue) {
                if (ta.getText().length() > maxLength) {
                    String s = ta.getText().substring(0, maxLength);
                    ta.setText(s);
                }
            }
        });
    }

}
