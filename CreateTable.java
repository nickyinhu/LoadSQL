package SQL;

import java.sql.*;

public class CreateTable {
	public static void createTable(String tableName, String createString, Connection conn) 
		throws SQLException {
		Statement stmt = null;
		try {		
			DatabaseMetaData md = conn.getMetaData();
			ResultSet rs = md.getTables(null, null, tableName, null);
			if(rs.next()){
				System.out.println("ERROR!" + rs.getString(3) + " already existed!");
			} else {
				stmt = conn.createStatement();
				stmt.executeUpdate(createString);
				System.out.println("Table " + tableName +" is created");
			}
		} catch (SQLException ex) {
			System.out.println("Creating error");
			PrintSQLEX.printSQLException(ex);
		} finally {
			if(stmt != null) {
				stmt.close();
			}
		}
	}
}
