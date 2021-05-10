package com.bridgelabz.addressbooksystem;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class AddressBookFileIO {

	public static String ADDRESSBOOK_FILE_NAME = "addressbook_file.txt";

	public void writeData(ArrayList<AddressBook> contactDetails) {
		StringBuffer contactbuffer = new StringBuffer();
		contactDetails.forEach(contact -> {
			String contactDataString = contact.toString().concat("\n");
			contactbuffer.append(contactDataString);
		});
		try {
			Files.write(Paths.get("addressbook_file.txt"), contactbuffer.toString().getBytes());
		} catch (IOException e) {
			System.out.println(e);
		}

	}

	public void printData() {
		try {
			Files.lines(new File("addressbook_file.txt").toPath());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public long countEntries() {
		long entries = 0;
		try {
			entries = Files.lines(new File("addressbook_file.txt").toPath()).count();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return entries;

	}
}
