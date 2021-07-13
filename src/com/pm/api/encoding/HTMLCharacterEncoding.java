package com.pm.api.encoding;

import java.io.IOException;

import org.codehaus.jackson.SerializableString;
import org.codehaus.jackson.io.CharacterEscapes;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;

public class HTMLCharacterEncoding extends CharacterEscapes {

	private final int[] asciiEscapes;

	public HTMLCharacterEncoding() {
		int[] esc = CharacterEscapes.standardAsciiEscapesForJSON();
		asciiEscapes = esc;
	}
	
	public HTMLCharacterEncoding(char[] ch) {
		int[] esc = CharacterEscapes.standardAsciiEscapesForJSON();
		
		for(int i=0;i<ch.length;i++) {
			if(!(Character.isAlphabetic(ch[i]) || Character.isDigit(ch[i]))) {
				esc[ch[i]] = CharacterEscapes.ESCAPE_CUSTOM;
			}
		}
		
		asciiEscapes = esc;
	}
	
	@Override
	public int[] getEscapeCodesForAscii() {
		return asciiEscapes.clone();
	}

	@Override
	public SerializableString getEscapeSequence(int ch) {
		return new EscapedSerializableString(ch);
	}
	
	public ObjectMapper getEscapingMapper(char[] ch) {
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(SerializationConfig.Feature.FAIL_ON_EMPTY_BEANS, false);
		mapper.getJsonFactory().setCharacterEscapes(new HTMLCharacterEncoding(ch));
		return mapper;
	}
	
	public Object serializeWithEscapes(Object obj, String str) throws IOException {
		return getEscapingMapper(str.toCharArray()).writeValueAsString(obj);
	}

}
