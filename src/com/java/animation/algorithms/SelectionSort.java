package com.java.animation.algorithms;

import javafx.animation.ParallelTransition;
import javafx.animation.PauseTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.TranslateTransition;
import javafx.scene.control.TextField;
import javafx.util.Duration;

public class SelectionSort {
    private TextField[] ListTF;
    private int i; // Outer loop index
    private int j; // Inner loop index
    private int minIndex; // Index of the minimum value
    private static final Duration ANIMATION_SPEED = Duration.millis(600);

    public SelectionSort(TextField[] ListTF) {
        this.ListTF = ListTF;
        this.i = 0; // Initialize the outer loop index
        this.j = 0; // Initialize the inner loop index
        this.minIndex = 0; // Initialize the minimum index
    }

    public void runAnimation() {
        if (i < ListTF.length - 1) {
            minIndex = i; // Start with the current index as the minimum
            j = i + 1; // Start comparing with the next element
            highlightAndSwapMin();
        } else {
            resetStyles(); // Reset styles after completion
        }
    }

    private void highlightAndSwapMin() {
        ListTF[i].setStyle("-fx-background-color: #3A6D8C;");
        if (j < ListTF.length) {
            ListTF[j].setStyle("-fx-background-color: yellow;");

            // Reset the style of the previous element if it's not the minimum or the starting point
            if (j - 1 >= 0 && j - 1 != minIndex && j - 1 != i) {
                ListTF[j - 1].setStyle("");
            }

            // Always keep the minimum element highlighted in orange
            if (minIndex != i) {
                ListTF[minIndex].setStyle("-fx-background-color: red;");
            }

            // Compare the current element with the minimum
            int currentValue = Integer.parseInt(ListTF[j].getText());
            int minValue = Integer.parseInt(ListTF[minIndex].getText());

            if (currentValue < minValue) {
                // Reset the old minimum's style if it's not the starting index
                if (minIndex != i) {
                    ListTF[minIndex].setStyle("");
                }

                // Update the minimum index and highlight it
                minIndex = j;
                ListTF[minIndex].setStyle("-fx-background-color: orange;");
            }

            j++; // Move to the next element

            // Delay before moving to the next comparison
            PauseTransition delay = new PauseTransition(ANIMATION_SPEED);
            delay.setOnFinished(e -> highlightAndSwapMin());
            delay.play();
        } else {
            // After finding the minimum, swap it with the current index
            if (minIndex != i) {
                swap(i, minIndex, () -> {
                    i++; // Move to the next index for the outer loop
                    runAnimation(); // Continue sorting
                });
            } else {
                // No swap needed, move to the next index
                i++;
                runAnimation();
            }
        }
    }


    public void swap(int index, int nextIndex, Runnable callback) {
        TextField First = ListTF[index];
        TextField Second = ListTF[nextIndex];

        First.setStyle("-fx-background-color: blue;");
        Second.setStyle("-fx-background-color: blue;");

        double TF_1X = First.getLayoutX();
        double TF_2X = Second.getLayoutX();
        double distance = TF_2X - TF_1X;

        TranslateTransition FirstUp = new TranslateTransition(ANIMATION_SPEED, First);
        FirstUp.setByY(-50);
        TranslateTransition FirstRight = new TranslateTransition(ANIMATION_SPEED, First);
        FirstRight.setByX(distance);
        TranslateTransition FirstDown = new TranslateTransition(ANIMATION_SPEED, First);
        FirstDown.setByY(50);

        TranslateTransition SecondDown = new TranslateTransition(ANIMATION_SPEED, Second);
        SecondDown.setByY(50);
        TranslateTransition SecondLeft = new TranslateTransition(ANIMATION_SPEED, Second);
        SecondLeft.setByX(-distance);
        TranslateTransition SecondUp = new TranslateTransition(ANIMATION_SPEED, Second);
        SecondUp.setByY(-50);

        SequentialTransition FirstST = new SequentialTransition(FirstUp, FirstRight, FirstDown);
        SequentialTransition SecondST = new SequentialTransition(SecondDown, SecondLeft, SecondUp);

        // Parallel animation for swapping
        ParallelTransition Swap = new ParallelTransition(FirstST, SecondST);

        Swap.setOnFinished(event -> {
            // Swap the values in the TextFields
            String temp = First.getText();
            First.setText(Second.getText());
            Second.setText(temp);

            // Reset styles and positions
            resetStyles();
            First.setTranslateX(0);
            Second.setTranslateX(0);
            First.setTranslateY(0);
            Second.setTranslateY(0);

            // Continue the animation with the callback
            if (callback != null) {
                callback.run();
            }
        });

        Swap.play();
    }

    private void resetStyles() {
        for (TextField tf : ListTF) {
            tf.setStyle("");
        }
    }
}
