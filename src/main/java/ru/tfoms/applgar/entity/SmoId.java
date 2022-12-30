package ru.tfoms.applgar.entity;

import java.io.Serializable;
import java.util.Objects;

public class SmoId implements Serializable {
	private static final long serialVersionUID = 1L;

	private String tfOkato;
	
	private String code;
	
	public SmoId() {
	}

	public SmoId(String tfOkato, String smoCode) {
		super();
		this.tfOkato = tfOkato;
		this.code = smoCode;
	}

	public String gettfOkato() {
		return tfOkato;
	}

	public void settfOkato(String tfOkato) {
		this.tfOkato = tfOkato;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String smoCode) {
		this.code = smoCode;
	}

	@Override
	public int hashCode() {
		return Objects.hash(code, tfOkato);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SmoId other = (SmoId) obj;
		return Objects.equals(code, other.code) && Objects.equals(tfOkato, other.tfOkato);
	}
	
}
