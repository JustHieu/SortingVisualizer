package com.java.animation.controller;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class Linear {

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
    private TextField FieldX;
    public TextField[] ListTF;
    @FXML
    public void initialize() {
        ListTF = new TextField[]{Field1, Field2, Field3, Field4, Field5, Field6, Field7, Field8};
    }
    public TextField[] getListTF(){
        return ListTF;
    }
    public void LoadValues(int []arr, int x){
        for(int i =0 ; i < ListTF.length;i++){
            ListTF[i].setText(String.valueOf(arr[i]));
        }
        FieldX.setText(String.valueOf((x)));
    }
}
