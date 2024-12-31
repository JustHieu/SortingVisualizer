package com.java.animation.algorithms;

import javafx.animation.Animation;
import javafx.animation.FillTransition;
import javafx.animation.Timeline;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.util.Duration;
import javafx.animation.KeyFrame;

public class BinarySearch{
    private TextField[] ListTF;
    public BinarySearch(TextField[] ListTF){
        this.ListTF = ListTF;
    }
    public void runAnimation(int x) {
        Timeline timeline = new Timeline();
        boolean[] found = {false};
        int[] bounds = {0, ListTF.length - 1};

        timeline.getKeyFrames().add(new KeyFrame(Duration.seconds(1.5), e -> {
            int left = bounds[0];
            int right = bounds[1];

            if (left > right) {

                timeline.stop();
                showAlert("Information", "Không tìm thấy giá trị trong danh sách.");
                return;
            }

            int mid = (left + right) / 2;
            for (TextField tf : ListTF) {
                tf.setStyle("");
            }


            ListTF[left].setStyle("-fx-background-color: pink; -fx-border-color: black;");
            ListTF[right].setStyle("-fx-background-color: blue; -fx-border-color: black;");
            ListTF[mid].setStyle("-fx-background-color: yellow; -fx-border-color: black;");

            int midValue;
            try {
                midValue = Integer.parseInt(ListTF[mid].getText().trim());
            } catch (NumberFormatException ex) {
                showAlert("Error", "Giá trị tại vị trí giữa không hợp lệ!");
                timeline.stop();
                return;
            }

            if (midValue == x) {
                found[0] = true;
                ListTF[mid].setStyle("-fx-background-color: green; -fx-border-color: black;");
                timeline.stop();
                showAlert("Information", "Đã tìm thấy giá trị trong danh sách.");
            } else if (midValue < x) {
                bounds[0] = mid + 1;
            } else {
                bounds[1] = mid - 1;
            }
        }));

        timeline.setCycleCount(Animation.INDEFINITE); // tiếp tục lặp tới khi dừng
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