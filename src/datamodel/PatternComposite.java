package datamodel;

import java.util.ArrayList;
import java.util.Iterator;

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
        this.componentsList.add(component);
    }

    /**
     * Removes a PatternComponent item from the ArrayList
     *     if component List contains the item requested, remove it.
     *
     * @param patternComponentTitle pattern components name
     */
    public void remove(String patternComponentTitle)
    {
        this.componentsList.removeIf((PatternComponent p) -> p.getName().equals(patternComponentTitle));

    }

    //TODO
    @Override
    public String toString() {
        return "PatternComposite{}";
    }


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

    //TODO: save to file
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

    //TODO: will be implemented in Release 2.0
    //public abstract void decorateComponents(DecoratorAbstractFactory decoratorFactory);
}