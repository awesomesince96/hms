package com.HMS.services;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import com.HMS.commonUtills.Connection;
import com.HMS.commonUtills.vStatus;
import com.HMS.models.CityMaster;
import com.HMS.models.ContryMaster;
import com.HMS.models.PatientMaster;
import com.HMS.models.StateMaster;
import com.HMS.models.User;
import com.HMS.models.VisitMaster;

public class VisitService {
	
	public int add(VisitMaster visitMaster ) throws Exception{
		java.sql.Connection connection = Connection.getConnection();
		int key = 0;
		try {
			PreparedStatement prepareStatement = connection.prepareStatement("insert into visit(vstatus,dov,uid,pid) values(?,?,?,?)",Statement.RETURN_GENERATED_KEYS);
			prepareStatement.setInt(1, visitMaster.getVstatus().getId());
			prepareStatement.setTimestamp(2,visitMaster.getDov());
			prepareStatement.setLong(3,visitMaster.getUser().getId());
			prepareStatement.setLong(4,visitMaster.getPatient().getPid());
			prepareStatement.executeUpdate();
			ResultSet rs = prepareStatement.getGeneratedKeys();
			rs.next();
		    key = rs.getInt(1);
			System.out.println("the key is : "+key);
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
		return key;
	}

	public List<VisitMaster> checkopen(long id) {
		System.out.println("pid :: "+id);
        java.sql.Connection connection = Connection.getConnection();
        List<VisitMaster> visitList = new ArrayList<VisitMaster>();
        try {
            PreparedStatement statement = connection.prepareStatement("select * from visit where pid = ? and vstatus = 1");
            statement.setLong(1, id);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
				visitList.add(loadMaster(rs));
			}
//            if (!rs.next() ) {
//                result = 0;
//                System.out.println("nah");
//            }else
//            {
//            	result = 1;
//            	System.out.println("something");
//            }
//           
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
		return visitList;
    }
	
	public List<VisitMaster> getAll() {
			
			List<VisitMaster> visitList = new ArrayList<VisitMaster>();
			java.sql.Connection connection = Connection.getConnection();
			
			try {

				PreparedStatement prepareStatement = connection.prepareStatement("select * from visit order by vid DESC");
				ResultSet resultSet = prepareStatement.executeQuery();
				while (resultSet.next()) {
					visitList.add(loadMaster(resultSet));
				}
				resultSet.close();
				return visitList;
			} catch (Exception e) {
				e.printStackTrace(System.out);
			} finally {
				try {
					connection.close();
				} catch (Exception e) {
					e.printStackTrace(System.out);
				}
			}
			return visitList;
	}
	
	
	private VisitMaster loadMaster(ResultSet resultSet) throws SQLException {
		CityMasterService cityservice = new CityMasterService();
		StateMasterService stateservice = new StateMasterService();
		ContryMasterService countryservice = new ContryMasterService();
		UserServices userservice = new UserServices();
		PatientServices patientservice = new PatientServices();
	    java.sql.Date date=new java.sql.Date(System.currentTimeMillis());   
		
		return new VisitMaster(resultSet.getLong("vid"),vStatus.fromId(resultSet.getInt("vstatus")),
				resultSet.getTimestamp("dov"),userservice.get(resultSet.getLong("uid")),
				patientservice.get(resultSet.getLong("pid")));
				
	}
	
	public void closeVisit(long id) {
        java.sql.Connection connection = Connection.getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement("update visit set vstatus = 2 where vid = ?");
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
	
	public VisitMaster getById(long id) {
		VisitMaster visitmaster = null;
		java.sql.Connection connection = Connection.getConnection();
		try {
			PreparedStatement statement = connection.prepareStatement("Select * from visit where vid = ?");
			statement.setLong(1, id);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				visitmaster = loadMaster(resultSet);
				break;
			}
			resultSet.close();
			statement.close();
			return visitmaster;
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
	
	public User get(int convertStringToInt) {
		// TODO Auto-generated method stub
		return null;
	}

	

}
