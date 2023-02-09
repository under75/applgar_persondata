package ru.tfoms.applgar.util;

import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
import static ru.tfoms.applgar.util.Constants.*;

public class DateValidator {

	public static boolean isValid(String dateStr) {
		try {
			DATE_TIME_FORMATTER.withResolverStyle(ResolverStyle.STRICT).parse(dateStr);
		} catch (DateTimeParseException e) {
			return false;
		}
		return true;
	}
}