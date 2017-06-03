package com.example.addndemo2;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.json.JSONException;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.addndemo2.entity.HttpResponseJson;

public class MainActivity extends Activity implements OnClickListener {
	private Button submitButton;
	private TextView textView1;
	private TextView textView2;
	private TextView textView3;
	private TextView textView4;
	private TextView tvAboutUsShow;
	private TextView tvHbac1Show;
	private TextView tvBmiShow;

	private EditText etAgeInYears;
	private EditText etHeightInCm;
	private EditText etWeightInKg;
	private EditText etHbA1cNgsp;

	private Spinner spBinaryGender;
	private Spinner spTreatment;
	private String resultJsonStr = "{    'ageInYears': 14,    'ageRangeLowerBound': 11,    'ageRangeUpperBound': 14,    'bodyMassIndex': 15.2,    'bodyMassIndexDistribution': {        'fiftiethPercentileValue': 19.6,        'max': 31.7,        'mean': 20.2,        'min': 13.8,        'seventyFifthPercentileValue': 21.9,        'standardDeviation': 3.8,        'twentyFifthPercentileValue': 17.4    },    'bodyMassIndexPercentile': 4.1,    'config': {        'ageInYears': 14,        'binaryGender': 'MALE',        'hbA1cNgsp': 7.0,        'heightInCm': 109,        'treatment': 'BD',        'weightInKg': 18.0    },    'description': 'Report successfully generated',    'hbA1cDistribution': {        'fiftiethPercentileValue': 8.2,        'max': 14.0,        'mean': 8.6,        'min': 5.5,        'seventyFifthPercentileValue': 9.3,        'standardDeviation': 1.5,        'twentyFifthPercentileValue': 7.7    },    'hbA1cNgsp': 7.0,    'hbA1cNgspRange': 'RECOMMENDED',    'hbA1cNgspRangePercentageMap': {        'ABOVE_RECOMMENDED': 77.8,        'RECOMMENDED': 22.2    },    'hbA1cPercentile': 8.3,    'result': 'SUCCESS',    'weightRange': 'UNDER_WEIGHT',    'weightRangePercentageMap': {        'HEALTHY_WEIGHT': 64.8,        'UNDER_WEIGHT': 1.9,        'UNHEALTHY_WEIGHT': 33.3    }}";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		// show the alert box to the main avtivity
		new AlertDialog.Builder(this).setTitle("Disclaimer")
				.setMessage(getResources().getString(R.string.disclaimer)).setPositiveButton("Accept", null)
				.setNegativeButton("Reject", new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface arg0, int arg1) {
						System.exit(0);
					}
				}).show();
		// init all the interface control that the main activity need.
		submitButton = (Button) findViewById(R.id.submitButton);
		textView1 = (TextView) findViewById(R.id.text_view_1);
		textView2 = (TextView) findViewById(R.id.text_view_2);
		textView3 = (TextView) findViewById(R.id.text_view_3);
		textView4 = (TextView) findViewById(R.id.text_view_4);
		tvAboutUsShow = (TextView) findViewById(R.id.about_us_show);
		tvHbac1Show = (TextView) findViewById(R.id.hbaic_desc_show);
		tvBmiShow = (TextView) findViewById(R.id.bmi_desc_show);

		etAgeInYears = (EditText) findViewById(R.id.et_ageInYears);
		etHeightInCm = (EditText) findViewById(R.id.et_heightInCm);
		etWeightInKg = (EditText) findViewById(R.id.et_weightInKg);
		etHbA1cNgsp = (EditText) findViewById(R.id.et_hbA1cNgsp);

		spBinaryGender = (Spinner) findViewById(R.id.sp_binaryGender);
		spTreatment = (Spinner) findViewById(R.id.sp_treatment);
		// add the onClick Listener to the interface control.
		submitButton.setOnClickListener(this);
		textView1.setOnClickListener(this);
//		textView2.setOnClickListener(this);
//		textView3.setOnClickListener(this);
//		textView4.setOnClickListener(this);
		tvAboutUsShow.setOnClickListener(this);
		tvHbac1Show.setOnClickListener(this);
		tvBmiShow.setOnClickListener(this);

	}

	@Override
	public void onClick(View view) {
		final Intent intent = new Intent();
		switch (view.getId()) {
		case R.id.submitButton:
			if(TextUtils.isEmpty(etAgeInYears.getText())
					||TextUtils.isEmpty(etHeightInCm.getText())
					||TextUtils.isEmpty(etWeightInKg.getText())
					||TextUtils.isEmpty(etHbA1cNgsp.getText())
					||TextUtils.isEmpty((CharSequence) spBinaryGender.getSelectedItem())
					||TextUtils.isEmpty((CharSequence) spTreatment.getSelectedItem())){
				new AlertDialog.Builder(this).setTitle("Incomplete")
				.setMessage(getResources().getString(R.string.incomplete)).setPositiveButton("Done", null)
				.show();
				break;
			}
			int ageInYears = TextUtils.isEmpty(etAgeInYears.getText()) ? 0 : Integer
					.parseInt(etAgeInYears.getText().toString());
			int heightInCm = TextUtils.isEmpty(etHeightInCm.getText())? 0 : Integer
					.parseInt(etHeightInCm.getText().toString());
			float weightInKg = TextUtils.isEmpty(etWeightInKg.getText()) ? 0 : Float
					.parseFloat(etWeightInKg.getText().toString());
			float hbA1cNgsp = TextUtils.isEmpty(etHbA1cNgsp.getText()) ? 0 : Float
					.parseFloat(etHbA1cNgsp.getText().toString());
			String binaryGender = TextUtils.isEmpty((CharSequence) spBinaryGender.getSelectedItem())? ""
					: spBinaryGender.getSelectedItem().toString();
			String treatment = TextUtils.isEmpty((CharSequence) spTreatment.getSelectedItem()) ? ""
					: spTreatment.getSelectedItem().toString();
			
			StringBuffer sbf = new StringBuffer();
			sbf.append("https://dev.addn.org.au/reports/personal?");
			sbf.append("ageInYears=" + ageInYears);
			sbf.append("&heightInCm=" + heightInCm);
			sbf.append("&weightInKg=" + weightInKg);
			sbf.append("&hbA1cNgsp=" + hbA1cNgsp);
			sbf.append("&binaryGender=" + binaryGender);
			sbf.append("&treatment=" + treatment);
			final String url = sbf.toString();
			
			try {
				new Thread(new Runnable(){
	                @Override
	                public void run() {
	                	try {
//	                		  String url =
//	                				 "https://dev.addn.org.au/reports/personal?ageInYears=14&heightInCm=109&weightInKg=18&hbA1cNgsp=7.5&binaryGender=MALE&treatment=BD";
	                				 String resultJson = "";
							resultJson = httpGet(url);
							if (resultJson != null && !"".equals(resultJson)) {
								try {
									Bundle bundle = new Bundle();
									HttpResponseJson responseJson = new HttpResponseJson();
									responseJson.initByJson(resultJson);
									bundle.putSerializable("responseJson", responseJson);
									intent.setClass(MainActivity.this, ChartDisplayActivity.class);
									intent.putExtras(bundle);
									startActivity(intent);
								} catch (JSONException e) {
									Log.e("submitButton", "init json error");
									e.printStackTrace();
								}
								
							} else {
//								if(isNetworkAvailable(getBaseContext())){
//									new AlertDialog.Builder(this).setTitle("Error")
//									.setMessage("ResponseJson is null.and the netWork is OK. Please Check!!")
//									.setPositiveButton("Yes", null).show();
//								}else{
//									new AlertDialog.Builder(this).setTitle("Error")
//									.setMessage("NetWork is close. Please Check!!")
//									.setPositiveButton("Yes", null).show();
//								}
								
							}
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
	                }
	            }).start();
				
//				resultJson = resultJsonStr;
			} catch (Exception e) {
				Log.e("submitButton", "http get error");
				e.printStackTrace();
			}
			
			break;
		case R.id.text_view_1:

			try {
				new Thread(new Runnable(){
					@Override
					public void run() {
						try {
	                		  String url =
	                				 "https://dev.addn.org.au/reports/personal?ageInYears=14&heightInCm=109&weightInKg=18&hbA1cNgsp=7.5&binaryGender=MALE&treatment=BD";
							String resultJson = "";
							resultJson = httpGet(url);
							if (resultJson != null && !"".equals(resultJson)) {
								try {
									Bundle bundle = new Bundle();
									HttpResponseJson responseJson = new HttpResponseJson();
									responseJson.initByJson(resultJson);
									bundle.putSerializable("responseJson", responseJson);
									intent.setClass(MainActivity.this, TestMyViewActivity.class);
									intent.putExtras(bundle);
									startActivity(intent);
								} catch (JSONException e) {
									Log.e("test my chart", "init json error");
									e.printStackTrace();
								}

							}
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}).start();

//				resultJson = resultJsonStr;
			} catch (Exception e) {
				Log.e("submitButton", "http get error");
				e.printStackTrace();
			}

			break;
		case R.id.text_view_2:
			new AlertDialog.Builder(this).setTitle("Attention")
					.setMessage("text view 2").setPositiveButton("Yes", null)
					.show();
			break;
		case R.id.text_view_3:
			new AlertDialog.Builder(this).setTitle("Attention")
					.setMessage("text view 3").setPositiveButton("Yes", null)
					.show();
			break;
		case R.id.text_view_4:
			new AlertDialog.Builder(this).setTitle("Attention")
					.setMessage("text view 4").setPositiveButton("Yes", null)
					.show();
			break;
		case R.id.about_us_show:
			new AlertDialog.Builder(this).setTitle("About Us")
					.setMessage(getResources().getString(R.string.about_us_desc)).setPositiveButton("Done", null)
					.show();
			break;
		case R.id.hbaic_desc_show:
			new AlertDialog.Builder(this).setTitle("HBA1c")
					.setMessage(getResources().getString(R.string.hba1c_desc)).setPositiveButton("Done", null)
					.show();
			break;
		case R.id.bmi_desc_show:
			new AlertDialog.Builder(this).setTitle("BMI")
					.setMessage(getResources().getString(R.string.bmi_desc)).setPositiveButton("Done", null)
					.show();
			break;
		default:
			break;
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public String httpGet(String url) throws Exception {
		HttpClient client = HttpClientHelper.getHttpClient();
		HttpGet httpGet = new HttpGet(url);
		HttpResponse response = client.execute(httpGet);
		int returnCode = response.getStatusLine().getStatusCode();
		String returnMessage = response.getStatusLine().getReasonPhrase();
		if (returnCode == 200) {
			InputStream is = response.getEntity().getContent();
			String result = new String(StreamTools.readInputStream(is));
			Log.d("resultJson", result);
			return result;
		} else {
			Log.e("resultJson","call http error. returnCode:" + returnCode+"; returnMessage:"+returnMessage);
//			new AlertDialog.Builder(this).setTitle("Error")
//			.setMessage("call http error. returnCode:" + returnCode+"; returnMessage:"+returnMessage)
//			.setPositiveButton("Yes", null).show();
			return null;
		}

	}

	static class StreamTools {
		public static byte[] readInputStream(InputStream inStream)
				throws Exception {
			ByteArrayOutputStream outSteam = new ByteArrayOutputStream();
			byte[] buffer = new byte[1024];
			int len = -1;
			while ((len = inStream.read(buffer)) != -1) {
				outSteam.write(buffer, 0, len);
			}
			outSteam.close();
			inStream.close();
			return outSteam.toByteArray();
		}
	}
	

public static boolean isNetworkAvailable(Context context) { 
	ConnectivityManager connectivity = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE); 
	if (connectivity == null) { 
		Log.i("NetWorkState", "Unavailabel"); 
		return false; 
	} else { 
		NetworkInfo[] info = connectivity.getAllNetworkInfo(); 
	if (info != null) { 
		for (int i = 0; i < info.length; i++) { 
			if (info[i].getState() == NetworkInfo.State.CONNECTED) { 
				Log.i("NetWorkState", "Availabel"); 
				return true; 
			} 
		} 
	} 
} 
return false; 
} 

}
