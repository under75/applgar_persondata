package ru.tfoms.applgar.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "v013", schema = "OMCOWNER")
public class PersCategory {
	
	@Id
	@Column(name = "idkat")
	private Integer id;
	
	@Column(name = "katname")	
	private String name;
	
	public PersCategory() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
}
