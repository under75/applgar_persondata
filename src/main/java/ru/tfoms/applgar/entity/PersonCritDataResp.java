package ru.tfoms.applgar.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@Table(name = "mpi_find_person_by_crit_resp", schema = "OMCOWNER")
@IdClass(PersDataId.class)
public class PersonCritDataResp {
	@Id
	@Column(name = "rid")
	private Long rid;

	@Id
	@Column(name = "nr")
	private Integer nr;
	
	@Column(name = "fio")
	private String fio;
	
	@Column(name = "enp")
	private String enp;
	
	@Column(name = "dr")
	private LocalDate birthDay;
	
	@Column(name = "sex")
	private Integer gender;
	
	@Column(name = "oip")
	private String oip;

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

	public String getFio() {
		return fio;
	}

	public void setFio(String fio) {
		this.fio = fio;
	}

	public String getEnp() {
		return enp;
	}

	public void setEnp(String enp) {
		this.enp = enp;
	}

	public LocalDate getBirthDay() {
		return birthDay;
	}

	public void setBirthDay(LocalDate birthDay) {
		this.birthDay = birthDay;
	}

	public Integer getGender() {
		return gender;
	}

	public void setGender(Integer gender) {
		this.gender = gender;
	}

	public String getOip() {
		return oip;
	}

	public void setOip(String oip) {
		this.oip = oip;
	}
	
}
