package com.avm.controller;

import jakarta.servlet.RequestDispatcher;
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
		String address = request.getParameter("address");
		
		RegisterModel rm = new RegisterModel();
		rm.setUsername(username);
		rm.setPassword(password);
		rm.setPhonenumber(phonenumber);
		rm.setGender(gender);
		rm.setEmail(email);
		rm.setAddress(address);
		
		UserDAOInterface r = new AVMImplementation();
		String status = r.userRegisteration(rm);
		System.out.println(status);
		
		if(status.contains("row(s) inserted")) {
			RequestDispatcher rd = request.getRequestDispatcher("LogIn.html");
			rd.forward(request, response);
		}
		else {
			RequestDispatcher rd = request.getRequestDispatcher("Register.html");
			rd.forward(request, response);
		}
		
	}

}
