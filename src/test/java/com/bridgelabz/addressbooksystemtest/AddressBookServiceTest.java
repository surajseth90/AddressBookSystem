package com.bridgelabz.addressbooksystemtest;

import java.util.Arrays;
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
		addressBookService.addNewContact("Rahul", "kushwaha", "2020-11-22", "datia", "datia", "mp", 156262,
				"999999999999", "rahul@gmail.com");
		boolean result = addressBookService.checkEmployeePayrollInSyncWithDB("Rahul");
		Assert.assertTrue(result);
	}

	@Test
	public void givenMultipleContact_WhenAdded_ShouldSyncWithDB() throws AddressBookException {
		AddressBook[] contactArray = {
				new AddressBook("C", "J", "2020-11-23", "SH", "GZ", "UP", 517536, "8975658741", "th@ail.com"),
				new AddressBook("N", "P", "2020-11-23", "OK", "DE", "AP", 517533, "9874563201", "ni@gl.com") };
		addressBookService.addMultipleContactsToDBUsingThreads(Arrays.asList(contactArray));
		boolean result1 = addressBookService.checkEmployeePayrollInSyncWithDB("Tharun");
		boolean result2 = addressBookService.checkEmployeePayrollInSyncWithDB("Nani");
		Assert.assertTrue(result1);
		Assert.assertTrue(result2);

	}
}
