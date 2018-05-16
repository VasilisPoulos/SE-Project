package datamodel;

public class Decorator extends PatternComposite {

    private String beginTag;
    private String endTag;

    public Decorator(String name, String beginTag, String endTag)
    {
        super(name);
        this.beginTag = beginTag;
        this.endTag = endTag;
    }

    /**
     * Saves the decorated document
     *
     *  Save begin tag
     *  call save on decorated object
     *  then save end tag
     */
    public void saveName()
    {

    }

    /**
     * Delegate calls to saveContents methods of objects
     */
    public void saveContents()
    {

    }

    public String getBeginTag() {
        return beginTag;
    }

    public String getEndTag() {
        return endTag;
    }

    @Override
    public void decorateComponents(LatexDecoratorFactory latexDecoratorFactory)
    {

    }
}
