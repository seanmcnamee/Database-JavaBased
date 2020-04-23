package app.backendSQL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

public class Queries {

	private static final String TAG = "Queries: ";

	private final static String DB_URL = "jdbc:mariadb://23.236.194.106:3306/csci300?user=csciremote&password=deltabravo2020";

	private static Connection conn;

	public static void startConnection() {
		try {
			//Class.forName("org.mariadb.jdbc.Driver"); 
			conn = DriverManager.getConnection(DB_URL);
		} catch (SQLException e) {
			e.printStackTrace();
		} // end try-catch
	} // end start connection

	public static void stopConnection() {
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} // end try-catch
	} // end stop connection


	public void testQuery() {

		System.out.println(TAG + "testQuery: attempting to query the server");

		startConnection();

		ResultSet resultSet = null;

		try {
			Statement stmt = conn.createStatement();
			resultSet = stmt.executeQuery("SELECT * FROM `Orders`");
			
			while(resultSet.next()) {
				System.out.println("Query Result: " + resultSet.getString("ORDER-NO"));
				
				ResultSetMetaData metaData = resultSet.getMetaData();
				System.out.println("DB Result: " + metaData.getColumnCount());
				for(int i = 1; i <= metaData.getColumnCount(); i++){
					System.out.println("DB Result: " + resultSet.getObject(i));
				} // end for
			} // end while loop

		} catch (SQLException e) {
			e.printStackTrace();
		} // end try-catch
		
		stopConnection();

	} // end testQuery


} // end Queries class