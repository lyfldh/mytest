package dao;

import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import c3p0Utils.C3p0Util;
import domain.TUser;

public class LoginDao {

	public TUser find(String username, String password) throws SQLException {
		QueryRunner qr=new QueryRunner(C3p0Util.getDataSource());
		String sql=" select * from t_user where username=? and password=? ";
		Object[] params={username,password};
		
		return qr.query(sql, new BeanHandler<TUser>(TUser.class), params);
	}

}
