package ru.tfoms.applgar.entity;

import java.io.Serializable;
import java.util.Objects;

public class PersDataId implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long rid;

	private Integer nr;

	public PersDataId() {
	}

	public PersDataId(Long rid, Integer nr) {
		super();
		this.rid = rid;
		this.nr = nr;
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

	@Override
	public int hashCode() {
		return Objects.hash(nr, rid);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PersDataId other = (PersDataId) obj;
		return Objects.equals(nr, other.nr) && Objects.equals(rid, other.rid);
	}
	
}
