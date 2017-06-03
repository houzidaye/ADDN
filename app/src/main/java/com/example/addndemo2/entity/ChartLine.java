package com.example.addndemo2.entity;

import lecho.lib.hellocharts.model.Line;

public class ChartLine {
private float pointValue;
private Line line;

public ChartLine(){

}


public ChartLine(float pointValue,Line line){
	this.line = line;
	this.pointValue = pointValue;
}
public float getPointValue() {
	return pointValue;
}
public void setPointValue(float pointValue) {
	this.pointValue = pointValue;
}
public Line getLine() {
	return line;
}
public void setLine(Line line) {
	this.line = line;
}

}
