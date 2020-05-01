package app.frontendGUI.Pages.Adding;

import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

import app.App;
import app.frontendGUI.GUI;
import app.frontendGUI.GUIPage;

public class AddContractPage extends GUIPage {
    private ArrayList<VariableComponent> contractItemInputs = new ArrayList<> ();;

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


            new VariableComponent(new JButton("Submit"), .5, .9, 1 / 3.0, 1 / 17.0),
            new VariableComponent(new JButton("Back"), .1, .95, .2, .1)
        };
        this.setBackgroundAndTextOfComponentsInRange(components, 0, 10, Color.WHITE, Color.WHITE);
        ((JLabel) components[0].component).setFont(new Font("Verdana", Font.PLAIN, 20));
        return components;
    }

    @Override
    public void actionPerformed(Object obj, GUI main) {
        if (obj.equals(this.components[this.components.length-1].component)) {
            System.out.println("Back to menu page");
            prepareAndSwitchToPage(App.ADD_DATA, main);
        } else if (obj.equals(this.components[this.components.length-2].component)) {
            System.out.println("Back to menu page");
            
            String[] values = this.getStringsOfTextAreas(components, 2, 4, 6, 8, 10, 12);
            int num1 = Integer.parseInt(values[0]);
            int num2 = Integer.parseInt(values[1]);
            int num3 = Integer.parseInt(values[3]);
            int num4 = Integer.parseInt(values[4]);
            int num5 = Integer.parseInt(values[5]);

            this.queries.insertContract(num1, values[1], num2);
            this.queries.insertContractItem(num1, num3, num4, num5);
            prepareAndSwitchToPage(App.ADD_DATA, main);
        }
    }

    //public void 

}