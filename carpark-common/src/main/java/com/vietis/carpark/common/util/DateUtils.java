package com.vietis.carpark.common.util;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {

	public static Date getCurrentTime() {
		return new Date();
	}

	/**
	 * Now : truncated hour
	 *
	 * @return Timestamp
	 */
	public static Timestamp before(long days) {
		return Timestamp.valueOf(LocalDateTime.now().minusDays(days)
				.truncatedTo(ChronoUnit.HOURS));
	}

	public static Date beginOfDay(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);

		return cal.getTime();
	}

	public static Date endOfDay(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.HOUR_OF_DAY, 23);
		cal.set(Calendar.MINUTE, 59);
		cal.set(Calendar.SECOND, 59);
		cal.set(Calendar.MILLISECOND, 999);
		return cal.getTime();
	}
	
	


	
	
}
