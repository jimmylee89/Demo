package com.jimmylee.cm.common.util;

import java.security.MessageDigest;

import javax.crypto.KeyGenerator;
import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 * �����������
 * 
 */
public abstract class Coder {
	public static final String KEY_SHA = "SHA";
	public static final String KEY_MD5 = "MD5";

	/**
	 * MAC�㷨��ѡ���¶����㷨
	 * 
	 * <pre>
	 * HmacMD5 
	 * HmacSHA1 
	 * HmacSHA256 
	 * HmacSHA384 
	 * HmacSHA512
	 * </pre>
	 */
	public static final String KEY_MAC = "HmacMD5";

	/**
	 * BASE64����
	 * 
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public static byte[] decryptBASE64(String key) throws Exception {
		return (new BASE64Decoder()).decodeBuffer(key);
	}

	/**
	 * BASE64����
	 * 
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public static String encryptBASE64(byte[] key) throws Exception {
		return (new BASE64Encoder()).encodeBuffer(key);
	}

	/**
	 * MD5����
	 * 
	 * @param data
	 * @return
	 * @throws Exception
	 */
	public static byte[] encryptMD5(byte[] data) throws Exception {

		MessageDigest md5 = MessageDigest.getInstance(KEY_MD5);
		md5.update(data);

		return md5.digest();

	}

	/**
	 * SHA����
	 * 
	 * @param data
	 * @return
	 * @throws Exception
	 */
	public static byte[] encryptSHA(byte[] data) throws Exception {

		MessageDigest sha = MessageDigest.getInstance(KEY_SHA);
		sha.update(data);

		return sha.digest();

	}

	/**
	 * ��ʼ��HMAC��Կ
	 * 
	 * @return
	 * @throws Exception
	 */
	public static String initMacKey() throws Exception {
		KeyGenerator keyGenerator = KeyGenerator.getInstance(KEY_MAC);

		SecretKey secretKey = keyGenerator.generateKey();
		return encryptBASE64(secretKey.getEncoded());
	}

	/**
	 * HMAC����
	 * 
	 * @param data
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public static byte[] encryptHMAC(byte[] data, String key) throws Exception {
		SecretKey secretKey = new SecretKeySpec(decryptBASE64(key), KEY_MAC);
		Mac mac = Mac.getInstance(secretKey.getAlgorithm());
		mac.init(secretKey);

		return mac.doFinal(data);
	}

	/*public static void main(String[] args) {
		try {

			String inputStr = "ab";
			System.err.println("ԭ��:\n" + inputStr);

			byte[] inputData = inputStr.getBytes();
			String code = Coder.encryptBASE64(inputData);

			System.err.println("BASE64���ܺ�:\n" + code);

			byte[] output = Coder.decryptBASE64(code);

			String outputStr = new String(output);

			System.err.println("BASE64���ܺ�:\n" + outputStr);

			String key = Coder.initMacKey();
			System.err.println("Mac��Կ:\n" + key);

			BigInteger md5 = new BigInteger(Coder.encryptMD5(inputData));
			System.err.println("MD5:\n" + md5.toString(16));

			BigInteger sha = new BigInteger(Coder.encryptSHA(inputData));
			System.err.println("SHA:\n" + sha.toString(32));

			BigInteger mac = new BigInteger(Coder.encryptHMAC(inputData,
					inputStr));
			System.err.println("HMAC:\n" + mac.toString(16));

		} catch (Exception e) {
			e.printStackTrace();
		}
	}*/
}
