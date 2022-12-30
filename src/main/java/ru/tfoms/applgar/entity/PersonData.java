package ru.tfoms.applgar.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "mpi_person_data", schema = "OMCOWNER")
public class PersonData {
	@Id
	@Column(name = "rid")
	private String rid;

	@Column(name = "oip")
	private String oip;

	@Column(name = "polis_type")
	private String pcyType;

	@Column(name = "polis")
	private String pcy;

	@Column(name = "dudlser")
	private String dudlSer;

	@Column(name = "dudlnum")
	private String dudlNum;

	@OneToOne
	@JoinColumn(name = "dudltype", referencedColumnName = "cd_doc")
	private DudlType dudlType;

	@Column(name = "snils")
	private String snils;

	@Column(name = "dr")
	private Date birthDay;
	
	@Column(name = "fam")
	private String lastName;
	
	@Column(name = "im")
	private String firstName;
	
	@Column(name = "ot")
	private String patronymic;
	
	@Column(name = "dt")
	private Date dt;
	
	@Column(name = "showx")
	private String show;
	
	public PersonData() {
	}

	public String getRid() {
		return rid;
	}

	public void setRid(String rid) {
		this.rid = rid;
	}

	public String getOip() {
		return oip;
	}

	public void setOip(String oip) {
		this.oip = oip;
	}

	public String getPcyType() {
		return pcyType;
	}

	public void setPcyType(String pcyType) {
		this.pcyType = pcyType;
	}

	public String getPcy() {
		return pcy;
	}

	public void setPcy(String pcy) {
		this.pcy = pcy;
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

	public DudlType getDudlType() {
		return dudlType;
	}

	public void setDudlType(DudlType dudlType) {
		this.dudlType = dudlType;
	}

	public String getSnils() {
		return snils;
	}

	public void setSnils(String snils) {
		this.snils = snils;
	}

	public Date getBirthDay() {
		return birthDay;
	}

	public void setBirthDay(Date birthDay) {
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

	public Date getDt() {
		return dt;
	}

	public void setDt(Date dt) {
		this.dt = dt;
	}

	public String getShow() {
		return show;
	}

	public void setShow(String show) {
		this.show = show;
	}
	
	
}
