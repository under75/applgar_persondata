package ru.tfoms.applgar.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

@Entity
@Table(name = "mpi_person", schema = "OMCOWNER")
@IdClass(PersDataId.class)
public class Person {
	@Id
	@Column(name = "rid")
	private Long rid;

	@Id
	@Column(name = "nr")
	private Integer nr;

	@Column(name = "im")
	private String firstName;

	@Column(name = "fam")
	private String lastName;

	@Column(name = "ot")
	private String patronymic;

	@Column(name = "dr")
	private Date birthDay;

	@Column(name = "sex")
	private Integer gender;

	@OneToOne
	@JoinColumn(name = "birth_oksm", referencedColumnName = "alfa3")
	private Oksm birthOksm;
	
	@Column(name = "death_dt")
	private Date deathDate;

	public Person() {
	}

	public Person(Long rid, Integer nr, String firstName, String lastName, String patronymic, Date birthDay,
			Integer gender, Oksm birthOksm, Date deathDate) {
		super();
		this.rid = rid;
		this.nr = nr;
		this.firstName = firstName;
		this.lastName = lastName;
		this.patronymic = patronymic;
		this.birthDay = birthDay;
		this.gender = gender;
		this.birthOksm = birthOksm;
		this.deathDate = deathDate;
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

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPatronymic() {
		return patronymic;
	}

	public void setPatronymic(String patronymic) {
		this.patronymic = patronymic;
	}

	public Date getBirthDay() {
		return birthDay;
	}

	public void setBirthDay(Date birthDay) {
		this.birthDay = birthDay;
	}

	public Integer getGender() {
		return gender;
	}

	public void setGender(Integer gender) {
		this.gender = gender;
	}

	public Oksm getBirthOksm() {
		return birthOksm;
	}

	public void setBirthOksm(Oksm birthOksm) {
		this.birthOksm = birthOksm;
	}

	public Date getDeathDate() {
		return deathDate;
	}

	public void setDeathDate(Date deathDate) {
		this.deathDate = deathDate;
	}

}
