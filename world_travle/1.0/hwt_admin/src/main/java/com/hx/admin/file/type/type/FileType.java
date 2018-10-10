package com.hx.admin.file.type.type;

public enum FileType  {
	/**
	 * JEPG.
	 * FFD8FF
	 */
	JPG("FFD8FF"),

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
	BMP("424D"),
	
	/**
	 * AAC
	 */
	AAC("AAC"),
	
	/**
	 * mp4
	 */
	MP4("0000001C667479706D703432000000016D7034316D70343269736F6D"),
	/***
	 * 
	 * 

	 目前不考虑的文件说明:::::::
	///////////////////////////////////////////////////////////

	/**
	 * CAD.
	 * 41433130
	 */
	//DWG("41433130"),

	/**
	 * Adobe Photoshop.
	 */
	//PSD("38425053"),

	/**
	 * Rich Text Format.
	 */
	//RTF("7B5C727466"),

	/**
	 * XML.
	 */
	//XML("3C3F786D6C"),

	/**
	 * HTML.
	 * 68746D6C3E
	 */
	//HTML("68746D6C3E"),

	/**
	 * Email [thorough only].
	 * 44656C69766572792D646174653A
	 */
	//EML("44656C69766572792D646174653A"),

	/**
	 * Outlook Express.
	 * CFAD12FEC5FD746F
	 */
	//DBX("CFAD12FEC5FD746F"),

	/**
	 * Outlook (pst).
	 * 2142444E
	 */
	//PST("2142444E"),

	/**
	 * MS Word/Excel.
	 * D0CF11E0
	 */
	//XLS_DOC("D0CF11E0"),

	/**
	 * MS Access.
	 * 5374616E64617264204A
	 */
	//MDB("5374616E64617264204A"),

	/**
	 * WordPerfect.
	 * FF575043
	 */
	//WPD("FF575043"),

	/**
	 * Postscript.
	 * 252150532D41646F6265
	 */
	//EPS("252150532D41646F6265"),

	/**
	 * Adobe Acrobat.
	 * 255044462D312E
	 */
	//PDF("255044462D312E"),

	/**
	 * Quicken.
	 * AC9EBD8F
	 */
	//QDF("AC9EBD8F"),

	/**
	 * Windows Password.
	 * E3828596
	 */
	//PWL("E3828596"),

	/**
	 * ZIP Archive.
	 * 504B0304
	 */
	//ZIP("504B0304"),

	/**
	 * RAR Archive.
	 * 52617221
	 */
	RAR("52617221"),

	/**
	 * 0000001866747970336770340000000069736f6d3367703400000001
	 * 49443303000000001059544954320000000B000001FFFE056E735E03
	 * RAR Archive.
	 * 52617221
	 */
	MP3("FFF96CA001C0002066000198000EFFF96CA001C0002066000198000E"),

	/**
	 * Wave.
	 * 57415645
	 */
	WAV("57415645"),

	/**
	 * AVI.
	 * 41564920
	 */
	AVI("41564920"),

	/**
	 * Real Audio.
	 * 2E7261FD
	 */
	RAM("2E7261FD"),

	/**
	 * Real Media.
	 * 2E524D46
	 */
	RM("2E524D46"),

	/**
	 * MPEG (mpg).
	 * 000001BA
	 */
	MPG("000001BA"),

	/**
	 * Quicktime.
	 * 6D6F6F76
	 */
	MOV("6D6F6F76"),

	/**
	 * Windows Media.
	 * 3026B2758E66CF11
	 */
	ASF("3026B2758E66CF11"),

	/**
	 * MIDI.
	 * 4D546864
	 */
	MID("4D546864");
	
	


	private String value = "";

	/**
	 * Constructor.
	 * 
	 * @param value
	 */
	private FileType(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
}
