package com.sample.start;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent panel = FXMLLoader.load(getClass().getResource("/fxml/main.fxml"));

        primaryStage.setMinHeight(500);
        primaryStage.setMinWidth(600);
        primaryStage.setTitle("Мои заметки");
        primaryStage.setScene(new Scene(panel, 800, 400));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
