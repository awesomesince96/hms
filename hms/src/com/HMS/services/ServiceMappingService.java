package com.HMS.services;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.HMS.commonUtills.Connection;
import com.HMS.models.PatientMaster;
import com.HMS.models.ServiceMapping;
import com.HMS.models.ServiceMaster;

public class ServiceMappingService {
	
	public void add(ServiceMapping servicemapping ) throws Exception{
		java.sql.Connection connection = Connection.getConnection();
		try {
			PreparedStatement prepareStatement = connection.prepareStatement("insert into service_mapping(service,bill) values(?,?)");
			prepareStatement.setLong(1, servicemapping.getService().getId());;
			prepareStatement.setLong(2, servicemapping.getBill().getId());
			
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
	
	public List<ServiceMapping> get(long id) {
		List<ServiceMapping> servicemapping = new ArrayList<ServiceMapping>();
//		ServiceMapping serviceMapping = null;
		java.sql.Connection connection = Connection.getConnection();
		try {
			PreparedStatement statement = connection.prepareStatement("Select * from service_mapping where bill = ?");
			statement.setLong(1, id);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				servicemapping.add(loadMaster(resultSet));
//				serviceMapping = loadMaster(resultSet);
			}
			resultSet.close();
			statement.close();
			return servicemapping;
		} catch (Exception e) {
			e.printStackTrace(System.out);
		} finally {
			try {
				connection.close();
			} catch (Exception e) {
				e.printStackTrace(System.out);
			}
		}
		return servicemapping;
	}

	private ServiceMapping loadMaster(ResultSet resultSet) throws SQLException {
		ServiceMasterService service = new ServiceMasterService();
		BillService billservice = new BillService();
		
		return new ServiceMapping(resultSet.getLong("id"),service.get(resultSet.getLong("service")),
				billservice.get(resultSet.getLong("bill")));
	}

}
