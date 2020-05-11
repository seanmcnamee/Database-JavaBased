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

public class AddContractPage extends GUIPage {

    public AddContractPage() {
        super();
        this.panel.setBackground(Color.GRAY);
    }

    @Override
    public VariableComponent[] createComponents() { 
        VariableComponent[] components = {
            new VariableComponent(new JLabel("Add Contract", SwingConstants.CENTER), .5, .1, 1, .2),

            new VariableComponent(new JLabel("Contract Number:"), .2, .3, 1 / 5.0, 1 / 6.0),
            new VariableComponent(new JTextArea(), .6, .3, 1 / 3.0, 1 / 17.0),
            new VariableComponent(new JLabel("Supplier Number:"), .2, .4, 1 / 5.0, 1 / 6.0),
            new VariableComponent(new JTextArea(), .6, .4, 1 / 3.0, 1 / 17.0),
            new VariableComponent(new JLabel("Date of Contract:"), .2, .5, 1 / 5.0, 1 / 6.0),
            new VariableComponent(new JTextArea(), .6, .5, 1 / 3.0, 1 / 17.0),

            new VariableComponent(new JLabel("Amount of Items to add to this contract:"), .2, .6, 1 / 3.0, 1 / 6.0),
            new VariableComponent(new JSpinner(new SpinnerNumberModel(0, 0, Integer.MAX_VALUE, 1)), .6, .6, 1 / 3.0, 1 / 17.0),

            new VariableComponent(new JLabel(""), .5, .7, 1 / 2.0, 1 / 6.0),

            new VariableComponent(new JButton("Submit"), .5, .9, 1 / 3.0, 1 / 17.0),
            new VariableComponent(new JButton("Back"), .1, .95, .2, .1)
        };
        this.setBackgroundAndTextOfComponentsAtIndices(components, Color.WHITE, Color.WHITE, 0, 1, 3, 5, 7);
        this.setBackgroundAndTextOfComponentsAtIndices(components, Color.WHITE, Color.BLACK, 2, 4, 6, 8);
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
            ((JLabel) this.components[9].component).setText(null);

            try {
                addContractAndSwitch(main);
            } catch (Exception e) {
                ((JLabel) this.components[9].component).setText("Problem with the input. Try again");
            }
        }
    }

    private void addContractAndSwitch(GUI main) throws Exception {
        String[] values = this.getStringsOfTextAreas(components, 2, 4, 6);
        int contractNum = Integer.parseInt(values[0]);
        int supplierNum = Integer.parseInt(values[1]);

        System.out.println("Contract: CNum, Date, SupplierNum, : " + contractNum + " " + values[2] + " " + supplierNum);
        this.queries.insertContract(contractNum, values[2], supplierNum);
        
        int num = Integer.parseInt(((JSpinner) this.components[8].component).getValue().toString());

        AddContractItemsPage contractItems = (AddContractItemsPage) prepareAndSwitchToPage(App.ADD_CONTRACT_ITEMS, main);
        contractItems.makeNJTextAreaSets(num, main);
        contractItems.setContractNum(contractNum);
    }
}