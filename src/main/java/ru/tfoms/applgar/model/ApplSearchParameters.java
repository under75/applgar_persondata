package ru.tfoms.applgar.model;

import javax.validation.constraints.NotEmpty;

public class ApplSearchParameters {

	private Integer idPers;
	@NotEmpty
	private String dtReg1;
	@NotEmpty
	private String dtReg2;
	private String serDoc;
	private String numDoc;
	private Integer cdInsp;

	public ApplSearchParameters() {
		// TODO Auto-generated constructor stub
	}

	public Integer getIdPers() {
		return idPers;
	}

	public void setIdPers(Integer idPers) {
		this.idPers = idPers;
	}

	public String getDtReg1() {
		return dtReg1;
	}

	public void setDtReg1(String dtReg1) {
		this.dtReg1 = dtReg1;
	}

	public String getDtReg2() {
		return dtReg2;
	}

	public void setDtReg2(String dtReg2) {
		this.dtReg2 = dtReg2;
	}

	public String getSerDoc() {
		return serDoc;
	}

	public void setSerDoc(String serDoc) {
		this.serDoc = serDoc;
	}

	public String getNumDoc() {
		return numDoc;
	}

	public void setNumDoc(String numDoc) {
		this.numDoc = numDoc;
	}

	public Integer getCdInsp() {
		return cdInsp;
	}

	public void setCdInsp(Integer cdInsp) {
		this.cdInsp = cdInsp;
	}

}
