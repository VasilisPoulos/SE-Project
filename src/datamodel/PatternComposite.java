package datamodel;

import java.util.ArrayList;

public abstract class PatternComposite extends PatternComponent
{
    /**
     * ArrayList of components.
     */
    protected ArrayList<PatternComponent> componentList;

    /**
     * Calling parent constructor.
     * @param name Name of the composite
     */
    public PatternComposite(String name)
    {
        super(name);
        componentList = new ArrayList<>();
    }

    /**
     * Adds a PatternComponent item in ArrayList
     *
     * @param component new component added to the list
     */
    @Override
    public void add(PatternComponent component)
    {
        componentList.add(component);
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
        for (int i = 0; i < componentList.size(); i++)
        {
            if (componentList.get(i).getName().equals(patternComponentTitle))
            {
                componentList.remove(i);
            }
        }

    }

    //TODO: needs deep copy, iterate through all the items and clone them with
    //TODO: super.clone() method.
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

    //TODO: will be implemented in Release 2.0
    //public abstract void decorateComponents(DecoratorAbstractFactory decoratorFactory);
}