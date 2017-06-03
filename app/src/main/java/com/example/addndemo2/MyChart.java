package com.example.addndemo2;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.example.addndemo2.chartEntity.Circle;
import com.example.addndemo2.chartEntity.Line;
import com.example.addndemo2.chartEntity.Point;
import com.example.addndemo2.chartEntity.Rectangle;
import com.example.addndemo2.utils.Constants;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by USER on 2017/5/20.
 */

public class MyChart extends View {
    private Paint mPaint;
    private List<Line> lines4Chart = new ArrayList<Line>();
    private List<Circle> circles4Chart= new ArrayList<Circle>();
    private List<Point> points4Chart= new ArrayList<Point>();
    private List<Rectangle> rectangles4Chart= new ArrayList<Rectangle>();
    private float axisX;
    private float axisY;

    public List<Line> getLines4Chart() {
        return lines4Chart;
    }

    public void setLines4Chart(List<Line> lines4Chart) {
        this.lines4Chart = lines4Chart;
    }

    public List<Circle> getCircles4Chart() {
        return circles4Chart;
    }

    public void setCircles4Chart(List<Circle> circles4Chart) {
        this.circles4Chart = circles4Chart;
    }

    public List<Point> getPoints4Chart() {
        return points4Chart;
    }

    public void setPoints4Chart(List<Point> points4Chart) {
        this.points4Chart = points4Chart;
    }

    public List<Rectangle> getRectangles4Chart() {
        return rectangles4Chart;
    }

    public void setRectangles4Chart(List<Rectangle> rectangles4Chart) {
        this.rectangles4Chart = rectangles4Chart;
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

    public MyChart(Context context) {
        super(context);
    }

    public MyChart(Context context, AttributeSet attrs) {
        super(context, attrs);
        mPaint = new Paint();
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.MyView);
        int textColor = array.getColor(R.styleable.MyView_textColor, 0XFF00FF00);
        float textSize = array.getDimension(R.styleable.MyView_textSize, 36);
        mPaint.setColor(textColor);
        mPaint.setTextSize(textSize);
        array.recycle();
    }

    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
//        rectangles4Chart.add(new Rectangle(20,20,20,20,Color.YELLOW, Paint.Style.FILL));

//        canvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR);
        for (Rectangle rectangle : rectangles4Chart) {
            Log.d("print rectangle",String.valueOf(rectangle.getTop()));
            mPaint.setColor(rectangle.getLineColor());
            mPaint.setStyle(rectangle.getStyle());
            canvas.drawRect(rectangle.getLeft(), rectangle.getTop(), rectangle.getRight(),
                    rectangle.getBottom(), mPaint);
        }

        for (Circle circle : circles4Chart) {
            mPaint.setColor(circle.getColor());
            canvas.drawCircle(circle.getAxisX(),circle.getAxisY(),
                    circle.getRadius(),mPaint);
        }

        for (Point point : points4Chart) {
            mPaint.setColor(point.getMsgColor());
            canvas.drawText(point.getMessage(), point.getAxisX(),
                    point.getAxisY(), mPaint);
        }

        for (Line line : lines4Chart) {
            mPaint.setColor(line.getLineColor());
            canvas.drawLine(line.getLAxisX(), line.getLxisY(), line.getRAxisX(),
                    line.getRxisY(), mPaint);
        }
        mPaint.setColor(Color.WHITE);
        canvas.drawLine(10, Constants.chartAxisY,10,0,mPaint);
        canvas.drawLine(10,Constants.chartAxisY,Constants.chartAxisX,Constants.chartAxisY,mPaint);

    }

}
