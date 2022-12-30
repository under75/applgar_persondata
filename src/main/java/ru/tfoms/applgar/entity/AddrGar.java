package ru.tfoms.applgar.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@Table(name = "as_addr_obj", schema = "FIASOWNER")
@IdClass(GarId.class)
public class AddrGar {
	@Id
	@Column(name = "id")
	private Long id;
	@Id
	@Column(name = "isactive")
	private Boolean isActive;
	@Id
	@Column(name = "isactual")
	private Boolean isActual;
	
	@Column(name = "objectguid")
	private String objectguid;
	
	@Column(name = "name_")
	private String name;
	
	public AddrGar() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public Boolean getIsActual() {
		return isActual;
	}

	public void setIsActual(Boolean isActual) {
		this.isActual = isActual;
	}

	public String getObjectguid() {
		return objectguid;
	}

	public void setObjectguid(String objectguid) {
		this.objectguid = objectguid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
