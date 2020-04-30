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

	private ResultSet performStatement(String statement) {

		ResultSet resultSet = null;
		try {
			Statement stmt = conn.createStatement();
			resultSet = stmt.executeQuery(statement);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return resultSet;
	}

	//INSERTION
	public ResultSet insertContract(int contractNum, String dateOfContract, int itemNum, int contractPrice, int contractAmount) {
		return performStatement("INSERT INTO Contracts(CNO, DATEOFCONTRACT, INO, CPRICE, CAMOUNT) VALUES(" + contractNum + ", '" + dateOfContract + "', " + itemNum + ", " + contractPrice + ", " + contractAmount + ");");
	}

	public ResultSet insertProject(int projNum, String projData) {
		return performStatement("INSERT INTO Projects(PNO, PDATA) VALUES(" + projNum + ",'" + projData + "');");
	}

	public ResultSet insertOrder(int orderNum, String dateRequired, String dateCompleted) {
		return performStatement("INSERT INTO Orders(ONO, ODATEREQ , ODATECOMP) VALUES(" + orderNum + ",'" + dateRequired + "','" + dateCompleted + "');");
	}

	public ResultSet insertItem(int itemNum, String itemDesc) {
		return performStatement("INSERT INTO Items(INO, IDESC) VALUES(" + itemNum + ",'" + itemDesc + "');");
	}

	public ResultSet insertSupplier(int supplierNum, String supplierAddress, String supplierName) {
		return performStatement("INSERT INTO Suppliers(SNO, SADDRESS, SNAME) VALUES(" + supplierNum + ",'" + supplierAddress + "','" + supplierName + "');");
	}


	//VIEWING
	public ResultSet viewAmtOfItemStillUnderContract() {
		return performStatement("");
	}

	public ResultSet viewContractAndSupplierInfo() {
		return performStatement("");
	}

	public ResultSet viewItemInOrder() {
		return performStatement("");
	}

	public ResultSet viewItemPriceInContract() {
		return performStatement("");
	}

	public ResultSet viewItemPriceInOrder(int itemNum, int orderNum) {
		return performStatement("SELECT CONTRACT-PRICE FROM Contract-Item WHERE ITEM-NO = '" + itemNum + "' and CONTRACT-NO in " +
								"SELECT Contract-No FROM Order WHERE ORDER-NO = " + orderNum + ";");
	}

	public ResultSet viewOrdersForItem(int orderNum) {
		return performStatement("SELECT * FROM orders WHERE ONO = " + orderNum + ";");
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