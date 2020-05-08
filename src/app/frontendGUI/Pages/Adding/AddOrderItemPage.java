package app.frontendGUI.Pages.Adding;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import app.App;
import app.frontendGUI.DynamicInputGUIPage;
import app.frontendGUI.GUI;

public class AddOrderItemPage extends DynamicInputGUIPage {
    private int orderNum;

    public AddOrderItemPage() {
        super(2, .5, .3);
        this.panel.setBackground(Color.GRAY);
    }

    @Override
    public VariableComponent[] createComponents() {
        VariableComponent[] components = { new VariableComponent(new JButton("Submit"), .5, .9, 1 / 3.0, 1 / 17.0),
                new VariableComponent(new JButton("Back"), .1, .95, .2, .1),

                new VariableComponent(new JLabel("Add Items To Order", SwingConstants.CENTER), .5, .1, 1, .2),

                new VariableComponent(new JLabel("Item Number:"), .2, .3, 1 / 5.0, 1 / 6.0),
                new VariableComponent(new JLabel("Order Quantity:"), .2, .4, 1 / 5.0, 1 / 6.0) };
        return components;
    }

    @Override
    public void actionPerformed(Object obj, GUI main) {
        if (obj.equals(this.components[1].component)) {
            System.out.println("Back to Order page");
            prepareAndSwitchToPage(App.ADD_ORDER_PAGE, main);
        } else if (obj.equals(this.components[0].component)) {
            System.out.println("Submitted");

            //Loop through each OrderItem
            for (String[] values : getStringsOfTextAreasForEachGroup()) {
                int itemNum = Integer.parseInt(values[0]);
                int orderQuantity = Integer.parseInt(values[1]);

                //Try to grab the add the OrderItem
                try {
                    System.out.println("Evaluating");
                    int underContract = this.queries.viewAmtOfItemStillUnderContractInOrder(itemNum, orderNum);
                    
                    System.out.println("Current contract amount: " + underContract);
                    if (underContract >= orderQuantity) { //If successful, add it and switch back to the ADD_DATA page
                        underContract -= orderQuantity;

                        System.out.println("Setting New contract amount: " + underContract + "...");
                        this.queries.updateContractAmountForOrderItem(orderNum, itemNum, underContract);

                        System.out.println("Inserting item...");
                        this.queries.insertOrderItem(orderNum, itemNum, orderQuantity);
                        prepareAndSwitchToPage(App.ADD_DATA, main);
                    }   else { //Notify if there's a problem with the order
                        //TODO notify user that there was a problem with the order.
                        System.out.println("Not enough orders left");
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                    System.out.println("Problem with getting the SQL Contract Amount");
                }
            }
        }
    }

    public void setOrderNum(int orderNum) {
        this.orderNum = orderNum;
    }
    
}