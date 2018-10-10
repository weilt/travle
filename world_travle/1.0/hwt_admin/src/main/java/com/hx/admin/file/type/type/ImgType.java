package com.hx.admin.file.type.type;

public enum ImgType {
	/**
	 * JEPG.
	 * FFD8FF
	 */
	JPEG("FFD8FF"),

	/**
	 * PNG.
	 * 89504E47
	 */
	PNG("89504E47"),

	/**
	 * GIF.
	 * 47494638
	 */
	GIF("47494638"),

	/**
	 * TIFF.
	 * 49492A00
	 */
	TIFF("49492A00"),

	/**
	 * Windows Bitmap.
	 * 424D
	 */
	BMP("424D");
	
	


	private String value = "";

	/**
	 * Constructor.
	 * 
	 * @param value
	 */
	private ImgType(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
}
