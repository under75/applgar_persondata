package ru.tfoms.applgar.model;

public class PersSearchParameters {
	private String policyType;
	private String policyNum;
	private Integer dudlType;
	private String dudlSer;
	private String dudlNum;
	private String snils;
	private String birthDay;
	private String lastName;
	private String firstName;
	private String patronymic;
	private Boolean historical = false;
	private String dt;
	private String dtFrom;
	private String dtTo;
	private String show;

	public String getPolicyType() {
		return policyType;
	}

	public void setPolicyType(String policyType) {
		this.policyType = policyType;
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

	public Boolean getHistorical() {
		return historical;
	}

	public void setHistorical(Boolean historical) {
		this.historical = historical;
	}

	public String getDt() {
		return dt;
	}

	public void setDt(String dt) {
		this.dt = dt;
	}

	public String getDtFrom() {
		return dtFrom;
	}

	public void setDtFrom(String dtFrom) {
		this.dtFrom = dtFrom;
	}

	public String getDtTo() {
		return dtTo;
	}

	public void setDtTo(String dtTo) {
		this.dtTo = dtTo;
	}

	public String getShow() {
		return show;
	}

	public void setShow(String show) {
		this.show = show;
	}

}
