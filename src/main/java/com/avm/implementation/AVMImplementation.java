package com.avm.implementation;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import com.avm.DAO.UserDAOInterface;
import com.avm.controller.RegisterModel;
import com.mysql.cj.jdbc.CallableStatement;

public class AVMImplementation implements UserDAOInterface {

	static Connection con = null;

	@Override
	public String validateLogin(RegisterModel rm) {
		String s = "Fail";
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/project","root","root");
			
			String username = rm.getUsername();
			String password = rm.getPassword();
			CallableStatement cs = (CallableStatement) con.prepareCall("{CALL encrypt_password(?)}");
			cs.setString(1, password);
			ResultSet rs1 = cs.executeQuery(); 
			while(rs1.next()) {
				password = rs1.getString(1);
				System.out.println(password);
			}
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM users;");
			while(rs.next()) {
				
				if(username.equalsIgnoreCase(rs.getString(2)) && password.equalsIgnoreCase(rs.getString(5))) {
					s = "Success";
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return s;
	}

	@Override
	public String userRegisteration(RegisterModel rm) {

		String s = "";

		try {

			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/project", "root", "root");
			String query = "INSERT INTO users (full_name, email, phone, password_hash, address, gender) VALUES (?, ?, ?, SHA2(?, 256), ?, ?)";
			PreparedStatement ps = con.prepareStatement(query);
			ps.setString(1, rm.getUsername());
			ps.setString(2, rm.getEmail());
			ps.setString(3, rm.getPhonenumber());
			ps.setString(4, rm.getPassword());
			ps.setString(5, rm.getAddress());
			ps.setString(6, rm.getGender());
			System.out.println(query);
			int row = ps.executeUpdate();

			s = row + " row(s) inserted";
		} catch (Exception e) {
			e.printStackTrace();
		}

		return s;
	}

}
