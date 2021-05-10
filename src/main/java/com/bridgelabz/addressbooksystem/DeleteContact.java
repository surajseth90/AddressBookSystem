package com.bridgelabz.addressbooksystem;
import java.util.ArrayList;

public class DeleteContact {

	public static void deleteContact(ArrayList <AddressBook> contactDetails,String name)
	{
	
		for(int i =0;i<contactDetails.size();i++)
		{
		String na=contactDetails.get(i).getFirstName();
		if(na.equals(name)){
	      contactDetails.remove(contactDetails.get(i));
		} 
		}
	}
}

