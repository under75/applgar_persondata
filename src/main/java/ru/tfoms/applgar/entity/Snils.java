package ru.tfoms.applgar.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "mpi_snils", schema = "OMCOWNER")
@IdClass(PersDataId.class)
public class Snils {
	@Id
	@Column(name = "rid")
	private Long rid;

	@Id
	@Column(name = "nr")
	private Integer nr;

	@Column(name = "snils")
	private String snils;

	@OneToOne
	@JoinColumn(name = "dsource", referencedColumnName = "cod")
	private Okato dsource;

	@Column(name = "dsourcetype")
	private String dsourceType;

	@Column(name = "descr")
	private String descr;
	
	public Snils() {
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

	public String getSnils() {
		return snils;
	}

	public void setSnils(String snils) {
		this.snils = snils;
	}

	public Okato getDsource() {
		return dsource;
	}

	public void setDsource(Okato dsource) {
		this.dsource = dsource;
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
	
}
