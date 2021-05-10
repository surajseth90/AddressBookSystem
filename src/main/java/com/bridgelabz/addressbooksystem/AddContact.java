package com.bridgelabz.addressbooksystem;
import java.util.ArrayList;
import java.util.Scanner;


public class AddContact {

	ArrayList<AddressBook> contactDetails = new ArrayList<AddressBook>();

	public void add() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("How many contacts you want add ? ");
		int contactInput = scanner.nextInt();
		
		
		for (int i = 0; i < contactInput; i++) {
			scanner.nextLine();
			System.out.println("Enter your First Name : ");
			String firstName = scanner.nextLine();

			System.out.println("Enter your Last Name : ");
			String lastName = scanner.nextLine();

			if (contactDetails.stream().anyMatch(
					person -> person.getFirstName().equals(firstName) && person.getLastName().equals(lastName))) {
				System.out.println(firstName + lastName + " is already exixts !");
				continue;
			}

			else {

				System.out.println("Enter your Address : ");
				String address = scanner.nextLine();

				System.out.println("Enter your City Name : ");
				String city = scanner.nextLine();

				System.out.println("Enter your State Name : ");
				String state = scanner.nextLine();

				System.out.println("Enter your Phone number : ");
				String phoneNumber = scanner.nextLine();

				System.out.println("Enter your Email : ");
				String email = scanner.nextLine();

				System.out.println("Enter your Zip code : ");
				int zip = scanner.nextInt();

				contactDetails.add(new AddressBook(firstName, lastName, address, city, state, phoneNumber, email, zip));
			}
		}
		ShowContact.show(contactDetails);
	}

	public ArrayList<AddressBook> getContact() {
		return contactDetails;
	}
}
