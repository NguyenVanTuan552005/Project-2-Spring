package com.nvt.web.customexceptions;

public class InvalidDataException extends RuntimeException {
	public InvalidDataException(String message) {
		super(message);
	}
}
