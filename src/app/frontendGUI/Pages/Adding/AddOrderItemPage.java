package app.frontendGUI.Pages.Adding;

import java.sql.ResultSet;

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
        VariableComponent[] components = { 
            new VariableComponent(new JButton("Submit"), .5, .9, 1 / 3.0, 1 / 17.0),
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

            for (String[] values : getStringsOfTextAreasForEachGroup()) {
                int itemNum = Integer.parseInt(values[0]);
                int orderQuantity = Integer.parseInt(values[1]);

                int underContract = 0;
                ResultSet underContractSet = this.queries.viewAmtOfItemStillUnderContractInOrder(itemNum, orderNum);
                //TODO figure out what's in this.


                if (underContract >= orderQuantity) {
                    underContract -= orderQuantity;
                    this.queries.updateContractAmountForOrderItem(orderNum, itemNum, underContract);
                    this.queries.insertOrderItem(itemNum, orderNum, orderQuantity);
                }   else {
                    //TODO notify user that there was a problem with the order.
                }

                
            }
                
            prepareAndSwitchToPage(App.ADD_DATA, main);
        }
    }

    public void setOrderNum(int orderNum) {
        this.orderNum = orderNum;
    }
    
}