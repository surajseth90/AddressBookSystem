package com.bridgelabz.dbaddressbook;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bridgelabz.addressbooksystem.AddressBook;
import com.bridgelabz.exceptionaddressbooksystem.AddressBookException;

public class AddressBookDBService {
	List<AddressBook> addressBookList;
	private static AddressBookDBService addressBookDBService;
	private PreparedStatement preparedStatement;

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
		String sql = "SELECT * FROM address_book; ";
		return this.getDataFromDB(sql);
	}

	private List<AddressBook> getAddressBookDetails(ResultSet resultSet) throws AddressBookException {
		List<AddressBook> addressBookData = new ArrayList<>();
		try {
			while (resultSet.next()) {
				String firstName = resultSet.getString("FirstName");
				String lastName = resultSet.getString("LastName");
				String start = resultSet.getString("Start");
				String address = resultSet.getString("Address");
				String city = resultSet.getString("City");
				String state = resultSet.getString("State");
				int zip = resultSet.getInt("Zip");
				String phoneNo = resultSet.getString("PhoneNo");
				String email = resultSet.getString("Email");
				addressBookData
						.add(new AddressBook(firstName, lastName, start, address, city, state, zip, phoneNo, email));
			}
		} catch (SQLException e) {
			throw new AddressBookException(AddressBookException.AddressBookExceptionType.DATABASE_EXCEPTION,
					"Unable to retrieve data");
		}
		return addressBookData;
	}

	private List<AddressBook> getDataFromDB(String sql) throws AddressBookException {
		try {
			Connection connection = this.getConnection();
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(sql);
			addressBookList = this.getAddressBookDetails(resultSet);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new AddressBookException(AddressBookException.AddressBookExceptionType.READ_DATA_EXCEPTION,
					"!!Unable to retrive data from database!!");
		}
		return addressBookList;
	}

	public List<AddressBook> getAddressBookByFirstNameFromDB(String firstName) throws AddressBookException {
		List<AddressBook> addressBookList = new ArrayList<>();
		if (this.preparedStatement == null) {
			this.preparedStatementForRetrieveDataUsingFirstName();
		}
		try {
			preparedStatement.setString(1, firstName);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				String firstname = resultSet.getString("first_name");
				String lastName = resultSet.getString("last_name");
				String address = resultSet.getString("address");
				String city = resultSet.getString("city");
				String state = resultSet.getString("state");
				int zip = resultSet.getInt("zip");
				String phoneNumber = resultSet.getString("phone_number");
				String email = resultSet.getString("Email");
				addressBookList
						.add(new AddressBook(firstname, lastName, address, city, state, phoneNumber, email, zip));
			}
			return addressBookList;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new AddressBookException(AddressBookException.AddressBookExceptionType.READ_DATA_EXCEPTION,
					"!!Unable to read data from database!!");
		}
	}

	private void preparedStatementForRetrieveDataUsingFirstName() {
		try {
			Connection connection = this.getConnection();
			String sql = "SELECT * FROM address_book WHERE first_name=?";
			this.preparedStatement = connection.prepareStatement(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public int updateAddressBookUsingPreparedStatement(String address, String state, String city, int zip, String phone,
			String email, String first_name) throws AddressBookException {
		if (this.preparedStatement == null) {
			this.prepareStatementForUpdateAddressBook();
		}
		try {
			preparedStatement.setString(1, address);
			preparedStatement.setString(2, state);
			preparedStatement.setString(3, city);
			preparedStatement.setInt(4, zip);
			preparedStatement.setString(5, phone);
			preparedStatement.setString(6, email);
			preparedStatement.setString(7, first_name);
			int rowsAffected = preparedStatement.executeUpdate();
			return rowsAffected;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new AddressBookException(AddressBookException.AddressBookExceptionType.UPDATION_EXCEPTION,
					"Unable to update database!!");
		}
	}

	private void prepareStatementForUpdateAddressBook() throws AddressBookException {
		try {
			Connection connection = this.getConnection();
			String sql = "UPDATE address_book SET address=?,state=?,city=?,zip=?,phone_number=?,email=? WHERE first_name=?";
			this.preparedStatement = connection.prepareStatement(sql);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new AddressBookException(AddressBookException.AddressBookExceptionType.UPDATION_EXCEPTION,
					"Unable to update database!!");
		}

	}

	public List<AddressBook> readData(LocalDate start, LocalDate end) throws AddressBookException {
		String query = null;
		if (start != null)
			query = String.format("select * from addressBook where Date between '%s' and '%s';", start, end);
		if (start == null)
			query = "select * from addressBook";
		List<AddressBook> addressBookList = new ArrayList<>();
		try (Connection con = this.getConnection();) {
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(query);
			addressBookList = this.getAddressBookDetails(rs);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new AddressBookException(AddressBookException.AddressBookExceptionType.DATABASE_EXCEPTION,
					"Unable to fetch data!!");
		}
		return addressBookList;
	}

	public int countDataByStateOrCity(String select, String name) throws AddressBookException {
		int count = 0;
		String query = String.format("SELECT COUNT(first_Name) FROM address_book where %s = '%s';", select, name);
		try (Connection connection = this.getConnection()) {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(query);
			while (resultSet.next()) {
				count = resultSet.getInt("COUNT(first_name)");
			}
		} catch (SQLException e) {
			throw new AddressBookException(AddressBookException.AddressBookExceptionType.DATABASE_EXCEPTION,
					"Unable to fetch data!!");
		}
		return count;
	}

	public AddressBook addNewContact(String firstName, String lastName, String start, String address, String city,
			String state, int zip, String phoneNo, String email) throws AddressBookException {
		int id = -1;
		AddressBook addressBookData = null;
		String query = String.format(
				"insert into addressBook(FirstName, LastName, Date, Address, City, State, Zip, PhoneNo, Email) values ('%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s','%s')",
				firstName, lastName, start, address, city, state, zip, phoneNo, email);
		try (Connection connection = this.getConnection()) {
			Statement statement = connection.createStatement();
			int rowChanged = statement.executeUpdate(query, statement.RETURN_GENERATED_KEYS);
			if (rowChanged == 1) {
				ResultSet resultSet = statement.getGeneratedKeys();
				if (resultSet.next())
					id = resultSet.getInt(1);
			}
			addressBookData = new AddressBook(firstName, lastName, start, address, city, state, zip, phoneNo, email);
		} catch (SQLException e) {
			throw new AddressBookException(AddressBookException.AddressBookExceptionType.ADD_CONTACT_EXCEPTION,
					"Unable to add employee!!");
		}
		return addressBookData;
	}

	public void addMultipleContactsToDBUsingThread(List<AddressBook> record) {
		Map<Integer, Boolean> addressAdditionStatus = new HashMap<Integer, Boolean>();
		record.forEach(addressbookdata -> {
			Runnable task = () -> {
				addressAdditionStatus.put(addressbookdata.hashCode(), false);
				System.out.println("Contact Being Added:" + Thread.currentThread().getName());
				try {
					this.addNewContact(addressbookdata.getFirstName(), addressbookdata.getLastName(),
							addressbookdata.getStart(), addressbookdata.getAddress(), addressbookdata.getCity(),
							addressbookdata.getState(), addressbookdata.getZip(), addressbookdata.getPhoneNumber(),
							addressbookdata.getEmail());
				} catch (AddressBookException e) {
					e.printStackTrace();
				}
				addressAdditionStatus.put(addressbookdata.hashCode(), true);
				System.out.println("Contact Added:" + Thread.currentThread().getName());
			};
			Thread thread = new Thread(task, addressbookdata.getFirstName());
			thread.start();
		});
		while (addressAdditionStatus.containsValue(false)) {
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
			}
		}
	}

}
