/***********************************************************************
/* Project   : VCSMService
/* Developer : 25-00113
/* Date		 : 2019/02/05 19:48:33
/* Copyright © 2019 | AEON Microfinance Co.,Ltd. All Rights Reserved.
/**********************************************************************/
package mm.aeon.com.common;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.codec.binary.Base64;

public class VCSMPasswordEncoder {

	private static final String DEFAULT_ENCODE_CHAR_SET = "Windows-31J";

	private static final String SALT_KEY = "PRESTO PRESTO PRESTO";

	private static String algorithmName = "SHA-1";

	private static String encodeCharSet = DEFAULT_ENCODE_CHAR_SET;

	public static void main(String[] args) {
		System.out.println(encode("vcs-api-client"));
	}

	/**
	 * Constructor.
	 */
	private VCSMPasswordEncoder() {

	}

	/**
	 * Convert into byte code.
	 * 
	 * @param bits
	 * @return
	 */
	private static StringBuffer byteEncode(byte[] bits) {

		StringBuffer out = new StringBuffer();
		for (byte b : bits) {

			String str = Integer.toHexString(b & 0xff);
			if (str.length() == 1) {
				out.append("0");
			}
			out.append(str);
		}
		return out;
	}

	/**
	 * Encode inputed value using SHA-1 algorithm.
	 * 
	 * @param in
	 *            value to encode
	 * @return encoded data
	 */
	public static String encode(String in) {
		String localAlgorithmName = getAlgorithmName();
		try {
			MessageDigest md = MessageDigest.getInstance(localAlgorithmName);
			md.update(in.getBytes(encodeCharSet));

			byte[] outByte = md.digest();
			StringBuffer out = byteEncode(outByte);

			out.append("#");
			out.append(SALT_KEY);

			md.reset();
			md.update(out.toString().getBytes(encodeCharSet));

			outByte = md.digest();

			out = byteEncode(outByte);

			return out.toString();

		} catch (NoSuchAlgorithmException ex) {
			throw new RuntimeException();
		} catch (UnsupportedEncodingException uee) {
			throw new RuntimeException();
		}

	}

	/**
	 * Getter method for algorithmName.
	 * 
	 * @return algorithmName
	 */
	public static String getAlgorithmName() {
		return algorithmName;
	}

	/**
	 * Setter method for algorithmName.
	 * 
	 * @param algorithm
	 *            Algorithm Name
	 */
	public static void setAlgorithmName(String algorithm) {
		algorithmName = algorithm;
	}

	/**
	 * 
	 * @param encodedPassword
	 * @param isDecode
	 * @return
	 */
	public static String decodePassword(String encodedPassword, boolean isDecode) {
		Base64 codec = new Base64();
		byte[] temp = null;
		String plainPassword = null;
		if (isDecode) {
			if (encodedPassword != null) {
				temp = codec.decode(encodedPassword.getBytes());
			}
		} else {
			temp = codec.encode(encodedPassword.getBytes());
		}
		plainPassword = new String(temp);
		return plainPassword;
	}

}
