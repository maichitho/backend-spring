package co.siten.myvtg.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.springframework.core.env.Environment;

public class AppUtil {
	public static SimpleDateFormat getDateFormat() {
		return new SimpleDateFormat("mm/dd/yyyy hh:mm:ss a", Locale.ENGLISH);
	}

	public static Date getDateFromString(String date) {
		try {
			return AppUtil.getDateFormat().parse(date);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}
}
