package ru.tfoms.applgar.util;

import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;

public class DateValidator {

	public static boolean isValid(String dateStr) {
		try {
			DateTimeFormatter.ofPattern("uuuu-MM-dd").withResolverStyle(ResolverStyle.STRICT).parse(dateStr);
		} catch (DateTimeParseException e) {
			return false;
		}
		return true;
	}
}