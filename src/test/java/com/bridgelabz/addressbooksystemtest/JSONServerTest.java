package com.bridgelabz.addressbooksystemtest;

import java.util.Arrays;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.bridgelabz.addressbooksystem.AddressBook;
import com.bridgelabz.addressbooksystem.AddressBookService;
import com.bridgelabz.addressbooksystem.AddressBookService.IOService;
import com.google.gson.Gson;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class JSONServerTest {
	@Before
	public void setup() {
		RestAssured.baseURI = "http://localhost";
		RestAssured.port = 3000;
	}

	public AddressBook[] getAddressBook() {
		Response response = RestAssured.get("/AddressBook");
		System.out.println("AddressBook entries in json server :\n" + response.asString());
		AddressBook[] arrayOfAddressBook = new Gson().fromJson(response.asString(), AddressBook[].class);
		return arrayOfAddressBook;

	}

	@Test
	public void givenAddressBookJSONServer_WhenRetrieved_ShouldReturnCount() {
		AddressBook[] arrayOfAddressBook = getAddressBook();
		AddressBookService addressBookService;
		addressBookService = new AddressBookService(Arrays.asList(arrayOfAddressBook));
		long entries = addressBookService.countEntries(IOService.REST_IO);
		Assert.assertEquals(1, entries);

	}

}
