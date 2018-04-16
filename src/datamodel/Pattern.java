package datamodel;

import gui.Main;

import java.util.ArrayList;

public class Pattern extends PatternComposite implements Cloneable
{

    /**
     * Calling parent constructor.
     * TODO: A pattern is a list of PatternPart objects with specific titles (which are dictated by the template)!
     * TODO: and descriptions. You can find those in one of the provided pdf docs
     * @param name Name of the composite
     */
    public Pattern(String name) {

        super(name);
    }

    //TODO: needs deep copy, iterate through all the items and clone them with
    //TODO: super.clone() method. (Clone name , array and its objects).
    /**
     * Creates a clone of a PatternComponent object.
     *     This is a Deep copy of this object because name is of type
     *     String which is immutable.
     *
     * I think it is best to use copy constructors or factory methods
     * instead of overriding clone().
     *
     * @return PatternComponent clone.
     */
    @Override
    public Pattern clone()
    {
        Pattern newPattern = new Pattern(this.getName());
        //newPattern.componentsList = new ArrayList<>();
        for (PatternComponent p : this.componentsList){
            newPattern.componentsList.add(new PatternPart(p.getName(),p.getContents()));
        }
        return newPattern;

        /*
        catch(CloneNotSupportedException e){
            throw new AssertionError();
        }
        */
    }

    /*
     * Must be implemented
     * @param decoratorFactory
     */
    //@Override
    //public void decorateComponents(DecoratorAbstractFactory decoratorFactory){}
}


