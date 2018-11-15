package com.ctc.shorturl;

import java.security.MessageDigest;

public class MD5 {

	public static String MD5Encode(String sourceString) {
		String resultString = null;
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			resultString = byte2hexString(md.digest(sourceString.getBytes()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultString;
	}

	public static final String byte2hexString(byte[] bytes) {
		StringBuffer bf = new StringBuffer(bytes.length * 2);
		for (int i = 0; i < bytes.length; i++) {
			if ((bytes[i] & 0xff) < 0x10) {
				bf.append("0");
			}
			bf.append(Long.toString(bytes[i] & 0xff, 16));
		}
		return bf.toString();
	}
}
