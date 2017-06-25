package service;

import java.sql.SQLException;

import dao.LoginDao;
import domain.TUser;

public class LoginService {

	public TUser find(String username, String password) throws SQLException {
		LoginDao ld=new LoginDao();
		return ld.find(username,password);
	}

}
