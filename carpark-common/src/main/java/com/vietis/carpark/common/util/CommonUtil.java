package com.vietis.carpark.common.util;

import java.io.StringReader;
import java.io.StringWriter;
import java.math.BigDecimal;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.TimeZone;
import java.util.Vector;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.commons.codec.digest.DigestUtils;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 
 * @author thomc
 *
 */

public class CommonUtil {
	static char digits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };

	public static Document convertStringToXmlDocument(String xmlStr) throws Exception {
		DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
		Document doc = builder.parse(new InputSource(new StringReader(xmlStr)));
		return doc;
	}

	public static <T> Vector<T> arrayToVector(T[] array) {
		if (array.length == 0) {
			return null;
		}
		Vector<T> result = new Vector<T>();

		for (int i = 0; i < array.length; i++) {
			result.addElement(array[i]);
		}
		return result;
	}

	public static List<Integer> arrayStrToIntVector(String str, String split) {
		if (CommonUtil.isEmpty(str)) {
			return new LinkedList<>();
		}
		LinkedList<Integer> result = new LinkedList<>();
		String[] resultStr = str.split(split);
		for (int i = 0; i < resultStr.length; i++) {
			try {
				result.add(Integer.parseInt(resultStr[i]));
			} catch (Exception e) {

			}

		}
		return result;
	}

	public static String convertXmlDocumentToString(Document xmlDoc) throws Exception {
		TransformerFactory tf = TransformerFactory.newInstance();
		Transformer transformer;
		try {
			transformer = tf.newTransformer();
			StringWriter writer = new StringWriter();
			transformer.transform(new DOMSource(xmlDoc), new StreamResult(writer));
			return writer.getBuffer().toString();
		} catch (Exception e) {
			throw e;
		}
	}

	public static char randomDecimalDigit() {
		return digits[(int) Math.floor(Math.random() * 10)];
	}

	public static String randomDecimalString(int ndigits) {
		StringBuilder result = new StringBuilder();
		for (int i = 0; i < ndigits; i++) {
			result.append(randomDecimalDigit());
		}
		return result.toString();
	}

	public static String encodeHex(byte[] digest) throws NoSuchAlgorithmException {
		MessageDigest md = MessageDigest.getInstance("SHA-256");
		byte[] mdbytes = md.digest(digest);
		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < mdbytes.length; i++) {
			sb.append(Integer.toString((mdbytes[i] & 0xff) + 0x100, 16).substring(1));
		}
		return sb.toString();
	}

	public static String encryptPassword(String plainPassword, String salt, int loopNumber) {
		String result = "";
		for (int i = 0; i < loopNumber; i++) {
			result = DigestUtils.sha256Hex(plainPassword + salt + result);
		}
		return result;
	}

	public static String convertObjectToJsonString(Object o) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			String r = mapper.writeValueAsString(o);
			return r;
		} catch (Exception e) {
			return o.toString();
		}

	}

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

	public static Date getCurrentTime() {
		Date date = new Date();
		return new Date(date.getTime() - Calendar.getInstance().getTimeZone().getOffset(date.getTime()));
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

	public static Date dateFromUTC(Date date, String idTimezone) {
		return new Date(date.getTime() + TimeZone.getTimeZone(idTimezone).getOffset(date.getTime()));
	}

	public static Date dateToUTC(Date date, String idTimezone) {
		return new Date(date.getTime() - TimeZone.getTimeZone(idTimezone).getOffset(date.getTime()));
	}

	public static boolean sendEMail(final String username, final String password, String from, String to, String host,
			String port, String subject, String body, String isAuthen, String isSsl) throws Exception {
		Properties props = new Properties();
		props.put("mail.smtp.starttls.enable", isSsl);
		props.put("mail.smtp.auth", isAuthen);
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.port", port);

		Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});

		try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(from));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
			message.setSubject(MimeUtility.encodeText(subject, "utf-8", "B"));
			message.setContent(body, "text/html;charset=UTF-8");

			Transport.send(message);

			System.out.println("Sent email Done");
			return true;
		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}

}
