package com.bridgelabz.addressbooksystem;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;

public class CSVFileOfAddressBook {

	private static final String CSV_PATH = "C:\\Users\\ASUS\\eclipse-workspace\\PP4 AddressBookSystem\\src\\main\\java\\com\\bridgelabz\\addressbooksystem-AddressBook_CSV_File.csv";

	public static void main(String[] args) throws IOException, CsvDataTypeMismatchException, CsvRequiredFieldEmptyException {
		writeCsvFile();
		readCsvFile();

	}

	public static void writeCsvFile() throws IOException, CsvDataTypeMismatchException, CsvRequiredFieldEmptyException {
		Writer writer = Files.newBufferedWriter(Paths.get(CSV_PATH));
		StatefulBeanToCsv<AddressBook> beanToCsv = new StatefulBeanToCsvBuilder(writer).build();
		List<AddressBook> addressBook = new ArrayList<AddressBook>();
		addressBook.add(new AddressBook("Suraj", "Gupta", "Badoni", "Datia", "MadhyaPradesh", "1234567890",
											"surajseth90@gmail.com", 475686));

		beanToCsv.write(addressBook);
	}

	public static void readCsvFile() throws IOException {
		try (Reader reader = Files.newBufferedReader(Paths.get(CSV_PATH));) {
			CsvToBean<AddressBook> csvToBean = new CsvToBeanBuilder<AddressBook>(reader).withType(AddressBook.class)
					.withIgnoreLeadingWhiteSpace(true).build();
			List<AddressBook> addressBook = csvToBean.parse();
			for (AddressBook addressBook1 : addressBook) {
				System.out.println("Name is : " + addressBook1.getName());
				System.out.println("Address is : " + addressBook1.getAddress());
				System.out.println("City is : " + addressBook1.getCity());
				System.out.println("State is : " + addressBook1.getState());
				System.out.println("Email is : " + addressBook1.getEmail());
				System.out.println("Phone number is : " + addressBook1.getPhoneNumber());
				System.out.println("Zip code is : " + addressBook1.getZip());

			}
		}

	}
}