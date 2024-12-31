package com.java.animation.algorithms;

import javafx.animation.ParallelTransition;
import javafx.animation.TranslateTransition;
import javafx.scene.control.TextField;
import javafx.util.Duration;

public class ShellSort {
    private TextField[] ListTF;
    private int n;
    private int gap;
    private int i; // biến vòng lặp trong
    private int j; // biến vòng lặp ngoài

    public ShellSort(TextField[] ListTF) {
        this.ListTF = ListTF;
        this.n = ListTF.length;
    }

    public void runAnimation() {
        gap = n / 2; // Initial gap value
        stepGapSort(() -> System.out.println("Sorting complete!")); // in ra màn hình console
    }

    private void stepGapSort(Runnable onComplete) {
        if (gap > 0) {
            i = gap;
            stepOuterLoop(() -> {
                gap /= 2; // Reduce the gap
                stepGapSort(onComplete); // Recurse with the next gap
            });
        } else if (onComplete != null) {
            onComplete.run(); // Trigger onComplete when done
        }
    }

    private void stepOuterLoop(Runnable onComplete) {
        if (i < n) {
            int temp = parseIntSafe(ListTF[i].getText());
            j = i;

            stepInnerLoop(temp, () -> {
                i++;
                stepOuterLoop(onComplete); // Continue the outer loop
            });
        } else if (onComplete != null) {
            onComplete.run(); // Trigger when done
        }
    }

    private void stepInnerLoop(int temp, Runnable onComplete) {
        if (j >= gap && parseIntSafe(ListTF[j - gap].getText()) > temp) {
            swap(j, j - gap, () -> {
                j -= gap;
                stepInnerLoop(temp, onComplete); // Continue the inner loop
            });
        } else {
            // Insert the temp value
            ListTF[j].setText(String.valueOf(temp));
            resetStyles(j);
            if (onComplete != null) {
                onComplete.run(); // Trigger when done
            }
        }
    }

    public void swap(int index1, int index2, Runnable callback) {
        TextField First = ListTF[index1];
        TextField Second = ListTF[index2];

        // Highlight the elements being swapped
        First.setStyle("-fx-background-color: blue;");
        Second.setStyle("-fx-background-color: red;");

        double TF_1X = First.getLayoutX();
        double TF_2X = Second.getLayoutX();
        double distance = TF_2X - TF_1X;

        // Translation animations
        TranslateTransition FirstLeft = new TranslateTransition(Duration.millis(1500), First);
        FirstLeft.setByX(distance);

        TranslateTransition SecondRight = new TranslateTransition(Duration.millis(1500), Second);
        SecondRight.setByX(-distance);

        ParallelTransition SwapAnimation = new ParallelTransition(FirstLeft, SecondRight);

        SwapAnimation.setOnFinished(event -> {
            // Swap the text values
            String temp = First.getText();
            First.setText(Second.getText());
            Second.setText(temp);

            // Reset styles and positions
            resetStyles(index1, index2);
            First.setTranslateX(0);
            Second.setTranslateX(0);

            // Continue callback
            if (callback != null) {
                callback.run();
            }
        });

        SwapAnimation.play();
    }

    private void resetStyles(int index) {
        ListTF[index].setStyle("");
    }

    private void resetStyles(int index1, int index2) {
        ListTF[index1].setStyle("");
        ListTF[index2].setStyle("");
    }

    private int parseIntSafe(String text) {
        try {
            return Integer.parseInt(text);
        } catch (NumberFormatException e) {
            return Integer.MAX_VALUE; // trả về maxval nhằm không ngắt vòng lặp
        }
    }
}
