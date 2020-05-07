package app.frontendGUI.Pages.Adding;

import app.App;
import app.frontendGUI.DynamicInputGUIPage;
import app.frontendGUI.GUI;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

/**
 * AddContractItemsPage
 */
public class AddContractItemsPage extends DynamicInputGUIPage {
    private int contractNum;

    public AddContractItemsPage() {
        super(3, .4, .3);
        this.panel.setBackground(Color.GRAY);
    }

    @Override
    public VariableComponent[] createComponents() {
        VariableComponent[] components = { 
            new VariableComponent(new JButton("Submit"), .5, .9, 1 / 3.0, 1 / 17.0),
            new VariableComponent(new JButton("Back"), .1, .95, .2, .1),

            new VariableComponent(new JLabel("Add Items To Contract", SwingConstants.CENTER), .5, .1, 1, .2),

            new VariableComponent(new JLabel("Item Number:"), .2, .3, 1 / 5.0, 1 / 6.0),
            new VariableComponent(new JLabel("Contract Price:"), .2, .4, 1 / 5.0, 1 / 6.0),
            new VariableComponent(new JLabel("Contract Amount:"), .2, .5, 1 / 5.0, 1 / 6.0) };
        return components;
    }

    @Override
    public void actionPerformed(Object obj, GUI main) {
        if (obj.equals(this.components[1].component)) {
            System.out.println("Back to Contract page");
            prepareAndSwitchToPage(App.ADD_CONTRACT_PAGE, main);
        } else if (obj.equals(this.components[0].component)) {
            System.out.println("Submitted");

            for (String[] values : getStringsOfTextAreasForEachGroup()) {
                int itemNum = Integer.parseInt(values[0]);
                double contractPrice = Double.parseDouble(values[1]);
                int contractAmount = Integer.parseInt(values[2]);
                System.out.println("CNum, INum, CPrice, CAmt " + this.contractNum + " " + itemNum + " " + contractPrice + " " + contractAmount);
                this.queries.insertContractItem(this.contractNum, itemNum, contractPrice, contractAmount);
            }
                
            prepareAndSwitchToPage(App.ADD_DATA, main);
        }
    }

    public void setContractNum(int contractNum) {
        this.contractNum = contractNum;
    }

}