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

    /*
     * Must be implemented
     * @param decoratorFactory
     */
    //@Override
    //public void decorateComponents(DecoratorAbstractFactory decoratorFactory){}
}
