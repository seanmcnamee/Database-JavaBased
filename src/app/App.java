package app;

import app.frontendGUI.GUI;
import app.frontendGUI.Pages.AddDataPage;
import app.frontendGUI.Pages.MenuPage;
import app.frontendGUI.Pages.ViewDataPage;

public class App {
    public static int MENU = 0, ADD_DATA = 1, VIEW_DATA = 2;
    public static void main(String[] args) throws Exception {
        GUI g = new GUI(new MenuPage(), new AddDataPage(), new ViewDataPage());
        System.out.println("Hello Java");
    }
}