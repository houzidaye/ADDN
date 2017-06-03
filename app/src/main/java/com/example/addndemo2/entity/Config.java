package com.example.addndemo2.entity;

import java.io.Serializable;

public class Config implements Serializable {

	private Long ageInYears;

	private Long heightInCm;

	private Double weightInKg;

	private Double hbA1cNgsp;

	private String binaryGender;

	private String treatment;

	public Long getAgeInYears() {
		return ageInYears;
	}

	public void setAgeInYears(Long ageInYears) {
		this.ageInYears = ageInYears;
	}

	public Long getHeightInCm() {
		return heightInCm;
	}

	public void setHeightInCm(Long heightInCm) {
		this.heightInCm = heightInCm;
	}

	public Double getWeightInKg() {
		return weightInKg;
	}

	public void setWeightInKg(Double weightInKg) {
		this.weightInKg = weightInKg;
	}

	public Double getHbA1cNgsp() {
		return hbA1cNgsp;
	}

	public void setHbA1cNgsp(Double hbA1cNgsp) {
		this.hbA1cNgsp = hbA1cNgsp;
	}

	public String getBinaryGender() {
		return binaryGender;
	}

	public void setBinaryGender(String binaryGender) {
		this.binaryGender = binaryGender;
	}

	public String getTreatment() {
		return treatment;
	}

	public void setTreatment(String treatment) {
		this.treatment = treatment;
	}

	public Config(Builder builder) {
		this.ageInYears = builder.ageInYears;

		this.heightInCm = builder.heightInCm;

		this.weightInKg = builder.weightInKg;

		this.hbA1cNgsp = builder.hbA1cNgsp;

		this.binaryGender = builder.binaryGender;

		this.treatment = builder.treatment;
	}

	public static class Builder {
		private Long ageInYears;

		private Long heightInCm;

		private Double weightInKg;

		private Double hbA1cNgsp;

		private String binaryGender;

		private String treatment;

		public Builder() {
			
		}

		public Config build() {
			return new Config(this);
		}

		public Builder ageInYears(Long ageInYears) {
			this.ageInYears = ageInYears;
			return this;

		}

		public Builder heightInCm(Long heightInCm) {
			this.heightInCm = heightInCm;
			return this;

		}

		public Builder weightInKg(Double weightInKg) {
			this.weightInKg = weightInKg;
			return this;

		}

		public Builder hbA1cNgsp(Double hbA1cNgsp) {
			this.hbA1cNgsp = hbA1cNgsp;
			return this;

		}

		public Builder binaryGender(String binaryGender) {
			this.binaryGender = binaryGender;
			return this;

		}

		public Builder treatment(String treatment) {
			this.treatment = treatment;
			return this;

		}
	}

}
