package com.java.animation.algorithms;

import javafx.animation.ParallelTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.TranslateTransition;
import javafx.scene.control.TextField;
import javafx.util.Duration;

public class InterchangeSort{
    private TextField[] ListTF;
    private int i;
    private int j;
    public InterchangeSort(TextField[] ListTF){
        this.ListTF = ListTF;
        this.i = 0;
        this.j = 1;
    }
    public void runAnimation(){
        if(i< ListTF.length-1){
            if(j< ListTF.length){
                int val1 = Integer.parseInt(ListTF[i].getText());
                int val2 = Integer.parseInt(ListTF[j].getText());
                if(val1>val2){
                    swap(i,j,()->{
                        j++;
                        runAnimation();
                    });
                }else{
                    j++;
                    runAnimation();
                }
            }else{
                i++;
                j = i+1;
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
            String temp = First.getText();
            First.setText(Second.getText());
            Second.setText(temp);
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
}