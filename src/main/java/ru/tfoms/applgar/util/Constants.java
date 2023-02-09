package ru.tfoms.applgar.util;

import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;

public class Constants {
	public static final String HSMO_ROLE = "hsmo_role";
	public static final String SMO_ROLE = "smo_role";
	public static final String TFOMS_ROLE = "tfoms_applgar";
	
	public final static Integer SMO_ADD_CODE = 64000;
	public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
	public static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("uuuu-MM-dd");
	
}
