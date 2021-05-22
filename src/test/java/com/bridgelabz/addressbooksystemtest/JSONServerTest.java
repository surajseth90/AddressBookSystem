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
import io.restassured.specification.RequestSpecification;

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
	
	private Response addContactToJsonServer(AddressBook addressBookData) {
		String addressBookJson = new Gson().toJson(addressBookData);
		RequestSpecification request = RestAssured.given();
		request.header("Content-Type", "application/json");
		request.body(addressBookJson);
		return request.post("/Addressbook");
	}

	@Test
	public void givenAddressBookJSONServer_WhenRetrieved_ShouldReturnCount() {
		AddressBook[] arrayOfAddressBook = getAddressBook();
		AddressBookService addressBookService;
		addressBookService = new AddressBookService(Arrays.asList(arrayOfAddressBook));
		long entries = addressBookService.countEntries(IOService.REST_IO);
		Assert.assertEquals(1, entries);

	}

	@Test
	public void givenMultipleContact_WhenAdded_ShouldMatch201ResponseAndCount() {
		AddressBook[] arrayOfAddressBook = getAddressBook();
		AddressBookService addressBookService;
		addressBookService = new AddressBookService(Arrays.asList(arrayOfAddressBook));
		AddressBook[] arrayOfPersonPayroll = {
				new AddressBook(2, "Purvi", "Gupta", "2020-09-16","badoni","Datia","MP", 475686, "8770959691", "purvi@gmail.com"),
				new AddressBook(3, "Rahul", "Kushwaha", "2020-12-16","kur","datia","mp", 475686, "7389423474", "rahul@gmail.com") };

		for (AddressBook addressBookData : arrayOfPersonPayroll) {

			Response response = addContactToJsonServer(addressBookData);
			int statusCode = response.getStatusCode();
			Assert.assertEquals(201, statusCode);

			addressBookData = new Gson().fromJson(response.asString(), AddressBook.class);
			addressBookService.addContactToAddressBook(addressBookData, IOService.REST_IO);
		}
		long entries = addressBookService.countEntries(IOService.REST_IO);
		Assert.assertEquals(3, entries);
	}
	
	@Test
	public void givenCity_WhenUpdated_ShouldMatch200response() {
		AddressBook[] arrayOfAddressBook = getAddressBook();
		AddressBookService addressBookService;
		addressBookService = new AddressBookService(Arrays.asList(arrayOfAddressBook));
		addressBookService.updateContactCity("Suraj", "Noida", IOService.REST_IO);
		AddressBook addressBookData = addressBookService.getAddressBookData("Akash");

		String addJson = new Gson().toJson(addressBookData);
		RequestSpecification request = RestAssured.given();
		request.header("Content-Type", "application/json");
		request.body(addJson);
		Response response = request.put("/Addressbook/" + addressBookData.getId());
		int statusCode = response.getStatusCode();
		Assert.assertEquals(200, statusCode);
	}
}
