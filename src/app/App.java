package app;

import app.backendSQL.Queries;
import app.frontendGUI.GUI;
import app.frontendGUI.Pages.AddDataPage;
import app.frontendGUI.Pages.Adding.AddSupplierPage;
import app.frontendGUI.Pages.Viewing.ViewItemInOrderPage;
import app.frontendGUI.Pages.Viewing.ViewItemPriceInOrderPage;
import app.frontendGUI.Pages.Viewing.ViewOrdersForItemPage;
import app.frontendGUI.Pages.Viewing.ViewItemPriceInContractPage;
import app.frontendGUI.Pages.MenuPage;
import app.frontendGUI.Pages.ViewDataPage;

public class App {
    public static int MENU = 0, ADD_DATA = 1, VIEW_DATA = 2, ADD_SUPPLIER_PAGE = 3, VIEW_ITEM_IN_ORDER_PAGE = 4, 
                            VIEW_ITEM_PRICE_IN_ORDER_PAGE = 5, VIEW_ITEM_PRICE_IN_CONTRACT_PAGE = 6, 
                            VIEW_ORDERS_FOR_ITEM_PAGE = 7;
    public static void main(String[] args) throws Exception {
        System.out.println("Making GUI...");
        GUI g = new GUI(new MenuPage(), new AddDataPage(), new ViewDataPage(), new AddSupplierPage(),
                new ViewItemInOrderPage(), new ViewItemPriceInOrderPage(), new ViewItemPriceInContractPage(), 
                new ViewOrdersForItemPage());
        System.out.println("Query Tests...");
        Queries query = new Queries();
        query.testQuery();
    }
}