package com.java.animation.controller;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

public class Merge{

    @FXML
    private TextField Field1;

    @FXML
    private TextField Field2;

    @FXML
    private TextField Field3;

    @FXML
    private TextField Field4;

    @FXML
    private TextField Field5;

    @FXML
    private TextField Field6;

    @FXML
    private TextField Field7;

    @FXML
    private TextField Field8;

    @FXML
    private HBox hbox1;

    @FXML
    private HBox hbox2;

    @FXML
    private HBox hbox3;

    public HBox[] ListHBox;
    public TextField[] ListTF;
    @FXML
    public void initialize() {
        ListTF = new TextField[]{Field1, Field2, Field3, Field4, Field5, Field6, Field7, Field8};
        ListHBox = new HBox[]{hbox1, hbox2, hbox3};

    }
    public TextField[] getListTF(){
        return ListTF;
    }
    public void LoadValues(int []arr){
        for(int i =0 ; i < ListTF.length;i++){
            ListTF[i].setText(String.valueOf(arr[i]));
        }
    }
    public HBox[] getListHBox(){
        return ListHBox;
    }

}
