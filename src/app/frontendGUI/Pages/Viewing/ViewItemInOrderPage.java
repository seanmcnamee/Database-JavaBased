package app.frontendGUI.Pages.Viewing;

import java.awt.Color;
import java.awt.Font;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

import app.App;
import app.Entities.Item;
import app.frontendGUI.GUI;
import app.frontendGUI.GUIPage;

public class ViewItemInOrderPage extends GUIPage {

    
    public ViewItemInOrderPage() {
        super();
        this.panel.setBackground(Color.GRAY);
    }
    @Override
    public VariableComponent[] createComponents() {
         VariableComponent[] components = {
                new VariableComponent(new JLabel("View items in:", SwingConstants.CENTER), .5, .1, 1, .2),
                new VariableComponent(new JLabel("Order Number:"), .2, .3, 1 / 5.0, 1 / 6.0),
                new VariableComponent(new JTextArea(), .6, .3, 1 / 3.0, 1 / 17.0),

                new VariableComponent(new JButton("Submit"), .5, .4, 1 / 3.0, 1 / 17.0),
                new VariableComponent(new JButton("Back"), .1, .95, .2, .1) };
        this.setBackgroundAndTextOfComponentsAtIndices(components, Color.WHITE, Color.WHITE, 0, 1);
        this.setBackgroundAndTextOfComponentsAtIndices(components, Color.WHITE, Color.BLACK, 2);
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

            String[] values = this.getStringsOfTextAreas(components, 2);
            int orderNum = Integer.parseInt(values[0]);
            
            
            String SqlResponseString = "";
            try {
                Item[] items = this.queries.viewItemInOrder(orderNum);
                SqlResponseString = "<html>Items in order " + orderNum + ": <br />";
                for (Item i : items) {
                    SqlResponseString = SqlResponseString + i.getItemNum() + ": " + i.getItemDescription() + "<br />";
                }
                SqlResponseString = SqlResponseString + "</html>";
            } catch (SQLException e) {
                e.printStackTrace();
                SqlResponseString = "Error with SQL obtaining the Items in that order";
            }

            
            ViewPage view = (ViewPage) prepareAndSwitchToPage(App.VIEW_PAGE, main);
            view.setViews("Items in Order", SqlResponseString);
        }
    }
}