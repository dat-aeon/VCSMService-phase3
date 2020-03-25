package mm.aeon.com.common;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class CommonUtil {

	/**
	 * 
	 * @param password
	 * @return
	 */
	public static String checkPasswordStrength(String password) {

		String status = CommonConstants.PASSWORD_WEAK;

		int digit = 0;
		int character = 0;
		int specialChar = 0;

		// classify alphanumeric and character counts.
		for (int i = 0; i < password.length(); i++) {
			char c = password.charAt(i);
			if (Character.isLetter(c)) {
				character++;
			} else if (Character.isDigit(c)) {
				digit++;
			} else {
				specialChar++;
			}
		}
		if (digit > 0 && character > 0 && specialChar > 0) {
			return CommonConstants.PASSWORD_STRONG;
		}
		return status;
	}

	public static Date getCurrentTime() {
		return Calendar.getInstance().getTime();
	}

	/**
	 * Convert date into custom pattern String.
	 */
	public static String formatByPattern(Date input, String pattern) {

		// Empty value check.
		if (input == null) {
			return null;
		}

		if (pattern == null || "".equals(pattern)) {
			return null;
		}

		// Convert process.
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);

		return sdf.format(input);
	}

	/**
	 * Get the current year, month and date in string form.
	 */
	public static String getCurrentDateInString() {
		DateFormat df = new SimpleDateFormat("yyyyMMdd");
		String currentDate = df.format(Calendar.getInstance().getTime());
		return currentDate;
	}

	/**
	 * Get DateTime as String.
	 */
	public static String getDateTimeString(Date date) {
		DateFormat df = new SimpleDateFormat("HH:mm");
		String currentDate = df.format(date);
		return currentDate;
	}

	/**
	 * Get the last date of last month.
	 */
	public static Date getLastMonthEndDate() {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MONTH, -1);
		cal.set(Calendar.DATE, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
		return cal.getTime();
	}

	public static Date getFirstDayOfMonth(Date criDate) {
		Date firstDate = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(criDate);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		cal.set(Calendar.DAY_OF_MONTH, cal.getActualMinimum(Calendar.DAY_OF_MONTH));
		firstDate = cal.getTime();

		return firstDate;
	}

	public static Date getCurrentMonthLastDate(Date updateDate) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(updateDate);
		cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
		return cal.getTime();
	}

	public static Date getNextMonthFirstDate() {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MONTH, 1);
		cal.set(Calendar.DATE, cal.getActualMinimum(Calendar.DAY_OF_MONTH));
		return cal.getTime();
	}

	public static Date getPreviousMonthLastDayOfInputDate(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.MONTH, -1);
		cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
		return cal.getTime();
	}

	public static String getURLEncodeFileName(String reportFileName) {
		String escapedFilename = null;
		StringBuilder fileName = null;
		try {
			// Encoding
			escapedFilename = URLEncoder.encode(reportFileName, "UTF-8").replaceAll("\\+", "%20");
			fileName = new StringBuilder(escapedFilename);
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
		return fileName.toString();
	}

	public static String getSaltString() {
		String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
		StringBuilder salt = new StringBuilder();
		Random rnd = new Random();
		while (salt.length() < 12) { // length of the random string.
			int index = (int) (rnd.nextFloat() * SALTCHARS.length());
			salt.append(SALTCHARS.charAt(index));
		}
		String saltStr = salt.toString();
		return saltStr;

	}

	private static byte[] fixSecret(String s, int length) throws UnsupportedEncodingException {
		if (s.length() < length) {
			int missingLength = length - s.length();
			for (int i = 0; i < missingLength; i++) {
				s += " ";
			}
		}
		return s.substring(0, length).getBytes("UTF-8");
	}

	public static boolean isPureAscii(String v) {
		return Charset.forName("US-ASCII").newEncoder().canEncode(v);
		// or "ISO-8859-1" for ISO Latin 1
		// or StandardCharsets.US_ASCII with JDK1.7+
	}

	public static String modifyInvalidPhone(String phoneNo) {
		String modPhoneNo = phoneNo;
		String phoneStartChar = modPhoneNo.substring(0, 1);
		if (phoneStartChar.equals("9")) {
			modPhoneNo = CommonConstants.ZERO + modPhoneNo;
			return modPhoneNo;
		}
		return modPhoneNo;
	}

}