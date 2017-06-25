package c3p0Utils;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class C3p0Util {
	private static ComboPooledDataSource dataSource = new ComboPooledDataSource();
private static ThreadLocal<Connection> local=new ThreadLocal<Connection>();
	public static DataSource getDataSource() {
		return dataSource;
	}
	public static Connection getConnection() throws SQLException {
		Connection con=local.get();
		if(con==null){
			con=dataSource.getConnection();
			local.set(con);
		}
		return con;
	}
}
