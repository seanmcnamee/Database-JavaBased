package app.frontendGUI.Pages.Adding;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

import app.App;
import app.frontendGUI.GUI;
import app.frontendGUI.GUIPage;

public class AddProjectPage extends GUIPage {

    public AddProjectPage() {
        super();
        this.panel.setBackground(Color.GRAY);
    }

    @Override
    public VariableComponent[] createComponents() {
        VariableComponent[] components = {
            new VariableComponent(new JLabel("Add Project", SwingConstants.CENTER), .5, .1, 1, .2),
            new VariableComponent(new JLabel("Project Number:"), .2, .3, 1 / 5.0, 1 / 6.0),
            new VariableComponent(new JTextArea(), .6, .3, 1 / 3.0, 1 / 17.0),
            new VariableComponent(new JLabel("Project Data:"), .2, .4, 1 / 5.0, 1 / 6.0),
            new VariableComponent(new JTextArea(), .6, .4, 1 / 3.0, 1 / 17.0),
            
            new VariableComponent(new JLabel(""), .5, .5, 1 / 2.0, 1 / 6.0),

            new VariableComponent(new JButton("Submit"), .5, .6, 1 / 3.0, 1 / 17.0),
            new VariableComponent(new JButton("Back"), .1, .95, .2, .1)
        };
        this.setBackgroundAndTextOfComponentsAtIndices(components, Color.WHITE, Color.WHITE, 0, 1, 3);
        this.setBackgroundAndTextOfComponentsAtIndices(components, Color.WHITE, Color.BLACK, 2, 4);
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

            ((JLabel) this.components[5].component).setText(null);

            try {
                addProject();
                prepareAndSwitchToPage(App.ADD_DATA, main);
            } catch (Exception e) {
                ((JLabel) this.components[5].component).setText("Problem with the input. Try again");
            }

        }
    }

    private void addProject() throws Exception {
        String[] values = this.getStringsOfTextAreas(components, 2, 4);
        int num = Integer.parseInt(values[0]);

        this.queries.insertProject(num, values[1]);
    }
}