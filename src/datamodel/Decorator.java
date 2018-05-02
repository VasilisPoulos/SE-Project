package datamodel;

public class Decorator extends PatternComposite {
    private String beginTag;
    private String endTag;

    public Decorator(String name) {

        super(name);
    }

    @java.lang.Override
    public void saveName() {
        super.saveName();
    }

    @java.lang.Override
    public void saveContents() {
        super.saveContents();
    }

    @java.lang.Override
    public void decorateComponents(DecoratorAbstractFactory decoratorFactory) {
        super.decorateComponents(decoratorFactory);
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
