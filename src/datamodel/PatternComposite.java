package datamodel;

import java.util.ArrayList;

public class PatternComposite extends PatternComponent
{

    protected ArrayList<PatternComponent> componentList;

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
    public void add(PatternComponent component)
    {
        componentList.add(component);
    }
}