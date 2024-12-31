package com.java.animation.algorithms;

import javafx.animation.ParallelTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.TranslateTransition;
import javafx.scene.control.TextField;
import javafx.util.Duration;

public class ShakerSort {
    private TextField[] ListTF;
    private int start; // Starting index of the unsorted section
    private int end;   // Ending index of the unsorted section
    private boolean swapped; // Flag to check if any swaps occurred

    public ShakerSort(TextField[] ListTF) {
        this.ListTF = ListTF;
        this.start = 0;
        this.end = ListTF.length - 1;
        this.swapped = true; // Initialize to start the first pass
    }

    public void runAnimation() {
        if (swapped) {
            swapped = false; // Reset swap flag
            forwardPass(start, () -> {
                end--; // Shrink the unsorted section after forward pass
                backwardPass(end, () -> {
                    start++; // Shrink the unsorted section after backward pass
                    runAnimation(); // Continue to the next round
                });
            });
        }
    }

    private void forwardPass(int i, Runnable callback) {
        if (i < end) {
            compareAndSwap(i, i + 1, () -> {
                forwardPass(i + 1, callback); // Continue the forward pass
            });
        } else if (callback != null) {
            callback.run(); // Trigger the backward pass
        }
    }

    private void backwardPass(int i, Runnable callback) {
        if (i > start) {
            compareAndSwap(i - 1, i, () -> {
                backwardPass(i - 1, callback); // Continue the backward pass
            });
        } else if (callback != null) {
            callback.run(); // Trigger the next forward pass
        }
    }

    private void compareAndSwap(int index1, int index2, Runnable callback) {
        int value1 = Integer.parseInt(ListTF[index1].getText());
        int value2 = Integer.parseInt(ListTF[index2].getText());

        // Highlight the TextFields being compared
        ListTF[index1].setStyle("-fx-background-color: yellow;");
        ListTF[index2].setStyle("-fx-background-color: yellow;");

        if (value1 > value2) {
            swapped = true; // Mark as swapped
            swap(index1, index2, callback);
        } else {
            resetStyles(index1, index2);
            if (callback != null) {
                callback.run();
            }
        }
    }

    public void swap(int index1, int index2, Runnable callback) {
        TextField First = ListTF[index1];
        TextField Second = ListTF[index2];

        First.setStyle("-fx-background-color: blue;");
        Second.setStyle("-fx-background-color: red;");

        double TF_1X = First.getLayoutX();
        double TF_2X = Second.getLayoutX();
        double distance = TF_2X - TF_1X;

        TranslateTransition FirstLeft = new TranslateTransition(Duration.millis(750), First);
        FirstLeft.setByX(distance);

        TranslateTransition SecondRight = new TranslateTransition(Duration.millis(750), Second);
        SecondRight.setByX(-distance);

        ParallelTransition Swap = new ParallelTransition(FirstLeft, SecondRight);

        Swap.setOnFinished(event -> {

            String temp = First.getText();
            First.setText(Second.getText());
            Second.setText(temp);
            resetStyles(index1, index2);
            if (callback != null) {
                callback.run();
            }
        });

        Swap.play();
    }

    private void resetStyles(int index1, int index2) {
        ListTF[index1].setStyle("");
        ListTF[index2].setStyle("");
        ListTF[index1].setTranslateX(0);
        ListTF[index2].setTranslateX(0);
    }
}
