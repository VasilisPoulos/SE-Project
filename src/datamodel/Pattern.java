package datamodel;

public class Pattern extends PatternComposite
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
    @Override
    public PatternComponent clone()
    {
        return super.clone();
    }

    /*
     * Must be implemented
     * @param decoratorFactory
     */
    //@Override
    //public void decorateComponents(DecoratorAbstractFactory decoratorFactory){}
}
