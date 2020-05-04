package app.frontendGUI;

import javax.swing.JTextArea;

import app.App;
import app.frontendGUI.GUIPage;

/**
 * Dynamically creates the needed amount of JTextArea VariableComponents
 */
public abstract class DynamicInputGUIPage extends GUIPage {
    private int numOfJTextAreasPerGroup;
    private int numOfStaticComponents;
    private int numOfJTextAreaGroups;
    private double xStartPercent, yStartPercent;
    private final double YIncrement = .1;

    public DynamicInputGUIPage(int numOfJTextAreasPerGroup, double xStartPercent, double yStartPercent) {
        super();
        this.numOfJTextAreasPerGroup = numOfJTextAreasPerGroup;
        this.xStartPercent = xStartPercent;
        this.yStartPercent = yStartPercent;
    }

    //Set all non-JTextArea based Components here. They will remain in these
    public abstract VariableComponent[] createComponents();

    //Make sure to use the name indices as the non-JTextArea components
    public abstract void actionPerformed(Object obj, GUI main);

    protected String[][] getStringsOfTextAreasForEachGroup() {
        String[][] inputsForEachGroup = new String[numOfJTextAreaGroups][numOfJTextAreasPerGroup];

        for (int groupIndex = 0; groupIndex < inputsForEachGroup.length; groupIndex++) {
            int currentComponentIndex = numOfStaticComponents + numOfJTextAreasPerGroup*groupIndex;
            
            int[] indicesToGrab = new int[numOfJTextAreasPerGroup];
            for (int i = 0; i < indicesToGrab.length; i++) {
                indicesToGrab[i] = currentComponentIndex+i;
            }

            String[] valuesForThisGroup = this.getStringsOfTextAreas(components, indicesToGrab);
            inputsForEachGroup[groupIndex] = valuesForThisGroup;
        }
        return inputsForEachGroup;
    }

    public void makeNJTextAreaSets(int numberOfGroups, GUI main) {
        this.numOfJTextAreaGroups = numberOfGroups;
        if (numOfStaticComponents == 0) {
            this.numOfStaticComponents = this.components.length;
        }

        VariableComponent[] newComponents = new VariableComponent[numOfStaticComponents+numOfJTextAreasPerGroup*numOfJTextAreaGroups];
        
        //Static components stay at the front of the array
        for (int i = 0; i < numOfStaticComponents; i++) {
            newComponents[i] = this.components[i];
        }

        //Dynamically created components
        for (int i = 0; i < numOfJTextAreaGroups; i++) {
            int groupBaseIndex = numOfStaticComponents + numOfJTextAreasPerGroup*i;
            double xLocation = i*(1-xStartPercent)/numOfJTextAreaGroups + xStartPercent;

            for (int componentCount = 0; componentCount < numOfJTextAreasPerGroup; componentCount++) {
                newComponents[groupBaseIndex+componentCount] = new VariableComponent(new JTextArea(), xLocation, yStartPercent+componentCount*YIncrement, (1-xStartPercent)/(numOfJTextAreaGroups+1), 1 / 17.0);
            }
        }
    
        this.components = newComponents;

        //Add all the components to the JPanel
        this.panel.removeAll();
        for (int i = 0; i < components.length; i++) {
            System.out.println("c: " + (this.components[i].component.getClass().toString()));
            this.panel.add(this.components[i].component);
        }

        //reload and redraw everything
        main.setComponentSizeAndLocation();
    }

}