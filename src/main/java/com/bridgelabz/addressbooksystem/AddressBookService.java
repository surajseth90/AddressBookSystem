package com.bridgelabz.addressbooksystem;

import java.util.List;

import com.bridgelabz.dbaddressbook.AddressBookDBService;
import com.bridgelabz.exceptionaddressbooksystem.AddressBookException;

public class AddressBookService {

	List<AddressBook> addressBookList;
	private static AddressBookDBService addressBookDBService;

	public AddressBookService() {
		addressBookDBService = AddressBookDBService.getInstance();
	}

	public List<AddressBook> readAddressBookData() throws AddressBookException {

		return this.addressBookList = addressBookDBService.readData();
	}

}
