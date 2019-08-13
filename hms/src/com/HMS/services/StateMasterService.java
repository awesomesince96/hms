package com.HMS.services;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.HMS.commonUtills.Connection;
import com.HMS.commonUtills.Status;
import com.HMS.models.StateMaster;

public class StateMasterService {
	
	ContryMasterService contryMasterService = new ContryMasterService();

	public void add(StateMaster stateMaster) {
		java.sql.Connection connection = Connection.getConnection();
		try {
			
			PreparedStatement prepareStatement = connection.prepareStatement("insert into state(name,status,contry)values(?,?,?)");
			prepareStatement.setString(1, stateMaster.getName());
			prepareStatement.setInt(2, stateMaster.getStatus().getId());
			prepareStatement.setLong(3,stateMaster.getContryMaster().getId());
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
            PreparedStatement statement = connection.prepareStatement("update state set status = 1 where id = ?");
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
            PreparedStatement statement = connection.prepareStatement("update state set status = 2 where id = ?");
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
    
    public void update(StateMaster stateMaster) {
        java.sql.Connection connection = Connection.getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement("update state set name = ?, status = ?,contry = ? where id = ?");
            statement.setString(1, stateMaster.getName());
            statement.setInt(2, stateMaster.getStatus().getId());
            statement.setLong(3, stateMaster.getContryMaster().getId());
            statement.setLong(4,stateMaster.getId());
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

    public List<StateMaster> getAll() {
        List<StateMaster> stateList = new ArrayList<StateMaster>();
        java.sql.Connection connection = Connection.getConnection();
        try {
        	
        	PreparedStatement prepareStatement = connection.prepareStatement("select * from state order by id");
			ResultSet resultSet = prepareStatement.executeQuery();
            while (resultSet.next()) {
            	stateList.add(loadStateMaster(resultSet));	
            }
            resultSet.close();
            return stateList;
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
        return stateList;
    }

	public StateMaster get(long id) {
        StateMaster stateMaster = null;
        java.sql.Connection connection = Connection.getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement("Select * from  state where id = ?");
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
            	stateMaster = loadStateMaster(resultSet);
                break;
            }
            resultSet.close();
            statement.close();
            return stateMaster;
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
    

    public List<StateMaster> getStateBystatus(Long countryid) {
        List<StateMaster> stateList = new ArrayList<StateMaster>();
        java.sql.Connection connection = Connection.getConnection();
      
        try {
            PreparedStatement statement = connection.prepareStatement("Select * from state where status = 1 and contry = ?");
            statement.setLong(1, countryid);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
            	stateList.add(loadStateMaster(resultSet));
            }
            resultSet.close();
            return stateList;
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
        return stateList;
    }

    private StateMaster loadStateMaster(ResultSet resultSet) throws SQLException {
    	return new StateMaster(resultSet.getLong("id"),resultSet.getString("name"),Status.fromId(resultSet.getInt("status")),contryMasterService.get(resultSet.getLong("contry")));
	}

}
