package app.backendSQL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Queries {

	private final String TAG = "Queries: ";
	private final String DB_URL = "jdbc:mariadb://23.236.194.106:3306/csci300?user=csciremote&password=deltabravo2020";

	private Connection conn;

	public Queries() {
		startConnection();
	}

	public void startConnection() {
		try {
			//Class.forName("org.mariadb.jdbc.Driver"); 
			this.conn = DriverManager.getConnection(DB_URL);
		} catch (SQLException e) {
			e.printStackTrace();
		} // end try-catch
	} // end start connection

	public void stopConnection() {
		try {
			this.conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} // end try-catch
	} // end stop connection

	private ResultSet viewData(String statement) {

		ResultSet resultSet = null;
		try {
			Statement stmt = conn.createStatement();
			resultSet = stmt.executeQuery(statement);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return resultSet;
	}

	public ResultSet viewAmtOfItemStillUnderContract() {
		return viewData("");
	}

	public ResultSet viewContractAndSupplierInfo() {
		return viewData("");
	}

	public ResultSet viewItemInOrder() {
		return viewData("");
	}

	public ResultSet viewItemPriceInContract() {
		return viewData("");
	}

	public ResultSet viewItemPriceInOrder() {
		return viewData("");
	}

	public ResultSet viewOrdersForItem() {
		return viewData("");
	}

	private void insertData(String statement) {
		ResultSet resultSet = null;
		try {
			Statement stmt = conn.createStatement();
			resultSet = stmt.executeQuery(statement);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void insertContract() {
		insertData("");
	}

	private void insertItem() {
		insertData("");
	}

	private void insertOrder() {
		insertData("");
	}

	private void insertProject() {
		insertData("");
	}

	private void insertSupplier() {
		insertData("");
	}

	

			/**
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
			*/

} // end Queries class