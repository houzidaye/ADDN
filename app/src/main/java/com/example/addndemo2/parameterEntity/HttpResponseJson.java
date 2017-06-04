package com.example.addndemo2.parameterEntity;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

public class HttpResponseJson implements Serializable {
	private String result;
	private String description;
	private float ageInYears;
	private float ageRangeLowerBound;
	private float ageRangeUpperBound;
	private float bodyMassIndex;
	private float bodyMassIndexPercentile;
	private float hbA1cNgsp;
	private float hbA1cPercentile;
	private BodyMassIndexDistribution bodyMassIndexDistribution;
	private HbA1cDistribution hbA1cDistribution;

	public float getAgeRangeUpperBound() {
		return ageRangeUpperBound;
	}

	public void setAgeRangeUpperBound(float ageRangeUpperBound) {
		this.ageRangeUpperBound = ageRangeUpperBound;
	}

    public float getAgeRangeLowerBound() {
        return ageRangeLowerBound;
    }

    public void setAgeRangeLowerBound(float ageRangeLowerBound) {
        this.ageRangeLowerBound = ageRangeLowerBound;
    }

	public HttpResponseJson() {
	}

	public void initByJson(String json) throws JSONException {
		JSONObject jObj = new JSONObject(json);
		this.result = jObj.getString("result");
		this.description = jObj.getString("description");
		this.ageInYears = jObj.getLong("ageInYears");
		this.ageRangeLowerBound = jObj.getLong("ageRangeLowerBound");
		this.ageRangeUpperBound = jObj.getLong("ageRangeUpperBound");
		this.bodyMassIndex = jObj.getLong("bodyMassIndex");
		this.bodyMassIndexPercentile = jObj.getLong("bodyMassIndexPercentile");
		this.hbA1cNgsp = jObj.getLong("hbA1cNgsp");
		this.hbA1cPercentile = jObj.getLong("hbA1cPercentile");
		this.bodyMassIndexDistribution = new BodyMassIndexDistribution()
				.initByJson(json);
        this.bodyMassIndexDistribution.setBmiPercentileValue(this.bodyMassIndex);
		this.hbA1cDistribution = new HbA1cDistribution().initByJson(json);
        this.hbA1cDistribution.setHbA1cPercentileValue(this.hbA1cNgsp);
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public BodyMassIndexDistribution getBodyMassIndexDistribution() {
		return bodyMassIndexDistribution;
	}

	public void setBodyMassIndexDistribution(
			BodyMassIndexDistribution bodyMassIndexDistribution) {
		this.bodyMassIndexDistribution = bodyMassIndexDistribution;
	}

	public HbA1cDistribution getHbA1cDistribution() {
		return hbA1cDistribution;
	}

	public void setHbA1cDistribution(HbA1cDistribution hbA1cDistribution) {
		this.hbA1cDistribution = hbA1cDistribution;
	}

	public String toString() {
		return " result:" + result + " description:" + description
				+ " ageInYears:" + ageInYears + " ageRangeLowerBound:"
				+ ageRangeLowerBound + " ageRangeUpperBound:"
				+ ageRangeUpperBound + " bodyMassIndex:" + bodyMassIndex
				+ " bodyMassIndexPercentile:" + bodyMassIndexPercentile
				+ " hbA1cNgsp:" + hbA1cNgsp + " hbA1cPercentile:"
				+ hbA1cPercentile;
	}
}
