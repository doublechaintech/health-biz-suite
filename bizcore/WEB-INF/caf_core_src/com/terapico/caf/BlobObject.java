package com.terapico.caf;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.codec.Charsets;

public class BlobObject {
	private String fileName;
	private String mimeType;
	private byte[] data;
	private Map<String, String> headers;

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
		String headerName = "Content-Disposition";
		String attachmentHeader = String.format("attachment; filename=\"%s\"", encodeFileName(fileName));
		this.addHeader(headerName, attachmentHeader);
	}
	protected static String encodeAsRFCAttachmentName(final String s) {
	    final byte[] chars = s.getBytes(Charsets.UTF_8);
	    final int len = chars.length;
	    final StringBuilder resultBuffer = new StringBuilder(len << 1);
	    final char[] hexDigits = {'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'};
	    final byte[] attributeCharCandidate = {'!','#','$','&','+','-','.','0','1','2','3','4','5','6','7','8','9',           'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z','^','_','`',                        'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z','|', '~'};
	    for (int i = 0; i < len; ++i) {
	        final byte b = chars[i];
	        if (Arrays.binarySearch(attributeCharCandidate, b) >= 0) {
	        	resultBuffer.append((char) b);
	        	continue;
	        }
	        resultBuffer.append('%');
	        resultBuffer.append(hexDigits[0x0f & (b >>> 4)]);
	        resultBuffer.append(hexDigits[b & 0x0f]);
	        
	    }

	    return resultBuffer.toString();
	}
	protected static boolean isEnglishChar(int ch) {
		
		if(closeRange(ch,'a','z')) {
			return true;
		}
		if(closeRange(ch,'A','Z')) {
			return true;
		}
		if(ch=='.') {
			return true;
		}
		return false;
		
	}
	
	private static boolean closeRange(int ch, char lowerBondary, char upperBondary) {
		if(ch<lowerBondary) {
			return false;
		}
		if(ch>upperBondary) {
			return false;
		}
		return true;
	}
	protected String encodeFileName(String fileName) {
		
		boolean hasCharOtherThanAscii = fileName.chars()
				.filter(ch->!isEnglishChar(ch)).findAny().isPresent();
		if(!hasCharOtherThanAscii) {
			return fileName;
		}
		return encodeAsRFCAttachmentName(fileName);
		
		
		
	}
	
	
	public String getMimeType() {
		return mimeType;
	}

	public void setMimeType(String mimeType) {
		this.mimeType = mimeType;
	}

	public byte[] getData() {
		return data;
	}

	public void setData(byte[] data) {
		this.data = data;
	}

	public Map<String, String> getHeaders() {
		if (headers == null) {
			headers = new HashMap<String, String>();
		}
		return headers;
	}

	public void setHeaders(Map<String, String> headers) {
		this.headers = headers;
	}

	public BlobObject addHeader(String name, String value) {
		getHeaders().put(name, value);
		return this;
	}

}
