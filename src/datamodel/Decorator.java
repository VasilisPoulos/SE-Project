package datamodel;

public class Decorator extends PatternComposite {
    private String beginTag;
    private String endTag;

    public Decorator(String name) {

        super(name);
    }


    @java.lang.Override
    public void decorateComponents(DecoratorAbstractFactory decoratorFactory) {
        //TODO
    }

    public String getBeginTag() {
        return beginTag;
    }

    public void setBeginTag(String beginTag) {
        this.beginTag = beginTag;
    }


    public String getEndTag() {
        return endTag;
    }

    public void setEndTag(String endTag) {
        this.endTag = endTag;
    }


}
