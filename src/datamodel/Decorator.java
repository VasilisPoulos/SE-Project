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

    public void saveName()
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
