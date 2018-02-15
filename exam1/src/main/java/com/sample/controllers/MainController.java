package com.sample.controllers;

import com.sample.interfaces.impls.DBNotesTable;
import com.sample.objects.Note;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ListChangeListener;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static javafx.stage.Modality.WINDOW_MODAL;

public class MainController {

    private final int MAX_TEXT_LENGTH = 100;

    private DBNotesTable db = new DBNotesTable();

    private Parent fxmlEdit;
    private FXMLLoader fxmlLoader = new FXMLLoader();
    private EditDialogController editDialogController;
    private Stage aditDialogStage;
    private Stage mainStage;

    public void setMainStage(Stage mainStage) {
        this.mainStage = mainStage;
    }

    @FXML
    private Button btnAdd;
    @FXML
    private Button btnEdit;
    @FXML
    private Button btnDelete;

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
        initListeners();
        Thread t = new Thread(new Runnable() {
            public void run() {
                db.getUpdateTable();
            }
        });
        t.start();
        tableNotesTable.setItems(db.getNotes());

        try {
            fxmlLoader.setLocation(getClass().getResource("/fxml/add.fxml"));
            fxmlEdit = fxmlLoader.load();
            editDialogController = fxmlLoader.getController();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void initListeners(){
        tableNotesTable.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if(event.getClickCount()==2){
                    editDialogController.setNote((Note) tableNotesTable.getSelectionModel().getSelectedItem());
                    showDialog();
                }
            }
        });
    }

    public void actionButtonPressed(ActionEvent actionEvent) {

        Object source = actionEvent.getSource();
        if (!(source instanceof Button)) {
            return;
        }
        Button clickedButton = (Button) source;
        Note selectedNote = (Note) tableNotesTable.getSelectionModel().getSelectedItem();

        switch (clickedButton.getId()) {
            case "btnAdd":
                editDialogController.setNote(new Note());
                showDialog();
                Thread tr = new Thread(new Runnable() {
                    public void run() {
                        db.add(editDialogController.getNote());
                        db.getUpdateTable();
                    }
                });
                tr.start();
                break;
            case "btnEdit":
                if (selectedNote != null) {
                    editDialogController.setNote(selectedNote);
                    showDialog();
                    if (!editDialogController.getStartTextValue().equals(editDialogController.getNote().getText())) {
                        Thread th = new Thread(new Runnable() {
                            public void run() {
                                db.edit(editDialogController.getNote(), editDialogController.getStartTextValue());
                                db.getUpdateTable();
                            }
                        });
                        th.start();
                    }
                }
                break;
            case "btnDelete":
                if (selectedNote != null) {
                    editDialogController.setNote((Note) tableNotesTable.getSelectionModel().getSelectedItem());
                    Thread t = new Thread(new Runnable() {
                        public void run() {
                            db.delete(editDialogController.getNote());
                            db.getUpdateTable();
                        }
                    });
                    t.start();
                }
                break;
        }
    }

    private void showDialog() {
        if (aditDialogStage == null) {
            aditDialogStage = new Stage();
            aditDialogStage.setTitle("Редактирование записи");
            aditDialogStage.setHeight(400);
            aditDialogStage.setWidth(600);
            aditDialogStage.setResizable(false);
            aditDialogStage.setScene(new Scene(fxmlEdit));
            aditDialogStage.initModality(WINDOW_MODAL);
            aditDialogStage.initOwner(mainStage);
            clock();
            addTextLimiter(editDialogController.textArea, MAX_TEXT_LENGTH);
        }
        aditDialogStage.showAndWait();
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

    private void clock() {
        Thread t = new Thread(new Runnable() {
            public void run() {
                while (true) {
                    Date d = new Date();
                    editDialogController.fldDate.setText(new SimpleDateFormat("dd:MM:yyyy HH:mm:ss").format(d));
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }
        });
        t.start();
    }
}
