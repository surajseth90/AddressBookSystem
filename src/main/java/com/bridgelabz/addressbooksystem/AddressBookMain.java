package com.bridgelabz.addressbooksystem;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;


public class AddressBookMain {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		AddContact addContact = new AddContact();

		System.out.println("Do you Want to Excecute : y/n ");
		String next = scanner.nextLine();

		while (next.equalsIgnoreCase("y")) {
			System.out.println("What you want to perform : ");
			System.out.println("1. Add Address Book ");
			System.out.println("2. Edit Contact");
			System.out.println("3. Remove Contact");
			System.out.println("4. Search Contact");
			System.out.println("5. View Person");
			System.out.println("6. Count Persons");
			System.out.println("7. View Sorted AddressBook");
			int input = scanner.nextInt();
			switch (input) {
			case 1: {
				MultipleAddressBook.multipleAddressBook();
				break;
			}
			case 2: {

				ArrayList<AddressBook> contactDetails = addContact.getContact();
				String name = scanner.nextLine();
				UpdateContact.searchContact(contactDetails, name);
				break;
			}
			case 3: {
				ArrayList<AddressBook> contactDetails = addContact.getContact();
				System.out.println("Enter First name of contact to be removed");
				String name = scanner.nextLine();
				DeleteContact.deleteContact(contactDetails, name);
				break;
			}
			case 4: {
				System.out.println("Want to search a person by: \n City  : press 1 \n State : press 2 ");
				ArrayList<AddressBook> contactDetails = addContact.getContact();
				int search = scanner.nextInt();
				scanner.nextLine();
				if (search == 1) {
					System.out.println("Enter the City name :");
					String cityName = scanner.nextLine();
					List<String> namesByCity = contactDetails.stream()
							.filter(person -> person.getCity().equalsIgnoreCase(cityName)).map(AddressBook::getName)
							.collect(Collectors.toList());
					System.out.println(namesByCity);
				} else {
					System.out.println("Enter the State name :");
					String stateName = scanner.nextLine();
					List<String> namesByState = contactDetails.stream()
							.filter(person -> person.getState().equalsIgnoreCase(stateName)).map(AddressBook::getName)
							.collect(Collectors.toList());
					System.out.println(namesByState);
				}
				break;
			}

			case 5: {
				System.out.println("Want to view a person by : \n City  : press 1 \n State : press 2 ");
				ArrayList<AddressBook> contactDetails = addContact.getContact();
				int search = scanner.nextInt();
				scanner.nextLine();
				if (search == 1) {
					System.out.println("Enter the City name :");
					String cityName = scanner.nextLine();
					List<String> namesByCity = contactDetails.stream()
							.filter(person -> person.getCity().equalsIgnoreCase(cityName)).map(AddressBook::getName)
							.collect(Collectors.toList());
					System.out.println(namesByCity);
				} else {
					System.out.println("Enter the State name :");
					String stateName = scanner.nextLine();
					List<String> namesByState = contactDetails.stream()
							.filter(person -> person.getState().equalsIgnoreCase(stateName)).map(AddressBook::getName)
							.collect(Collectors.toList());
					System.out.println(namesByState);
				}
				break;

			}
			case 6: {
				System.out.println("Want to view a person by : \n City  : press 1 \n State : press 2 ");
				ArrayList<AddressBook> contactDetails = addContact.getContact();
				int search = scanner.nextInt();
				scanner.nextLine();
				if (search == 1) {
					System.out.println("Enter the City name :");
					String cityName = scanner.nextLine();
					int countByCity = (int) contactDetails.stream()
							.filter(person -> person.getCity().equalsIgnoreCase(cityName)).count();
					System.out.println(countByCity);
				} else {
					System.out.println("Enter the State name :");
					String stateName = scanner.nextLine();
					int countByState = (int) contactDetails.stream()
							.filter(person -> person.getState().equalsIgnoreCase(stateName)).count();
					System.out.println(countByState);
				}
				break;
			}
			case 7: {
				ArrayList<AddressBook> contactDetails = addContact.getContact();
				System.out.println("Want to see sorted address book : ");
				System.out.println(
						"By Person's name - press 1 \nBy City  - press 2 \nBy State - press 3 \nBy Zip   - press 4");
				int sortingByWhich = scanner.nextInt();
				if (sortingByWhich == 1) {

					List<AddressBook> sortedAddressBookByName = contactDetails.stream()
							.sorted((p1, p2) -> p1.getName().compareToIgnoreCase(p2.getName()))
							.collect(Collectors.toList());
					System.out.println(sortedAddressBookByName.toString());
				} else if (sortingByWhich == 2) {
					List<AddressBook> sortedAddressBookByCity = contactDetails.stream()
							.sorted((p1, p2) -> p1.getCity().compareToIgnoreCase(p2.getCity()))
							.collect(Collectors.toList());
					System.out.println(sortedAddressBookByCity.toString());
				} else if (sortingByWhich == 3) {
					List<AddressBook> sortedAddressBookByState = contactDetails.stream()
							.sorted((p1, p2) -> p1.getState().compareToIgnoreCase(p2.getState()))
							.collect(Collectors.toList());
					System.out.println(sortedAddressBookByState.toString());
				} else if (sortingByWhich == 4) {
					List<AddressBook> sortedAddressBookByZip = contactDetails.stream()
							.sorted((p1, p2) -> Integer.compare(p1.getZip(), p2.getZip())).collect(Collectors.toList());
					System.out.println(sortedAddressBookByZip);
				} else
					System.out.println("Not a Valid Input !");
				break;
			}
			}

			System.out.println("if you want to continue , press 'y'");
			next = scanner.nextLine();
		}
	}
}