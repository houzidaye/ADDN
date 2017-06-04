package com.example.addndemo2.chartEntity;

/**
 * Line entity for the Chart.
 */

public class Line {
    private float LAxisX;
    private float LxisY;
    private float RAxisX;
    private float RxisY;
    private int lineColor;

    public Line(float LAxisX, float lxisY, float RAxisX, float rxisY, int lineColor) {
        this.LAxisX = LAxisX;
        LxisY = lxisY;
        this.RAxisX = RAxisX;
        RxisY = rxisY;
        this.lineColor = lineColor;
    }

    public float getLAxisX() {
        return LAxisX;
    }

    public void setLAxisX(float LAxisX) {
        this.LAxisX = LAxisX;
    }

    public float getLxisY() {
        return LxisY;
    }

    public void setLxisY(float lxisY) {
        LxisY = lxisY;
    }

    public float getRAxisX() {
        return RAxisX;
    }

    public void setRAxisX(float RAxisX) {
        this.RAxisX = RAxisX;
    }

    public float getRxisY() {
        return RxisY;
    }

    public void setRxisY(float rxisY) {
        RxisY = rxisY;
    }

    public int getLineColor() {
        return lineColor;
    }

    public void setLineColor(int lineColor) {
        this.lineColor = lineColor;
    }
}
