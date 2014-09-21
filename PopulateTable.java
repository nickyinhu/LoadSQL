package SQL;
import java.sql.*;
public class PopulateTable {
	
	public static void populateTable(Connection conn, String updateQuery){
		Statement stmt = null;
		try {
			stmt = conn.createStatement();
			stmt.executeUpdate(updateQuery);
//			System.out.println(updateQuery);
		} catch (SQLException ex){
			PrintSQLEX.printSQLException(ex);
		}
	}
}
