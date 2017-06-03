package com.example.addndemo2.chartEntity;

/**
 * Created by USER on 2017/5/20.
 */

public class Point {
    private float axisX;
    private float axisY;
    private String message;
    private int msgColor;

    public Point(float axisX, float axisY, String message, int msgColor) {
        this.axisX = axisX;
        this.axisY = axisY;
        this.message = message;
        this.msgColor = msgColor;
    }

    public float getAxisX() {
        return axisX;
    }

    public void setAxisX(float axisX) {
        this.axisX = axisX;
    }

    public float getAxisY() {
        return axisY;
    }

    public void setAxisY(float axisY) {
        this.axisY = axisY;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getMsgColor() {
        return msgColor;
    }

    public void setMsgColor(int msgColor) {
        this.msgColor = msgColor;
    }
}
