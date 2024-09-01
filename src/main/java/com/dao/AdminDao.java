package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.apache.catalina.filters.AddDefaultCharsetFilter;

import com.bean.Policy;
import com.bean.Underwriter;
import com.util.DBUtil;

public class AdminDao {

	// Method to return total number of underwriters
	public int getTotalUnderwriters() {
		int totalUnderwriters = 0;
		Connection connection = DBUtil.createConnection();
		String sql = "SELECT COUNT(*) FROM underwriter";
		try (PreparedStatement pstmt = connection.prepareStatement(sql); ResultSet rs = pstmt.executeQuery()) {
			if (rs.next()) {
				totalUnderwriters = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return totalUnderwriters;
	}

	// Method to return total number of vehicles
	public int getTotalVehicles() {
		int totalVehicles = 0;
		Connection connection = DBUtil.createConnection();
		String sql = "SELECT COUNT(*) FROM vehicle";
		try (PreparedStatement pstmt = connection.prepareStatement(sql); ResultSet rs = pstmt.executeQuery()) {
			if (rs.next()) {
				totalVehicles = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return totalVehicles;
	}

	// Existing method to delete policy by ID of underwriter
	public boolean deleteUnderwriter(int underwriterId) {
		Connection connection = DBUtil.createConnection();
		String sql = "DELETE FROM UnderWriter WHERE underwriterId = ?";
		try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
			pstmt.setInt(1, underwriterId);
			int affectedRows = pstmt.executeUpdate();
			return affectedRows > 0;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	// register new underwriter
	public int registerUnderwriter(Underwriter underwriter) {
	    int generatedId = -1; // Default to -1 to indicate failure
	    
	    try {
	        String sql = "INSERT INTO UnderWriter (name, dob, joiningDate, password) VALUES (?, ?, ?, ?)";
	        Connection connection = DBUtil.createConnection();
	        PreparedStatement pstmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

	        // Set values for the underwriter
	        pstmt.setString(1, underwriter.getName());
	        pstmt.setDate(2, java.sql.Date.valueOf(underwriter.getDob()));
	        pstmt.setDate(3, java.sql.Date.valueOf(underwriter.getJoiningDate()));
	        pstmt.setString(4, underwriter.getPassword());

	        int affectedRows = pstmt.executeUpdate();

	        if (affectedRows == 0) {
	            return generatedId; // Return -1 if no rows were affected
	        }

	        // Retrieve the generated underwriter ID
	        try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
	            if (generatedKeys.next()) {
	                generatedId = generatedKeys.getInt(1);
	                underwriter.setUnderwriterId(generatedId); // Set the ID in the underwriter object
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    
	    return generatedId; // Return the new ID or -1 if there was an error
	}

	
	//search underwriter details by ID
	public Underwriter getUnderwriterById(int underwriterId) {
        Connection connection = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Underwriter underwriter = null;

        try {
            connection = DBUtil.createConnection();
            String sql = "SELECT * FROM UnderWriter WHERE underwriterId = ?";
            pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, underwriterId);

            rs = pstmt.executeQuery();

            if (rs.next()) {
                underwriter = new Underwriter();
                underwriter.setUnderwriterId(rs.getInt("underwriterId"));
                underwriter.setName(rs.getString("name"));
                underwriter.setDob(rs.getDate("dob").toLocalDate());
                underwriter.setJoiningDate(rs.getDate("joiningDate").toLocalDate());
                underwriter.setPassword(rs.getString("password"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.closeAllConnection(connection, pstmt, rs);
        }

        return underwriter;
    }
	
	//update underwriter password by ID
	public int updateUnderwriterPassword(int underwriterId, String newPassword) {
	    Connection connection = null;
	    PreparedStatement pstmt = null;
	    ResultSet rs = null;
	    int result = 0; // Default to 0, which represents no operation

	    try {
	        connection = DBUtil.createConnection();

	        // Check if the underwriter exists and fetch the current password
	        String checkSql = "SELECT password FROM UnderWriter WHERE underwriterId = ?";
	        pstmt = connection.prepareStatement(checkSql);
	        pstmt.setInt(1, underwriterId);
	        rs = pstmt.executeQuery();

	        if (rs.next()) {
	            String currentPassword = rs.getString("password");

	            // Check if the new password is the same as the old password
	            if (newPassword.equals(currentPassword)) {
	                result = 1; // Same as old password
	            } else {
	                // Update the password
	                String updateSql = "UPDATE UnderWriter SET password = ? WHERE underwriterId = ?";
	                pstmt = connection.prepareStatement(updateSql);
	                pstmt.setString(1, newPassword);
	                pstmt.setInt(2, underwriterId);

	                int affectedRows = pstmt.executeUpdate();
	                if (affectedRows > 0) {
	                    result = 3; // Success
	                }
	            }
	        } else {
	            result = 2; // ID not found
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        DBUtil.closeAllConnection(connection, pstmt, rs);
	    }

	    return result;
	}
	
	//view all underwriters with their details
	public List<Underwriter> getAllUnderwriters() {
        List<Underwriter> underwriters = new ArrayList<>();
        Connection connection = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            connection = DBUtil.createConnection();
            String sql = "SELECT underwriterId, name, dob, joiningDate, password FROM UnderWriter";
            pstmt = connection.prepareStatement(sql);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                Underwriter underwriter = new Underwriter();
                underwriter.setUnderwriterId(rs.getInt("underwriterId"));
                underwriter.setName(rs.getString("name"));
                underwriter.setDob(rs.getDate("dob").toLocalDate());
                underwriter.setJoiningDate(rs.getDate("joiningDate").toLocalDate());
                underwriter.setPassword(rs.getString("password"));
                
                underwriters.add(underwriter);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.closeAllConnection(connection, pstmt, rs);
        }

        return underwriters;
    }
	
	//view all vehicles for underwriterID
	public List<Policy> getVehiclesByUnderwriterId(int underwriterId) {
	    Connection connection = null;
	    PreparedStatement pstmt = null;
	    ResultSet rs = null;
	    List<Policy> vehicles = new ArrayList<>();

	    try {
	        connection = DBUtil.createConnection();
	        String sql = "SELECT * FROM Vehicle WHERE underwriterId = ?";
	        pstmt = connection.prepareStatement(sql);
	        pstmt.setInt(1, underwriterId);

	        rs = pstmt.executeQuery();

	        while (rs.next()) {
	        	Policy vehicle = new Policy();
	        	vehicle.setPolicyNo(rs.getInt("policyNo"));
	            vehicle.setVehicleNo(rs.getString("vehicleNo"));
	            vehicle.setVehicleType(rs.getString("vehicleType"));
	            vehicle.setCustomerName(rs.getString("customerName"));
	            vehicle.setEngineNo(rs.getInt("engineNo"));
	            vehicle.setChasisNo(rs.getInt("chasisNo"));
	            vehicle.setPhoneNo(rs.getString("phoneNo"));
	            vehicle.setInsuranceType(rs.getString("insuranceType"));
	            vehicle.setPremiumAmt(rs.getDouble("premiumAmt"));
	            vehicle.setFromDate(rs.getDate("fromDate").toLocalDate());
	            vehicle.setToDate(rs.getDate("toDate").toLocalDate());
	            vehicle.setUnderwriterId(rs.getInt("underwriterId"));
	            vehicles.add(vehicle);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        DBUtil.closeAllConnection(connection, pstmt, rs);
	    }

	    return vehicles;
	}


}