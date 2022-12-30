package ru.tfoms.applgar.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@Table(name = "СМО_РОССИИ", schema = "LPUOWNER")
@IdClass(SmoId.class)
public class Smo {
	@Id
	@Column(name = "tf_okato")
	private Long tfOkato;

	@Id
	@Column(name = "smocod")
	private Integer code;
	
	@Column(name = "nam_smop")
	private String fullName;
	
	@Column(name = "ogrn")
	private String ogrn;
	
	public Smo() {
	}

	public Long getTfOkato() {
		return tfOkato;
	}

	public void setTfOkato(Long tfOkato) {
		this.tfOkato = tfOkato;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getOgrn() {
		return ogrn;
	}

	public void setOgrn(String ogrn) {
		this.ogrn = ogrn;
	}
	
}
