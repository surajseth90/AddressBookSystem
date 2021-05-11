package com.bridgelabz.addressbooksystem;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

public class JsonFileOfAddessBook {
	private static final String CSV_PATH = "C:\\Users\\ASUS\\eclipse-workspace\\PP4 AddressBookSystem\\src\\main\\java\\com\\"
			+ "bridgelabz\\addressbooksystem-AddressBook_CSV_File.csv";
	private static final String JSON_PATH = "C:\\Users\\ASUS\\eclipse-workspace\\PP4 AddressBookSystem\\src\\main\\java\com"
														+ "\\bridgelabz\\addressbooksystem-AddressBook_Json_File.json" ;

	public static void main(String[] args) {
		try {
			Reader reader = Files.newBufferedReader(Paths.get(CSV_PATH));

			CsvToBean<AddressBook> csvToBean = new CsvToBeanBuilder<AddressBook>(reader).withType(AddressBook.class)
					.withIgnoreLeadingWhiteSpace(true).build();
			List<AddressBook> addressBook = csvToBean.parse();
			Gson gson = new Gson();
			String json = gson.toJson(AddressBook);
			FileWriter writer = new FileWriter(JSON_PATH);
			writer.write(json);
			writer.close();
			BufferedReader bufferReader = new BufferedReader(new FileReader(JSON_PATH));
			AddressBook[] obj = gson.fromJson(bufferReader, AddressBook[].class);
			List<AddressBook> myList = Arrays.asList(obj);

		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
