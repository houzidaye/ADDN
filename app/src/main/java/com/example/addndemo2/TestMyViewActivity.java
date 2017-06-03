package com.example.addndemo2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.addndemo2.chartEntity.Line;
import com.example.addndemo2.chartEntity.Point;
import com.example.addndemo2.chartEntity.Rectangle;
import com.example.addndemo2.entity.HbA1cDistribution;
import com.example.addndemo2.entity.HttpResponseJson;

import java.util.List;

/**
 * Created by USER on 2017/5/20.
 */

public class TestMyViewActivity extends Activity {
    private MyChart mChartView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_chart);
        mChartView = (MyChart) findViewById(R.id.my_chart);
//        List<Line> lineList = new ArrayList<Line>();
//        List<Rectangle> rectangles4Chart = new ArrayList<Rectangle>();
//        float maxAxisY = 1000;
//        float endAxisX = 1000;
//        float beginAxisX = (float) (endAxisX*0.2);
//
//        lineList.add(new Line(beginAxisX, 80, 1000, 80, Color.WHITE));
//
//        List<com.example.addndemo2.chartEntity.Point> points = new ArrayList<com.example.addndemo2.chartEntity.Point>();
//        points.add(new com.example.addndemo2.chartEntity.Point(beginAxisX,maxAxisY-100,null,Color.parseColor("#1EAD64")));
//        points.add(new com.example.addndemo2.chartEntity.Point(beginAxisX,maxAxisY-800,null,Color.RED));
//        points.add(new com.example.addndemo2.chartEntity.Point(beginAxisX,maxAxisY-700,null, Color.parseColor("#F9AB21")));
//        points.add(new com.example.addndemo2.chartEntity.Point(beginAxisX,maxAxisY-600,null,Color.parseColor("#E37E2B")));
//        points.add(new com.example.addndemo2.chartEntity.Point(beginAxisX,maxAxisY-500,null,Color.parseColor("#BF3829")));
//
//        for (int i=0;i<points.size()-1;i++) {
//            for (int j=0;j<points.size()-i-1;j++) {
//                if (points.get(j).getAxisY()<points.get(j+1).getAxisY()) {
//                    com.example.addndemo2.chartEntity.Point minPoint = new com.example.addndemo2.chartEntity.Point(points.get(j).getAxisX(),
//                            points.get(j).getAxisY(),points.get(j).getMessage(),points.get(j).getMsgColor());
//                    points.set(j, points.get(j+1));
//                    points.set(j+1,minPoint);
//                }
//            }
//        }
//        for (int i=0; i<points.size()-1;i++) {
//            rectangles4Chart.add(new Rectangle(beginAxisX, maxAxisY-points.get(i).getAxisY()  , endAxisX,
//                    maxAxisY-points.get(i+1).getAxisY(), points.get(i).getMsgColor(), Paint.Style.FILL));
//        }
        Intent intent = this.getIntent();
        TextView tv = (TextView) findViewById(R.id.chartDsipaly_show_intent);
        HttpResponseJson responseJson = (HttpResponseJson) intent
                .getSerializableExtra("responseJson");
        // tv.setText(responseJson.toString());
        HbA1cDistribution hba1cd = responseJson.getHbA1cDistribution();
        List<Rectangle> rectangles4Chart = hba1cd.getHbA1cDistibutionRectangle();
        List<Line> lineList = hba1cd.getLine();
        List<Point> pointList = hba1cd.getPoint();
        for (Rectangle rect:rectangles4Chart
             ) {
            Log.d("print rectangle ",rect.toString());
        }
        mChartView.setRectangles4Chart(rectangles4Chart);
        mChartView.setLines4Chart(lineList);
        mChartView.setPoints4Chart(pointList);
    }
}
