package mx.inntecsa.smem.util;

import java.io.Serializable;

public class FileAux implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3837135379840734113L;
	private String Name;
	private String mime;
	private long length;
	private byte[] data;
	private String contentType;

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public String getMime() {
		return mime;
	}

	public void setMime(String mime) {
		this.mime = mime;
	}

	public long getLength() {
		return length;
	}

	public void setLength(long length) {
		this.length = length;
	}

	public byte[] getData() {
		return data;
	}

	public void setData(byte[] data) {
		this.data = data;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}
	
}
