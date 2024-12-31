package com.java.animation;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../../../fxml/sort_view.fxml"));
        BorderPane root = loader.load();
        primaryStage.setTitle("Sort Animation");
        primaryStage.setScene(new Scene(root, 1200, 800));
        primaryStage.show();
    }
    public static void main(String[] args) {

        launch(args);
    }
}
