package com.kelvem.sample.system;

public class SystemsException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4462369668777201406L;

	public SystemsException(){
		super();
	}

    public SystemsException(String message) {
        super(message);
    }

    public SystemsException(String message, Throwable cause) {
        super(message, cause);
    }

    public SystemsException(Throwable cause) {
        super(cause);
    }
}
