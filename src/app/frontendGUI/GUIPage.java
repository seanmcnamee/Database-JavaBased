package app.frontendGUI;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import app.backendSQL.Queries;

public abstract class GUIPage {
    protected JPanel panel;
    protected VariableComponent[] components;
    protected Queries queries;

    public class VariableComponent {
        public JComponent component;
        public double x, y, width, height;

        public VariableComponent(JComponent component, double x, double y, double width, double height) {
            this.component = component;
            this.x = x;
            this.y = y;
            this.width = width;
            this.height = height;
        }
    }

    public GUIPage() {
        this.panel = new JPanel();
        panel.setLayout(null);
        this.components = createComponents();
        addComponentsToPanel();
    }

    public abstract VariableComponent[] createComponents();

    public abstract void actionPerformed(Object obj, GUI main);

    public void setButtonListeners(GUI parent) {
        for (VariableComponent vB : this.components) {
            if (vB.component.getClass().equals(JButton.class)) {
                ((JButton) vB.component).addActionListener(parent);
            }
        }
    }

    public void addQueryManager(Queries q) {
        this.queries = q;
    }

    /**
     * Set position and sizes of components based on percentages
     */
    public void setComponentSizeAndLocation(double pixelWidth, double pixelHeight, double xStart, double yStart) {
        // For each component
        for (VariableComponent vB : this.components) {
            double b1Width = vB.width * pixelWidth;
            double b1Height = vB.height * pixelHeight;
            double b1X = xStart + vB.x * pixelWidth - (b1Width / 2.0);
            double b1Y = yStart + vB.y * pixelHeight - (b1Height / 2.0);
            vB.component.setBounds((int) b1X, (int) b1Y, (int) b1Width, (int) b1Height);
        }
    }

    public JPanel getPanel() {
        return this.panel;
    }

    protected void setBackgroundAndTextOfComponentsInRange(VariableComponent[] components, int start, int end,
            Color backColor, Color textColor) {
        for (int i = start; i <= end; i++) {
            components[i].component.setBackground(backColor);
            components[i].component.setForeground(textColor);
        }
    }

    protected String[] getStringsOfTextAreas(VariableComponent[] components, int... indices) {
        String[] values = new String[indices.length];
        int valueIndex = 0;
        for (int indexOfComponents : indices) {
            values[valueIndex] = ((JTextArea) this.components[indexOfComponents].component).getText();
            valueIndex++;
        }
        return values;
    }

    protected GUIPage prepareAndSwitchToPage(int page, GUI main) {
        this.panel.setVisible(false);
        GUIPage newPage = main.switchToAndReturnPage(page);
        main.setComponentSizeAndLocation();
        return newPage;
    }

    private void addComponentsToPanel() {
        for (VariableComponent vB: this.components) {
            panel.add(vB.component);
        }
    }
}