package app.frontendGUI.Pages.Adding;

import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;

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
    
    private ArrayList<VariableComponent> contractItemInputs;
    private int contractNum;

    public AddContractPage() {
        super();
        this.panel.setBackground(Color.GRAY);
    }

    @Override
    public VariableComponent[] createComponents() { //TODO this page layout with have to be variable (many items added at once)
        VariableComponent[] components = {
            new VariableComponent(new JLabel("Add Contract", SwingConstants.CENTER), .5, .1, 1, .2),

            new VariableComponent(new JLabel("Contract Number:"), .2, .3, 1 / 5.0, 1 / 6.0),
            new VariableComponent(new JTextArea(), .6, .3, 1 / 3.0, 1 / 17.0),
            new VariableComponent(new JLabel("Supplier Number:"), .2, .4, 1 / 5.0, 1 / 6.0),
            new VariableComponent(new JTextArea(), .6, .4, 1 / 3.0, 1 / 17.0),
            new VariableComponent(new JLabel("Date of Contract:"), .2, .5, 1 / 5.0, 1 / 6.0),
            new VariableComponent(new JTextArea(), .6, .5, 1 / 3.0, 1 / 17.0),

            new VariableComponent(new JLabel("Amount of Items to add to this contract:"), .2, .6, 1 / 5.0, 1 / 6.0),
            new VariableComponent(new JSpinner(new SpinnerNumberModel(0, Integer.MAX_VALUE, 0, 1)), .6, .6, 1 / 3.0, 1 / 17.0),

            new VariableComponent(new JButton("Submit"), .5, .9, 1 / 3.0, 1 / 17.0),
            new VariableComponent(new JButton("Back"), .1, .95, .2, .1)
        };
        this.setBackgroundAndTextOfComponentsInRange(components, 0, 8, Color.WHITE, Color.WHITE);
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
            if (contractItemInputs == null) {
                String[] values = this.getStringsOfTextAreas(components, 2, 4, 6);
                this.contractNum = Integer.parseInt(values[0]);
                int supplierNum = Integer.parseInt(values[1]);

                this.queries.insertContract(contractNum, values[1], supplierNum);
            }
            
            
            //this.queries.insertContractItem(num1, num3, num4, num5);
            prepareAndSwitchToPage(App.ADD_DATA, main);
        }
    }

    public void makeNContractItemInputSets() {


        contractItemInputs.add(new VariableComponent(new JLabel("Item Number:"), .2, .6, 1 / 5.0, 1 / 6.0));
        contractItemInputs.add(new VariableComponent(new JLabel("Contract Price:"), .2, .7, 1 / 5.0, 1 / 6.0));
        contractItemInputs.add(new VariableComponent(new JLabel("Contract Amount:"), .2, .8, 1 / 5.0, 1 / 6.0));


    }

    private void makeSingleItemInputSetAtXLocation(int xPercent) {
        contractItemInputs.add(new VariableComponent(new JTextArea(), .6, .6, 1 / 12.0, 1 / 17.0));
        contractItemInputs.add(new VariableComponent(new JTextArea(), .6, .7, 1 / 12.0, 1 / 17.0));
        contractItemInputs.add(new VariableComponent(new JTextArea(), .6, .8, 1 / 12.0, 1 / 17.0));
    }

}