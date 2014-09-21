package SQL;

import java.sql.*;
import java.util.*;

public class SQLConnection {
	
	public static Connection getConnection(String dbName, String userName, String password)  {
		Connection conn = null;
		Properties connectionProps = new Properties();
		connectionProps.put("user", userName);
		connectionProps.put("password", password);
		try {
		conn = DriverManager.getConnection(
				"jdbc:mysql://localhost:3306/" + dbName,
				connectionProps);
		} catch (SQLException ex){
			PrintSQLEX.printSQLException(ex);
		} 		
		return conn;
	}
}
