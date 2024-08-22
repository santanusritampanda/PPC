package com.santanu.ppc.exception;

public class InvalidFormatException extends RuntimeException {
    private static final long serialVersionUID = 7132423639119278473L;

	public InvalidFormatException(String message) {
        super(message);
    }
}
