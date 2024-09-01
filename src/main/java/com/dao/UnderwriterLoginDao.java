package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.util.DBUtil;

public class UnderwriterLoginDao {
	public int validateLogin(String Username, String Password) {
		int id = -1;
		try {
			Connection con = DBUtil.createConnection();
			String sql = "SELECT * FROM UnderWriter WHERE name = ? AND password = ?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, Username);
			ps.setString(2, Password);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				id = rs.getInt("underwriterId");
			}
//			DBUtil.closeAllConnection(con, ps, rs);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return id;
	}
}