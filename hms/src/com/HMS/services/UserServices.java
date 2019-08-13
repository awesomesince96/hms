package com.HMS.services;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.HMS.commonUtills.Connection;
import com.HMS.commonUtills.Status;
import com.HMS.models.User;
import com.HMS.models.Client;
import com.HMS.models.StateMaster;

public class UserServices {

	Client client = new Client();
	User user = new User();

	public int addClient(Client client) {
		java.sql.Connection connection = Connection.getConnection();
		int lastID = 0;
		try {

			String query = "insert into client(hname,address,contact)values(?,?,?)";
			PreparedStatement prepareStatement;
			prepareStatement = connection.prepareStatement(query);
			prepareStatement.setString(1, client.getHname());
			prepareStatement.setString(2, client.getAddress());
			prepareStatement.setLong(3, client.getContact());
			prepareStatement.executeUpdate();
			ResultSet rs = prepareStatement.getGeneratedKeys();
			if (rs.next()) {
				lastID = rs.getInt(1);
			}
			prepareStatement.close();

		} catch (SQLException e) {
			e.printStackTrace(System.out);
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace(System.out);
			}
		}
		return lastID;

	}

	public void addUser(User user) {
		java.sql.Connection connection = Connection.getConnection();
		try {

			PreparedStatement prepareStatement = connection.prepareStatement(
					"insert into user(username,password,fname,mname,lname,email,gender,contact,status,clientid)values(?,?,?,?,?,?,?,?,?,?)");
			prepareStatement.setString(1, user.getUsername());
			prepareStatement.setString(2, user.getPassword());
			prepareStatement.setString(3, user.getFname());
			prepareStatement.setString(4, user.getMname());
			prepareStatement.setString(5, user.getLname());
			prepareStatement.setString(6, user.getEmail());
			prepareStatement.setString(7, user.getGender());
			prepareStatement.setLong(8, user.getContact());
			// prepareStatement.setLong(9,user.getStatus());
			prepareStatement.setInt(9, user.getStatus().getId());
			prepareStatement.setLong(10, user.getClient().getId());
			prepareStatement.executeUpdate();
			System.out.println("Services: " + user.getUsername() + user.getPassword() + user.getFname()
					+ user.getMname() + user.getLname() + user.getEmail() + user.getGender() + user.getContact()
					+ user.getStatus() + user.getClient());
			prepareStatement.close();
		} catch (SQLException e) {
			e.printStackTrace(System.out);
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace(System.out);
			}
		}
	}

	

	public List<User> getAll() {
		List<User> userList = new ArrayList<User>();
		java.sql.Connection connection = Connection.getConnection();
		try {

			PreparedStatement prepareStatement = connection.prepareStatement("select * from user order by id");
			ResultSet resultSet = prepareStatement.executeQuery();
			while (resultSet.next()) {
				userList.add(loadMaster(resultSet));
			}
			resultSet.close();
			return userList;
		} catch (Exception e) {
			e.printStackTrace(System.out);
		} finally {
			try {
				connection.close();
			} catch (Exception e) {
				e.printStackTrace(System.out);
			}
		}
		return userList;
	}

	private User loadMaster(ResultSet resultSet) throws SQLException {
		return new User(resultSet.getLong("id"), resultSet.getString("username"), resultSet.getString("password"),
				resultSet.getString("fname"), resultSet.getString("mname"), resultSet.getString("lname"),
				resultSet.getString("email"), resultSet.getString("gender"), Status.fromId(resultSet.getInt("status")),
				resultSet.getLong("contact"), this.idtoClient(resultSet.getLong("clientid")));

	}

	public Client idtoClient(long id) {
		java.sql.Connection connection = Connection.getConnection();
		Client client1 = new Client();
		try {

			PreparedStatement prepareStatement = connection.prepareStatement("select * from client where id=?");
			prepareStatement.setLong(1, id);
			ResultSet resultSet = prepareStatement.executeQuery();
			while (resultSet.next()) {

				client1.setId(resultSet.getLong("id"));
				client1.setHname(resultSet.getString("hname"));
				client1.setAddress((resultSet.getString("address")));
				client1.setContact(resultSet.getLong("contact"));
				break;
			}
			resultSet.close();
			prepareStatement.executeQuery();
			prepareStatement.close();
			return client1;
		} catch (SQLException e) {
			e.printStackTrace(System.out);
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace(System.out);
			}
		}
		return client1;

	}
	
	public User getUserFromEmail(String email) {
		java.sql.Connection connection = Connection.getConnection();
		User user = null;
		try {
	
			PreparedStatement prepareStatement = connection.prepareStatement("select * from user where email=?");
			prepareStatement.setString(1, email);
			ResultSet resultSet = prepareStatement.executeQuery();
			while (resultSet.next()) {
				user = loadMaster(resultSet);			
			}
			
			resultSet.close();
			prepareStatement.executeQuery();
			prepareStatement.close();
		
			return user;
		} catch (SQLException e) {
			e.printStackTrace(System.out);
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace(System.out);
			}
		}
		return null;

	}

	public void add(StateMaster stateMaster) {
		java.sql.Connection connection = Connection.getConnection();
		try {

			PreparedStatement prepareStatement = connection
					.prepareStatement("insert into state(name,status,contry)values(?,?,?)");
			prepareStatement.setString(1, stateMaster.getName());
			prepareStatement.setInt(2, stateMaster.getStatus().getId());
			prepareStatement.setLong(3, stateMaster.getContryMaster().getId());
			prepareStatement.executeUpdate();
			prepareStatement.close();
		} catch (SQLException e) {
			e.printStackTrace(System.out);
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace(System.out);
			}
		}

	}

	public static void active(long id) {
		java.sql.Connection connection = Connection.getConnection();
		try {
			PreparedStatement statement = connection.prepareStatement("update state set status = 1 where id = ?");
			statement.setLong(1, id);
			statement.execute();
			statement.close();
		} catch (Exception e) {
			e.printStackTrace(System.out);
		} finally {
			try {
				connection.close();
			} catch (Exception e) {
				e.printStackTrace(System.out);
			}
		}
	}

	public static void inactive(long id) {
		java.sql.Connection connection = Connection.getConnection();
		try {
			PreparedStatement statement = connection.prepareStatement("update state set status = 2 where id = ?");
			statement.setLong(1, id);
			statement.execute();
			statement.close();
		} catch (Exception e) {
			e.printStackTrace(System.out);
		} finally {
			try {
				connection.close();
			} catch (Exception e) {
				e.printStackTrace(System.out);
			}
		}
	}

	public void update(StateMaster stateMaster) {
		java.sql.Connection connection = Connection.getConnection();
		try {
			PreparedStatement statement = connection
					.prepareStatement("update state set name = ?, status = ?,contry = ? where id = ?");
			statement.setString(1, stateMaster.getName());
			statement.setInt(2, stateMaster.getStatus().getId());
			statement.setLong(3, stateMaster.getContryMaster().getId());
			statement.setLong(4, stateMaster.getId());
			statement.execute();
			statement.close();
		} catch (Exception e) {
			e.printStackTrace(System.out);
		} finally {
			try {
				connection.close();
			} catch (Exception e) {
				e.printStackTrace(System.out);
			}
		}
	}

	public User get(long id) {
		User userMaster = null;
		java.sql.Connection connection = Connection.getConnection();
		try {
			PreparedStatement statement = connection.prepareStatement("Select * from  user where id = ?");
			statement.setLong(1, id);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				userMaster = loadMaster(resultSet);
				break;
			}
			resultSet.close();
			statement.close();
			return userMaster;
		} catch (Exception e) {
			e.printStackTrace(System.out);
		} finally {
			try {
				connection.close();
			} catch (Exception e) {
				e.printStackTrace(System.out);
			}
		}
		return null;
	}

}
