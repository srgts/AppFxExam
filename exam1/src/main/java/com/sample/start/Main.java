package com.sample.start;

import com.sample.controllers.MainController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/fxml/main.fxml"));
        Parent fxmlMain = fxmlLoader.load();
        MainController mainController = fxmlLoader.getController();
        mainController.setMainStage(primaryStage);

        primaryStage.setMinHeight(500);
        primaryStage.setMinWidth(600);
        primaryStage.setTitle("Мои заметки");
        primaryStage.setScene(new Scene(fxmlMain, 800, 400));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
