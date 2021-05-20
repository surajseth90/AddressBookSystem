package com.bridgelabz.addressbooksystemtest;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.bridgelabz.addressbooksystem.AddressBook;
import com.bridgelabz.addressbooksystem.AddressBookService;
import com.bridgelabz.exceptionaddressbooksystem.AddressBookException;

public class AddressBookServiceTest {
	AddressBookService addressBookService = new AddressBookService();
	List<AddressBook> addressBookList;

	@Test
	public void givenAddressBook_WhenRetrived_ShouldReturnAddressBookSize() throws AddressBookException {
		addressBookList = addressBookService.readAddressBookData();
		Assert.assertEquals(3, addressBookList.size());
	}

	@Test
	public void givenAddressBook_WhenUpdate_ShouldSYNCWithDataBase() throws AddressBookException {
		addressBookService.updateAddressBookUsingPrepareStatement("Nodia", "UP", "Noida", 000000, "9234567890",
				"suraj@gmail.com", "Suraj");
		boolean result = addressBookService.checkEmployeePayrollInSyncWithDB("Suraj");
		Assert.assertTrue(result);
	}
	
	@Test
	public void givenAddresBook_WhenRetrieved_ShouldReturnCountOfCity() throws AddressBookException {
		Assert.assertEquals(1, addressBookService.readAddressBookData("city", "delhi"));
	}
	
	@Test
	public void givenAddresBookDetails_WhenAdded_ShouldSyncWithDB() throws AddressBookException {
		addressBookService.readAddressBookData();
		addressBookService.addNewContact("Rahul", "kushwaha", "2020-11-22", "datia", "datia", "mp", 156262, "999999999999", "rahul@gmail.com");
		boolean result = addressBookService.checkUpdatedRecordSyncWithDatabase("Rahul");
		Assert.assertTrue(result);
	}
}
