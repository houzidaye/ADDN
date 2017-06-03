package com.example.addndemo2.chartEntity;

/**
 * Created by USER on 2017/5/20.
 */

public class Circle {
    private float axisX;
    private float axisY;
    private float radius;
    private int color;

    public Circle(float axisX, float axisY, float radius, int color) {
        this.axisX = axisX;
        this.axisY = axisY;
        this.radius = radius;
        this.color = color;
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

    public float getRadius() {
        return radius;
    }

    public void setRadius(float radius) {
        this.radius = radius;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }
}
