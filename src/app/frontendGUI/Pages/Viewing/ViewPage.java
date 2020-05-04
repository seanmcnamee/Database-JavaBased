package app.frontendGUI.Pages.Viewing;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import app.App;
import app.frontendGUI.GUI;
import app.frontendGUI.GUIPage;

public class ViewPage extends GUIPage {
    private String prefixToTitle = "Viewing ";

    public ViewPage() {
        super();
        this.panel.setBackground(Color.GRAY);
    }

    @Override
    public VariableComponent[] createComponents() {
        VariableComponent[] components = 
        {
            new VariableComponent(new JLabel("", SwingConstants.CENTER), .5, .1, 1, .2),

            new VariableComponent(new JLabel(""), .15, .4, 1/3.0, 1/4.0),

            new VariableComponent(new JButton("Back"), .1, .95, .2, .1)
        };
        this.setBackgroundAndTextOfComponentsAtIndices(components, Color.WHITE, Color.WHITE, 0);
        this.setBackgroundAndTextOfComponentsAtIndices(components, Color.WHITE, Color.BLACK, 1);
        ((JLabel) components[0].component).setFont(new Font("Verdana", Font.PLAIN, 20));
        //((JLabel) components[0]).setVerticalTextPosition(AbstractButton.CENTER);
        //vB.component.setHorizontalTextPosition(AbstractButton.LEADING);
        return components;
    }

    @Override
    public void actionPerformed(Object obj, GUI main) {
        if (obj.equals(this.components[this.components.length-1].component)) {
            System.out.println("Back to view page");
            prepareAndSwitchToPage(App.VIEW_DATA, main);
        }
    }

    public void setViews(String title, String dataString) {
        JLabel lblTitle = ((JLabel) this.components[0].component);
        lblTitle.setText(prefixToTitle + title);

        JLabel lblData = ((JLabel) this.components[1].component);
        lblData.setText(dataString);
    }

}