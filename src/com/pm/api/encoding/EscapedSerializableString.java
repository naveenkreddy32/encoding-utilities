package com.pm.api.encoding;

import org.codehaus.jackson.SerializableString;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * 
 * @author Naveen K Reddy Nagireddy
 *
 */

public class EscapedSerializableString  implements SerializableString {

	private final String stringValue;
	private String encodedStr;
	
	private static final String LOGGER_NAME = "com.pm.api.encoding.encoding-utilities";
	private static final Logger LOGGER = LogManager.getLogger(LOGGER_NAME);
	
	public EscapedSerializableString(int i) {
		stringValue = Character.toString((char) i);
	}
	
	@Override
	public String getValue() {
		try {
			encodedStr = URLEncoder.encode(stringValue, "utf-8");
		}
		catch (UnsupportedEncodingException e) {
			
			LOGGER.error(e);
		}
		
		return encodedStr;
	}
	
	@Override
	public char[] asQuotedChars() {
		return new char[0];
	}

	@Override
	public byte[] asQuotedUTF8() {
		return new byte[0];
	}

	@Override
	public byte[] asUnquotedUTF8() {
		return new byte[0];
	}

	@Override
	public int charLength() {
		return stringValue.length();
	}

}
