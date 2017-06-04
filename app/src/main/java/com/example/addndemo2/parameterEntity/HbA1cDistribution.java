package com.example.addndemo2.parameterEntity;

import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;

import com.example.addndemo2.chartEntity.Point;
import com.example.addndemo2.chartEntity.Rectangle;
import com.example.addndemo2.utils.Constants;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * HbA1c Distribution parameter entity.
 * Including the lines and points and rectangles for the HbA1c chart.
 */
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

    /**
     * Constructor for HbA1c distribution.
     *
     * @param min
     * @param max
     * @param mean
     * @param standardDeviation
     * @param twentyFifthPercentileValue
     * @param fiftiethPercentileValue
     * @param seventyFifthPercentileValue
     */
    public HbA1cDistribution(float min, float max, float mean, float standardDeviation, float twentyFifthPercentileValue, float fiftiethPercentileValue, float seventyFifthPercentileValue)
    {
        this.min = min;
        this.max = max;
        this.mean = mean;
        this.standardDeviation = standardDeviation;
        this.twentyFifthPercentileValue = twentyFifthPercentileValue;
        this.fiftiethPercentileValue = fiftiethPercentileValue;
        this.seventyFifthPercentileValue = seventyFifthPercentileValue;
        //Get the multiple for the HbA1c chart.
        this.multiple = Constants.chartAxisY / max;
    }

    /**
     * Get HbA1c parameters by Json.
     *
     * @param json
     * @return
     * @throws JSONException
     */
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

    /**
     * Override the toString function.
     *
     * @return
     */
    public String toString()
    {
        return " min:" + min + " max:" + max + " mean:" + mean + " standardDeviation:" + standardDeviation + " twentyFifthPercentileValue:" + twentyFifthPercentileValue + " fiftiethPercentileValue:" + fiftiethPercentileValue + " seventyFifthPercentileValue:" + seventyFifthPercentileValue;
    }

    /**
     * Creating all the point for the HbA1c chart.
     *
     * @return Point List.
     */
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
        return pointList;
    }

    /**
     * Creating all the Lines for the HbA1c chart.
     *
     * @return line list.
     */
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

    /**
     * Creating all the rectangle for the HbA1c chart.
     *
     * @return rectangle list.
     */
    public List<Rectangle> getRectangle()
    {
        List<Rectangle> rectangles4Chart = new ArrayList<Rectangle>();
        List<Point> points = new ArrayList<Point>();
        float maxAxisY = max;
        //Creating a list of points by the HbA1c parameters.
        points.add(new Point(Constants.beginAxisX, min, null, Color.parseColor("#1EAD64")));
        points.add(new Point(Constants.beginAxisX, max, null, Color.parseColor("#234060")));
        points.add(new Point(Constants.beginAxisX, fiftiethPercentileValue, null, Color.parseColor("#E57D2A")));
        points.add(new Point(Constants.beginAxisX, twentyFifthPercentileValue, null, Color.parseColor("#FDAA22")));
        points.add(new Point(Constants.beginAxisX, seventyFifthPercentileValue, null, Color.parseColor("#BF3829")));
        //Sorting the points that created by the HbA1c parameters with bubble sort algorithm.
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
        //printing the order points to the logCat.
        for (Point point : points)
        {
            Log.d("check sort data", "check sort data:" + String.valueOf(point.getAxisY()));
        }
        //creating the rectangles by the order points.
        for (int i = 0; i < points.size() - 1; i++)
        {
            rectangles4Chart.add(new Rectangle(Constants.beginAxisX, (maxAxisY - points.get(i + 1).getAxisY()) * multiple, Constants.endAxisX, (maxAxisY - points.get(i).getAxisY()) * multiple, points.get(i).getMsgColor(), Paint.Style.FILL));
        }
        return rectangles4Chart;
    }


}
