package app.frontendGUI.Pages.Viewing;

import java.awt.Color;
import java.awt.Font;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

import app.App;
import app.Entities.ContractSupplier;
import app.frontendGUI.GUI;
import app.frontendGUI.GUIPage;

public class ViewContractAndSupplierInfoPage extends GUIPage {

    public ViewContractAndSupplierInfoPage() {
        super();
        this.panel.setBackground(Color.GRAY);
    }
    @Override
    public VariableComponent[] createComponents() {
        VariableComponent[] components = {
                new VariableComponent(new JLabel("View Contract And Supplier Information:", SwingConstants.CENTER), .5, .1, 1, .2),
                new VariableComponent(new JLabel("Contract Number:"), .2, .3, 1 / 5.0, 1 / 6.0),
                new VariableComponent(new JTextArea(), .6, .3, 1 / 3.0, 1 / 17.0),
                new VariableComponent(new JLabel("Supplier Number:"), .2, .4, 1 / 5.0, 1 / 6.0),
                new VariableComponent(new JTextArea(), .6, .4, 1 / 3.0, 1 / 17.0),

                new VariableComponent(new JButton("Submit"), .5, .4, 1 / 3.0, 1 / 17.0),
                new VariableComponent(new JButton("Back"), .1, .95, .2, .1) };
        this.setBackgroundAndTextOfComponentsAtIndices(components, Color.WHITE, Color.WHITE, 0, 1, 3);
        this.setBackgroundAndTextOfComponentsAtIndices(components, Color.WHITE, Color.BLACK, 2, 4);
        ((JLabel) components[0].component).setFont(new Font("Verdana", Font.PLAIN, 20));
        return components;
    }

    @Override
    public void actionPerformed(Object obj, GUI main) {
        if (obj.equals(this.components[this.components.length-1].component)) {
            System.out.println("Back to view page");
            prepareAndSwitchToPage(App.VIEW_DATA, main);
        } else if (obj.equals(this.components[this.components.length-2].component)) {
            System.out.println("Back to menu page");

            String[] values = this.getStringsOfTextAreas(components, 2, 4);
            int num = Integer.parseInt(values[0]);
            int num2 = Integer.parseInt(values[1]);
            

            
            String SqlResponseString = "";
            try {
                ContractSupplier contractSupplier = this.queries.viewContractAndSupplierInfo(num, num2);
                SqlResponseString = "Contract " + contractSupplier.getContractNum()+ " from " + contractSupplier.getDateOfContract().toString() + 
                                    "\nSupplier " + contractSupplier.getSupplierNum() + ": " + contractSupplier.getSupplierName() + " located at " + 
                                    contractSupplier.getSupplierAddress();
            } catch (SQLException e) {
                e.printStackTrace();
                SqlResponseString = "Error with SQL obtaining the Contract and Supplier info";
            }
            
            ViewPage view = (ViewPage) prepareAndSwitchToPage(App.VIEW_PAGE, main);
            view.setViews("Contract-Supplier Info", SqlResponseString);
        }
    }
}