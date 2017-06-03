package com.example.addndemo2.utils;

import java.util.ArrayList;
import java.util.List;

import android.graphics.Color;

import lecho.lib.hellocharts.model.Line;
import lecho.lib.hellocharts.model.PointValue;


public class LineChartUtil {
	public static Line createLine(float axisYvalue,float initAxisXValue,float axisXSize,int color,boolean isFilled) {
		List<PointValue> values = new ArrayList<PointValue>();// the points of the line
		values.add(new PointValue(initAxisXValue, axisYvalue));
		values.add(new PointValue(initAxisXValue+axisXSize, axisYvalue));
		Line line = new Line(values).setColor(color);// new a line and set its color
		line.setCubic(false);// true--smooth,false--straight
		line.setFilled(isFilled);//fill with color
		line.setHasPoints(false);//show the points
		line.setHasLabels(true);//show the data of the points
//		line.setHasLabelsOnlyForSelected(true);//show the data when touch
		return line;
	}
	public static Line createOnePointLine(float showAtAxisX,float axisYvalue,int color,String msg) {
		List<PointValue> values = new ArrayList<PointValue>();// the points of the line
		PointValue pointValue = new PointValue(showAtAxisX, axisYvalue);
		pointValue.setLabel(msg+" "+String.valueOf(axisYvalue));
		values.add(pointValue);
		Line line = new Line(values).setColor(color);// new a line and set its color
		line.setCubic(false);// true--smooth,false--straight
		line.setFilled(false);//fill with color
		line.setHasPoints(true);//show the points
		line.setHasLabels(true);//show the data of the points
//		line.setHasLabelsOnlyForSelected(true);//show the data when touch
		return line;
	}
}
