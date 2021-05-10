package com.bridgelabz.addressbooksystem;
import java.util.*;



public class MultipleAddressBook {

	public static void multipleAddressBook() {
		Scanner scanner = new Scanner(System.in);
		AddContact addContact = new AddContact();
		HashMap<String, AddContact> hmap = new HashMap<String, AddContact>();
		System.out.println("How many Address Book you want to add :");
		int numberOfAddressBook = scanner.nextInt();
		scanner.nextLine();
		for (int i = 0; i < numberOfAddressBook; i++) {
			System.out.println("Please Enter the name of Address Book : ");
			String addressBookName = scanner.nextLine();
			addContact.add();
			hmap.put(addressBookName, addContact);
		}

	}

}
