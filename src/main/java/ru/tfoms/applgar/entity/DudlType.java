package ru.tfoms.applgar.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "spr_dok", schema = "OMCOWNER")
public class DudlType {
	
	@Id
	@Column(name = "cd_dok")
	private Integer docCode;
	
	@Column(name = "nm_dok")
	private String docName;
	
	@Column(name = "rus")
	private Integer rus;
	
	public DudlType() {
	}

	public Integer getDocCode() {
		return docCode;
	}

	public void setDocCode(Integer docCode) {
		this.docCode = docCode;
	}

	public String getDocName() {
		return docName;
	}

	public void setDocName(String docName) {
		this.docName = docName;
	}

	public Integer getRus() {
		return rus;
	}

	public void setRus(Integer rus) {
		this.rus = rus;
	}
	
}
