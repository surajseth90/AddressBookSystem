package com.bridgelabz.exceptionaddressbooksystem;

public class AddressBookException extends Exception {
	public enum AddressBookExceptionType {
		READ_DATA_EXCEPTION, UPDATION_EXCEPTION, DATABASE_EXCEPTION, ADD_CONTACT_EXCEPTION;
	}

	public AddressBookExceptionType type;

	public AddressBookException() {

	}

	public AddressBookException(AddressBookExceptionType type, String message) {
		this.type = type;
	}

}
