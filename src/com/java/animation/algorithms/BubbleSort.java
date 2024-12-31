package com.java.animation.algorithms;

import javafx.animation.ParallelTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.TranslateTransition;
import javafx.scene.control.TextField;
import javafx.util.Duration;

public class BubbleSort{
    private TextField[] ListTF;
    private int i,j;
    public BubbleSort(TextField[] ListTF){
        this.ListTF = ListTF;
        this.i = 0;
        this.j = 0;
    }
    private void bubbleSortStep() {

        if (i < ListTF.length - 1) {
            j = 0;
            runAnimation();
        }
    }
    public void runAnimation(){
        if (j < ListTF.length - i - 1) {
            int firstValue = Integer.parseInt(ListTF[j].getText());
            int secondValue = Integer.parseInt(ListTF[j + 1].getText());

            if (firstValue > secondValue) {

                swap(j, j + 1, () -> {
                    j++; // Move to the next pair
                    runAnimation(); // Continue the inner loop
                });
            } else {
                j++;
                runAnimation();
            }
        } else {

            i++;
            bubbleSortStep();
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

        TranslateTransition SecondDown = new TranslateTransition(Duration.millis(300), Second);
        SecondDown.setByY(50);
        TranslateTransition SecondLeft = new TranslateTransition(Duration.millis(300), Second);
        SecondLeft.setByX(-distance);
        TranslateTransition SecondUp = new TranslateTransition(Duration.millis(300), Second);
        SecondUp.setByY(-50);

        SequentialTransition FirstST = new SequentialTransition(FirstUp, FirstRight, FirstDown);
        SequentialTransition SecondST = new SequentialTransition(SecondDown, SecondLeft, SecondUp);

        // gộp hiệu ứng
        ParallelTransition Swap = new ParallelTransition(FirstST, SecondST);

        Swap.setOnFinished(event -> {

            String temp = First.getText();
            First.setText(Second.getText());
            Second.setText(temp);

            // Reset style  and positions
            First.setStyle("");
            Second.setStyle("");
            First.setTranslateX(0); // không di chuyeenr tiếp
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