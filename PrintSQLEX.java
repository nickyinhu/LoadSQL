package SQL;

import java.sql.*;

public class PrintSQLEX {
	public static void printSQLException (SQLException ex){
		System.out.print("Message: " + ex.getMessage());
	}
}
