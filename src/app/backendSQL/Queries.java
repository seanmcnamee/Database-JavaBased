package app.backendSQL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import app.Entities.ContractSupplier;
import app.Entities.Item;
import app.Entities.Order;

public class Queries {

	private final String DB_URL = "jdbc:mariadb://23.236.194.106:3306/csci300?user=csciremote&password=deltabravo2020";

	private Connection conn;

	public Queries() {
		startConnection();
	}

	public void startConnection() {
		try {
			// Class.forName("org.mariadb.jdbc.Driver");
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

	///////////////////////////////// INSERTION
	///////////////////////////////// //////////////////////////////////////////
	public ResultSet insertContract(int contractNum, String dateOfContract, int supplierNum) {
		return performStatement("INSERT INTO Contracts(`CONTRACT-NO`, `DATE-OF-CONTRACT`, `SUPPLIER-NO`) VALUES("
				+ contractNum + ", '" + dateOfContract + "', " + supplierNum + ");");
	}

	public ResultSet insertContractItem(int contractNum, int itemNum, double contractPrice, int contractAmount) {
		return performStatement(
				"INSERT INTO `Contract-Item`(`CONTRACT-NO`, `ITEM-NO`, `CONTRACT-PRICE`, `CONTRACT-AMOUNT`) VALUES("
						+ contractNum + ", " + itemNum + ", " + contractPrice + ", " + contractAmount + ");");
	}

	public ResultSet insertProject(int projNum, String projData) {
		return performStatement(
				"INSERT INTO Projects(`PROJECT-NO`, `PROJECT-DATA`) VALUES(" + projNum + ", '" + projData + "');");
	}

	public ResultSet insertOrder(int orderNum, String dateRequired, int projectNum, int contractNum) {
		return performStatement("INSERT INTO Orders(`ORDER-NO`, `DATE-REQUIRED` , `PROJECT-NO`, `CONTRACT-NO`) VALUES("
				+ orderNum + ", '" + dateRequired + "', " + projectNum + ", " + contractNum + ");");
	}

	public ResultSet insertOrderItem(int orderNum, int itemNum, int orderQuantity) {
		return performStatement("INSERT INTO `Order-Item`(`ITEM-NO`, `ORDER-NO` , `ORDER-QTY`) VALUES(" + itemNum + ", "
				+ orderNum + ", " + orderQuantity + ");");
	}

	public ResultSet insertItem(int itemNum, String itemDesc) {
		return performStatement(
				"INSERT INTO Items(`ITEM-NO`, `ITEM-DESCRIPTION`) VALUES(" + itemNum + ", '" + itemDesc + "');");
	}

	public ResultSet insertSupplier(int supplierNum, String supplierAddress, String supplierName) {
		return performStatement("INSERT INTO Suppliers(`SUPPLIER-NO`, `SUPPLIER-ADDRESS`, `SUPPLIER-NAME`) VALUES("
				+ supplierNum + ", '" + supplierAddress + "', '" + supplierName + "');");
	}

	////////////////////////////////// VIEWING
	////////////////////////////////// ///////////////////////////////////////////
	public int viewAmtOfItemStillUnderContract(int itemNum, int contractNum) throws SQLException {
		ResultSet rS = performStatement("SELECT `CONTRACT-AMOUNT` FROM `Contract-Item` WHERE " + "`ITEM-NO` = "
				+ itemNum + " AND `CONTRACT-NO` = " + contractNum + ";");
		rS.next();
		return Integer.parseInt(rS.getString("CONTRACT-AMOUNT"));
	}

	public int viewAmtOfItemStillUnderContractInOrder(int itemNum, int orderNum) throws SQLException {
		ResultSet rS = performStatement("SELECT `CONTRACT-AMOUNT` FROM `Contract-Item` WHERE " + "`ITEM-NO` = "
				+ itemNum + " AND `CONTRACT-NO` IN " + "(SELECT `CONTRACT-NO` FROM Orders WHERE `ORDER-NO` = "
				+ orderNum + ");");
		rS.next();
		return Integer.parseInt(rS.getString("CONTRACT-AMOUNT"));
	}

	public ContractSupplier viewContractAndSupplierInfo(int supplierNum, int contractNum)
			throws SQLException {
		ResultSet rS =  performStatement(
				"SELECT * FROM Contract	INNER JOIN Supplier ON " + "Contract.SUPPLIER-NO = " + supplierNum + " AND "
						+ "Supplier.SUPPLIER-NO = " + supplierNum + " AND `CONTRACT-NO` = " + contractNum + ";");
		return new ContractSupplier(contractNum, rS.getDate("`DATE-OF-CONTRACT`"), supplierNum, rS.getString("`SUPPLIER-ADDRESS`"), rS.getString("`SUPPLIER-NAME`"));
	}

	public Item[] viewItemInOrder(int orderNum) throws SQLException {
		ResultSet rS = performStatement("SELECT * FROM Items WHERE `ITEM-NO` IN "
				+ "(SELECT `ITEM-NO` FROM `Order-Item` WHERE `ORDER-NO` = " + orderNum + ");");
		Item[] items = new Item[countRows(rS)];
		int index = 0;
		while (rS.next()) {
			items[index] = new Item(rS.getInt("`ITEM-NO`"), rS.getString("`ITEM-DESCRIPTION`"));
			index++;
		}
		return items;
	}

	public double viewItemPriceInContract(int itemNum, int contractNum) throws SQLException {
		ResultSet rS = performStatement("SELECT `CONTRACT-PRICE` FROM `Contract-Item` WHERE `ITEM-NO` = " + itemNum + 
										" AND `CONTRACT-NO` = " + contractNum + ";");
		return rS.getDouble("`CONTRACT-PRICE`");
	}

	public double viewItemPriceInOrder(int itemNum, int orderNum) throws SQLException {
		ResultSet rS = performStatement("SELECT `CONTRACT-PRICE` FROM `Contract-Item` WHERE `ITEM-NO` = " + itemNum + " AND `CONTRACT-NO` IN " +
									"(SELECT `CONTRACT-NO` FROM Order WHERE `ORDER-NO` = " + orderNum + ");");
		return rS.getDouble("`CONTRACT-PRICE`");
	}

	public Order[] viewOrdersForItem(int itemNum) throws SQLException {
		ResultSet rS = performStatement("SELECT * FROM Orders WHERE `ORDER-NO` IN " +
									"(SELECT `ORDER-NO` FROM `ORDER-ITEM` WHERE `ITEM-NO` = " + itemNum + ");");
		Order[] orders = new Order[countRows(rS)];
		int index = 0;
		while(rS.next()) {
			orders[index] = new Order(rS.getInt("`ORDER-NO`"), rS.getDate("`DATE-REQUIRED`"), rS.getDate("`DATE-COMPLETED`"), rS.getInt("`PROJECT-NO`"), rS.getInt("`CONTRACT-NO`"));
			index++;
		}
		return orders;
	}

	//////////////////////////////   UPDATING    ////////////////////////////////////////


	public ResultSet updateContractAmountForOrderItem(int orderNum, int itemNum, int contractAmount) {
		return performStatement("UPDATE `Contract-Item` SET `CONTRACT-AMOUNT` = " + contractAmount +
									" WHERE `ITEM-NO` = " + itemNum + " AND `CONTRACT-NO` IN " + 
									"(SELECT `CONTRACT-NO` FROM Orders WHERE `ORDER-NO` = " + orderNum + ");");
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

	private int countRows(ResultSet resultSet) {
		int totalRows = 0;
		try {
			resultSet.last();
			totalRows = resultSet.getRow();
			resultSet.beforeFirst();
		} 
		catch(Exception ex)  {
			return 0;
		}
		return totalRows ; 
	}

} // end Queries class