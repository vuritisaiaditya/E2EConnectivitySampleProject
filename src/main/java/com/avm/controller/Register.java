package com.avm.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.avm.DAO.UserDAOInterface;
import com.avm.implementation.AVMImplementation;

@WebServlet("/Register")
public class Register extends HttpServlet {
	//private static final long serialVersionUID = 1L;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("Login Servelet");
		String firstname = request.getParameter("firstname");
		String lastname = request.getParameter("lastname");
		String username = firstname+" "+lastname;
		String password = request.getParameter("password");
		String email = request.getParameter("email");
		String phonenumber = request.getParameter("phonenumber");
		String gender = request.getParameter("gender");
		
		RegisterModel rm = new RegisterModel();
		rm.setUsername(username);
		rm.setPassword(password);
		rm.setPhonenumber(phonenumber);
		rm.setGender(gender);
		rm.setEmail(email);
		
		UserDAOInterface r = new AVMImplementation();
		System.out.println(r.userRegisteration(rm));
		
	}

}
