package com.HMS.services;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.HMS.commonUtills.CommonUtills;
import com.HMS.commonUtills.Connection;
import com.HMS.commonUtills.Status;
import com.HMS.models.ContryMaster;
import com.HMS.models.ServiceMaster;
import com.google.gson.Gson;

public class ServiceMasterService {
	
	public void add(ServiceMaster serviceMaster ) throws Exception{
		
		java.sql.Connection connection = Connection.getConnection();
		try {
			PreparedStatement prepareStatement = connection.prepareStatement("insert into service(name,amount,status) values(?,?,?)");
			prepareStatement.setString(1,serviceMaster.getName());
			prepareStatement.setDouble(2,serviceMaster.getAmount());
			prepareStatement.setLong(3, serviceMaster.getStatus().getId());
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
	
	public void active(long id) {
        java.sql.Connection connection = Connection.getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement("update service set status = 1 where id = ?");
            statement.setLong(1, id);
            statement.execute();
            statement.close();
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        finally {
            try {
                connection.close();
            }
            catch (Exception e) {
                e.printStackTrace(System.out);
            }
        }
    }

    public void inactive(long id) {
        java.sql.Connection connection = Connection.getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement("update service set status = 2 where id = ?");
            statement.setLong(1, id);
            statement.execute();
            statement.close();
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        finally {
            try {
                connection.close();
            }
            catch (Exception e) {
                e.printStackTrace(System.out);
            }
        }
    }
    
    public void update(ServiceMaster serviceMaster) {
        java.sql.Connection connection = Connection.getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement("update service set name = ?, amount=?, status = ? where id = ?");
            statement.setString(1, serviceMaster.getName());
            statement.setDouble(2, serviceMaster.getAmount());
            statement.setInt(3, serviceMaster.getStatus().getId());
            statement.setLong(4,serviceMaster.getId() );
            statement.execute();
            statement.close();
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        finally {
            try {
                connection.close();
            }
            catch (Exception e) {
                e.printStackTrace(System.out);
            }
        }
    }

    public List<ServiceMaster> getAll() {
        List<ServiceMaster> serviceList = new ArrayList<ServiceMaster>();
        java.sql.Connection connection = Connection.getConnection();
        try {
        	
        	PreparedStatement prepareStatement = connection.prepareStatement("select * from service order by id");
			ResultSet resultSet = prepareStatement.executeQuery();
            while (resultSet.next()) {
            	serviceList.add(loadServiceMaster(resultSet));
            }
            resultSet.close();
            return serviceList;
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        finally {
            try {
                connection.close();
            }
            catch (Exception e) {
                e.printStackTrace(System.out);
            }
        }
        return serviceList;
    }

    public ServiceMaster get(long id) {
        ServiceMaster serviceMaster = null;
        java.sql.Connection connection = Connection.getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement("Select * from  service where id = ?");
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                serviceMaster = loadServiceMaster(resultSet);
                break;
            }
            resultSet.close();
            statement.close();
            return serviceMaster;
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        finally {
            try {
                connection.close();
            }
            catch (Exception e) {
                e.printStackTrace(System.out);
            }
        }
        return null;
    }
    

    public List<ServiceMaster> getServiceBystatus() {
        List<ServiceMaster> serviceList=new ArrayList<>();
        java.sql.Connection connection = Connection.getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement("Select * from service where status = 1");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
            	serviceList.add(loadServiceMaster(resultSet));
            }
            resultSet.close();
            return serviceList;
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        finally {
            try {
                connection.close();
            }
            catch (Exception e) {
                e.printStackTrace(System.out);
            }
        }
        return serviceList;
    }

    private ServiceMaster loadServiceMaster(ResultSet resultSet) throws SQLException {
		return new ServiceMaster(resultSet.getLong("id"),
				resultSet.getString("name"),
				resultSet.getDouble("amount"),
				Status.fromId(resultSet.getInt("status"))
				);
	}

}
