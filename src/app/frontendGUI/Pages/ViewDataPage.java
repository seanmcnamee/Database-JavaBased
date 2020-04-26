package app.frontendGUI.Pages;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import app.App;
import app.frontendGUI.GUI;
import app.frontendGUI.GUIPage;
public class ViewDataPage extends GUIPage {
    
    public ViewDataPage() {
        super();
        this.panel.setBackground(Color.GRAY);
    }

    @Override
    public VariableComponent[] createComponents() {
        VariableComponent[] components = 
        {
            new VariableComponent(new JLabel("View the:", SwingConstants.CENTER), .5, .1, 1, .2),

            new VariableComponent(new JButton("Items in an order"), .15, .4, 1/3.0, 1/4.0),
            new VariableComponent(new JButton("Item price in an order"), .5, .4, 1/3.0, 1/4.0),
            new VariableComponent(new JButton("Item price in a contract"), .85, .4, 1/3.0, 1/4.0),

            new VariableComponent(new JButton("Orders for an item"), .15, .7, 1/3.0, 1/4.0),
            new VariableComponent(new JButton("Contract and Supplier information"), .5, .7, 1/3.0, 1/4.0),
            new VariableComponent(new JButton("Amount of an item still under contract"), .85, .7, 1/3.0, 1/4.0),

            new VariableComponent(new JButton("Back"), .1, .95, .2, .1)
        };
        this.setBackgroundAndTextOfComponentsInRange(components, 1, 6, Color.BLUE, Color.WHITE);
        ((JLabel) components[0].component).setFont(new Font("Verdana", Font.PLAIN, 20));
        //((JLabel) components[0]).setVerticalTextPosition(AbstractButton.CENTER);
        //vB.component.setHorizontalTextPosition(AbstractButton.LEADING);
        return components;
    }

    @Override
    public void actionPerformed(Object obj, GUI main) {
        if (obj.equals(this.components[this.components.length-1].component)) {
            System.out.println("Back to menu page");
            prepareAndSwitchToPage(App.MENU, main);
        } else if (obj.equals(this.components[1].component)) {
            System.out.println("Back to menu page");
            prepareAndSwitchToPage(App.VIEW_ITEM_IN_ORDER_PAGE, main);
        } else if (obj.equals(this.components[2].component)) {
            System.out.println("Back to menu page");
            prepareAndSwitchToPage(App.VIEW_ITEM_PRICE_IN_ORDER_PAGE, main);
        } else if (obj.equals(this.components[3].component)) {
            System.out.println("Back to menu page");
            prepareAndSwitchToPage(App.VIEW_ITEM_PRICE_IN_CONTRACT_PAGE, main);
        } else if (obj.equals(this.components[4].component)) {
            System.out.println("Back to menu page");
            prepareAndSwitchToPage(App.VIEW_ORDERS_FOR_ITEM_PAGE, main);
        } else if (obj.equals(this.components[5].component)) {
            System.out.println("Back to menu page");
            prepareAndSwitchToPage(App.VIEW_CONTRACT_AND_SUPPLIER_INFO_PAGE, main);
        } else if (obj.equals(this.components[6].component)) {
            System.out.println("Back to menu page");
            prepareAndSwitchToPage(App.VIEW_AMT_OF_ITEM_STILL_UNDER_CONTRACT_PAGE, main);
        }
    }
}