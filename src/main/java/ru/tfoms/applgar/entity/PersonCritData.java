package ru.tfoms.applgar.entity;

import java.time.LocalDate;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "mpi_find_person_by_crit", schema = "OMCOWNER")
public class PersonCritData {
	@Id
	@Column(name = "rid")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_sequence")
	@SequenceGenerator(name = "id_sequence", sequenceName = "OMCOWNER.MPI_SEQ", allocationSize = 1)
	private Long rid;
	
	@Column(name = "dt_ins")
	private Date dtIns;
	
	@Column(name = "usr")
	private String user;
	
	@Column(name = "page_number")
	private Integer pageNumber;
	
	@Column(name = "item_per_page")
	private Integer itemPerPage;
	
	@Column(name = "ter")
	private String terrOkato;
	
	@Column(name = "fam")
	private String lastName;

	@Column(name = "im")
	private String firstName;

	@Column(name = "ot")
	private String patronymic;
	
	@Column(name = "oldsfp")
	private Boolean oldsfp;
	
	@Column(name = "dost")
	private String dost;
	
	@Column(name = "oksm")
	private String oksm;
	
	@Column(name = "nocitizenship")
	private Boolean noCitizenship;
	
	@Column(name = "birthdayfrom")
	private LocalDate birthDayFrom;
	
	@Column(name = "birthdayto")
	private LocalDate birthDayTo;
	
	@Column(name = "deathdatefrom")
	private LocalDate deathDateFrom;
	
	@Column(name = "deathdateto")
	private LocalDate deathDateTo;
	
	@Column(name = "oip")
	private String oip;
	
	@Column(name = "polis_type")
	private String pcyType;
	
//	private String enp;
	
//	private String pcySer;
	
	@Column(name = "polis")
	private String pcyNum;
	
//	private String tmpcertNum;
	
	@Column(name = "dudltype")
	private Integer dudlType;
	
	@Column(name = "dudlser")
	private String dudlSer;
	
	@Column(name = "dudlnum")
	private String dudlNum;
	
	@Column(name = "snils")
	private String snils;
	
	@Column(name = "dr")
	private LocalDate birthDay;
	
	@Column(name = "ern")
	private String ern;
	
	@Column(name = "dt")
	private LocalDate dt;
	
	@Column(name = "err")
	private Boolean hasError;
	
	@Column(name = "person_cnt")
	private Integer persFoundCnt;

	public Long getRid() {
		return rid;
	}

	public void setRid(Long rid) {
		this.rid = rid;
	}

	public Date getDtIns() {
		return dtIns;
	}

	public void setDtIns(Date dtIns) {
		this.dtIns = dtIns;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public Integer getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(Integer pageNumber) {
		this.pageNumber = pageNumber;
	}

	public Integer getItemPerPage() {
		return itemPerPage;
	}

	public void setItemPerPage(Integer itemPerPage) {
		this.itemPerPage = itemPerPage;
	}

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

	public LocalDate getBirthDayFrom() {
		return birthDayFrom;
	}

	public void setBirthDayFrom(LocalDate birthDayFrom) {
		this.birthDayFrom = birthDayFrom;
	}

	public LocalDate getBirthDayTo() {
		return birthDayTo;
	}

	public void setBirthDayTo(LocalDate birthDayTo) {
		this.birthDayTo = birthDayTo;
	}

	public LocalDate getDeathDateFrom() {
		return deathDateFrom;
	}

	public void setDeathDateFrom(LocalDate deathDateFrom) {
		this.deathDateFrom = deathDateFrom;
	}

	public LocalDate getDeathDateTo() {
		return deathDateTo;
	}

	public void setDeathDateTo(LocalDate deathDateTo) {
		this.deathDateTo = deathDateTo;
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

	public String getPcyNum() {
		return pcyNum;
	}

	public void setPcyNum(String pcyNum) {
		this.pcyNum = pcyNum;
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

	public LocalDate getBirthDay() {
		return birthDay;
	}

	public void setBirthDay(LocalDate birthDay) {
		this.birthDay = birthDay;
	}

	public String getErn() {
		return ern;
	}

	public void setErn(String ern) {
		this.ern = ern;
	}

	public LocalDate getDt() {
		return dt;
	}

	public void setDt(LocalDate dt) {
		this.dt = dt;
	}

	public Boolean getHasError() {
		return hasError;
	}

	public void setHasError(Boolean hasError) {
		this.hasError = hasError;
	}

	public Integer getPersFoundCnt() {
		return persFoundCnt;
	}

	public void setPersFoundCnt(Integer persFoundCnt) {
		this.persFoundCnt = persFoundCnt;
	}

}
