package datamodel;

public class Pattern extends PatternComposite implements Cloneable
{

    /**
     * Calling parent constructor.
     * <p>
     *      TODO: A pattern is a list of PatternPart objects with specific titles (which are dictated by the template)!
     *      TODO: and descriptions. You can find those in one of the provided pdf docs
     * </p>
     * 
     * @param name Name of the composite
     */
    public Pattern(String name) {

        super(name);
    }

    /**
     * Creates a clone of a PatternComponent object (deep copy).
     * <p>
     *      Note :  There is a secondary implementation overriding clone() method of Object class
     *              I think it is better to use copy constructors or factory methods
     *              instead of overriding clone().
     * </p>
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
    }

    //TODO: (V2)
    //@Override
    //public void decorateComponents(DecoratorAbstractFactory decoratorFactory){}
}


