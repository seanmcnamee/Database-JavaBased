package app.backendSQL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Queries {

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

	/////////////////////////////////INSERTION//////////////////////////////////////////
	public ResultSet insertContract(int contractNum, String dateOfContract, int supplierNum) {
		return performStatement("INSERT INTO Contracts(`CONTRACT-NO`, `DATE-OF-CONTRACT`, `SUPPLIER-NO`) VALUES(" + contractNum + ", '" + dateOfContract + ", " + supplierNum + ");");
	}

	public ResultSet insertContractItem(int contractNum, int itemNum, int contractPrice, int contractAmount) {
		return performStatement("INSERT INTO Contracts(CONTRACT-NO, INO, CPRICE, CAMOUNT) VALUES(" + contractNum + ", " + itemNum + ", " + contractPrice + ", " + contractAmount + ");");
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


	//////////////////////////////////VIEWING///////////////////////////////////////////
	public ResultSet viewAmtOfItemStillUnderContract(int itemNum, int contractNum) {
		return performStatement("SELECT CONTRACT-AMOUNT FROM Contract-Item WHERE " +
								"ITEM-NO = " + itemNum + " AND CONTRACT-NO = " + contractNum + ";");
	}

	public ResultSet viewContractAndSupplierInfo(int supplierNum, int contractNum) {
		return performStatement("SELECT * FROM Contract	INNER JOIN Supplier ON " +
								"Contract.SUPPLIER-NO = " + supplierNum + " AND CONTRACT-NO = " + contractNum + ";");
	}

	public ResultSet viewItemInOrder(int orderNum) {
		return performStatement("SELECT * FROM Items WHERE ITEM-NO IN " +
									"SELECT ITEM-NO FROM ORDER-ITEM WHERE ORDER-NO = " + orderNum + ";");
	}

	public ResultSet viewItemPriceInContract(int itemNum, int contractNum) {
		return performStatement("SELECT CONTRACT-PRICE FROM CONTRACT-ITEM WHERE ITEM-NO = " + itemNum + " AND CONTRACT-NO = " + contractNum + ";");
	}

	public ResultSet viewItemPriceInOrder(int itemNum, int orderNum) {
		return performStatement("SELECT CONTRACT-PRICE FROM Contract-Item WHERE ITEM-NO = '" + itemNum + "' AND CONTRACT-NO in " +
									"SELECT CONTRACT-NO FROM Order WHERE ORDER-NO = " + orderNum + ";");
	}

	public ResultSet viewOrdersForItem(int itemNum) {
		return performStatement("SELECT * FROM Orders WHERE ORDER-NO IN " +
									"SELECT ORDER-NO FROM ORDER-ITEM WHERE ITEM-NO = " + itemNum + ";");
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