package app;

import app.frontendGUI.GUI;
import app.frontendGUI.Pages.AddDataMenuPage;
import app.frontendGUI.Pages.Adding.AddContractItemsPage;
import app.frontendGUI.Pages.Adding.AddContractPage;
import app.frontendGUI.Pages.Adding.AddItemPage;
import app.frontendGUI.Pages.Adding.AddOrderItemPage;
import app.frontendGUI.Pages.Adding.AddOrderPage;
import app.frontendGUI.Pages.Adding.AddProjectPage;
import app.frontendGUI.Pages.Adding.AddSupplierPage;
import app.frontendGUI.Pages.Viewing.ViewAmtOfItemStillUnderContractPage;
import app.frontendGUI.Pages.Viewing.ViewContractAndSupplierInfoPage;
import app.frontendGUI.Pages.Viewing.ViewItemInOrderPage;
import app.frontendGUI.Pages.Viewing.ViewItemPriceInOrderPage;
import app.frontendGUI.Pages.Viewing.ViewOrdersForItemPage;
import app.frontendGUI.Pages.Viewing.ViewPage;
import app.frontendGUI.Pages.Viewing.ViewItemPriceInContractPage;
import app.frontendGUI.Pages.MenuPage;
import app.frontendGUI.Pages.ViewDataMenuPage;

public class App {
    public static int MENU = 0, ADD_DATA = 1, VIEW_DATA = 2, ADD_SUPPLIER_PAGE = 3, VIEW_ITEM_IN_ORDER_PAGE = 4, 
                            VIEW_ITEM_PRICE_IN_ORDER_PAGE = 5, VIEW_ITEM_PRICE_IN_CONTRACT_PAGE = 6, 
                            VIEW_ORDERS_FOR_ITEM_PAGE = 7, VIEW_CONTRACT_AND_SUPPLIER_INFO_PAGE = 8,
                            VIEW_AMT_OF_ITEM_STILL_UNDER_CONTRACT_PAGE = 9, ADD_PROJECT_PAGE = 10,
                            ADD_ITEM_PAGE = 11, ADD_CONTRACT_PAGE = 12, ADD_ORDER_PAGE = 13, VIEW_PAGE = 14, 
                            ADD_CONTRACT_ITEMS = 15, ADD_ORDER_ITEMS = 16;
    public static void main(String[] args) throws Exception {
        System.out.println("Making GUI...");
        GUI g = new GUI(new MenuPage(), new AddDataMenuPage(), new ViewDataMenuPage(), new AddSupplierPage(),
                new ViewItemInOrderPage(), new ViewItemPriceInOrderPage(), new ViewItemPriceInContractPage(), 
                new ViewOrdersForItemPage(), new ViewContractAndSupplierInfoPage(), new ViewAmtOfItemStillUnderContractPage(), 
                new AddProjectPage(), new AddItemPage(), new AddContractPage(), new AddOrderPage(), new ViewPage(), 
                new AddContractItemsPage(), new AddOrderItemPage());
        g.start();
        //query.testQuery();
    }
}