package com.bridgelabz.addressbooksystem;
import java.util.ArrayList;
import java.util.Scanner;

public class UpdateContact {

	public static void searchContact(ArrayList <AddressBook> contactDetails ,String name)
	{
		Scanner scanner = new Scanner(System.in);
		for(int i =0;i<contactDetails.size();i++)
		{
			String na=contactDetails.get(i).getFirstName();
			if(na.equals(name)){
			contactDetails.remove(contactDetails.get(i));
                 System.out.println("Enter your last Name to be changed: ");
		         String lastName=scanner.nextLine();
		         
		         System.out.println("Enter new Address : ");
		         String address=scanner.nextLine();
		
		         System.out.println("Enter new City Name : ");
		         String city=scanner.nextLine();
		
		         System.out.println("Enter  State Name : ");
		         String state=scanner.nextLine();
		
		         System.out.println("Enter new Phone number : ");
		         String phoneNumber=scanner.nextLine();
		
		         System.out.println("Enter Email : ");
		         String email=scanner.nextLine();
		
		         System.out.println("Enter Zip code : ");
		         int zip=scanner.nextInt();
		
		         contactDetails.add(new AddressBook(name, lastName, address, city,
				                                 state, phoneNumber, email, zip));
                 System.out.println("Contact updated");
                 	
                 	break;
		}
	}
}
}