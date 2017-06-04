package com.example.addndemo2;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

import com.example.addndemo2.chartEntity.Line;
import com.example.addndemo2.chartEntity.Point;
import com.example.addndemo2.chartEntity.Rectangle;
import com.example.addndemo2.parameterEntity.BodyMassIndexDistribution;
import com.example.addndemo2.parameterEntity.HbA1cDistribution;
import com.example.addndemo2.parameterEntity.HttpResponseJson;

import java.util.ArrayList;
import java.util.List;

/**
 * Activity for the chart show.
 */
@TargetApi(Build.VERSION_CODES.HONEYCOMB)
@SuppressLint("NewApi")
public class ChartDisplayActivity extends Activity implements OnClickListener
{
    private MyChart mChartView;
    private TextView tvHbA1c;
    private TextView tvBodymass;
    private TextView tvHttpUrl;
    private TextView ageAroundShow;
    private TextView hba1cOrBmiTextShow;

    @SuppressWarnings("deprecation")
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        //init the activity
        setContentView(R.layout.activity_my_chart);
        tvHbA1c = (TextView) findViewById(R.id.chart_hbA1c);
        tvBodymass = (TextView) findViewById(R.id.chart_bodymass);
        tvHttpUrl = (TextView) findViewById(R.id.http_url);
        ageAroundShow = (TextView) findViewById(R.id.age_around_show);
        hba1cOrBmiTextShow = (TextView) findViewById(R.id.hba1c_Or_Bmi_text_Show);
        //set onClick action
        tvHbA1c.setOnClickListener(this);
        tvBodymass.setOnClickListener(this);
        tvHttpUrl.setOnClickListener(this);

        tvHbA1c.setBackgroundDrawable(getResources().getDrawable(R.drawable.textview_border_select));
        tvHbA1c.setTextColor(Color.parseColor("#234060"));
        tvBodymass.setBackgroundDrawable(getResources().getDrawable(R.drawable.textview_border_unselect));
        tvBodymass.setTextColor(Color.WHITE);
        hba1cOrBmiTextShow.setText("Your HBA1C");
        //get the response result from MainActivity
        Intent intent = this.getIntent();
        TextView tv = (TextView) findViewById(R.id.chartDsipaly_show_intent);
        HttpResponseJson responseJson = (HttpResponseJson) intent.getSerializableExtra("responseJson");
        // tv.setText(responseJson.toString());
        //get HbA1c object from response result
        HbA1cDistribution hba1cd = responseJson.getHbA1cDistribution();
        //set age around show String
        String ageAroundShowStr = "Results are sampled from age " + responseJson.getAgeRangeLowerBound() + " to " + responseJson.getAgeRangeUpperBound();
        ageAroundShow.setText(ageAroundShowStr);
        //creating a MyChart view
        mChartView = (MyChart) findViewById(R.id.my_chart);
        //get the show object for the chart from HbA1c object
        List<Rectangle> rectangles4Chart = hba1cd.getRectangle();
        List<Line> lineList = hba1cd.getLine();
        List<Point> pointList = hba1cd.getPoint();
        for (Rectangle rect : rectangles4Chart)
        {
            Log.d("print rectangle ", rect.toString());
        }
        //put the show objects to chart to show
        mChartView.setRectangles4Chart(rectangles4Chart);
        mChartView.setLines4Chart(lineList);
        mChartView.setPoints4Chart(pointList);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    /**
     * Chart translating between HbA1c and BMI.
     * @param view
     */
    @Override
    public void onClick(View view)
    {
        //get response result from MainActivity.
        Intent intent = this.getIntent();
        String ageAroundShowStr = "";
        TextView tv = (TextView) findViewById(R.id.chartDsipaly_show_intent);
        HttpResponseJson responseJson = (HttpResponseJson) intent.getSerializableExtra("responseJson");
        List<Line> lines = new ArrayList<Line>();

        List<Rectangle> rectangles4Chart = new ArrayList<Rectangle>();
        List<Line> lineList = new ArrayList<Line>();
        List<Point> pointList = new ArrayList<Point>();
        switch (view.getId())
        {
            //change to HbA1c chart.
            case R.id.chart_hbA1c:
                //set HbA1c chart style
                tvHbA1c.setBackgroundDrawable(getResources().getDrawable(R.drawable.textview_border_select));
                tvHbA1c.setTextColor(Color.parseColor("#234060"));
                tvBodymass.setBackgroundDrawable(getResources().getDrawable(R.drawable.textview_border_unselect));
                //get show objects to chart view.
                tvBodymass.setTextColor(Color.WHITE);
                hba1cOrBmiTextShow.setText("Your HBA1C");
                HbA1cDistribution hba1cd = responseJson.getHbA1cDistribution();
                ageAroundShowStr = "Results are sampled from age " + responseJson.getAgeRangeLowerBound() + " to " + responseJson.getAgeRangeUpperBound();
                ageAroundShow.setText(ageAroundShowStr);
                //			hba1cd.setHbA1cPercentileValue(responseJson.getHbA1cPercentile());
                rectangles4Chart = hba1cd.getRectangle();
                lineList = hba1cd.getLine();
                pointList = hba1cd.getPoint();
                for (Rectangle rect : rectangles4Chart)
                {
                    Log.d("print rectangle ", rect.toString());
                }
                //set show objects to chart view
                mChartView.invalidate();
                mChartView.setRectangles4Chart(rectangles4Chart);
                mChartView.setLines4Chart(lineList);
                mChartView.setPoints4Chart(pointList);
                break;
            //change to BMI chart.
            case R.id.chart_bodymass:
                //set BMI chart style
                tvBodymass.setBackgroundDrawable(getResources().getDrawable(R.drawable.textview_border_select));
                tvBodymass.setTextColor(Color.parseColor("#234060"));
                tvHbA1c.setBackgroundDrawable(getResources().getDrawable(R.drawable.textview_border_unselect));
                tvHbA1c.setTextColor(Color.WHITE);
                hba1cOrBmiTextShow.setText("Your BMI");
                BodyMassIndexDistribution bmiDist = responseJson.getBodyMassIndexDistribution();
                ageAroundShowStr = "Results are sampled from age " + responseJson.getAgeRangeLowerBound() + " to " + responseJson.getAgeRangeUpperBound();
                ageAroundShow.setText(ageAroundShowStr);
                //			bmiDist.setBmiPercentileValue(responseJson.getBodyMassIndexPercentile());
                //get show objects to chart show.
                rectangles4Chart = bmiDist.getRectangle();
                lineList = bmiDist.getLine();
                pointList = bmiDist.getPoint();
                for (Rectangle rect : rectangles4Chart)
                {
                    Log.d("print rectangle ", rect.toString());
                }
                //set show objects to chart view
                mChartView.invalidate();
                mChartView.setRectangles4Chart(rectangles4Chart);
                mChartView.setLines4Chart(lineList);
                mChartView.setPoints4Chart(pointList);
                break;
            //change to outside web page.
            case R.id.http_url:
                Uri uri = Uri.parse(getResources().getString(R.string.html_url));
                Intent it = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(it);
                break;
            default:
                break;
        }
    }

}
