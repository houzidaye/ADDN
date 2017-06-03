package com.example.addndemo2.entity;

import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;

import com.example.addndemo2.R;
import com.example.addndemo2.chartEntity.Point;
import com.example.addndemo2.chartEntity.Rectangle;
import com.example.addndemo2.utils.Constants;
import com.example.addndemo2.utils.LineChartUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import lecho.lib.hellocharts.model.Line;

public class HbA1cDistribution implements Serializable
{

    private float min;
    private float max;
    private float mean;
    private float standardDeviation;
    private float twentyFifthPercentileValue;
    private float fiftiethPercentileValue;
    private float seventyFifthPercentileValue;
    private float hbA1cPercentileValue = 0;
    public static float multiple = 100;


    public HbA1cDistribution()
    {
    }

    public HbA1cDistribution(float min, float max, float mean, float standardDeviation, float twentyFifthPercentileValue, float fiftiethPercentileValue, float seventyFifthPercentileValue)
    {
        this.min = min;
        this.max = max;
        this.mean = mean;
        this.standardDeviation = standardDeviation;
        this.twentyFifthPercentileValue = twentyFifthPercentileValue;
        this.fiftiethPercentileValue = fiftiethPercentileValue;
        this.seventyFifthPercentileValue = seventyFifthPercentileValue;
        this.multiple = Constants.chartAxisY / max;
//        long maxLong = (long) this.max;
//        int maxLength = String.valueOf(maxLong).length();
//        int maxValue = 1;
//        for (int i = 0; i < maxLength; i++)
//        {
//            maxValue = maxValue * 10;
//        }
//        this.multiple = Constants.chartAxisY * 10 / maxValue;
//        Log.d("get multiple", "max = " + max + " maxLong=" + maxLong + " maxLength=" + maxLength + " maxValue=" + maxValue + " multiple=" + multiple);
    }

    public HbA1cDistribution initByJson(String json) throws JSONException
    {
        JSONObject jObj = new JSONObject(json);
        JSONObject bodyMassJson = jObj.getJSONObject("hbA1cDistribution");
        float min = (float) bodyMassJson.getDouble("min");
        float max = (float) bodyMassJson.getDouble("max");
        float mean = (float) bodyMassJson.getDouble("mean");
        float standardDeviation = (float) bodyMassJson.getDouble("standardDeviation");
        float twentyFifthPercentileValue = (float) bodyMassJson.getDouble("twentyFifthPercentileValue");
        float fiftiethPercentileValue = (float) bodyMassJson.getDouble("fiftiethPercentileValue");
        float seventyFifthPercentileValue = (float) bodyMassJson.getDouble("seventyFifthPercentileValue");
        return new HbA1cDistribution(min, max, mean, standardDeviation, twentyFifthPercentileValue, fiftiethPercentileValue, seventyFifthPercentileValue);
    }


    public float getHbA1cPercentileValue()
    {
        return hbA1cPercentileValue;
    }

    public void setHbA1cPercentileValue(float hbA1cPercentileValue)
    {
        this.hbA1cPercentileValue = hbA1cPercentileValue;
    }

    public float getMin()
    {
        return min;
    }

    public void setMin(float min)
    {
        this.min = min;
    }

    public float getMax()
    {
        return max;
    }

    public void setMax(float max)
    {
        this.max = max;
    }

    public float getMean()
    {
        return mean;
    }

    public void setMean(float mean)
    {
        this.mean = mean;
    }

    public float getStandardDeviation()
    {
        return standardDeviation;
    }

    public void setStandardDeviation(float standardDeviation)
    {
        this.standardDeviation = standardDeviation;
    }

    public float getTwentyFifthPercentileValue()
    {
        return twentyFifthPercentileValue;
    }

    public void setTwentyFifthPercentileValue(float twentyFifthPercentileValue)
    {
        this.twentyFifthPercentileValue = twentyFifthPercentileValue;
    }

    public float getFiftiethPercentileValue()
    {
        return fiftiethPercentileValue;
    }

    public void setFiftiethPercentileValue(float fiftiethPercentileValue)
    {
        this.fiftiethPercentileValue = fiftiethPercentileValue;
    }

    public float getSeventyFifthPercentileValue()
    {
        return seventyFifthPercentileValue;
    }

    public void setSeventyFifthPercentileValue(float seventyFifthPercentileValue)
    {
        this.seventyFifthPercentileValue = seventyFifthPercentileValue;
    }

    public String toString()
    {
        return " min:" + min + " max:" + max + " mean:" + mean + " standardDeviation:" + standardDeviation + " twentyFifthPercentileValue:" + twentyFifthPercentileValue + " fiftiethPercentileValue:" + fiftiethPercentileValue + " seventyFifthPercentileValue:" + seventyFifthPercentileValue;
    }

    public List<com.example.addndemo2.chartEntity.Point> getPoint()
    {
        List<com.example.addndemo2.chartEntity.Point> pointList = new ArrayList<com.example.addndemo2.chartEntity.Point>();
        float maxAxisY = max;
        List<Point> points = new ArrayList<Point>();
        pointList.add(new Point(Constants.midAxisX, (maxAxisY - min) * multiple, "Min" + String.valueOf(min), Color.parseColor("#FFFFFF")));
        pointList.add(new Point(Constants.midAxisX, (maxAxisY - max) * multiple + 25, "Max " + String.valueOf(max), Color.parseColor("#FFFFFF")));
        pointList.add(new Point(Constants.beginAxisX, (maxAxisY - mean) * multiple, "Mean " + String.valueOf(mean), Color.parseColor("#FFFFFF")));
        pointList.add(new Point(Constants.midAxisX, (maxAxisY - fiftiethPercentileValue) * multiple, String.valueOf(fiftiethPercentileValue), Color.parseColor("#FFFFFF")));
        pointList.add(new Point(Constants.midAxisX, (maxAxisY - twentyFifthPercentileValue) * multiple, String.valueOf(twentyFifthPercentileValue), Color.parseColor("#FFFFFF")));
        pointList.add(new Point(Constants.midAxisX, (maxAxisY - seventyFifthPercentileValue) * multiple, String.valueOf(seventyFifthPercentileValue), Color.parseColor("#FFFFFF")));
        pointList.add(new Point(Constants.endAxisX - 300, (maxAxisY - hbA1cPercentileValue) * multiple, "Your HBA1C " + String.valueOf(hbA1cPercentileValue), Color.parseColor("#FFFFFF")));
//        NumberFormat nt = NumberFormat.getPercentInstance();
//        nt.setMinimumFractionDigits(2);
//        for (int i = 1; i <= Constants.numOfAxisYSize; i++)
//        {
//            float axisYValue = (Constants.chartAxisY /Constants.numOfAxisYSize)*i / multiple;
//            float axisY = (max /Constants.numOfAxisYSize)*i ;
//            pointList.add(new Point(Constants.charBeginAxisX,Constants.chartAxisY-axisY , String.valueOf("-"+axisYValue), Color.parseColor("#FFFFFF")));
//            Log.d("axisY","axisY="+axisY+" axisYValue="+axisYValue);
//        }
        return pointList;
    }

    public List<com.example.addndemo2.chartEntity.Line> getLine()
    {
        List<com.example.addndemo2.chartEntity.Line> lineList = new ArrayList<com.example.addndemo2.chartEntity.Line>();
        float maxAxisY = max;
        float meanAxisY = (maxAxisY - mean) * multiple;
        float yourLineAxisY = (maxAxisY - hbA1cPercentileValue) * multiple;
        lineList.add(new com.example.addndemo2.chartEntity.Line(Constants.charBeginAxisX, meanAxisY, (float) (Constants.endAxisX * 1.1), meanAxisY, Color.WHITE));
        lineList.add(new com.example.addndemo2.chartEntity.Line(Constants.charBeginAxisX, yourLineAxisY, (float) (Constants.endAxisX * 1.1), yourLineAxisY, Color.parseColor("#00FFFF")));
        return lineList;
    }

    public List<Rectangle> getHbA1cDistibutionRectangle()
    {
        List<Rectangle> rectangles4Chart = new ArrayList<Rectangle>();
        List<Point> points = new ArrayList<Point>();
        float maxAxisY = max;
        points.add(new Point(Constants.beginAxisX, min, null, Color.parseColor("#1EAD64")));
        points.add(new Point(Constants.beginAxisX, max, null, Color.parseColor("#234060")));
        points.add(new Point(Constants.beginAxisX, fiftiethPercentileValue, null, Color.parseColor("#E57D2A")));
        points.add(new Point(Constants.beginAxisX, twentyFifthPercentileValue, null, Color.parseColor("#FDAA22")));
        points.add(new Point(Constants.beginAxisX, seventyFifthPercentileValue, null, Color.parseColor("#BF3829")));

        for (int i = 0; i < points.size() - 1; i++)
        {
            for (int j = 0; j < points.size() - i - 1; j++)
            {
                if (points.get(j).getAxisY() > points.get(j + 1).getAxisY())
                {
                    Point maxPoint = new Point(points.get(j).getAxisX(), points.get(j).getAxisY(), points.get(j).getMessage(), points.get(j).getMsgColor());
                    points.set(j, points.get(j + 1));
                    points.set(j + 1, maxPoint);
                }
            }
        }
        for (Point point : points)
        {
            Log.d("check sort data", "check sort data:" + String.valueOf(point.getAxisY()));
        }
        for (int i = 0; i < points.size() - 1; i++)
        {
            rectangles4Chart.add(new Rectangle(Constants.beginAxisX, (maxAxisY - points.get(i + 1).getAxisY()) * multiple, Constants.endAxisX, (maxAxisY - points.get(i).getAxisY()) * multiple, points.get(i).getMsgColor(), Paint.Style.FILL));
        }
        return rectangles4Chart;
    }

    public List<Line> getHbA1cDistibutionLines()
    {
        float initAxisXValue = 1;
        float axisXSize = 10;
        float axisXMaxSize = (float) (axisXSize * 1.2);
        float axisXmid = (axisXSize - initAxisXValue) / 2;
        Line zeroLine = LineChartUtil.createLine(0, 0, axisXMaxSize, Color.WHITE, true);
        Line minLine = LineChartUtil.createLine(min, initAxisXValue, axisXSize, Color.parseColor("#234060"), true);
        Line minOnePointLine = LineChartUtil.createOnePointLine(axisXmid, min, Color.parseColor("#1EAD64"), "Min");
        Line maxLine = LineChartUtil.createLine(max, initAxisXValue, axisXSize, Color.parseColor("#BF3829"), true);
        Line maxOnePointLine = LineChartUtil.createOnePointLine(axisXmid, max, R.color.seventyFifthPercent_color, "Max");
        Line yourLine = LineChartUtil.createLine(hbA1cPercentileValue, 0, axisXMaxSize, Color.parseColor("#00FFFF"), false);
        Line yourOnePointLine = LineChartUtil.createOnePointLine(axisXmid, hbA1cPercentileValue, Color.parseColor("#00FFFF"), "Your HBA1C");
        Line meanLine = LineChartUtil.createLine(mean, 0, axisXMaxSize, Color.WHITE, false);
        Line meanOnePointLine = LineChartUtil.createOnePointLine(axisXmid, mean, Color.WHITE, "Mean");
        Line standardDeviationLine = LineChartUtil.createLine(standardDeviation, initAxisXValue, axisXSize, Color.DKGRAY, true);
        Line twentyFifthPercentileValueLine = LineChartUtil.createLine(twentyFifthPercentileValue, initAxisXValue, axisXSize, Color.parseColor("#FDAA22"), true);
        Line twentyFifthPercentileValueOnePointLine = LineChartUtil.createOnePointLine(axisXSize + initAxisXValue, twentyFifthPercentileValue, Color.parseColor("#FDAA22"), "");
        Line fiftiethPercentileValueLine = LineChartUtil.createLine(fiftiethPercentileValue, initAxisXValue, axisXSize, Color.parseColor("#E57D2A"), true);
        Line fiftiethPercentileValueOnePointLine = LineChartUtil.createOnePointLine(axisXSize + initAxisXValue, fiftiethPercentileValue, Color.parseColor("#E57D2A"), "");
        Line seventyFifthPercentileValueLine = LineChartUtil.createLine(seventyFifthPercentileValue, initAxisXValue, axisXSize, Color.parseColor("#BF3829"), true);
        Line seventyFifthPercentileValueOnePointLine = LineChartUtil.createOnePointLine(axisXSize + initAxisXValue, seventyFifthPercentileValue, Color.parseColor("#BF3829"), "");

        List<ChartLine> inputList = new ArrayList<ChartLine>();

        inputList.add(new ChartLine(min, minLine));
        inputList.add(new ChartLine(max, maxLine));
        //		inputList.add(new ChartLine(standardDeviation,standardDeviationLine));
        inputList.add(new ChartLine(twentyFifthPercentileValue, twentyFifthPercentileValueLine));
        inputList.add(new ChartLine(fiftiethPercentileValue, fiftiethPercentileValueLine));
        inputList.add(new ChartLine(seventyFifthPercentileValue, seventyFifthPercentileValueLine));


        for (int i = 0; i < inputList.size() - 1; i++)
        {
            for (int j = 0; j < inputList.size() - i - 1; j++)
            {
                if (inputList.get(j).getPointValue() < inputList.get(j + 1).getPointValue())
                {
                    ChartLine maxChartLine = new ChartLine(inputList.get(j).getPointValue(), inputList.get(j).getLine());
                    inputList.set(j, inputList.get(j + 1));
                    inputList.set(j + 1, maxChartLine);
                }
            }
        }

        List<Line> lines = new ArrayList<Line>();
        lines.add(zeroLine);
        lines.add(yourLine);
        lines.add(meanLine);
        lines.add(minOnePointLine);
        lines.add(maxOnePointLine);
        lines.add(yourOnePointLine);
        lines.add(meanOnePointLine);

        lines.add(twentyFifthPercentileValueOnePointLine);
        lines.add(fiftiethPercentileValueOnePointLine);
        lines.add(seventyFifthPercentileValueOnePointLine);
        for (ChartLine line : inputList)
        {
            lines.add(line.getLine());
            Log.d("HBA1C sort line", String.valueOf(line.getPointValue()));
        }

        return lines;
    }
}
