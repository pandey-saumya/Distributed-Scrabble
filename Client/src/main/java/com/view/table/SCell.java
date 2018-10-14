package com.view.table;

import javafx.scene.paint.Color;
import javafx.scene.control.Button;
import javafx.scene.effect.InnerShadow;


public class SCell extends Button {

    public CStatus status;
    public Boolean isHighLight;

    public SCell() {
        isHighLight = false;
        setInitStatus();
    }

    public void setInitStatus() {
        setEffect(null);
        setStyle("-fx-text-fill: rgb(132, 117, 225)");
        status = CStatus.INIT;
        isHighLight = false;
    }

    public void setLockStatus(){
        System.out.print("when is the lock getting set");
        status=CStatus.LOCK;
        isHighLight=false;
    }

    public void setHighLightStatus() {
        InnerShadow innerShadow = new InnerShadow();
        innerShadow.setRadius(50);
        innerShadow.setWidth(30);
        innerShadow.setColor(Color.DEEPSKYBLUE);
        setEffect(innerShadow);
        isHighLight = true;
    }
}