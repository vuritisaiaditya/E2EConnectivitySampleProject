package com.avm.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.avm.DAO.UserDAOInterface;
import com.avm.implementation.AVMImplementation;


@WebServlet("/ValidLogin")
public class ValidLogin extends HttpServlet {
	//private static final long serialVersionUID = 1L;

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Login Servelet");
		String username = request.getParameter("UserName");
		String password = request.getParameter("Password");
		System.out.println(username);
		
		RegisterModel rm = new RegisterModel();
		rm.setUsername(username);
		rm.setPassword(password);
		
		UserDAOInterface l = new AVMImplementation();
		System.out.println(l.validateLogin(rm));
		
	}

}
