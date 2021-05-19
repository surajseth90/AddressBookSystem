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
}