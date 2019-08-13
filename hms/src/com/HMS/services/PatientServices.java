package com.HMS.services;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.HMS.commonUtills.Connection;
import com.HMS.commonUtills.Status;
import com.HMS.models.CityMaster;
import com.HMS.models.ContryMaster;
import com.HMS.models.PatientMaster;
import com.HMS.models.StateMaster;
import com.HMS.models.User;

public class PatientServices {

	public void add(PatientMaster patientMaster ) throws Exception{
		CityMaster citymaster = new CityMaster();
		StateMaster statemaster = new StateMaster();
		ContryMaster countrymaster = new ContryMaster();
		User user = new User();
		java.sql.Date todaydate = new java.sql.Date(Calendar.getInstance().getTime().getTime());
		java.sql.Connection connection = Connection.getConnection();
		try {
			PreparedStatement prepareStatement = connection.prepareStatement("insert into patient(fname,mname,lname,gender,dob,bloodgroup,phone,address,cityid,stateid,countryid,userid,email,dor) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
			prepareStatement.setString(1,patientMaster.getFname());
			prepareStatement.setString(2,patientMaster.getMname());
			prepareStatement.setString(3,patientMaster.getLname());
			prepareStatement.setString(4,patientMaster.getGender());
			prepareStatement.setDate(5,patientMaster.getDob());
			prepareStatement.setString(6,patientMaster.getBloodgroup());
			prepareStatement.setLong(7,patientMaster.getPhone());
			prepareStatement.setString(8,patientMaster.getAddress());
			prepareStatement.setLong(9,patientMaster.getCity().getId());
			prepareStatement.setLong(10,patientMaster.getState().getId());
			prepareStatement.setLong(11,patientMaster.getCountry().getId());
			prepareStatement.setLong(12,patientMaster.getUser().getId());
			prepareStatement.setString(13,patientMaster.getEmail());
			prepareStatement.setDate(14,todaydate);
			prepareStatement.executeUpdate();
			prepareStatement.close();
		} catch (SQLException e) {
			e.printStackTrace(System.out);
		}finally {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace(System.out);
			}
		}
	}

	public List<PatientMaster> getAll() {
			
			List<PatientMaster> patientList = new ArrayList<PatientMaster>();
			java.sql.Connection connection = Connection.getConnection();
			
			try {

				PreparedStatement prepareStatement = connection.prepareStatement("select * from patient order by pid");
				ResultSet resultSet = prepareStatement.executeQuery();
				while (resultSet.next()) {
					patientList.add(loadMaster(resultSet));
				}
				resultSet.close();
				return patientList;
			} catch (Exception e) {
				e.printStackTrace(System.out);
			} finally {
				try {
					connection.close();
				} catch (Exception e) {
					e.printStackTrace(System.out);
				}
			}
			return patientList;
	}
	
	public PatientMaster get(long id) {
		PatientMaster patientMaster = null;
		java.sql.Connection connection = Connection.getConnection();
		try {
			PreparedStatement statement = connection.prepareStatement("Select * from patient where pid = ?");
			statement.setLong(1, id);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				patientMaster = loadMaster(resultSet);
				break;
			}
			resultSet.close();
			statement.close();
			return patientMaster;
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
	
	
	private PatientMaster loadMaster(ResultSet resultSet) throws SQLException {
		CityMasterService cityservice = new CityMasterService();
		StateMasterService stateservice = new StateMasterService();
		ContryMasterService countryservice = new ContryMasterService();
		UserServices userservice = new UserServices();
//		String timestamp1 = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Timestamp(System.currentTimeMillis()));
//		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
//		LocalDate date = new Date(0);
//		date = java.time.LocalDate.now();  
	    java.sql.Date date=new java.sql.Date(System.currentTimeMillis());   
		
		return new PatientMaster(resultSet.getLong("pid"),resultSet.getString("fname"),resultSet.getString("mname"),resultSet.getString("lname"),
				resultSet.getString("gender"),resultSet.getDate("dob"),resultSet.getString("bloodgroup"),resultSet.getLong("phone"),
				resultSet.getString("address"),cityservice.get(resultSet.getLong("cityid")),stateservice.get(resultSet.getLong("stateid")),
				countryservice.get(resultSet.getLong("countryid")),userservice.get(resultSet.getLong("userid")),
				resultSet.getString("email"),date);
	}
	
	
	public User get(int convertStringToInt) {
		// TODO Auto-generated method stub
		return null;
	}

	
}
