package datamodel;

import java.util.ArrayList;

public abstract class PatternComposite extends PatternComponent
{

    /**
     * ArrayList of components.
     */
    protected ArrayList<PatternComponent> componentsList;

    /**
     * Calling parent constructor, initializing array.
     * @param name Name of the composite
     */
    public PatternComposite(String name)
    {
        super(name);
        componentsList = new ArrayList<>();
    }

    /**
     * Adds a PatternComponent item in ArrayList
     *
     * @param component new component added to the list
     */
    @Override
    public void add(PatternComponent component)
    {
        componentsList.add(component);
    }

    /**
     * Removes a PatternComponent item from the ArrayList
     *     if component List contains the item requested, remove it.
     *
     * @param patternComponentTitle pattern components name
     */
    //TODO: Find a better way to do this.
    public void remove(String patternComponentTitle)
    {
        for (int i = 0; i < componentsList.size(); i++)
        {
            if (componentsList.get(i).getName().equals(patternComponentTitle))
            {
                componentsList.remove(i);
            }
        }

    }
    //for testing..logika kanei return ta pedia tou arraylist
    @Override
    public String toString() {
        return "PatternComposite{}";
    }

    @Override

    public ArrayList<PatternComponent> getComponentsList() {
        return componentsList;
    }

    //TODO: needs deep copy, iterate through all the items and clone them with
    //TODO: super.clone() method. (Clone name , array and its objects).
    @Override
    public PatternComponent clone()
    {
        return super.clone();
    }

    //TODO: not sure what this is supposed to do.
    @Override
    public void saveContents() {
        super.saveContents();
    }

    public void setComponentsList(ArrayList<PatternComponent> componentsList) {
        this.componentsList = componentsList;
    }

    @Override
    public PatternComponent getChild() {
        return super.getChild();
    }

    @Override
    public String setContents() {
        return super.setContents();
    }
    //TODO: will be implemented in Release 2.0
    //public abstract void decorateComponents(DecoratorAbstractFactory decoratorFactory);
}