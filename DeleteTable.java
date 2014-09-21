package SQL;

import java.sql.*;

public class DeleteTable {
	
	public static void deleteTable(String deleteString, Connection conn) {
		Statement stmt = null;
		try 
		{
			stmt = conn.createStatement();
			stmt.executeUpdate(deleteString);
			System.out.println("Table deleted");
		} catch (SQLException ex){
			System.out.println("Deletion error");
			PrintSQLEX.printSQLException(ex);
		}
	}
}
