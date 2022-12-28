package ru.tfoms.applgar.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import ru.tfoms.applgar.entity.User;
import ru.tfoms.applgar.model.ApplSearchParameters;
import ru.tfoms.applgar.model.RowData;
import ru.tfoms.applgar.util.Constants;

@Component
public class ApplDAO {
	protected final NamedParameterJdbcTemplate jdbcTemplate;

	@Autowired
	public ApplDAO(NamedParameterJdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public Collection<RowData> getDataForExcel(User user, ApplSearchParameters appl) {
		MapSqlParameterSource namedParams = new MapSqlParameterSource();

		StringBuilder sql = new StringBuilder("select rownum, appl.* from OMCOWNER.V_APPL_EXT appl where");
		sql.append(" trunc(appl.DT_APPL) > to_date(:dtReg1, 'yyyy-MM-dd')");
		sql.append(" and trunc(appl.DT_APPL) < to_date(:dtReg2, 'yyyy-MM-dd')");
		sql.append(" and appl.CD_SMO = :smo");
		namedParams.addValue("dtReg1", appl.getDtReg1());
		namedParams.addValue("dtReg2", appl.getDtReg2());
		namedParams.addValue("smo", user.getSmo() + Constants.SMO_ADD_CODE);

		if (!(user.getRoles().stream().filter(t -> t.getRole_name().equals(Constants.HSMO_ROLE)).count() > 0)) {
			sql.append(" and appl.CD_FSMO = :fSmo");
			namedParams.addValue("fSmo", user.getfSmo());
		}

		String serDoc = appl.getSerDoc().trim();
		String numDoc = appl.getNumDoc().trim();
		if (!serDoc.isEmpty()) {
			sql.append(" and appl.SER_DOC = :serDoc");
			namedParams.addValue("serDoc", serDoc);
		}
		if (!numDoc.isEmpty()) {
			sql.append(" and appl.NUM_DOC = :numDoc");
			namedParams.addValue("numDoc", numDoc);
		}
		if (appl.getCdInsp() != null) {
			sql.append(" and appl.CD_INSP = :cdInsp");
			namedParams.addValue("cdInsp", appl.getCdInsp());
		}

		Collection<RowData> data = jdbcTemplate.query(sql.toString(), namedParams,
				new BeanPropertyRowMapper<RowData>(RowData.class) {
					@Override
					public RowData mapRow(ResultSet rs, int rowNum) throws SQLException {
						RowData data = new RowData();
						data.setNum(rs.getString(1));
						data.setSmoCode(String.valueOf(rs.getInt(2)));
						data.setFsmoCode(String.valueOf(rs.getInt(3)));
						data.setFsmoName(rs.getString(4));
						data.setApplDate(rs.getDate(5));
						data.setApplType(rs.getString(6));
						data.setApplCause(rs.getString(7));
						data.setPersonLastName(rs.getString(8));
						data.setPersonFirstName(rs.getString(9));
						data.setPersonPatronymic(rs.getString(10));
						data.setPersonBirsday(rs.getDate(11));
						data.setPersonGender(rs.getString(12));
						data.setPersonHomePhone(rs.getString(13));
						data.setPersonWorkPhone(rs.getString(14));
						data.setPersonEmail(rs.getString(15));
						data.setRepresentativeHomePhone(rs.getString(16));
						data.setRepresentativeWorkPhone(rs.getString(17));
						data.setRepresentativeEmail(rs.getString(18));
						data.setInspectorCode(rs.getString(19));
						data.setInspectorFullName(rs.getString(20));
						data.setRguidreg(rs.getString(21));
						data.setAoguidreg(rs.getString(22));
						data.setHsguidreg(rs.getString(23));
						data.setRguidpr(rs.getString(24));
						data.setAoguidpr(rs.getString(25));
						data.setHsguidpr(rs.getString(26));
						data.setPersonPolisNumber(rs.getString(27));

						return data;
					}
				});

		for (RowData rowData : data) {
			setAddress(rowData);
		}

		return data;
	}

	private void setAddress(RowData data) {
		String sql = "select addr.name_ from FIASOWNER.as_addr_obj addr where addr.objectguid = :objectguid";
		MapSqlParameterSource namedParams = new MapSqlParameterSource();

		namedParams.addValue("objectguid", data.getRguidreg());
		String lev1reg = jdbcTemplate.queryForList(sql, namedParams, String.class).stream().findAny().orElse("");

		namedParams.addValue("objectguid", data.getAoguidreg());
		String lev2reg = jdbcTemplate.queryForList(sql, namedParams, String.class).stream().findAny().orElse("");

		namedParams.addValue("objectguid", data.getRguidpr());
		String lev1pr = jdbcTemplate.queryForList(sql, namedParams, String.class).stream().findAny().orElse("");

		namedParams.addValue("objectguid", data.getAoguidpr());
		String lev2pr = jdbcTemplate.queryForList(sql, namedParams, String.class).stream().findAny().orElse("");

		sql = "select addr.housenum, addr.addnum1, addr.addnum2 from FIASOWNER.as_houses addr where addr.objectguid = :objectguid";

		namedParams.addValue("objectguid", data.getHsguidreg());
		String[] arr = { "", "", "" };
		String[] lev3reg = (String[]) jdbcTemplate
				.query(sql, namedParams, new BeanPropertyRowMapper<String[]>(String[].class) {
					@Override
					public String[] mapRow(ResultSet rs, int rowNum) throws SQLException {
						arr[0] = rs.getString(1) != null ? rs.getString(1) : "";
						arr[1] = rs.getString(2) != null ? rs.getString(2) : "";
						arr[2] = rs.getString(3) != null ? rs.getString(3) : "";
						return arr;
					}
				}).stream().findAny().orElse(arr);

		namedParams.addValue("objectguid", data.getHsguidpr());
		arr[0] = "";
		arr[1] = "";
		arr[2] = "";
		String[] lev3pr = (String[]) jdbcTemplate
				.query(sql, namedParams, new BeanPropertyRowMapper<String[]>(String[].class) {
					@Override
					public String[] mapRow(ResultSet rs, int rowNum) throws SQLException {
						arr[0] = rs.getString(1) != null ? rs.getString(1) : "";
						arr[1] = rs.getString(2) != null ? rs.getString(2) : "";
						arr[2] = rs.getString(3) != null ? rs.getString(3) : "";
						return arr;
					}
				}).stream().findAny().orElse(arr);

		data.setPersonAddressReg(
				(lev1reg + " " + lev2reg + " " + lev3reg[0] + " " + lev3reg[1] + " " + lev3reg[2]).trim());
		data.setPersonAddressPr((lev1pr + " " + lev2pr + " " + lev3pr[0] + " " + lev3pr[1] + " " + lev3pr[2]).trim());
	}

}
