package ru.tfoms.applgar.model;

import javax.validation.constraints.Size;

public class PersCritSearchParameters {
	private String terrOkato;
	@Size(max=40)
	private String lastName;
	@Size(max=40)
	private String firstName;
	@Size(max=40)
	private String patronymic;
	private Boolean oldsfp;
	private String dost;
	private String oksm;
	private Boolean noCitizenship;
	private String birthDayFrom;
	private String birthDayTo;
	private String deathDateFrom;
	private String deathDateTo;
	@Size(max=12)
	private String oip;
	private String policyType;
	private String pcySer;
	@Size(max=12)
	private String pcyNum;
	private String tmpcertNum;
	private Integer dudlType;
	@Size(max=12)
	private String dudlSer;
	@Size(max=20)
	private String dudlNum;
	@Size(max=14)
	private String snils;
	private String birthDay;
	@Size(max=36)
	private String ern;
	private String dt;
	private String dateFrom;
	private String dateTo;
	
	public String getTerrOkato() {
		return terrOkato;
	}

	public void setTerrOkato(String terrOkato) {
		this.terrOkato = terrOkato;
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

	public Boolean getOldsfp() {
		return oldsfp;
	}

	public void setOldsfp(Boolean oldsfp) {
		this.oldsfp = oldsfp;
	}

	public String getDost() {
		return dost;
	}

	public void setDost(String dost) {
		this.dost = dost;
	}

	public String getOksm() {
		return oksm;
	}

	public void setOksm(String oksm) {
		this.oksm = oksm;
	}

	public Boolean getNoCitizenship() {
		return noCitizenship;
	}

	public void setNoCitizenship(Boolean noCitizenship) {
		this.noCitizenship = noCitizenship;
	}

	public String getBirthDayFrom() {
		return birthDayFrom;
	}

	public void setBirthDayFrom(String birthDayFrom) {
		this.birthDayFrom = birthDayFrom;
	}

	public String getBirthDayTo() {
		return birthDayTo;
	}

	public void setBirthDayTo(String birthDayTo) {
		this.birthDayTo = birthDayTo;
	}

	public String getDeathDateFrom() {
		return deathDateFrom;
	}

	public void setDeathDateFrom(String deathDateFrom) {
		this.deathDateFrom = deathDateFrom;
	}

	public String getDeathDateTo() {
		return deathDateTo;
	}

	public void setDeathDateTo(String deathDateTo) {
		this.deathDateTo = deathDateTo;
	}

	public String getOip() {
		return oip;
	}

	public void setOip(String oip) {
		this.oip = oip;
	}

	public String getPolicyType() {
		return policyType;
	}

	public void setPolicyType(String policyType) {
		this.policyType = policyType;
	}

	public String getPcySer() {
		return pcySer;
	}

	public void setPcySer(String pcySer) {
		this.pcySer = pcySer;
	}

	public String getPcyNum() {
		return pcyNum;
	}

	public void setPcyNum(String pcyNum) {
		this.pcyNum = pcyNum;
	}

	public String getTmpcertNum() {
		return tmpcertNum;
	}

	public void setTmpcertNum(String tmpcertNum) {
		this.tmpcertNum = tmpcertNum;
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

	public String getErn() {
		return ern;
	}

	public void setErn(String ern) {
		this.ern = ern;
	}

	public String getDt() {
		return dt;
	}

	public void setDt(String dt) {
		this.dt = dt;
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
