package com.java.animation.algorithms;


import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.util.Duration;

public class LinearSearch{
    private TextField[] ListTF;
    public LinearSearch(TextField[] ListTF){
        this.ListTF = ListTF;
    }
    public void runAnimation(int target) {
        Timeline timeline = new Timeline();
        boolean[] found = {false};

        for (int i = 0; i < ListTF.length; i++) {
            int index = i;
            KeyFrame keyFrame = new KeyFrame(Duration.seconds(i * 0.5), e -> {
                try {
                    int value = Integer.parseInt(ListTF[index].getText().trim());
                    ListTF[index].setStyle("-fx-background-color: yellow; -fx-border-color: black;");

                    if (value == target) {
                        found[0] = true;
                        new Timeline(new KeyFrame(Duration.seconds(0.5), ev ->
                                ListTF[index].setStyle("-fx-background-color: green; -fx-border-color: black;")
                        )).play();
                    } else {

                        new Timeline(new KeyFrame(Duration.seconds(0.5), ev ->
                                ListTF[index].setStyle("")
                        )).play();
                    }
                } catch (NumberFormatException ex) {
                    ListTF[index].setStyle("-fx-background-color: red; -fx-border-color: black;");
                    System.out.println("Invalid input in TextField at index " + index);
                }
            });
            timeline.getKeyFrames().add(keyFrame);
        }

        timeline.setOnFinished(e -> {
            if (found[0]) {
                showAlert("Information", "Đã tìm thấy giá trị trong danh sách.");
            } else {
                showAlert("Information", "Không tìm thấy giá trị trong danh sách.");
            }
        });
        timeline.setCycleCount(1);
        timeline.play();
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.getDialogPane().getStylesheets().add(getClass().getResource("/values/alertcustom.css").toExternalForm());
        alert.show();
    }

}
