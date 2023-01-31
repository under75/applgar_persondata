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
@Table(name = "mpi_social_status", schema = "OMCOWNER")
@IdClass(PersDataId.class)
public class SocialStatus {
	@Id
	@Column(name = "rid")
	private Long rid;

	@Id
	@Column(name = "nr")
	private Integer nr;

	@Column(name = "socialstatus")
	private Integer socialStatus;

	@Column(name = "dsourcetype")
	private String dsourceType;

	@OneToOne
	@NotFound(action = NotFoundAction.IGNORE) 
	@JoinColumn(name = "dsource", referencedColumnName = "cod", insertable=false, updatable=false)
	private Okato dsource;
	
	@Column(name = "dsource")
	private String dsourceStr;

	@Column(name = "descr")
	private String descr;

	@Column(name = "regdate")
	private Date regDate;
	
	public SocialStatus() {
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

	public Integer getSocialStatus() {
		return socialStatus;
	}

	public void setSocialStatus(Integer socialStatus) {
		this.socialStatus = socialStatus;
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

	public String getDescr() {
		return descr;
	}

	public void setDescr(String descr) {
		this.descr = descr;
	}

	public Date getRegDate() {
		return regDate;
	}

	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}
	
}
