package resouces;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * 
 * @author ericson
 */
public class Fecha {

	public static final String PATTERN_DD_MM_YYYY;
	public static final String PATTERN_YYYY_MM_DD;
	public static final String PATTERN_DDMMYYYYHHMMS;
	public static final int LOCALE_ES;

	static {
		PATTERN_DD_MM_YYYY = "dd'/'MM'/'yyyy";
		PATTERN_YYYY_MM_DD = "yyyy'-'MM'-'dd";
		PATTERN_DDMMYYYYHHMMS = "ddMMyyyyHHMMS";
		LOCALE_ES = 0;
	}

	public String getFecha(Date date, String pattern, int locale) {
		if (date != null) {
			SimpleDateFormat sp = null;
			if (locale == LOCALE_ES) {
				sp = new SimpleDateFormat(pattern, new Locale("es_ES"));
			}
			return sp.format(date);
		} else {
			return "";
		}

	}

	public Locale getLocale(int locale) {
		switch (locale) {
		case 0:
			return new Locale("es_ES");
		}
		return null;
	}

	public static List<Date> getDias(int mes, int year) {
		int nDias;
		switch (mes) {
		case 1:
		case 3:
		case 5:
		case 7:
		case 8:
		case 10:
		case 12:
			nDias = 31;
			break;
		default:
			nDias = 30;
		}
		if ((mes == 2)
				&& (((year % 100 != 0) && (year % 4 == 0)) || (year % 400 == 0))) {
			nDias = 29;
		} else if (mes == 2) {
			nDias = 28;
		}

		List<Date> dateList = new ArrayList<Date>();
		for (int i = 0; i < nDias; i++) {
			@SuppressWarnings("deprecation")
			Date date = new Date(year, mes - 1, i + 1);
			dateList.add(date);
		}

		return dateList;
	}
}