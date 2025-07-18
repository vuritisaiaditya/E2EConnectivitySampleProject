package com.avm.DAO;

import com.avm.controller.RegisterModel;

public interface UserDAOInterface {
	
	public String validateLogin(RegisterModel rm);
	public String userRegisteration(RegisterModel rm);
	

}
