package ru.tfoms.applgar.model;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class SmoSearchParameters implements SearchParameters{
	private String policyType;
	@Size(max=10)
	private String policySer;
	@Size(max=16)
	private String policyNum;
	private Integer dudlType;
	@Size(max=12)
	private String dudlSer;
	@Size(max=20)
	private String dudlNum;
	@Size(max=14)
	private String snils;
	@NotEmpty
	private String birthDay;
	@Size(min=3,max=40)
	private String lastName;
	@Size(min=3,max=40)
	private String firstName;
	@Size(min=3,max=40)
	private String patronymic;
	private String dateFrom;
	private String dateTo;
	
	public String getPolicyType() {
		return policyType;
	}
	public void setPolicyType(String policyType) {
		this.policyType = policyType;
	}
	public String getPolicySer() {
		return policySer;
	}
	public void setPolicySer(String policySer) {
		this.policySer = policySer;
	}
	public String getPolicyNum() {
		return policyNum;
	}
	public void setPolicyNum(String policyNum) {
		this.policyNum = policyNum;
	}
	public Integer getDudlType() {
		return dudlType;
	}
	public void setDudlType(Integer dudlType) {
		this.dudlType = dudlType;
	}
	public String getDudlSer() {
		return dudlSer;
	}
	public void setDudlSer(String dudlSer) {
		this.dudlSer = dudlSer;
	}
	public String getDudlNum() {
		return dudlNum;
	}
	public void setDudlNum(String dudlNum) {
		this.dudlNum = dudlNum;
	}
	public String getSnils() {
		return snils;
	}
	public void setSnils(String snils) {
		this.snils = snils;
	}
	public String getBirthDay() {
		return birthDay;
	}
	public void setBirthDay(String birthDay) {
		this.birthDay = birthDay;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getPatronymic() {
		return patronymic;
	}
	public void setPatronymic(String patronymic) {
		this.patronymic = patronymic;
	}
	public String getDateFrom() {
		return dateFrom;
	}
	public void setDateFrom(String dateFrom) {
		this.dateFrom = dateFrom;
	}
	public String getDateTo() {
		return dateTo;
	}
	public void setDateTo(String dateTo) {
		this.dateTo = dateTo;
	}
}
