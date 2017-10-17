package com.juslin.sqlinjectiondemo.security;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.xml.bind.DatatypeConverter;


public class Crypter {

	public static final String MD5 = "MD5";
	public static final String SHA1 = "SHA-1";
	public static final String SHA256 = "SHA-256";
	public static final String SHA384 = "SHA-384";
	public static final String SHA512 = "SHA-512";


	public static String salaa(String toBeCrypted, String salt,
			String algorithm, int howManyTimes)
			throws NoSuchAlgorithmException, UnsupportedEncodingException {

		MessageDigest md = MessageDigest.getInstance(algorithm);
		md.reset();
		md.update(salt.getBytes("UTF-8"));
		byte[] input = md.digest(toBeCrypted.getBytes("UTF-8"));
		for (int i = 0; i < howManyTimes; i++) {
			md.reset();
			input = md.digest(input);
		}

		String encyptedText = DatatypeConverter.printBase64Binary(input);
		return encyptedText;

	}
	

	public static String generateSalt() throws NoSuchAlgorithmException {
		SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
		byte[] saltAsBinary = new byte[8];
		random.nextBytes(saltAsBinary);
		String saltString = DatatypeConverter.printBase64Binary(saltAsBinary);

		return saltString;
	}
}
