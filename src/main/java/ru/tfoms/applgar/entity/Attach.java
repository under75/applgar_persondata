package ru.tfoms.applgar.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@Table(name = "mpi_attach", schema = "OMCOWNER")
@IdClass(PersDataId.class)
public class Attach {
	@Id
	@Column(name = "rid")
	private Long rid;

	@Id
	@Column(name = "nr")
	private Integer nr;
	
	@Column(name = "areatype")
	private String areaType;
	
	@Column(name = "areaid")
	private String areaId;
	
	@Column(name = "attachmethod")
	private Integer attachMethod;
	
	@Column(name = "mcapitation")
	private Integer mCapitation;
	
	@Column(name = "dateattachb")
	private Date dateAttachB;
	
	@Column(name = "dateattache")
	private Date dateAttachE;
	
	@Column(name = "moid")
	private String moId;
	
	@Column(name = "mocode")
	private String moСode;
	
	@Column(name = "mofid")
	private String moFId;
	
	@Column(name = "snilsdoctor")
	private String snilsDoctor;
	
	@Column(name = "doctorid")
	private String doctorId;
	
	@Column(name = "doctorsince")
	private Date doctorSince;
	
	@Column(name = "descr")
	private String descr;
	
	@Column(name = "mookato")
	private String moOkato;
	
	@Column(name = "attachstatus")
	private String attachStatus;
	
	@Column(name = "dsource")
	private String dsource;
	
	@Column(name = "dsourcetype")
	private String dsourceType;
	
	public Attach() {
	}

	public Long getRid() {
		return rid;
	}

	public void setRid(Long rid) {
		this.rid = rid;
	}

	public Integer getNr() {
		return nr;
	}

	public void setNr(Integer nr) {
		this.nr = nr;
	}

	public String getAreaType() {
		return areaType;
	}

	public void setAreaType(String areaType) {
		this.areaType = areaType;
	}

	public String getAreaId() {
		return areaId;
	}

	public void setAreaId(String areaId) {
		this.areaId = areaId;
	}

	public Integer getAttachMethod() {
		return attachMethod;
	}

	public void setAttachMethod(Integer attachMethod) {
		this.attachMethod = attachMethod;
	}

	public Integer getmCapitation() {
		return mCapitation;
	}

	public void setmCapitation(Integer mCapitation) {
		this.mCapitation = mCapitation;
	}

	public Date getDateAttachB() {
		return dateAttachB;
	}

	public void setDateAttachB(Date dateAttachB) {
		this.dateAttachB = dateAttachB;
	}

	public Date getDateAttachE() {
		return dateAttachE;
	}

	public void setDateAttachE(Date dateAttachE) {
		this.dateAttachE = dateAttachE;
	}

	public String getMoId() {
		return moId;
	}

	public void setMoId(String moId) {
		this.moId = moId;
	}

	public String getMoСode() {
		return moСode;
	}

	public void setMoСode(String moСode) {
		this.moСode = moСode;
	}

	public String getMoFId() {
		return moFId;
	}

	public void setMoFId(String moFId) {
		this.moFId = moFId;
	}

	public String getSnilsDoctor() {
		return snilsDoctor;
	}

	public void setSnilsDoctor(String snilsDoctor) {
		this.snilsDoctor = snilsDoctor;
	}

	public String getDoctorId() {
		return doctorId;
	}

	public void setDoctorId(String doctorId) {
		this.doctorId = doctorId;
	}

	public Date getDoctorSince() {
		return doctorSince;
	}

	public void setDoctorSince(Date doctorSince) {
		this.doctorSince = doctorSince;
	}

	public String getDescr() {
		return descr;
	}

	public void setDescr(String descr) {
		this.descr = descr;
	}

	public String getMoOkato() {
		return moOkato;
	}

	public void setMoOkato(String moOkato) {
		this.moOkato = moOkato;
	}

	public String getAttachStatus() {
		return attachStatus;
	}

	public void setAttachStatus(String attachStatus) {
		this.attachStatus = attachStatus;
	}

	public String getDsource() {
		return dsource;
	}

	public void setDsource(String dsource) {
		this.dsource = dsource;
	}

	public String getDsourceType() {
		return dsourceType;
	}

	public void setDsourceType(String dsourceType) {
		this.dsourceType = dsourceType;
	}
	
}
