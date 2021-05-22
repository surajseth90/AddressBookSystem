package com.bridgelabz.addressbooksystem;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.bridgelabz.dbaddressbook.AddressBookDBService;
import com.bridgelabz.exceptionaddressbooksystem.AddressBookException;

public class AddressBookService {

	List<AddressBook> addressBookList;
	private static AddressBookDBService addressBookDBService;

	public enum IOService {
		CONSOLE_IO, FILE_IO, DB_IO, REST_IO
	}

	public AddressBookService() {
		addressBookDBService = AddressBookDBService.getInstance();
	}

	public AddressBookService(List<AddressBook> asList) {
		this.addressBookList = new ArrayList<>(asList);
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

	public List<AddressBook> readAddressBookData(String start, String end) throws AddressBookException {
		try {
			LocalDate startLocalDate = LocalDate.parse(start);
			LocalDate endLocalDate = LocalDate.parse(end);
			return addressBookDBService.readData(startLocalDate, endLocalDate);
		} catch (AddressBookException e) {
			e.printStackTrace();
			throw new AddressBookException(AddressBookException.AddressBookExceptionType.DATABASE_EXCEPTION,
					"Unable to fetch data!!");
		}
	}

	public int countDataByStateOrCity(String select, String name) throws AddressBookException {
		return addressBookDBService.countDataByStateOrCity(select, name);
	}

	public void addNewContact(String firstName, String lastName, String start, String address, String city,
			String state, int zip, String phoneNo, String email) throws AddressBookException {
		addressBookList.add(addressBookDBService.addNewContact(firstName, lastName, start, address, city, state, zip,
				phoneNo, email));
	}

	public void addMultipleContactsToDBUsingThreads(List<AddressBook> arrayList) {
		addressBookDBService.addMultipleContactsToDBUsingThread(arrayList);
	}

	public long countEntries(IOService service) {
		if (service.equals(IOService.REST_IO))
			return addressBookList.size();
		return 0;

	}

	public void addContactToAddressBook(AddressBook addressBookData, IOService restIo) {
		addressBookList.add(addressBookData);
	}

	public AddressBook getAddressBookData(String firstName) {
		AddressBook addressBookData;
		addressBookData = this.addressBookList.stream().filter(dataItem -> dataItem.getFirstName().equals(firstName))
				.findFirst().orElse(null);
		return addressBookData;
	}

	public void updateContactCity(String firstName, String city, IOService restIo) {
		AddressBook addressBookData = this.getAddressBookData(firstName);
		if (addressBookData != null)
			addressBookData.setCity(city);

	}

	public void deleteContactPayroll(String firstName, IOService ioService) {
		AddressBook addressBookData = this.getAddressBookData(firstName);
		addressBookList.remove(addressBookData);
	}
}
