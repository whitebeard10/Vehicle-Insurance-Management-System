package com.dao;

import com.bean.Policy;
import com.util.DBUtil;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PolicyDao {

	private Connection connection;

	public PolicyDao() {
		connection = DBUtil.createConnection();
	}

	public int  createPolicy(Policy policy, int underwriterId) {
		int newPolicyId = -1;
		try {
			// Calculate premium amount
			String sql = "INSERT INTO vehicle (vehicleNo, vehicleType, customerName, engineNo, chasisNo, phoneNo, insuranceType, premiumAmt, fromDate, toDate, underwriterId) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			Connection connection = DBUtil.createConnection();
			PreparedStatement pstmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			double premiumAmt = calculatePremium(policy.getVehicleType(), policy.getInsuranceType());
			policy.setPremiumAmt(premiumAmt);

			// Set to_date as 1 year from from_date
			LocalDate fromDate = policy.getFromDate();
			LocalDate toDate = fromDate.plusYears(1);
			policy.setToDate(toDate);

			pstmt.setString(1, policy.getVehicleNo());
			pstmt.setString(2, policy.getVehicleType());
			pstmt.setString(3, policy.getCustomerName());
			pstmt.setInt(4, policy.getEngineNo());
			pstmt.setInt(5, policy.getChasisNo());
			pstmt.setString(6, policy.getPhoneNo());
			pstmt.setString(7, policy.getInsuranceType());
			pstmt.setDouble(8, policy.getPremiumAmt());
			pstmt.setDate(9, java.sql.Date.valueOf(policy.getFromDate()));
			pstmt.setDate(10, java.sql.Date.valueOf(policy.getToDate()));
			pstmt.setInt(11, underwriterId);

			int affectedRows = pstmt.executeUpdate();

			if (affectedRows > 0) {
                ResultSet generatedKeys = pstmt.getGeneratedKeys();
                if (generatedKeys.next()) {
                    newPolicyId = generatedKeys.getInt(1); // Retrieve the generated ID
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return newPolicyId;
    }


	private double calculatePremium(String vehicleType, String insuranceType) {
		if ("2-wheeler".equals(vehicleType)) {
			if ("Full Insurance".equalsIgnoreCase(insuranceType)) {
				return 2000;
			} else if ("ThirdParty".equalsIgnoreCase(insuranceType)) {
				return 3000;
			}
		} else if ("4-wheeler".equalsIgnoreCase(vehicleType)) {
			if ("Full Insurance".equalsIgnoreCase(insuranceType)) {
				return 4000;
			} else if ("ThirdParty".equalsIgnoreCase(insuranceType)) {
				return 5000;
			}
		}
		// Default case, should not reach here if input is valid
		throw new IllegalArgumentException("Invalid vehicle type or insurance type");
	}

	public Policy getPolicyById(int policyId) {
		String sql = "SELECT * FROM vehicle WHERE policyNo = ?";
		try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
			pstmt.setInt(1, policyId);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				return extractPolicyFromResultSet(rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public List<Policy> getAllPolicies() {
		List<Policy> policies = new ArrayList<>();
		String sql = "SELECT * FROM vehicle";
		try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
			while (rs.next()) {
				policies.add(extractPolicyFromResultSet(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return policies;
	}

	public Policy renewPolicy(int policyId, int underwriterId) {
		Policy updatedPolicy = null;
		try {
			// Fetch existing policy details
			Connection connection = DBUtil.createConnection();
			String fetchSql = "SELECT * FROM vehicle WHERE policyNo = ? AND underwriterId = ?";
			PreparedStatement fetchStmt = connection.prepareStatement(fetchSql);
			fetchStmt.setInt(1, policyId);
			fetchStmt.setInt(2, underwriterId);
			ResultSet rs = fetchStmt.executeQuery();

			if (rs.next()) {
				// Policy found, calculate new dates and premium
				double currentPremium = rs.getDouble("premiumAmt");
				double newPremium = currentPremium * 1.05; // Increase by 5%
				LocalDate currentDate = LocalDate.now();
				LocalDate oldToDate = rs.getDate("toDate").toLocalDate();
				LocalDate newFromDate = currentDate.isAfter(oldToDate) ? currentDate : oldToDate.plusDays(1);
				LocalDate newToDate = newFromDate.plusYears(1);

				// Create a new Policy object with updated details
				Policy policy = new Policy();
				policy.setVehicleNo(rs.getString("vehicleNo"));
				policy.setVehicleType(rs.getString("vehicleType"));
				policy.setCustomerName(rs.getString("customerName"));
				policy.setEngineNo(rs.getInt("engineNo"));
				policy.setChasisNo(rs.getInt("chasisNo"));
				policy.setPhoneNo(rs.getString("phoneNo"));
				policy.setInsuranceType(rs.getString("insuranceType"));
				policy.setPremiumAmt(newPremium);
				policy.setFromDate(newFromDate);
				policy.setToDate(newToDate);
				policy.setUnderwriterId(underwriterId);

				// Insert new policy record with updated details
				String insertSql = "INSERT INTO vehicle (vehicleNo, vehicleType, customerName, engineNo, chasisNo, phoneNo, insuranceType, premiumAmt, fromDate, toDate, underwriterId) "
						+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
				PreparedStatement insertStmt = connection.prepareStatement(insertSql, Statement.RETURN_GENERATED_KEYS);
				insertStmt.setString(1, policy.getVehicleNo());
				insertStmt.setString(2, policy.getVehicleType());
				insertStmt.setString(3, policy.getCustomerName());
				insertStmt.setInt(4, policy.getEngineNo());
				insertStmt.setInt(5, policy.getChasisNo());
				insertStmt.setString(6, policy.getPhoneNo());
				insertStmt.setString(7, policy.getInsuranceType());
				insertStmt.setDouble(8, newPremium);
				insertStmt.setDate(9, java.sql.Date.valueOf(newFromDate));
				insertStmt.setDate(10, java.sql.Date.valueOf(newToDate));
				insertStmt.setInt(11, underwriterId);

				int affectedRows = insertStmt.executeUpdate();

				if (affectedRows > 0) {
					// Fetch the newly inserted policy details
					try (ResultSet generatedKeys = insertStmt.getGeneratedKeys()) {
						if (generatedKeys.next()) {
							int newPolicyId = generatedKeys.getInt(1);
							updatedPolicy = getPolicyById(newPolicyId);
						}
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return updatedPolicy;
	}

	public Policy updatePolicyType(int policyId, int underwriterId) {
		Policy updatedPolicy = null;
		try {
			// Fetch existing policy details
			Connection connection = DBUtil.createConnection();
			String fetchSql = "SELECT * FROM vehicle WHERE policyNo = ? AND underwriterId = ?";
			PreparedStatement fetchStmt = connection.prepareStatement(fetchSql);
			fetchStmt.setInt(1, policyId);
			fetchStmt.setInt(2, underwriterId);
			ResultSet rs = fetchStmt.executeQuery();

			if (rs.next()) {
				String currentInsuranceType = rs.getString("insuranceType");

				if ("Full Insurance".equalsIgnoreCase(currentInsuranceType)) {
					throw new IllegalStateException("Policy is already Full Insurance.");
				}

				// Policy found and needs to be updated
				double currentPremium = rs.getDouble("premiumAmt");
				double newPremium = currentPremium + 2000; // Increase for full insurance
				String newInsuranceType = "Full Insurance";

				// Update the policy
				String updateSql = "UPDATE vehicle SET insuranceType = ?, premiumAmt = ? WHERE policyNo = ?";
				PreparedStatement updateStmt = connection.prepareStatement(updateSql);
				updateStmt.setString(1, newInsuranceType);
				updateStmt.setDouble(2, newPremium);
				updateStmt.setInt(3, policyId);

				int affectedRows = updateStmt.executeUpdate();

				if (affectedRows > 0) {
					updatedPolicy = getPolicyById(policyId);
				}
			} else {
				throw new IllegalArgumentException("No policy found with the given ID for this underwriter.");
			}
		} catch (SQLException | IllegalStateException | IllegalArgumentException e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		}

		return updatedPolicy;
	}

	public Policy viewPolicy(int policyId) {
		Policy viewPolicy = null;
		String qString = "SELECT * FROM VEHICLE WHERE policyNo = ?";
		try (Connection connection = DBUtil.createConnection();
				PreparedStatement pstmt = connection.prepareStatement(qString)) {
			pstmt.setInt(1, policyId);
			try (ResultSet rSet = pstmt.executeQuery()) {
				if (rSet.next()) {
					viewPolicy = extractPolicyFromResultSet(rSet);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return viewPolicy;

	}

	

	private Policy extractPolicyFromResultSet(ResultSet rs) throws SQLException {
		Policy policy = new Policy();
		policy.setPolicyNo(rs.getInt("policyNo"));
		policy.setVehicleNo(rs.getString("vehicleNo"));
		policy.setVehicleType(rs.getString("vehicleType"));
		policy.setCustomerName(rs.getString("customerName"));
		policy.setEngineNo(rs.getInt("engineNo"));
		policy.setChasisNo(rs.getInt("chasisNo"));
		policy.setPhoneNo(rs.getString("phoneNo"));
		policy.setInsuranceType(rs.getString("insuranceType"));
		policy.setPremiumAmt(rs.getDouble("premiumAmt"));
		policy.setFromDate(rs.getDate("fromDate").toLocalDate());
		policy.setToDate(rs.getDate("toDate").toLocalDate());
		policy.setUnderwriterId(rs.getInt("underwriterId"));
		return policy;
	}
}