package ua.nure.nechaev.summarytask.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Utility class for hashing passwords
 * 
 * @author Maks
 *
 */
public class HashUtil {
	private static final String ALGORITHM = "SHA-256";

	/**
	 * Transform given string to hashed string using algorithm sha256
	 * 
	 * @param origin - string to hash
	 * @return hashed string or null if given string was null too
	 * @throws NoSuchAlgorithmException
	 */
	public static String hash(String origin) throws NoSuchAlgorithmException {
		if(origin == null) {
			return null;
		}
		MessageDigest md;
		md = MessageDigest.getInstance(ALGORITHM);
		md.update(origin.getBytes());
		return hashToString(md.digest());
	}

	private static String hashToString(byte[] hash) {
		StringBuffer hexString = new StringBuffer();
		for (int i = 0; i < hash.length; i++) {
			String hex = Integer.toHexString(0xff & hash[i]);
			if (hex.length() == 1)
				hexString.append('0');
			hexString.append(hex);
		}
		return hexString.toString();
	}

}
