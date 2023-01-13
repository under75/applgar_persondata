package ru.tfoms.applgar.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ЛПУ", schema = "LPUOWNER")
public class Lpu implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "cd_lpu")
	private Integer lpuCode;

	@Column(name = "nm_lpu")
	private String title;

	@Column(name = "mcod")
	private String moСode;

	public Integer getLpuCode() {
		return lpuCode;
	}

	public void setLpuCode(Integer lpuCode) {
		this.lpuCode = lpuCode;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getMoСode() {
		return moСode;
	}

	public void setMoСode(String moСode) {
		this.moСode = moСode;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
