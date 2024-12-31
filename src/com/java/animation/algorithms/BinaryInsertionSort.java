package com.java.animation.algorithms;

import javafx.animation.PauseTransition;
import javafx.animation.ParallelTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.TranslateTransition;
import javafx.scene.control.TextField;
import javafx.util.Duration;

public class BinaryInsertionSort {
    private TextField[] ListTF;
    private int i; // Outer loop index
    private int key; // Current element to insert
    private int position; // Position to insert the key

    private final Duration ANIMATION_SPEED = Duration.millis(750); //Time to run step

    public BinaryInsertionSort(TextField[] ListTF) {
        this.ListTF = ListTF;
        this.i = 1; // Start from the second element
    }

    public void runAnimation() {
        if (i < ListTF.length) {
            key = Integer.parseInt(ListTF[i].getText());
            position = binarySearch(0, i - 1, key);
            highlightKeyAndPosition(i, position, () -> {
                shiftElements(position, i, () -> {
                    ListTF[position].setText(String.valueOf(key));
                    ListTF[position].setStyle("-fx-background-color: green;");
                    i++;
                    runAnimation();
                });
            });
        }else{
            resetAllColors();
        }
    }

    private int binarySearch(int low, int high, int key) {
        while (low <= high) {
            int mid = (low + high) / 2;
            int midValue = Integer.parseInt(ListTF[mid].getText());

            if (midValue == key) {
                return mid + 1;
            } else if (midValue < key) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return low;
    }
    private void highlightKeyAndPosition(int keyIndex, int posIndex, Runnable callback) {
        ListTF[keyIndex].setStyle("-fx-background-color: yellow;");
        ListTF[posIndex].setStyle("-fx-background-color: orange;");

        PauseTransition pause = new PauseTransition(ANIMATION_SPEED);
        pause.setOnFinished(event -> {
            ListTF[keyIndex].setStyle("");
            ListTF[posIndex].setStyle("");
            callback.run();
        });
        pause.play();
    }
    private void shiftElements(int start, int end, Runnable callback) {
        if (end > start) {
            swap(end - 1, end, () -> shiftElements(start, end - 1, callback));
        } else if (callback != null) {
            callback.run();
        }
    }
    public void swap(int index, int nextIndex, Runnable callback) {
        TextField First = ListTF[index];
        TextField Second = ListTF[nextIndex];

        First.setStyle("-fx-background-color: blue;");
        Second.setStyle("-fx-background-color: red;");

        double TF_1X = First.getLayoutX();
        double TF_2X = Second.getLayoutX();
        double distance = TF_2X - TF_1X;

        TranslateTransition FirstLeft = new TranslateTransition(ANIMATION_SPEED, First);
        FirstLeft.setByX(distance);

        TranslateTransition SecondRight = new TranslateTransition(ANIMATION_SPEED, Second);
        SecondRight.setByX(-distance);

        ParallelTransition Swap = new ParallelTransition(FirstLeft, SecondRight);

        Swap.setOnFinished(event -> {
            String temp = First.getText();
            First.setText(Second.getText());
            Second.setText(temp);
            First.setStyle("");
            Second.setStyle("");
            First.setTranslateX(0);
            Second.setTranslateX(0);
            if (callback != null) {
                callback.run();
            }
        });

        Swap.play();
    }
    private void resetAllColors() {
        for (TextField tf : ListTF) {
            tf.setStyle("");
        }
    }
}
