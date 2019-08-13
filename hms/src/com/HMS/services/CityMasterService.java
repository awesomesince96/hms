package com.HMS.services;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.HMS.commonUtills.Connection;
import com.HMS.commonUtills.Status;
import com.HMS.models.CityMaster;
import com.HMS.models.Client;

public class CityMasterService {
	
	StateMasterService stateMasterService = new StateMasterService();
	

	public void add(CityMaster cityMaster) {
		java.sql.Connection connection = Connection.getConnection();
		try {
			PreparedStatement prepareStatement = connection.prepareStatement("insert into city(name,status,state)values(?,?,?)");
			prepareStatement.setString(1, cityMaster.getName());
			prepareStatement.setInt(2, cityMaster.getStatus().getId());
			prepareStatement.setLong(3,cityMaster.getStateMaster().getId());
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
            PreparedStatement statement = connection.prepareStatement("update city set status = 1 where id = ?");
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
            PreparedStatement statement = connection.prepareStatement("update city set status = 2 where id = ?");
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
    
    public void update(CityMaster cityMaster) {
        java.sql.Connection connection = Connection.getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement("update city set name = ?, status = ?,state = ? where id = ?");
            statement.setString(1, cityMaster.getName());
            statement.setInt(2, cityMaster.getStatus().getId());
            statement.setLong(3, cityMaster.getStateMaster().getId());
            statement.setLong(4,cityMaster.getId());
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

    public List<CityMaster> getAll() {
        List<CityMaster> cityList = new ArrayList<CityMaster>();
        java.sql.Connection connection = Connection.getConnection();
        try {
        	
        	PreparedStatement prepareStatement = connection.prepareStatement("select * from city order by id");
			ResultSet resultSet = prepareStatement.executeQuery();
            while (resultSet.next()) {
            	cityList.add(loadCityMaster(resultSet));
            }
            resultSet.close();
            return cityList;
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
        return cityList;
    }

	

	public CityMaster get(long id) {
        CityMaster cityMaster= null;
        java.sql.Connection connection = Connection.getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement("Select * from  city where id = ?");
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
            	cityMaster = loadCityMaster(resultSet);
                break;
            }
            resultSet.close();
            statement.close();
            return cityMaster;
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
    

    public List<CityMaster> getCityBystatus(long stateid) {
        List<CityMaster> cityList = new ArrayList<CityMaster>();
        java.sql.Connection connection = Connection.getConnection();
      
        try {
            PreparedStatement statement = connection.prepareStatement("Select * from city where status = 1 and state = ?");
            statement.setLong(1, stateid);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
            	cityList.add(loadCityMaster(resultSet));
            }
            resultSet.close();
            return cityList;
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
        return cityList;
    }

    public CityMaster idtoCity(long id) {
		java.sql.Connection connection = Connection.getConnection();
		CityMaster citymaster = new CityMaster();
		try {

			PreparedStatement prepareStatement = connection.prepareStatement("select * from city where id=?");
			prepareStatement.setLong(1, id);
			ResultSet resultSet = prepareStatement.executeQuery();
			while (resultSet.next()) {
				citymaster = loadCityMaster(resultSet);
			
			}
			resultSet.close();
			prepareStatement.executeUpdate();
			prepareStatement.close();
			return citymaster;
		} catch (SQLException e) {
			e.printStackTrace(System.out);
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace(System.out);
			}
		}
		return citymaster;

	}
    
    private CityMaster loadCityMaster(ResultSet resultSet) throws SQLException {

    	return new CityMaster(resultSet.getLong("id"),resultSet.getString("name"),Status.fromId(resultSet.getInt("status")),stateMasterService.get(resultSet.getLong("state")));
    	
	}
    
    
    
}
