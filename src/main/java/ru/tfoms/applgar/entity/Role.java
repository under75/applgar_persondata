package ru.tfoms.applgar.entity;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "SMOROLES", schema = "BIGADMIN")
public class Role {

	@Id
	@Column(name = "u_name")
	private String u_name;

	@Column(name = "role_name")
	private String role_name;

	public Role() {
	}

	public String getRole_name() {
		return role_name;
	}

	public void setRole_name(String role_name) {
		this.role_name = role_name;
	}

	public Role(String u_name, String role_name) {
		super();
		this.u_name = u_name;
		this.role_name = role_name;
	}

	public String getU_name() {
		return u_name;
	}

	public void setU_name(String u_name) {
		this.u_name = u_name;
	}

	@Override
	public int hashCode() {
		return Objects.hash(u_name);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Role other = (Role) obj;
		return Objects.equals(u_name, other.u_name);
	}

}
