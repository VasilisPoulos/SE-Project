package datamodel;

import java.util.ArrayList;

public abstract class PatternComposite extends PatternComponent
{

    private Integer count;      // so no two patters are created with the same name

    /**
     * ArrayList of components.
     */
    protected ArrayList<PatternComponent> componentsList;

    /**
     * Calling parent constructor, initializing array.
     *
     * @param name Name of the composite
     */
    public PatternComposite(String name)
    {
        super(name);
        this.count = 0;
        this.componentsList = new ArrayList<>();
    }

    /**
     * Adds a PatternComponent item in ArrayList
     *
     * @param component new component added to the list
     */
    @Override
    public void add(PatternComponent component)
    {
        for (PatternComponent i: this.componentsList) {
            if (i.getName().equals(component.getName())) {
                this.count++;
                component.setName(component.getName() + "-" + Integer.toString(this.count));
            }
        }
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

    public ArrayList<PatternComponent> getComponentsList() {
        return componentsList;
    }

    //TODO: will be implemented in Release 2.0
    //public abstract void decorateComponents(DecoratorAbstractFactory decoratorFactory);

    @Override
    public PatternComponent getChild() {
        return super.getChild();
    }

    @Override
    public String toString() {
        String list="";
        for(int i=0;i<componentsList.size();i++){
            list=componentsList.get(i).toString();
        }
        return list;
    }
}