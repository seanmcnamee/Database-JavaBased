package app.frontendGUI.Pages.Adding;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;

import app.App;
import app.frontendGUI.GUI;
import app.frontendGUI.GUIPage;

public class AddOrderPage extends GUIPage {

    public AddOrderPage() {
        super();
        this.panel.setBackground(Color.GRAY);
    }

    @Override
    public VariableComponent[] createComponents() {
        VariableComponent[] components = {
            new VariableComponent(new JLabel("Add Order", SwingConstants.CENTER), .5, .1, 1, .2),
            new VariableComponent(new JLabel("Order Number:"), .2, .3, 1 / 5.0, 1 / 6.0),
            new VariableComponent(new JTextArea(), .6, .3, 1 / 3.0, 1 / 17.0),
            new VariableComponent(new JLabel("Date Requested (yyyy-mm-dd):"), .2, .4, 1 / 3.0, 1 / 6.0),
            new VariableComponent(new JTextArea(), .6, .4, 1 / 3.0, 1 / 17.0),
            new VariableComponent(new JLabel("Project Number:"), .2, .5, 1 / 5.0, 1 / 6.0),
            new VariableComponent(new JTextArea(), .6, .5, 1 / 3.0, 1 / 17.0),
            new VariableComponent(new JLabel("Contract Number:"), .2, .6, 1 / 5.0, 1 / 6.0),
            new VariableComponent(new JTextArea(), .6, .6, 1 / 3.0, 1 / 17.0),

            new VariableComponent(new JLabel("Amount of Items to add to this Order:"), .2, .7, 1 / 3.0, 1 / 6.0),
            new VariableComponent(new JSpinner(new SpinnerNumberModel(0, 0, Integer.MAX_VALUE, 1)), .6, .7, 1 / 3.0, 1 / 17.0),

            new VariableComponent(new JLabel(""), .5, .8, 1 / 2.0, 1 / 6.0),

            new VariableComponent(new JButton("Submit"), .5, .9, 1 / 3.0, 1 / 17.0),
            new VariableComponent(new JButton("Back"), .1, .95, .2, .1)
        };
        this.setBackgroundAndTextOfComponentsAtIndices(components, Color.WHITE, Color.WHITE, 0, 1, 3, 5, 7, 9);
        this.setBackgroundAndTextOfComponentsAtIndices(components, Color.WHITE, Color.BLACK, 2, 4, 6, 8, 10);
        ((JLabel) components[0].component).setFont(new Font("Verdana", Font.PLAIN, 20));
        return components;
    }

    @Override
    public void actionPerformed(Object obj, GUI main) {
        if (obj.equals(this.components[this.components.length-1].component)) {
            System.out.println("Back to menu page");
            prepareAndSwitchToPage(App.ADD_DATA, main);
        } else if (obj.equals(this.components[this.components.length-2].component)) {
            System.out.println("Submitted");

            ((JLabel) this.components[11].component).setText(null);

            try {
                addOrderAndSwitch(main);
            } catch (Exception e) {
                ((JLabel) this.components[11].component).setText("Problem with the input. Try again");
            }
            

        }
    }

    private void addOrderAndSwitch(GUI main) throws Exception {
        String[] values = this.getStringsOfTextAreas(components, 2, 4, 6, 8);
        int orderNum = Integer.parseInt(values[0]);
        int projectNum = Integer.parseInt(values[2]);
        int contractNum = Integer.parseInt(values[3]);


        this.queries.insertOrder(orderNum, values[1], projectNum, contractNum);

        int num = Integer.parseInt(((JSpinner) this.components[10].component).getValue().toString());
        
        AddOrderItemPage orderItems = (AddOrderItemPage) prepareAndSwitchToPage(App.ADD_ORDER_ITEMS, main);
        orderItems.makeNJTextAreaSets(num, main);
        orderItems.setOrderNum(orderNum);
    }
}