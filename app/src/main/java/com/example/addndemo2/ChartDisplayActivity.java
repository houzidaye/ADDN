package com.example.addndemo2;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.addndemo2.chartEntity.Line;
import com.example.addndemo2.chartEntity.Point;
import com.example.addndemo2.chartEntity.Rectangle;
import com.example.addndemo2.entity.BodyMassIndexDistribution;
import com.example.addndemo2.entity.HbA1cDistribution;
import com.example.addndemo2.entity.HttpResponseJson;

import java.util.ArrayList;
import java.util.List;

import lecho.lib.hellocharts.gesture.ZoomType;
import lecho.lib.hellocharts.listener.ColumnChartOnValueSelectListener;
import lecho.lib.hellocharts.model.Axis;
import lecho.lib.hellocharts.model.AxisValue;
import lecho.lib.hellocharts.model.Column;
import lecho.lib.hellocharts.model.ColumnChartData;
import lecho.lib.hellocharts.model.LineChartData;
import lecho.lib.hellocharts.model.SubcolumnValue;
import lecho.lib.hellocharts.util.ChartUtils;
import lecho.lib.hellocharts.view.Chart;
import lecho.lib.hellocharts.view.ColumnChartView;

@TargetApi(Build.VERSION_CODES.HONEYCOMB)
@SuppressLint("NewApi")
public class ChartDisplayActivity extends Activity implements OnClickListener {
	private MyChart mChartView;
	private TextView tvHbA1c;
	private TextView tvBodymass;
	private TextView tvHttpUrl;
	private TextView ageAroundShow;
	private TextView hba1cOrBmiTextShow;

	public final static String[] months = new String[] { "Jan", "Feb", "Mar",
			"Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec", };
	ColumnChartView columnChart;
	ColumnChartData columnData;
	List<Column> lsColumn = new ArrayList<Column>();
	List<SubcolumnValue> lsValue;

	@SuppressWarnings("deprecation")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_my_chart);
		tvHbA1c = (TextView) findViewById(R.id.chart_hbA1c);
		tvBodymass = (TextView) findViewById(R.id.chart_bodymass);
		tvHttpUrl = (TextView) findViewById(R.id.http_url);
		ageAroundShow = (TextView) findViewById(R.id.age_around_show);
		hba1cOrBmiTextShow = (TextView) findViewById(R.id.hba1c_Or_Bmi_text_Show);

		tvHbA1c.setOnClickListener(this);
		tvBodymass.setOnClickListener(this);
		tvHttpUrl.setOnClickListener(this);

		tvHbA1c.setBackgroundDrawable(getResources().getDrawable(R.drawable.textview_border_select));
		tvHbA1c.setTextColor(Color.parseColor("#234060"));
		tvBodymass.setBackgroundDrawable(getResources().getDrawable(R.drawable.textview_border_unselect));
		tvBodymass.setTextColor(Color.WHITE);
		hba1cOrBmiTextShow.setText("Your HBA1C");

		// ColumnChart
		// columnChart = (ColumnChartView) findViewById(R.id.column_chart);
		// dataInit();
		Intent intent = this.getIntent();
		TextView tv = (TextView) findViewById(R.id.chartDsipaly_show_intent);
		HttpResponseJson responseJson = (HttpResponseJson) intent
				.getSerializableExtra("responseJson");
		// tv.setText(responseJson.toString());
		HbA1cDistribution hba1cd = responseJson.getHbA1cDistribution();
		String ageAroundShowStr = "Results are sampled from age "
				+ responseJson.getAgeRangeLowerBound() + " to "
				+ responseJson.getAgeRangeUpperBound();
		ageAroundShow.setText(ageAroundShowStr);
//		hba1cd.setHbA1cPercentileValue(responseJson.getHbA1cPercentile());
//		List<Line> lines = hba1cd.getHbA1cDistibutionLines();
//		setLines(lines);
		mChartView = (MyChart) findViewById(R.id.my_chart);
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

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	private void dataInit() {

		int numSubcolumns = 1;
		int numColumns = months.length;

		List<AxisValue> axisValues = new ArrayList<AxisValue>();
		List<Column> columns = new ArrayList<Column>();
		List<SubcolumnValue> values;
		for (int i = 0; i < numColumns; ++i) {

			values = new ArrayList<SubcolumnValue>();
			for (int j = 0; j < numSubcolumns; ++j) {
				values.add(new SubcolumnValue((float) Math.random() * 50f + 5,
						ChartUtils.pickColor()));
			}
			// show data when touch the column chart.
			axisValues.add(new AxisValue(i).setLabel(months[i]));

			columns.add(new Column(values).setHasLabelsOnlyForSelected(true));
		}

		columnData = new ColumnChartData(columns);

		columnData.setAxisXBottom(new Axis(axisValues).setHasLines(true)
				.setTextColor(Color.BLACK));
		columnData.setAxisYLeft(new Axis().setHasLines(true)
				.setTextColor(Color.BLACK).setMaxLabelChars(2));

		columnData.setStacked(true);

		columnChart.setColumnChartData(columnData);

		// Set value touch listener that will trigger changes for chartTop.
		columnChart.setOnValueTouchListener(new ValueTouchListener());

		// Set selection mode to keep selected month column highlighted.
		columnChart.setValueSelectionEnabled(true);

		columnChart.setZoomType(ZoomType.HORIZONTAL);

	}

	/**
	 * 
	 * @author 1017
	 * 
	 */
	private class ValueTouchListener implements
			ColumnChartOnValueSelectListener {

		@Override
		public void onValueSelected(int columnIndex, int subcolumnIndex,
				SubcolumnValue value) {
			// generateLineData(value.getColor(), 100);
		}

		@Override
		public void onValueDeselected() {

			// generateLineData(ChartUtils.COLOR_GREEN, 0);

		}
	}

	/**
	 * A fragment containing a column chart.
	 */
	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	public static class PlaceholderFragment extends Fragment {

		private static final int DEFAULT_DATA = 0;
		private static final int SUBCOLUMNS_DATA = 1;
		private static final int STACKED_DATA = 2;
		private static final int NEGATIVE_SUBCOLUMNS_DATA = 3;
		private static final int NEGATIVE_STACKED_DATA = 4;

		private ColumnChartView chart;
		private ColumnChartData data;
		private boolean hasAxes = true;
		private boolean hasAxesNames = true;
		private boolean hasLabels = false;
		private boolean hasLabelForSelected = false;
		private int dataType = DEFAULT_DATA;

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			setHasOptionsMenu(true);
			View rootView = inflater.inflate(R.layout.fragment_column_chart,
					container, false);

			chart = (ColumnChartView) rootView.findViewById(R.id.chart);
			chart.setOnValueTouchListener(new ValueTouchListener());

			generateData();

			return rootView;
		}

		// MENU
		@Override
		public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
			inflater.inflate(R.menu.column_chart, menu);
		}

		@Override
		public boolean onOptionsItemSelected(MenuItem item) {
			int id = item.getItemId();
			if (id == R.id.action_reset) {
				reset();
				generateData();
				return true;
			}
			if (id == R.id.action_subcolumns) {
				dataType = SUBCOLUMNS_DATA;
				generateData();
				return true;
			}
			if (id == R.id.action_stacked) {
				dataType = STACKED_DATA;
				generateData();
				return true;
			}
			if (id == R.id.action_negative_subcolumns) {
				dataType = NEGATIVE_SUBCOLUMNS_DATA;
				generateData();
				return true;
			}
			if (id == R.id.action_negative_stacked) {
				dataType = NEGATIVE_STACKED_DATA;
				generateData();
				return true;
			}
			if (id == R.id.action_toggle_labels) {
				toggleLabels();
				return true;
			}
			if (id == R.id.action_toggle_axes) {
				toggleAxes();
				return true;
			}
			if (id == R.id.action_toggle_axes_names) {
				toggleAxesNames();
				return true;
			}
			if (id == R.id.action_animate) {
				prepareDataAnimation();
				chart.startDataAnimation();
				return true;
			}
			if (id == R.id.action_toggle_selection_mode) {
				toggleLabelForSelected();

				Toast.makeText(
						getActivity(),
						"Selection mode set to "
								+ chart.isValueSelectionEnabled()
								+ " select any point.", Toast.LENGTH_SHORT)
						.show();
				return true;
			}
			if (id == R.id.action_toggle_touch_zoom) {
				chart.setZoomEnabled(!chart.isZoomEnabled());
				Toast.makeText(getActivity(),
						"IsZoomEnabled " + chart.isZoomEnabled(),
						Toast.LENGTH_SHORT).show();
				return true;
			}
			if (id == R.id.action_zoom_both) {
				chart.setZoomType(ZoomType.HORIZONTAL_AND_VERTICAL);
				return true;
			}
			if (id == R.id.action_zoom_horizontal) {
				chart.setZoomType(ZoomType.HORIZONTAL);
				return true;
			}
			if (id == R.id.action_zoom_vertical) {
				chart.setZoomType(ZoomType.VERTICAL);
				return true;
			}
			return super.onOptionsItemSelected(item);
		}

		private void reset() {
			hasAxes = true;
			hasAxesNames = true;
			hasLabels = false;
			hasLabelForSelected = false;
			dataType = DEFAULT_DATA;
			chart.setValueSelectionEnabled(hasLabelForSelected);

		}

		private void generateDefaultData() {
			int numSubcolumns = 1;
			int numColumns = 8;
			// Column can have many subcolumns, here by default I use 1
			// subcolumn in each of 8 columns.
			List<Column> columns = new ArrayList<Column>();
			List<SubcolumnValue> values;
			for (int i = 0; i < numColumns; ++i) {

				values = new ArrayList<SubcolumnValue>();
				for (int j = 0; j < numSubcolumns; ++j) {
					values.add(new SubcolumnValue(
							(float) Math.random() * 50f + 5, ChartUtils
									.pickColor()));
				}

				Column column = new Column(values);
				column.setHasLabels(hasLabels);
				column.setHasLabelsOnlyForSelected(hasLabelForSelected);
				columns.add(column);
			}

			data = new ColumnChartData(columns);

			if (hasAxes) {
				Axis axisX = new Axis();
				Axis axisY = new Axis().setHasLines(true);
				if (hasAxesNames) {
					axisX.setName("Axis X");
					axisY.setName("Axis Y");
				}
				data.setAxisXBottom(axisX);
				data.setAxisYLeft(axisY);
			} else {
				data.setAxisXBottom(null);
				data.setAxisYLeft(null);
			}

			chart.setColumnChartData(data);

		}

		/**
		 * Generates columns with subcolumns, columns have larger separation
		 * than subcolumns.
		 */
		private void generateSubcolumnsData() {
			int numSubcolumns = 4;
			int numColumns = 4;
			// Column can have many subcolumns, here I use 4 subcolumn in each
			// of 8 columns.
			List<Column> columns = new ArrayList<Column>();
			List<SubcolumnValue> values;
			for (int i = 0; i < numColumns; ++i) {

				values = new ArrayList<SubcolumnValue>();
				for (int j = 0; j < numSubcolumns; ++j) {
					values.add(new SubcolumnValue(
							(float) Math.random() * 50f + 5, ChartUtils
									.pickColor()));
				}

				Column column = new Column(values);
				column.setHasLabels(hasLabels);
				column.setHasLabelsOnlyForSelected(hasLabelForSelected);
				columns.add(column);
			}

			data = new ColumnChartData(columns);

			if (hasAxes) {
				Axis axisX = new Axis();
				Axis axisY = new Axis().setHasLines(true);
				if (hasAxesNames) {
					axisX.setName("Axis X");
					axisY.setName("Axis Y");
				}
				data.setAxisXBottom(axisX);
				data.setAxisYLeft(axisY);
			} else {
				data.setAxisXBottom(null);
				data.setAxisYLeft(null);
			}

			chart.setColumnChartData(data);

		}

		/**
		 * Generates columns with stacked subcolumns.
		 */
		private void generateStackedData() {
			int numSubcolumns = 4;
			int numColumns = 8;
			// Column can have many stacked subcolumns, here I use 4 stacke
			// subcolumn in each of 4 columns.
			List<Column> columns = new ArrayList<Column>();
			List<SubcolumnValue> values;
			for (int i = 0; i < numColumns; ++i) {

				values = new ArrayList<SubcolumnValue>();
				for (int j = 0; j < numSubcolumns; ++j) {
					values.add(new SubcolumnValue(
							(float) Math.random() * 20f + 5, ChartUtils
									.pickColor()));
				}

				Column column = new Column(values);
				column.setHasLabels(hasLabels);
				column.setHasLabelsOnlyForSelected(hasLabelForSelected);
				columns.add(column);
			}

			data = new ColumnChartData(columns);

			// Set stacked flag.
			data.setStacked(true);

			if (hasAxes) {
				Axis axisX = new Axis();
				Axis axisY = new Axis().setHasLines(true);
				if (hasAxesNames) {
					axisX.setName("Axis X");
					axisY.setName("Axis Y");
				}
				data.setAxisXBottom(axisX);
				data.setAxisYLeft(axisY);
			} else {
				data.setAxisXBottom(null);
				data.setAxisYLeft(null);
			}

			chart.setColumnChartData(data);
		}

		private void generateNegativeSubcolumnsData() {

			int numSubcolumns = 4;
			int numColumns = 4;
			List<Column> columns = new ArrayList<Column>();
			List<SubcolumnValue> values;
			for (int i = 0; i < numColumns; ++i) {

				values = new ArrayList<SubcolumnValue>();
				for (int j = 0; j < numSubcolumns; ++j) {
					int sign = getSign();
					values.add(new SubcolumnValue((float) Math.random() * 50f
							* sign + 5 * sign, ChartUtils.pickColor()));
				}

				Column column = new Column(values);
				column.setHasLabels(hasLabels);
				column.setHasLabelsOnlyForSelected(hasLabelForSelected);
				columns.add(column);
			}

			data = new ColumnChartData(columns);

			if (hasAxes) {
				Axis axisX = new Axis();
				Axis axisY = new Axis().setHasLines(true);
				if (hasAxesNames) {
					axisX.setName("Axis X");
					axisY.setName("Axis Y");
				}
				data.setAxisXBottom(axisX);
				data.setAxisYLeft(axisY);
			} else {
				data.setAxisXBottom(null);
				data.setAxisYLeft(null);
			}

			chart.setColumnChartData(data);
		}

		private void generateNegativeStackedData() {

			int numSubcolumns = 4;
			int numColumns = 8;
			// Column can have many stacked subcolumns, here I use 4 stacke
			// subcolumn in each of 4 columns.
			List<Column> columns = new ArrayList<Column>();
			List<SubcolumnValue> values;
			for (int i = 0; i < numColumns; ++i) {

				values = new ArrayList<SubcolumnValue>();
				for (int j = 0; j < numSubcolumns; ++j) {
					int sign = getSign();
					values.add(new SubcolumnValue((float) Math.random() * 20f
							* sign + 5 * sign, ChartUtils.pickColor()));
				}

				Column column = new Column(values);
				column.setHasLabels(hasLabels);
				column.setHasLabelsOnlyForSelected(hasLabelForSelected);
				columns.add(column);
			}

			data = new ColumnChartData(columns);

			// Set stacked flag.
			data.setStacked(true);

			if (hasAxes) {
				Axis axisX = new Axis();
				Axis axisY = new Axis().setHasLines(true);
				if (hasAxesNames) {
					axisX.setName("Axis X");
					axisY.setName("Axis Y");
				}
				data.setAxisXBottom(axisX);
				data.setAxisYLeft(axisY);
			} else {
				data.setAxisXBottom(null);
				data.setAxisYLeft(null);
			}

			chart.setColumnChartData(data);
		}

		private int getSign() {
			int[] sign = new int[] { -1, 1 };
			return sign[Math.round((float) Math.random())];
		}

		private void generateData() {
			switch (dataType) {
			case DEFAULT_DATA:
				generateDefaultData();
				break;
			case SUBCOLUMNS_DATA:
				generateSubcolumnsData();
				break;
			case STACKED_DATA:
				generateStackedData();
				break;
			case NEGATIVE_SUBCOLUMNS_DATA:
				generateNegativeSubcolumnsData();
				break;
			case NEGATIVE_STACKED_DATA:
				generateNegativeStackedData();
				break;
			default:
				generateDefaultData();
				break;
			}
		}

		private void toggleLabels() {
			hasLabels = !hasLabels;

			if (hasLabels) {
				hasLabelForSelected = false;
				chart.setValueSelectionEnabled(hasLabelForSelected);
			}

			generateData();
		}

		private void toggleLabelForSelected() {
			hasLabelForSelected = !hasLabelForSelected;
			chart.setValueSelectionEnabled(hasLabelForSelected);

			if (hasLabelForSelected) {
				hasLabels = false;
			}

			generateData();
		}

		private void toggleAxes() {
			hasAxes = !hasAxes;

			generateData();
		}

		private void toggleAxesNames() {
			hasAxesNames = !hasAxesNames;

			generateData();
		}

		/**
		 * To animate values you have to change targets values and then call
		 * {@link Chart#startDataAnimation()} method(don't confuse with
		 * View.animate()).
		 */
		private void prepareDataAnimation() {
			for (Column column : data.getColumns()) {
				for (SubcolumnValue value : column.getValues()) {
					value.setTarget((float) Math.random() * 100);
				}
			}
		}

		private class ValueTouchListener implements
				ColumnChartOnValueSelectListener {

			@Override
			public void onValueSelected(int columnIndex, int subcolumnIndex,
					SubcolumnValue value) {
				Toast.makeText(getActivity(), "Selected: " + value,
						Toast.LENGTH_SHORT).show();
			}

			@Override
			public void onValueDeselected() {
				// TODO Auto-generated method stub

			}

		}

	}

	@Override
	public void onClick(View view) {
		Intent intent = this.getIntent();
		String ageAroundShowStr = "";
		TextView tv = (TextView) findViewById(R.id.chartDsipaly_show_intent);
		HttpResponseJson responseJson = (HttpResponseJson) intent
				.getSerializableExtra("responseJson");
		List<Line> lines = new ArrayList<Line>();
		LineChartData data = new LineChartData();
		Axis axisX = new Axis();// x axis
		Axis axisY = new Axis();// y axis
		List<Rectangle> rectangles4Chart =  new ArrayList<Rectangle>();
		List<Line> lineList = new ArrayList<Line>();
		List<Point> pointList = new ArrayList<Point>();
		switch (view.getId()) {
		case R.id.chart_hbA1c:
			tvHbA1c.setBackgroundDrawable(getResources().getDrawable(R.drawable.textview_border_select));
			tvHbA1c.setTextColor(Color.parseColor("#234060"));
			tvBodymass.setBackgroundDrawable(getResources().getDrawable(R.drawable.textview_border_unselect));
			tvBodymass.setTextColor(Color.WHITE);
			hba1cOrBmiTextShow.setText("Your HBA1C");
			HbA1cDistribution hba1cd = responseJson.getHbA1cDistribution();
			ageAroundShowStr = "Results are sampled from age "
					+ responseJson.getAgeRangeLowerBound() + " to "
					+ responseJson.getAgeRangeUpperBound();
			ageAroundShow.setText(ageAroundShowStr);
//			hba1cd.setHbA1cPercentileValue(responseJson.getHbA1cPercentile());
			rectangles4Chart = hba1cd.getHbA1cDistibutionRectangle();
			 lineList = hba1cd.getLine();
			 pointList = hba1cd.getPoint();
			for (Rectangle rect:rectangles4Chart
					) {
				Log.d("print rectangle ",rect.toString());
			}
			mChartView.invalidate();
			mChartView.setRectangles4Chart(rectangles4Chart);
			mChartView.setLines4Chart(lineList);
			mChartView.setPoints4Chart(pointList);
			break;
		case R.id.chart_bodymass:
			tvBodymass.setBackgroundDrawable(getResources().getDrawable(R.drawable.textview_border_select));
			tvBodymass.setTextColor(Color.parseColor("#234060"));
			tvHbA1c.setBackgroundDrawable(getResources().getDrawable(R.drawable.textview_border_unselect));
			tvHbA1c.setTextColor(Color.WHITE);
			hba1cOrBmiTextShow.setText("Your BMI");
			BodyMassIndexDistribution bmiDist = responseJson
					.getBodyMassIndexDistribution();
			ageAroundShowStr = "Results are sampled from age "
					+ responseJson.getAgeRangeLowerBound() + " to "
					+ responseJson.getAgeRangeUpperBound();
			ageAroundShow.setText(ageAroundShowStr);
//			bmiDist.setBmiPercentileValue(responseJson.getBodyMassIndexPercentile());
			rectangles4Chart = bmiDist.getBodyMassIndexDistributionRectangle();
			lineList = bmiDist.getLine();
			pointList = bmiDist.getPoint();
			for (Rectangle rect:rectangles4Chart
					) {
				Log.d("print rectangle ",rect.toString());
			}
			mChartView.invalidate();
			mChartView.setRectangles4Chart(rectangles4Chart);
			mChartView.setLines4Chart(lineList);
			mChartView.setPoints4Chart(pointList);
			break;
		case R.id.http_url:
			// Intent intent2Html = new Intent();
			// intent2Html.setClass(ChartDisplayActivity.this,
			// HtmlShowActivity.class);
			// startActivity(intent2Html);
			Uri uri = Uri.parse(getResources().getString(R.string.html_url));
			Intent it = new Intent(Intent.ACTION_VIEW, uri);
			startActivity(it);
			break;
		default:
			break;
		}
	}

	public void setLines(List<Line> lines) {
//		LineChartData data = new LineChartData();
//		Axis axisX = new Axis();// x axis
//		Axis axisY = new Axis();// y axis
//		mChartView = (LineChartView) findViewById(R.id.line_chart);
//		mChartView.setInteractive(true);// set to be dragged
//		mChartView.setZoomType(ZoomType.VERTICAL);// set the way to drag
//		axisX.setHasLines(false);
//		axisX.setTextColor(Color.parseColor("#234060"));
//		axisY.setTextColor(Color.WHITE);
//		// axisX.setMaxLabelChars(1);
//		data.setAxisXBottom(axisX);
//		data.setAxisYLeft(axisY);
//		data.setLines(lines);
//		mChartView.setLineChartData(data);// set data to the lineChart
	}

}
