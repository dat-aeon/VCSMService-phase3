package mm.aeon.com.common;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class AESFactory {

	public static String encrypt(String clearText) {
		final String secretKey = "B777ER200A350MM2";
		byte[] encryptedText = null;
		try {
			byte[] keyData = secretKey.getBytes();
			SecretKey ks = new SecretKeySpec(keyData, "AES");
			Cipher c = Cipher.getInstance("AES");
			c.init(Cipher.ENCRYPT_MODE, ks);
			encryptedText = c.doFinal(clearText.getBytes("UTF-8"));
			return Base64.encodeBase64String(encryptedText);
		} catch (Exception e) {
			return null;
		}
	}

	public static String decrypt(String encryptedText) {
		final String secretKey = "B777ER200A350MM2";
		byte[] clearText = null;
		try {
			byte[] keyData = secretKey.getBytes();
			SecretKey ks = new SecretKeySpec(keyData, "AES");
			Cipher c = Cipher.getInstance("AES");
			c.init(Cipher.DECRYPT_MODE, ks);
			clearText = c.doFinal(Base64.decodeBase64(encryptedText));
			return new String(clearText, "UTF-8");
		} catch (Exception e) {
			return null;
		}
	}

	public static void main(String[] args) {
		QRScanObj qRScanObj = new QRScanObj();
		qRScanObj.setAgreementNo("");
		qRScanObj.setCustomerId(123);
		qRScanObj.setDaApplicationInfoId(11);
		Gson gson = new GsonBuilder().create();
		String jsonString = gson.toJson(qRScanObj);
		System.out.println(AESFactory.encrypt(jsonString));
	}
}
