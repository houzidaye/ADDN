package com.example.addndemo2;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;

public class HttpTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		HttpClient client = HttpClientHelper.getHttpClient();
		String url = "https://dev.addn.org.au/reports/personal?ageInYears=14&heightInCm=109&weightInKg=18&hbA1cNgsp=7.5&binaryGender=MALE&treatment=BD";
		HttpGet httpGet = new HttpGet(url);
		HttpResponse response;
		try {
			response = client.execute(httpGet);
			int returnCode = response.getStatusLine().getStatusCode();
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
