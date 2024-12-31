package com.java.animation.algorithms;

import javafx.animation.*;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.util.Duration;

import java.util.Arrays;

public class MergeSort {
    private TextField[] listTF;
    private String[] logicalArray; // Logical state of the array
    private static final double OFFSET_Y = 50.0;
    private static final Duration ANIMATION_DURATION = Duration.millis(750);

    public MergeSort(TextField[] listTF) {
        validateInputs(listTF);
        this.listTF = listTF;
        this.logicalArray = Arrays.stream(listTF)
                .map(TextField::getText)
                .toArray(String[]::new); // Initialize logicalArray
    }

    private void validateInputs(TextField[] inputs) {
        for (TextField tf : inputs) {
            if (!isValidInteger(tf.getText())) {
                tf.setStyle("-fx-border-color: red;");
                showAlert("Input Error", "All TextFields must contain valid integers.");
                throw new IllegalArgumentException("Invalid input: All fields must be integers");
            }
        }
    }

    public void runAnimation() {
        Animation animation = mergeSort(0, listTF.length - 1, 0);
        animation.play();
    }

    private Animation mergeSort(int left, int right, int depth) {
        if (left >= right) {
            return new PauseTransition(Duration.ZERO);
        }

        int mid = left + (right - left) / 2;
        SequentialTransition sequence = new SequentialTransition();

        // Move current range down based on depth
        sequence.getChildren().add(moveElementsDown(left, right, depth));

        // Recursive calls for left and right halves
        sequence.getChildren().add(mergeSort(left, mid, depth + 1));
        sequence.getChildren().add(mergeSort(mid + 1, right, depth + 1));

        // Merge the two halves
        sequence.getChildren().add(merge(left, mid, right));

        return sequence;
    }

    private Animation moveElementsDown(int left, int right, int depth) {
        ParallelTransition moveDown = new ParallelTransition();
        double targetTranslateY = depth * OFFSET_Y;

        for (int i = left; i <= right; i++) {
            final int index = i;
            TranslateTransition move = new TranslateTransition(ANIMATION_DURATION, listTF[index]);
            move.setToY(targetTranslateY);
            moveDown.getChildren().add(move);
        }

        return moveDown;
    }

    private Animation merge(int left, int mid, int right) {
        SequentialTransition sequence = new SequentialTransition();

        // Highlight merge sections
        sequence.getChildren().add(highlightMergeSections(left, mid, right));

        // Perform merge on logical array
        String[] mergedArray = new String[right - left + 1];
        int i = left, j = mid + 1, k = 0;

        while (i <= mid && j <= right) {
            int value1 = Integer.parseInt(logicalArray[i]);
            int value2 = Integer.parseInt(logicalArray[j]);

            if (value1 <= value2) {
                mergedArray[k++] = logicalArray[i++];
            } else {
                mergedArray[k++] = logicalArray[j++];
            }
        }

        while (i <= mid) mergedArray[k++] = logicalArray[i++];
        while (j <= right) mergedArray[k++] = logicalArray[j++];

        // Update logical array
        System.arraycopy(mergedArray, 0, logicalArray, left, mergedArray.length);

        // Animate the updates on the TextFields
        sequence.getChildren().add(updateTextFields(left, right, mergedArray));

        return sequence;
    }

    private Animation highlightMergeSections(int left, int mid, int right) {
        ParallelTransition highlightSections = new ParallelTransition();

        for (int i = left; i <= mid; i++) {
            highlightSections.getChildren().add(changeBackgroundColor(listTF[i], "lightblue"));
        }

        for (int i = mid + 1; i <= right; i++) {
            highlightSections.getChildren().add(changeBackgroundColor(listTF[i], "#00f899"));
        }

        return highlightSections;
    }

    private Animation updateTextFields(int left, int right, String[] mergedArray) {
        ParallelTransition updateValues = new ParallelTransition();

        for (int t = left, tempIdx = 0; t <= right; t++, tempIdx++) {
            final int index = t;
            final String newValue = mergedArray[tempIdx];

            Timeline updateAnimation = new Timeline(
                    new KeyFrame(Duration.ZERO,
                            e -> listTF[index].setStyle("-fx-background-color: #FFBC00;")),
                    new KeyFrame(ANIMATION_DURATION,
                            e -> listTF[index].setText(newValue)),
                    new KeyFrame(ANIMATION_DURATION.multiply(2),
                            e -> listTF[index].setStyle(""))
            );

            updateValues.getChildren().add(updateAnimation);
        }

        return updateValues;
    }
    private Animation changeBackgroundColor(TextField tf, String color) {
        return new Timeline(
                new KeyFrame(Duration.ZERO,
                        e -> tf.setStyle("-fx-background-color: " + color + ";")),
                new KeyFrame(ANIMATION_DURATION,
                        e -> tf.setStyle(""))
        );
    }

    private boolean isValidInteger(String input) {
        try {
            Integer.parseInt(input);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setContentText(content);
        alert.setHeaderText(null);
        alert.showAndWait();
    }
}
