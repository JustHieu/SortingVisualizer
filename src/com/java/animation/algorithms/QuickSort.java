package com.java.animation.algorithms;

import javafx.animation.*;
import javafx.scene.control.TextField;
import javafx.util.Duration;

import java.util.concurrent.atomic.AtomicInteger;

public class QuickSort {
    private TextField[] ListTF;

    public QuickSort(TextField[] ListTF) {
        this.ListTF = ListTF;
    }

    public void runAnimation() {
        quickSort(0, ListTF.length - 1, () -> {
            resetAllColors(); // Call reset only after all sorting is done
            System.out.println("QuickSort complete!");
        });
    }

    private void quickSort(int low, int high, Runnable callback) {
        if (low < high) {
            partitionWithAnimation(low, high, pivotIndex -> {
                quickSort(low, pivotIndex - 1, () -> {
                    quickSort(pivotIndex + 1, high, callback);
                });
            });
        } else if (callback != null) {
            callback.run();
        }
    }

    private interface PartitionCallback {
        void onPartitionComplete(int pivotIndex);
    }

    private void partitionWithAnimation(int low, int high, PartitionCallback callback) {
        final TextField pivotTF = ListTF[high];
        pivotTF.setStyle("-fx-background-color: orange;");

        final int pivotValue = Integer.parseInt(pivotTF.getText());
        final AtomicInteger i = new AtomicInteger(low - 1);

        partitionStep(low, high, i, pivotValue, () -> {
            final int pivotIndex = i.get() + 1;
            if (pivotIndex != high) {
                swap(pivotIndex, high, () -> {
                    resetStyles(low, high);
                    callback.onPartitionComplete(pivotIndex);
                });
            } else {
                resetStyles(low, high);
                callback.onPartitionComplete(pivotIndex);
            }
        });
    }

    private void partitionStep(int j, int high, AtomicInteger i, int pivotValue, Runnable callback) {
        if (j <= high - 1) {
            final TextField currentTF = ListTF[j];
            final int currentValue = Integer.parseInt(currentTF.getText());
            final int indexJ = j;
            currentTF.setStyle("-fx-background-color: yellow;");

            PauseTransition pause = new PauseTransition(Duration.millis(500));
            pause.setOnFinished(e -> {
                if (currentValue <= pivotValue) {
                    i.incrementAndGet();
                    final int indexI = i.get();
                    if (indexI != indexJ) {
                        swap(indexI, indexJ, () -> {
                            currentTF.setStyle("-fx-background-color: lightgreen;");
                            partitionStep(indexJ + 1, high, i, pivotValue, callback);
                        });
                    } else {
                        currentTF.setStyle("-fx-background-color: lightgreen;");
                        partitionStep(indexJ + 1, high, i, pivotValue, callback);
                    }
                } else {
                    currentTF.setStyle("-fx-background-color: lightcoral;");
                    partitionStep(indexJ + 1, high, i, pivotValue, callback);
                }
            });
            pause.play();
        } else {
            callback.run();
        }
    }

    public void swap(int index1, int index2, Runnable callback) {
        TextField First = ListTF[index1];
        TextField Second = ListTF[index2];

        ListTF[index1] = Second;
        ListTF[index2] = First;

        // Highlight the elements being swapped
        First.setStyle("-fx-background-color: blue;");
        Second.setStyle("-fx-background-color: blue;");

        double TF_1X = First.getLayoutX();
        double TF_2X = Second.getLayoutX();
        double distance = TF_2X - TF_1X;

        TranslateTransition FirstUp = new TranslateTransition(Duration.millis(300), First);
        FirstUp.setByY(-50);
        TranslateTransition FirstRight = new TranslateTransition(Duration.millis(300), First);
        FirstRight.setByX(distance);
        TranslateTransition FirstDown = new TranslateTransition(Duration.millis(300), First);
        FirstDown.setByY(50);

        TranslateTransition SecondDown = new TranslateTransition(Duration.millis(300), Second);
        SecondDown.setByY(50);
        TranslateTransition SecondLeft = new TranslateTransition(Duration.millis(300), Second);
        SecondLeft.setByX(-distance);
        TranslateTransition SecondUp = new TranslateTransition(Duration.millis(300), Second);
        SecondUp.setByY(-50);

        SequentialTransition FirstST = new SequentialTransition(FirstUp, FirstRight, FirstDown);
        SequentialTransition SecondST = new SequentialTransition(SecondDown, SecondLeft, SecondUp);

        ParallelTransition Swap = new ParallelTransition(FirstST, SecondST);

        Swap.setOnFinished(event -> {

            First.setLayoutX(TF_2X);
            Second.setLayoutX(TF_1X);

            First.setStyle("");
            Second.setStyle("");
            First.setTranslateX(0);
            Second.setTranslateX(0);
            First.setTranslateY(0);
            Second.setTranslateY(0);
            if (callback != null) {
                callback.run();
            }
        });

        Swap.play();
    }

    private void resetStyles(int low, int high) {
        for (int k = low; k <= high; k++) {
            TextField tf = ListTF[k];
            String style = tf.getStyle();

            if (style.contains("lightgreen") || style.contains("lightcoral") || style.contains("lightblue")) {
                continue;
            }

            tf.setStyle("");
        }
    }
    private void resetAllColors() {
        for (TextField tf : ListTF) {
            tf.setStyle("");

        }
    }
}
