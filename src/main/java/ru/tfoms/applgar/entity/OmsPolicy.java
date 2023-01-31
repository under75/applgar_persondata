package ru.tfoms.applgar.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

@Entity
@Table(name = "mpi_policy", schema = "OMCOWNER")
@IdClass(PersDataId.class)
public class OmsPolicy {
	@Id
	@Column(name = "rid")
	private Long rid;

	@Id
	@Column(name = "nr")
	private Integer nr;

	@Column(name = "pcyser")
	private String pcySer;

	@Column(name = "pcynum")
	private String pcyNum;

	@Column(name = "enpcalc")
	private String enpCalc;

	@Column(name = "enp")
	private String enp;

	@Column(name = "pcydateb")
	private Date pcyDateB;

	@Column(name = "pcydatee")
	private Date pcyDateE;

	@Column(name = "pcydateh")
	private Date pcyDateH;

	@Column(name = "pcydatet")
	private Date pcyDateT;

	@Column(name = "pcydateenpcalc")
	private Date pcyDateEnpCalc;

	@Column(name = "pcydatepr")
	private Date pcyDatePr;

	@Column(name = "pcytype")
	private String pcyType;

	@Column(name = "pcystatus")
	private String pcyStatus;

	@OneToOne
	@JoinColumn(name = "okato", referencedColumnName = "cod")
	private Okato okato;

	@OneToOne
	@NotFound(action = NotFoundAction.IGNORE) 
	@JoinColumn(name = "dsource", referencedColumnName = "cod", insertable=false, updatable=false)
	private Okato dsource;
	
	@Column(name = "dsource")
	private String dsourceStr;

	@Column(name = "dsourcetype")
	private String dsourceType;

	@Column(name = "descr")
	private String descr;

	@Column(name = "sex")
	private Integer gender;

	@Column(name = "insurname")
	private String insurName;

	@Column(name = "insurfname")
	private String insurfName;

	@Column(name = "insurogrn")
	private String insurOgrn;

	@Column(name = "insurfogrn")
	private String insurfOgrn;

	@Column(name = "insurcode")
	private String insurCode;

	@Column(name = "insurfcode")
	private String insurfCode;

	@Column(name = "insurfdate")
	private Date insurfDate;

	@Column(name = "tmpcertnum")
	private String tmpcertNum;

	@Column(name = "tmpcertdateb")
	private Date tmpcertDateB;

	@Column(name = "tmpcertdatee")
	private Date tmpcertDateE;

	@Column(name = "ueknum")
	private String uekNum;

	@OneToOne
	@JoinColumn(name = "pcycategory", referencedColumnName = "idkat")
	private PersCategory pcyCategory;

	@Column(name = "fam")
	private String lastName;

	@Column(name = "ot")
	private String patronymic;

	@Column(name = "im")
	private String firstName;

	@Column(name = "dr")
	private Date birthDay;

	@Column(name = "blanknum")
	private String blankNum;

	public OmsPolicy() {
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

	public String getEnpCalc() {
		return enpCalc;
	}

	public void setEnpCalc(String enpCalc) {
		this.enpCalc = enpCalc;
	}

	public String getEnp() {
		return enp;
	}

	public void setEnp(String enp) {
		this.enp = enp;
	}

	public Date getPcyDateB() {
		return pcyDateB;
	}

	public void setPcyDateB(Date pcyDateB) {
		this.pcyDateB = pcyDateB;
	}

	public Date getPcyDateE() {
		return pcyDateE;
	}

	public void setPcyDateE(Date pcyDateE) {
		this.pcyDateE = pcyDateE;
	}

	public Date getPcyDateH() {
		return pcyDateH;
	}

	public void setPcyDateH(Date pcyDateH) {
		this.pcyDateH = pcyDateH;
	}

	public Date getPcyDateT() {
		return pcyDateT;
	}

	public void setPcyDateT(Date pcyDateT) {
		this.pcyDateT = pcyDateT;
	}

	public Date getPcyDateEnpCalc() {
		return pcyDateEnpCalc;
	}

	public void setPcyDateEnpCalc(Date pcyDateEnpCalc) {
		this.pcyDateEnpCalc = pcyDateEnpCalc;
	}

	public Date getPcyDatePr() {
		return pcyDatePr;
	}

	public void setPcyDatePr(Date pcyDatePr) {
		this.pcyDatePr = pcyDatePr;
	}

	public String getPcyType() {
		return pcyType;
	}

	public void setPcyType(String pcyType) {
		this.pcyType = pcyType;
	}

	public String getPcyStatus() {
		return pcyStatus;
	}

	public void setPcyStatus(String pcyStatus) {
		this.pcyStatus = pcyStatus;
	}

	public Okato getOkato() {
		return okato;
	}

	public void setOkato(Okato okato) {
		this.okato = okato;
	}

	public Okato getDsource() {
		return dsource;
	}

	public void setDsource(Okato dsource) {
		this.dsource = dsource;
	}

	public String getDsourceStr() {
		return dsourceStr;
	}

	public void setDsourceStr(String dsourceStr) {
		this.dsourceStr = dsourceStr;
	}

	public String getDsourceType() {
		return dsourceType;
	}

	public void setDsourceType(String dsourceType) {
		this.dsourceType = dsourceType;
	}

	public String getDescr() {
		return descr;
	}

	public void setDescr(String descr) {
		this.descr = descr;
	}

	public Integer getGender() {
		return gender;
	}

	public void setGender(Integer gender) {
		this.gender = gender;
	}

	public String getInsurName() {
		return insurName;
	}

	public void setInsurName(String insurName) {
		this.insurName = insurName;
	}

	public String getInsurfName() {
		return insurfName;
	}

	public void setInsurfName(String insurfName) {
		this.insurfName = insurfName;
	}

	public String getInsurOgrn() {
		return insurOgrn;
	}

	public void setInsurOgrn(String insurOgrn) {
		this.insurOgrn = insurOgrn;
	}

	public String getInsurfOgrn() {
		return insurfOgrn;
	}

	public void setInsurfOgrn(String insurfOgrn) {
		this.insurfOgrn = insurfOgrn;
	}

	public String getInsurCode() {
		return insurCode;
	}

	public void setInsurCode(String insurCode) {
		this.insurCode = insurCode;
	}

	public String getInsurfCode() {
		return insurfCode;
	}

	public void setInsurfCode(String insurfCode) {
		this.insurfCode = insurfCode;
	}

	public Date getInsurfDate() {
		return insurfDate;
	}

	public void setInsurfDate(Date insurfDate) {
		this.insurfDate = insurfDate;
	}

	public String getTmpcertNum() {
		return tmpcertNum;
	}

	public void setTmpcertNum(String tmpcertNum) {
		this.tmpcertNum = tmpcertNum;
	}

	public Date getTmpcertDateB() {
		return tmpcertDateB;
	}

	public void setTmpcertDateB(Date tmpcertDateB) {
		this.tmpcertDateB = tmpcertDateB;
	}

	public Date getTmpcertDateE() {
		return tmpcertDateE;
	}

	public void setTmpcertDateE(Date tmpcertDateE) {
		this.tmpcertDateE = tmpcertDateE;
	}

	public String getUekNum() {
		return uekNum;
	}

	public void setUekNum(String uekNum) {
		this.uekNum = uekNum;
	}

	public PersCategory getPcyCategory() {
		return pcyCategory;
	}

	public void setPcyCategory(PersCategory pcyCategory) {
		this.pcyCategory = pcyCategory;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPatronymic() {
		return patronymic;
	}

	public void setPatronymic(String patronymic) {
		this.patronymic = patronymic;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public Date getBirthDay() {
		return birthDay;
	}

	public void setBirthDay(Date birthDay) {
		this.birthDay = birthDay;
	}

	public String getBlankNum() {
		return blankNum;
	}

	public void setBlankNum(String blankNum) {
		this.blankNum = blankNum;
	}

}
