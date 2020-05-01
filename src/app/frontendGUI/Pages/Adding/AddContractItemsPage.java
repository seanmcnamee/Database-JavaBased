package app.frontendGUI.Pages.Adding;
import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

import app.App;
import app.frontendGUI.GUI;
import app.frontendGUI.GUIPage;

public class AddContractItemsPage extends GUIPage {
    private int contractNum;
    private int numOfContracts;

    public AddContractItemsPage() {
        super();
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
            new VariableComponent(new JLabel("Contract Amount:"), .2, .5, 1 / 5.0, 1 / 6.0)
        };
        return components;
    }

    @Override
    public void actionPerformed(Object obj, GUI main) {
        if (obj.equals(this.components[1].component)) {
            System.out.println("Back to Contract page");
            prepareAndSwitchToPage(App.ADD_CONTRACT_PAGE, main);
        } else if (obj.equals(this.components[0].component)) {
            System.out.println("Submitted");

            int startIndex = 6;
            for (int i = 0; i < numOfContracts; i++) {
                int currentIndex = startIndex + 3*i;
                System.out.println("jijiijji"+currentIndex);
                String[] values = this.getStringsOfTextAreas(components, currentIndex+0, currentIndex+1, currentIndex+2);
                int itemNum = Integer.parseInt(values[0]);
                int contractPrice = Integer.parseInt(values[1]);
                int contractAmount = Integer.parseInt(values[2]);
                this.queries.insertContractItem(this.contractNum, itemNum, contractPrice, contractAmount);
            }
                
            prepareAndSwitchToPage(App.ADD_DATA, main);
        }
    }

    public void setContractNum(int contractNum) {
        this.contractNum = contractNum;
    }

    public void makeNContractItemInputSets(int numberOfContractItems, GUI main) {
        this.numOfContracts = numberOfContractItems;
        int baseComponentCount = 6;
        VariableComponent[] newComponents = new VariableComponent[baseComponentCount+3*numberOfContractItems];
        
        System.out.println(numberOfContractItems);

        System.out.println("P1");
        for (int i = 0; i < baseComponentCount; i++) {
            newComponents[i] = this.components[i];
        }
        System.out.println("P2");
        for (int i = 0; i < numberOfContractItems; i++) {
            int currentIndex = baseComponentCount + 3*i;
            System.out.println("jijiijji "+currentIndex);
            double xLocation = i*.4/numberOfContractItems + .6;
            System.out.println("XLoc of " + i + ": " + xLocation);
            newComponents[currentIndex+0] = new VariableComponent(new JTextArea(), xLocation, .3, 1 / 12.0, 1 / 17.0);
            newComponents[currentIndex+1] = new VariableComponent(new JTextArea(), xLocation, .4, 1 / 12.0, 1 / 17.0);
            newComponents[currentIndex+2] = new VariableComponent(new JTextArea(), xLocation, .5, 1 / 12.0, 1 / 17.0);
        }
        this.components = newComponents;

        System.out.println("test");

        System.out.println("P3");
        for (int i = 6; i < components.length; i++) {
            this.panel.add(components[i].component);
        }

        prepareAndSwitchToPage(App.ADD_CONTRACT_ITEMS, main);
    }

}