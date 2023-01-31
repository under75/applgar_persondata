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
@Table(name = "mpi_address", schema = "OMCOWNER")
@IdClass(PersDataId.class)
public class PersAddress {
	@Id
	@Column(name = "rid")
	private Long rid;

	@Id
	@Column(name = "nr")
	private Integer nr;

	@OneToOne
	@NotFound(action = NotFoundAction.IGNORE) 
	@JoinColumn(name = "okato", referencedColumnName = "cod")
	private Okato okato;

	@OneToOne
	@NotFound(action = NotFoundAction.IGNORE) 
	@JoinColumn(name = "oksm", referencedColumnName = "alfa3")
	private Oksm oksm;

	@OneToOne
	@NotFound(action = NotFoundAction.IGNORE) 
	@JoinColumn(name = "aoguid", referencedColumnName = "objectguid")
	private AddrGar aoguid;

	@OneToOne
	@NotFound(action = NotFoundAction.IGNORE) 
	@JoinColumn(name = "hsguid", referencedColumnName = "objectguid")
	private HouseGar hsguid;

	@Column(name = "addresstext")
	private String addressText;

	@Column(name = "mailindex")
	private String mailIndex;

	@Column(name = "appnum")
	private String appNum;

	@Column(name = "dsourcetype")
	private String dsourceType;

	@OneToOne
	@NotFound(action = NotFoundAction.IGNORE) 
	@JoinColumn(name = "dsource", referencedColumnName = "cod", insertable=false, updatable=false)
	private Okato dsource;
	
	@Column(name = "dsource")
	private String dsourceStr;

	@Column(name = "addresstype")
	private String addressType;

	@Column(name = "addressdateb")
	private Date addressDateB;

	@Column(name = "addressdatee")
	private Date addressDateE;

	public PersAddress() {
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

	public Okato getOkato() {
		return okato;
	}

	public void setOkato(Okato okato) {
		this.okato = okato;
	}

	public Oksm getOksm() {
		return oksm;
	}

	public void setOksm(Oksm oksm) {
		this.oksm = oksm;
	}

	public AddrGar getAoguid() {
		return aoguid;
	}

	public void setAoguid(AddrGar aoguid) {
		this.aoguid = aoguid;
	}

	public HouseGar getHsguid() {
		return hsguid;
	}

	public void setHsguid(HouseGar hsguid) {
		this.hsguid = hsguid;
	}

	public String getAddressText() {
		return addressText;
	}

	public void setAddressText(String addressText) {
		this.addressText = addressText;
	}

	public String getMailIndex() {
		return mailIndex;
	}

	public void setMailIndex(String mailIndex) {
		this.mailIndex = mailIndex;
	}

	public String getAppNum() {
		return appNum;
	}

	public void setAppNum(String appNum) {
		this.appNum = appNum;
	}

	public String getDsourceType() {
		return dsourceType;
	}

	public void setDsourceType(String dsourceType) {
		this.dsourceType = dsourceType;
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

	public String getAddressType() {
		return addressType;
	}

	public void setAddressType(String addressType) {
		this.addressType = addressType;
	}

	public Date getAddressDateB() {
		return addressDateB;
	}

	public void setAddressDateB(Date addressDateB) {
		this.addressDateB = addressDateB;
	}

	public Date getAddressDateE() {
		return addressDateE;
	}

	public void setAddressDateE(Date addressDateE) {
		this.addressDateE = addressDateE;
	}

}
