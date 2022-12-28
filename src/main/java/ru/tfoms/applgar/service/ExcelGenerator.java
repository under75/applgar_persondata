package ru.tfoms.applgar.service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Collection;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;

import ru.tfoms.applgar.exception.ExcelGeneratorException;
import ru.tfoms.applgar.model.RowData;

public class ExcelGenerator {
	protected HSSFWorkbook template;
	protected HSSFSheet sheet;
	protected Collection<RowData> data;

	public ExcelGenerator(Collection<RowData> rows) throws ExcelGeneratorException {
		if (rows.size() > 65535)
			throw new ExcelGeneratorException("Превышено допустимое количество строк для Excel, 65535 максимум");
		template = new HSSFWorkbook();
		sheet = template.createSheet();
		data = rows;

		createHeader();
		createBody();
	}

	public ByteArrayInputStream toExcel() throws IOException {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		template.write(out);

		return new ByteArrayInputStream(out.toByteArray());
	}

	private void createBody() {
		CellStyle bodyStyle = template.createCellStyle();
		Font bodyFont = template.createFont();
		bodyFont.setFontName("Calibri");
		bodyStyle.setFont(bodyFont);
		bodyStyle.setAlignment(HorizontalAlignment.CENTER);

		int rowNum = 2;// firstDataRow
		for (RowData rowData : data) {
			int column = 0;
			HSSFRow row = sheet.createRow(rowNum++);
			setCellValue(createCellAndFormat(row, column++, bodyStyle), rowData.getNum());
			setCellValue(createCellAndFormat(row, column++, bodyStyle), rowData.getSmoCode());
			setCellValue(createCellAndFormat(row, column++, bodyStyle), rowData.getFsmoCode());
			setCellValue(createCellAndFormat(row, column++, bodyStyle), rowData.getFsmoName());
			setCellValue(createCellAndFormat(row, column++, bodyStyle),
					new SimpleDateFormat("dd.MM.yyyy").format(rowData.getApplDate()));
			setCellValue(createCellAndFormat(row, column++, bodyStyle), rowData.getApplType());
			setCellValue(createCellAndFormat(row, column++, bodyStyle), rowData.getApplCause());
			setCellValue(createCellAndFormat(row, column++, bodyStyle), rowData.getPersonLastName());
			setCellValue(createCellAndFormat(row, column++, bodyStyle), rowData.getPersonFirstName());
			setCellValue(createCellAndFormat(row, column++, bodyStyle), rowData.getPersonPatronymic());
			setCellValue(createCellAndFormat(row, column++, bodyStyle),
					new SimpleDateFormat("dd.MM.yyyy").format(rowData.getPersonBirsday()));
			setCellValue(createCellAndFormat(row, column++, bodyStyle), rowData.getPersonGender());
			setCellValue(createCellAndFormat(row, column++, bodyStyle), rowData.getPersonHomePhone());
			setCellValue(createCellAndFormat(row, column++, bodyStyle), rowData.getPersonWorkPhone());
			setCellValue(createCellAndFormat(row, column++, bodyStyle), rowData.getPersonEmail());
			setCellValue(createCellAndFormat(row, column++, bodyStyle), rowData.getRepresentativeHomePhone());
			setCellValue(createCellAndFormat(row, column++, bodyStyle), rowData.getRepresentativeWorkPhone());
			setCellValue(createCellAndFormat(row, column++, bodyStyle), rowData.getRepresentativeEmail());
			setCellValue(createCellAndFormat(row, column++, bodyStyle), rowData.getInspectorCode());
			setCellValue(createCellAndFormat(row, column++, bodyStyle), rowData.getInspectorFullName());
			setCellValue(createCellAndFormat(row, column++, bodyStyle), rowData.getPersonAddressReg());
			setCellValue(createCellAndFormat(row, column++, bodyStyle), rowData.getPersonAddressPr());
			setCellValue(createCellAndFormat(row, column++, bodyStyle), rowData.getPersonPolisNumber());

		}
		for (int i = 0; i < 22; i++) {
			sheet.autoSizeColumn(i);
		}

	}

	private void createHeader() {
		Object[] columns = new String[] { "№", "код СМО", "код филиала", "филиал СМО", "дата заявл.", "тип заявл.",
				"прич. заявл.", "фамилия", "имя", "отчество", "ДР", "пол", "телефон дом.", "телефон раб.", "email",
				"телефон предст. дом.", "телефон предст. раб.", "email предст.", "код инспектора", "ФИО инспектора",
				"адрес рег.", "адрес пр.", "№ полиса (ЕНП)" };

		CellStyle headerStyle = template.createCellStyle();
		Font headerFont = template.createFont();
		headerFont.setBold(true);
		headerFont.setFontName("Calibri");
		headerStyle.setFont(headerFont);
		headerStyle.setWrapText(true);
		headerStyle.setAlignment(HorizontalAlignment.CENTER);

		HSSFRow row = sheet.createRow(0);
		for (int cn = 0; cn < columns.length; cn++) {
			setCellValue(createCellAndFormat(row, cn, headerStyle), columns[cn]);
		}

	}

	protected HSSFCell createCellAndFormat(HSSFRow row, Integer index, CellStyle style) {
		HSSFCell cell = row.createCell(index);
		cell.setCellStyle(style);

		return cell;
	}

	protected void setCellValue(HSSFCell cell, Object value) {
		if (value != null) {
			cell.setCellValue((String) value);
		} else {
			cell.setCellValue("");
		}
	}

}
