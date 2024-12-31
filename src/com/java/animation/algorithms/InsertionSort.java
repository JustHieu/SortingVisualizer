package com.java.animation.algorithms;

import javafx.animation.ParallelTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.TranslateTransition;
import javafx.scene.control.TextField;
import javafx.util.Duration;

public class InsertionSort {
    private TextField[] ListTF;
    private int i;
    private int j;
    public InsertionSort(TextField[] ListTF){
        this.ListTF = ListTF;
        this.i =1;
        this.j = 0;
    }
    public void runAnimation() {
        if (i < ListTF.length) {
            int key = Integer.parseInt(ListTF[i].getText());
            j = i - 1;
            performInsertion(key);
        }
    }

    private void performInsertion(int key) {
        if (j >= 0 && Integer.parseInt(ListTF[j].getText()) > key) {

            swap(j, j + 1, () -> {
                j--; // Move left
                performInsertion(key);
            });
        } else {
            i++;
            runAnimation(); // Proceed to the next key
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

        TranslateTransition FirstUp = new TranslateTransition(Duration.millis(300), First);
        FirstUp.setByY(-50);
        TranslateTransition FirstRight = new TranslateTransition(Duration.millis(300), First);
        FirstRight.setByX(distance);
        TranslateTransition FirstDown = new TranslateTransition(Duration.millis(300), First);
        FirstDown.setByY(50);


        TranslateTransition SecondLeft = new TranslateTransition(Duration.millis(300), Second);
        SecondLeft.setByX(-distance);


        SequentialTransition FirstST = new SequentialTransition(FirstUp, FirstRight, FirstDown);
        SequentialTransition SecondST = new SequentialTransition( SecondLeft);

        ParallelTransition Swap = new ParallelTransition(FirstST, SecondST);

        Swap.setOnFinished(event -> {
            String temp = First.getText();
            First.setText(Second.getText());
            Second.setText(temp);
            First.setStyle("");
            Second.setStyle("");
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
}
