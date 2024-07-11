package com.augustczar.healthforall.enums;

public enum TipoDocumentos {

	CPF(0),
	RG(1),
	DRIVING_LICENSE(2);
	
	private int code;
	
	private TipoDocumentos(int code) {
		this.code = code;
	}
	
	public int getCode() {
		return code;
	}
	
	public static TipoDocumentos valueOf(int code) {
		for(TipoDocumentos value : TipoDocumentos.values()) {
			if(code == value.getCode()) {
				return value;
			}
		}
		throw new IllegalArgumentException("Invalid DocumentTypes code");
	}
}
