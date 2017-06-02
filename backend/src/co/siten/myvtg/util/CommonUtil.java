package co.siten.myvtg.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Hashtable;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.web.multipart.MultipartFile;
import org.w3c.dom.NodeList;

/**
 * 
 * @author thomc
 *
 */

public class CommonUtil {

	public static boolean isRealNumber(String value) {
		if (value == null)
			return false;
		try {
			new BigDecimal(value.replace(".", "").replace(",", ""));
			return true;
		} catch (NumberFormatException e) {
			return false;
		} catch (Exception e) {
			return false;
		}
	}

	public static void main(String[] args) {
		String a = "01/01/2019 01:12:26 PM";

		SimpleDateFormat dt = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss aa", Locale.ENGLISH);
		try {
			Date xa = dt.parse(a);
			System.out.println(dt.format(new Date()));

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static String getFileExtension(String fileName) {
		return "";
	}

	public static List<Integer> getListIntegerFromString(String str, String splitStr) {
		List<String> parentIdsStrList = Arrays.asList(str.split(splitStr));
		List<Integer> parentIdsIntList = new Vector<>();
		for (String id : parentIdsStrList) {
			if (CommonUtil.isInteger(id))
				parentIdsIntList.add(Integer.parseInt(id));
		}
		return parentIdsIntList;
	}

	public static boolean isInteger(String s) {
		try {
			Integer.parseInt(s);
		} catch (NumberFormatException e) {
			return false;
		} catch (NullPointerException e) {
			return false;
		}
		// only got here if we didn't return false
		return true;
	}

	public static boolean isEmail(String strEmail) {
		Pattern pattern = Pattern.compile("(\\w+@[a-zA-Z_]+?\\.[a-zA-Z]{2,6})");
		Matcher matcher = pattern.matcher(strEmail.trim());
		if (matcher.find()) {
			return true;
		}
		return false;
	}

	public static boolean isEmpty(Hashtable<?, ?> htInput) {
		if (htInput == null || htInput.size() == 0) {
			return true;
		}

		return false;
	}

	public static boolean isEmpty(Vector<?> vctInput) {
		if (vctInput == null || vctInput.size() == 0) {
			return true;
		}

		return false;
	}

	public static boolean isEmpty(String strInput) {
		if (strInput == null || strInput.trim().equalsIgnoreCase("")) {
			return true;
		}
		return false;
	}

	public static boolean isEmpty(List<?> vctInput) {
		try {
			if (vctInput == null || vctInput.size() == 0) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return true;
		}
		return false;
	}

	public static boolean isEmpty(Map<?, ?> map) {
		if (map == null || map.size() == 0) {
			return true;
		}

		return false;
	}

	public static boolean isEmpty(NodeList nodeList) {
		if (nodeList == null || nodeList.getLength() == 0) {
			return true;
		}

		return false;
	}

	public static String capitalizFirstLetter(String strSource) {
		if (CommonUtil.isEmpty(strSource)) {
			return "";
		}

		if (strSource.length() == 1) {
			return strSource.toUpperCase();
		}
		String top = strSource.substring(0, 1).toUpperCase();
		String allow = strSource.substring(1);
		return top + allow;
	}

	public static InputStream getFile(String filePath) {
		try {
			InputStream in = CommonUtil.class.getResourceAsStream(filePath);
			return in;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static String firstLetterToLowerCase(String str) {
		String firstLetter = str.substring(0, 1).toLowerCase();
		return firstLetter + str.substring(1, str.length());
	}

	public static String firstLetterToUppperCase(String str) {
		String firstLetter = str.substring(0, 1).toUpperCase();
		return firstLetter + str.substring(1, str.length());
	}

	public static <T extends Object> T getEntityByAttribute(String attribute, Object val, List<T> objList) {
		if (objList == null || val == null || attribute == null) {
			return null;
		}
		Method getter = null;
		try {
			// for (T obj : objList) {
			// getter = obj.getClass().getMethod(
			// "get" + firstLetterToUppperCase(attribute));
			// Object ma = getter.invoke(obj);
			// if (ma.equals(val)) {
			// return obj;
			// }
			// }

			for (T obj : objList) {
				String[] attrs = attribute.split("\\.");
				Object ma = new Object();
				Object loopObj = obj;
				for (String atr : attrs) {
					getter = loopObj.getClass().getMethod("get" + firstLetterToUppperCase(atr));
					ma = getter.invoke(loopObj);
					loopObj = ma;
				}
				if (ma.equals(val)) {
					return obj;
				}
			}
		} catch (Exception ex) {
			System.err.println("CommonUtil.getEntityByAttribute: " + ex.getMessage());
			return null;
		}
		return null;
	}

	public static <T extends Object> List<T> getEntitiesByAttribute(String attribute, Object val, List<T> objList) {
		List<T> rs = new ArrayList<T>();
		if (objList == null || val == null || attribute == null) {
			return null;
		}
		Method getter = null;
		try {
			for (T obj : objList) {
				String[] attrs = attribute.split("\\.");
				Object ma = new Object();
				Object loopObj = obj;
				for (String atr : attrs) {
					getter = loopObj.getClass().getMethod("get" + firstLetterToUppperCase(atr));
					ma = getter.invoke(loopObj);
					loopObj = ma;
				}
				if (ma.equals(val)) {
					rs.add(obj);
				}
			}
			return rs;
		} catch (Exception ex) {
			System.err.println("CommonUtil.getEntityByAttribute: " + ex.getMessage());
			return null;
		}
	}

	public static <T> List<T> copyList(List<T> source) {
		if (source == null) {
			return null;
		}
		List<T> rs = new ArrayList<T>();
		for (T t : source) {
			rs.add(t);
		}
		return rs;
	}

	public static void runGarbageCollector() {
		Runtime r = Runtime.getRuntime();
		r.runFinalization();
		r.gc();
	}

	public static void copyFileUsingStream(File source, File dest) throws Exception {
		InputStream is = null;
		OutputStream os = null;
		try {
			is = new FileInputStream(source);
			os = new FileOutputStream(dest);
			byte[] buffer = new byte[1024];
			int length;
			while ((length = is.read(buffer)) > 0) {
				os.write(buffer, 0, length);
			}
		} catch (IOException ex) {
			ex.printStackTrace();
			throw new IOException(ex);
		} finally {
			try {
				if (is != null) {
					is.close();
				}
				if (os != null) {
					os.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
				throw new Exception(e);
			}
		}
	}

	public static File multipartToFile(MultipartFile multipart) throws IllegalStateException, IOException {
		if (multipart == null)
			return null;
		try {
			File convFile = new File(multipart.getOriginalFilename());
			convFile.createNewFile();
			FileOutputStream fos = new FileOutputStream(convFile);
			fos.write(multipart.getBytes());
			fos.close();
			return convFile;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static int subtractDays(Date date1, Date date2) {
		GregorianCalendar gc1 = new GregorianCalendar();
		gc1.setTime(date1);
		GregorianCalendar gc2 = new GregorianCalendar();
		gc2.setTime(date2);

		int days1 = 0;
		int days2 = 0;
		int maxYear = Math.max(gc1.get(Calendar.YEAR), gc2.get(Calendar.YEAR));

		GregorianCalendar gctmp = (GregorianCalendar) gc1.clone();
		for (int f = gctmp.get(Calendar.YEAR); f < maxYear; f++) {
			days1 += gctmp.getActualMaximum(Calendar.DAY_OF_YEAR);
			gctmp.add(Calendar.YEAR, 1);
		}

		gctmp = (GregorianCalendar) gc2.clone();
		for (int f = gctmp.get(Calendar.YEAR); f < maxYear; f++) {
			days2 += gctmp.getActualMaximum(Calendar.DAY_OF_YEAR);
			gctmp.add(Calendar.YEAR, 1);
		}

		days1 += gc1.get(Calendar.DAY_OF_YEAR) - 1;
		days2 += gc2.get(Calendar.DAY_OF_YEAR) - 1;

		return (days1 - days2);
	}

	public static Date getFirstDateOfCurrent() {
		Calendar c = Calendar.getInstance(); // this takes current date
		c.set(Calendar.DAY_OF_MONTH, 1);
		return c.getTime();
	}

	public static Date getFirstDateOfPreviousMonth() {
		Calendar aCalendar = Calendar.getInstance();
		// add -1 month to current month
		aCalendar.add(Calendar.MONTH, -1);
		// set DATE to 1, so first date of previous month
		aCalendar.set(Calendar.DATE, 1);
		Date firstDateOfPreviousMonth = aCalendar.getTime();
		return firstDateOfPreviousMonth;
	}

	public static Date GetCurrentTime(){
		return new Date();
	}
}
