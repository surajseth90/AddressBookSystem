package com.bridgelabz.addressbooksystem;
import java.util.ArrayList;

public class ShowContact {

	public static void show(ArrayList <AddressBook> contactDetails)
	{
		for(int i =0; i<contactDetails.size(); i++)
		{
			System.out.println("First name is : "+contactDetails.get(i).getFirstName());
			System.out.println("Last name is : " +contactDetails.get(i).getLastName());
			System.out.println("Address is : " +contactDetails.get(i).getAddress());
			System.out.println("City is : " +contactDetails.get(i).getCity());
			System.out.println("State is : " +contactDetails.get(i).getState());
			System.out.println("Phone number is : " +contactDetails.get(i).getPhoneNumber());
			System.out.println("Email is : " +contactDetails.get(i).getEmail());
			System.out.println("Zip is : " +contactDetails.get(i).getZip());
		
		}
	}
}
