package com.ntt.util;

public enum ErrorMessages {
	
	BALANCE_NOT_AVAILABLE("Saldo no disponible"),
	DAILY_QUOTA_EXCEEDED("Cupo diario excedido");
	
	private String errorMessage;

	ErrorMessages(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	
	/** 
	 * @return the errorMessage
	 */
	public String getErrorMessage() {
		return errorMessage;
	}
	/** 
	 * @param errorMessage the errorMessage to set
	 */
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	
	
	
	
	

}
