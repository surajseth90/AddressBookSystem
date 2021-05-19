package com.bridgelabz.dbaddressbook;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.bridgelabz.addressbooksystem.AddressBook;
import com.bridgelabz.exceptionaddressbooksystem.AddressBookException;

public class AddressBookDBService {
	List<AddressBook> addressBookList;
	private static AddressBookDBService addressBookDBService;

	private AddressBookDBService() {
	}

	public static AddressBookDBService getInstance() {
		if (addressBookDBService == null)
			addressBookDBService = new AddressBookDBService();
		return addressBookDBService;
	}

	private Connection getConnection() throws SQLException {
		String jdbcURL = "jdbc:mysql://localhost:3306/address_book_service?useSSL=false";
		String userName = "root";
		String password = "Surajmysql@90";
		Connection connectionn;
		System.out.println("Connecting to database: " + jdbcURL);
		connectionn = DriverManager.getConnection(jdbcURL, userName, password);
		System.out.println("Connection is successfull!! " + connectionn);
		return connectionn;
	}

	public List<AddressBook> readData() throws AddressBookException {
		addressBookList = new ArrayList<AddressBook>();
		String sql = "SELECT * FROM address_book; ";
		return this.getDataFromDB(sql);
	}

	private List<AddressBook> getDataFromDB(String sql) throws AddressBookException {
		addressBookList = new ArrayList<AddressBook>();
		try {
			Connection connection = this.getConnection();
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(sql);
			addressBookList = this.getAddressBookData(resultSet);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new AddressBookException(AddressBookException.AddressBookExceptionType.READ_DATA_EXCEPTION,
					"!!Unable to read data from database!!");
		}
		return addressBookList;
	}

	private List<AddressBook> getAddressBookData(ResultSet resultSet) throws AddressBookException {
		addressBookList = new ArrayList<AddressBook>();
		try {
			while (resultSet.next()) {
				String firstName = resultSet.getString("first_name");
				String lastName = resultSet.getString("last_name");
				String address = resultSet.getString("address");
				String city = resultSet.getString("city");
				String state = resultSet.getString("state");
				int zip = resultSet.getInt("zip");
				String phoneNumber = resultSet.getString("phone_number");
				String email = resultSet.getString("Email");
				addressBookList
						.add(new AddressBook(firstName, lastName, address, city, state, phoneNumber, email, zip));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new AddressBookException(AddressBookException.AddressBookExceptionType.READ_DATA_EXCEPTION,
					"!!Unable to read data from database!!");
		}
		return addressBookList;
	}
}
