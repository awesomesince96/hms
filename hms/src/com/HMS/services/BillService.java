package com.HMS.services;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.HMS.commonUtills.Connection;
import com.HMS.models.Bill;
import com.HMS.models.PatientMaster;
import com.HMS.models.Bill;
import com.HMS.models.VisitMaster;

public class BillService {
	
	public void add(Bill bill) throws Exception{
		java.sql.Connection connection = Connection.getConnection();
		try {
			PreparedStatement prepareStatement = connection.prepareStatement("insert into bill(visit,user,amount) values(?,?,?)");
			prepareStatement.setLong(1,bill.getVisit().getVid());
			prepareStatement.setLong(2,bill.getUser().getId());
			prepareStatement.setDouble(3,bill.getAmount());
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
	
	public Bill get(long id) {
		Bill bill = null;
		java.sql.Connection connection = Connection.getConnection();
		try {
			PreparedStatement statement = connection.prepareStatement("Select * from bill where visit = ?");
			statement.setLong(1, id);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				bill = loadMaster(resultSet);
				break;
			}
			resultSet.close();
			statement.close();
			return bill;
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
	
	public long getVisitId(long id) {
		long billid = 0;
		java.sql.Connection connection = Connection.getConnection();
		try {
			PreparedStatement statement = connection.prepareStatement("Select id from bill where visit = ?");
			statement.setLong(1, id);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
//				bill = loadMaster(resultSet);
				billid = resultSet.getLong("id");
				break;
			}
			resultSet.close();
			statement.close();
			return billid;
		} catch (Exception e) {
			e.printStackTrace(System.out);
		} finally {
			try {
				connection.close();
			} catch (Exception e) {
				e.printStackTrace(System.out);
			}
		}
		return billid;
	}
	
	public void update(Bill bill) {
        java.sql.Connection connection = Connection.getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement("update bill set amount=? where id = ?");
            statement.setDouble(1, bill.getAmount());
            statement.setLong(2, bill.getId());
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
	
	public List<Bill> getAll() {
        List<Bill> billList=new ArrayList<>();
        java.sql.Connection connection = Connection.getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement("Select * from bill");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
            	billList.add(loadMaster(resultSet));
            }
            resultSet.close();
            return billList;
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
        return billList;
    }
	
	private Bill loadMaster(ResultSet resultSet) throws SQLException {
		UserServices userservice = new UserServices();
		VisitService visitservice = new VisitService();
		
		return new Bill(resultSet.getLong("id"),visitservice.getById(resultSet.getLong("visit")),userservice.get(resultSet.getLong("user")),resultSet.getDouble("amount"));
	}

}
