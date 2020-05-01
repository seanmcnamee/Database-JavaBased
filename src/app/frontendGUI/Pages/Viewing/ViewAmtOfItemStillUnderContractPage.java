package app.frontendGUI.Pages.Viewing;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

import app.App;
import app.frontendGUI.GUI;
import app.frontendGUI.GUIPage;

public class ViewAmtOfItemStillUnderContractPage extends GUIPage {

    public ViewAmtOfItemStillUnderContractPage() {
        super();
        this.panel.setBackground(Color.GRAY);
    }
    @Override
    public VariableComponent[] createComponents() {
         VariableComponent[] components = {
                new VariableComponent(new JLabel("View amount of an item still under Contract:", SwingConstants.CENTER), .5, .1, 1, .2),
                new VariableComponent(new JLabel("Item Number:"), .2, .3, 1 / 5.0, 1 / 6.0),
                new VariableComponent(new JTextArea(), .6, .3, 1 / 3.0, 1 / 17.0),
                new VariableComponent(new JLabel("Contract Number:"), .2, .4, 1 / 5.0, 1 / 6.0),
                new VariableComponent(new JTextArea(), .6, .4, 1 / 3.0, 1 / 17.0),

                new VariableComponent(new JButton("Submit"), .5, .4, 1 / 3.0, 1 / 17.0),
                new VariableComponent(new JButton("Back"), .1, .95, .2, .1) };
        this.setBackgroundAndTextOfComponentsInRange(components, 0, 4, Color.WHITE, Color.WHITE);
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
            //TODO Add submit code (connect to SQL)

            String[] values = this.getStringsOfTextAreas(components, 2, 4);
            int num = Integer.parseInt(values[0]);
            int num2 = Integer.parseInt(values[1]);
            

            this.queries.viewAmtOfItemStillUnderContract(num, num2);


            ViewPage view = (ViewPage) prepareAndSwitchToPage(App.VIEW_PAGE, main);
            view.setViews("Items still under contract", "STRING_FROM_SQL_RETURN");
        }
    }
}