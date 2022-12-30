package ru.tfoms.applgar.entity;

import java.io.Serializable;

public class GarId implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	
	private Boolean isActive;
	
	private Boolean isActual;
	
	public GarId() {
	}

	public GarId(Long id, Boolean isActive, Boolean isActual) {
		super();
		this.id = id;
		this.isActive = isActive;
		this.isActual = isActual;
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
	
}
