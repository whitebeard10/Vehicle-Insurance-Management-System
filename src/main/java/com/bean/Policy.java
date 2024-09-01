package com.bean;

import java.time.LocalDate;
import java.util.Objects;

public class Policy {
	private int policyNo;
	private String vehicleNo;
	private String vehicleType;
	private String customerName;
	private int engineNo;
	private int chasisNo;
	private String phoneNo;
	private String insuranceType;
	private double premiumAmt;
	private LocalDate fromDate;
	private LocalDate toDate;
	private int underwriterId;

	// Default constructor
	public Policy() {
	}

	// Parameterized constructor for creating policy
	public Policy(String vehicleNo, String vehicleType, String customerName, int engineNo, int chasisNo,
			String phoneNo, String insuranceType, LocalDate fromDate, int underwriterId) {
		this.vehicleNo = vehicleNo;
		this.vehicleType = vehicleType;
		this.customerName = customerName;
		this.engineNo = engineNo;
		this.chasisNo = chasisNo;
		this.phoneNo = phoneNo;
		this.insuranceType = insuranceType;
		this.fromDate = fromDate;
		this.underwriterId = underwriterId;
	}
	

	public Policy(int policyNo,String vehicleNo, String vehicleType, String customerName, int engineNo, int chasisNo, String phoneNo,
			String insuranceType, double premiumAmt, LocalDate fromDate, LocalDate toDate, int underwriterId) {
		this.policyNo=policyNo;
		this.vehicleNo = vehicleNo;
		this.vehicleType = vehicleType;
		this.customerName = customerName;
		this.engineNo = engineNo;
		this.chasisNo = chasisNo;
		this.phoneNo = phoneNo;
		this.insuranceType = insuranceType;
		this.premiumAmt = premiumAmt;
		this.fromDate = fromDate;
		this.toDate = toDate;
		this.underwriterId = underwriterId;
	}

	// Getters and setters
	public int getPolicyNo() {
		return policyNo;
	}

	public void setPolicyNo(int policyNo) {
		this.policyNo = policyNo;
	}

	public String getVehicleNo() {
		return vehicleNo;
	}

	public void setVehicleNo(String vehicleNo) {
		this.vehicleNo = vehicleNo;
	}

	public String getVehicleType() {
		return vehicleType;
	}

	public void setVehicleType(String vehicleType) {
		this.vehicleType = vehicleType;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public int getEngineNo() {
		return engineNo;
	}

	public void setEngineNo(int engineNo) {
		this.engineNo = engineNo;
	}

	public int getChasisNo() {
		return chasisNo;
	}

	public void setChasisNo(int chasisNo) {
		this.chasisNo = chasisNo;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public String getInsuranceType() {
		return insuranceType;
	}

	public void setInsuranceType(String insuranceType) {
		this.insuranceType = insuranceType;
	}

	public double getPremiumAmt() {
		return premiumAmt;
	}

	public void setPremiumAmt(double premiumAmt) {
		this.premiumAmt = premiumAmt;
	}

	public LocalDate getFromDate() {
		return fromDate;
	}

	public void setFromDate(LocalDate fromDate) {
		this.fromDate = fromDate;
	}

	public LocalDate getToDate() {
		return toDate;
	}

	public void setToDate(LocalDate toDate) {
		this.toDate = toDate;
	}

	public int getUnderwriterId() {
		return underwriterId;
	}

	public void setUnderwriterId(int underwriterId) {
		this.underwriterId = underwriterId;
	}

	// toString() method
	@Override
	public String toString() {
		return "PolicyBean{" + "policyNo=" + policyNo + ", vehicleNo='" + vehicleNo + '\'' + ", vehicleType='"
				+ vehicleType + '\'' + ", customerName='" + customerName + '\'' + ", engineNo=" + engineNo
				+ ", chasisNo=" + chasisNo + ", phoneNo='" + phoneNo + '\'' + ", insuranceType='" + insuranceType + '\''
				+ ", premiumAmt=" + premiumAmt + ", fromDate=" + fromDate + ", toDate=" + toDate + ", underwriterId="
				+ underwriterId + '}';
	}

	// equals() method
	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Policy that = (Policy) o;
		return policyNo == that.policyNo && engineNo == that.engineNo && chasisNo == that.chasisNo
				&& Double.compare(that.premiumAmt, premiumAmt) == 0 && underwriterId == that.underwriterId
				&& Objects.equals(vehicleNo, that.vehicleNo) && Objects.equals(vehicleType, that.vehicleType)
				&& Objects.equals(customerName, that.customerName) && Objects.equals(phoneNo, that.phoneNo)
				&& Objects.equals(insuranceType, that.insuranceType) && Objects.equals(fromDate, that.fromDate)
				&& Objects.equals(toDate, that.toDate);
	}

	// hashCode() method
	@Override
	public int hashCode() {
		return Objects.hash(policyNo, vehicleNo, vehicleType, customerName, engineNo, chasisNo, phoneNo, insuranceType,
				premiumAmt, fromDate, toDate, underwriterId);
	}
}