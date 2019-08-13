package com.HMS.services;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.HMS.commonUtills.Connection;
import com.HMS.commonUtills.Status;
import com.HMS.models.ContryMaster;

public class ContryMasterService {

	public void add(ContryMaster contryMaster) {
		java.sql.Connection connection = Connection.getConnection();
		try {
			PreparedStatement prepareStatement = connection.prepareStatement("insert into contry(name,status)values(?,?)");
			prepareStatement.setString(1, contryMaster.getName());
			prepareStatement.setInt(2, contryMaster.getStatus().getId());
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
            PreparedStatement statement = connection.prepareStatement("update contry set status = 1 where id = ?");
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
            PreparedStatement statement = connection.prepareStatement("update contry set status = 2 where id = ?");
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
    
    public void update(ContryMaster contryMaster) {
        java.sql.Connection connection = Connection.getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement("update contry set name = ?, status = ? where id = ?");
            statement.setString(1, contryMaster.getName());
            statement.setInt(2, contryMaster.getStatus().getId());
            statement.setLong(3,contryMaster.getId() );
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

    public List<ContryMaster> getAll() {
        List<ContryMaster> contryList = new ArrayList<ContryMaster>();
        java.sql.Connection connection = Connection.getConnection();
        try {
        	
        	PreparedStatement prepareStatement = connection.prepareStatement("select * from contry order by id");
			ResultSet resultSet = prepareStatement.executeQuery();
            while (resultSet.next()) {
            	contryList.add(loadContryMaster(resultSet));
            }
            resultSet.close();
            return contryList;
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
        return contryList;
    }

    public ContryMaster get(long id) {
        ContryMaster contryMaster = null;
        java.sql.Connection connection = Connection.getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement("Select * from  contry where id = ?");
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                contryMaster = loadContryMaster(resultSet);
                break;
            }
            resultSet.close();
            statement.close();
            return contryMaster;
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
    

    public List<ContryMaster> getCountryBystatus() {
        List<ContryMaster> contryList=new ArrayList<>();
        java.sql.Connection connection = Connection.getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement("Select * from  contry where status = 1");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
            	contryList.add(loadContryMaster(resultSet));
            }
            resultSet.close();
            return contryList;
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
        return contryList;
    }

    private ContryMaster loadContryMaster(ResultSet resultSet) throws SQLException {
		return new ContryMaster(resultSet.getLong("id"),
				resultSet.getString("name"),
				Status.fromId(resultSet.getInt("status"))
				);
	}

}
