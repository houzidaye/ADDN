package com.example.addndemo2.chartEntity;

import android.graphics.Paint;

/**
 * Created by USER on 2017/5/20.
 */

public class Rectangle {
    private float left;
    private float top;
    private float right;
    private float bottom;
    private int lineColor;
    private Paint.Style style;

    @Override
    public String toString() {
        return "Rectangle{" +
                "left=" + left +
                ", top=" + top +
                ", right=" + right +
                ", bottom=" + bottom +
                ", lineColor=" + lineColor +
                ", style=" + style +
                '}';
    }

    public Rectangle(float left, float top, float right, float bottom, int lineColor, Paint.Style style) {
        this.left = left;
        this.top = top;
        this.right = right;
        this.bottom = bottom;
        this.lineColor = lineColor;
        this.style = style;
    }

    public float getLeft() {
        return left;
    }

    public void setLeft(float left) {
        this.left = left;
    }

    public float getTop() {
        return top;
    }

    public void setTop(float top) {
        this.top = top;
    }

    public float getRight() {
        return right;
    }

    public void setRight(float right) {
        this.right = right;
    }

    public float getBottom() {
        return bottom;
    }

    public void setBottom(float bottom) {
        this.bottom = bottom;
    }

    public int getLineColor() {
        return lineColor;
    }

    public void setLineColor(int lineColor) {
        this.lineColor = lineColor;
    }

    public Paint.Style getStyle() {
        return style;
    }

    public void setStyle(Paint.Style style) {
        this.style = style;
    }
}
