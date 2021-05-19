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

	public void updateAddressBookUsingPrepareStatement(String address, String state, String city, int zip, String phone,
			String email, String first_name) throws AddressBookException {
		int result = addressBookDBService.updateAddressBookUsingPreparedStatement(address, state, city, zip, phone,
				email, first_name);
		if (result == 0)
			return;

		AddressBook addressBook = this.getAddressBook(first_name);
		if (addressBook != null)
			addressBook.setAddress(address);
		addressBook.setState(state);
		addressBook.setCity(city);
		addressBook.setZip(zip);
		addressBook.setPhoneNumber(phone);
		addressBook.setEmail(email);
	}

	public AddressBook getAddressBook(String name) throws AddressBookException {
		addressBookList = addressBookDBService.readData();
		return this.addressBookList.stream().filter(addressBookObject -> addressBookObject.getFirstName().equals(name))
				.findFirst().orElse(null);
	}

	public boolean checkEmployeePayrollInSyncWithDB(String firstName) throws AddressBookException {
		List<AddressBook> addressBookList = addressBookDBService.getAddressBookByFirstNameFromDB(firstName);
		return addressBookList.get(0).equals(getAddressBook(firstName));
	}

	public static void main(String[] args) throws AddressBookException {
		AddressBookService addressBookService = new AddressBookService();
		System.out.println(addressBookService.checkEmployeePayrollInSyncWithDB("Suraj"));
		System.out.println(addressBookService.getAddressBook("Suraj"));
		System.out.println(addressBookDBService.getAddressBookByFirstNameFromDB("Suraj"));
	}
}
