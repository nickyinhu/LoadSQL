package SQL;

import java.io.*;
import java.sql.*;

public class Main {
	private String dbName;
	private String tableName;
	private File file;

	public static void main(String[] args){
		String dbName = "MyGene";
	    String tableName = "table1";
	    File file = new File("result.txt");
	    Main go = new Main();
		go.setUp(dbName, tableName, file);
	}
	
	
	public void setUp(String dbName, String tableName, File file){
		this.dbName = dbName;
	    this.tableName = tableName;
	    this.file = file;
	    
		String createString =
	            "create table IF NOT EXISTS " +
	            		tableName +
	            "(id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,"
	            + "gene_ID varchar(20) NOT NULL," 
	            + "Ajellomyces varchar(20) NOT NULL,"
	            + "Exophiala varchar(20) NOT NULL,"
	            + "Fusarium varchar(20) NOT NULL,"
	            + "Alias varchar(20))";
	    String deleteString = "Drop table table1";

	    FileReader fr = null;
		try {	
			//
		    System.out.println("Please enter your user name");
		    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		    String user = br.readLine().trim();
		    if(user.length() == 0){
		    	System.out.println("Please enter a valid user name, System exited");
		    	System.exit(0); 
		    }
		    System.out.println("Please enter your user password");
		    BufferedReader br2 = new BufferedReader(new InputStreamReader(System.in));
		    
		    String pswd = br2.readLine().trim();
			fr = new FileReader(file);
			BufferedReader reader = new BufferedReader(fr); 
			System.out.println("-----Data File Opened-----");
			
			Connection conn = SQLConnection.getConnection(dbName, user, pswd);
			System.out.println("Database connected");
			DeleteTable.deleteTable(deleteString, conn);
			CreateTable.createTable(tableName, createString, conn);
			String line = null;
			System.out.println("Data updating------------------------ Please wait");
			while(( line = reader.readLine())!=null){
				String[] set = line.trim().split("\t");
				
				String query = "insert into table1 values(null, \"";
				for(int i = 0;i<set.length-1;i++){
					query += set[i] + "\",\"";
				}

				query += set[set.length-1] + "\"";
				if(set.length < 5){
					query += ",\"-\"";
				}
				query += ")";
				
				PopulateTable.populateTable(conn, query);				
			}
			System.out.println("Data updating complete");
			reader.close();
			conn.close();
			System.out.println("Database disconnected");
		} catch (SQLException ex){
			PrintSQLEX.printSQLException(ex);
		} catch (FileNotFoundException fex){
			System.out.println("File not found");
		} catch (IOException ioex) {
			System.out.println("File not closed");
		} finally {
			if (fr != null){
				try {
					fr.close();
					System.out.println("-----Data File Closed-----");
				} catch (IOException ex){
					System.out.println("File cannot be closed");
				}
			}
		}
	}
}
