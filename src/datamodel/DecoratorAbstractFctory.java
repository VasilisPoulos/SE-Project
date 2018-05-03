package datamodel;

public interface DecoratorAbstractFctory {

    public PatternComponent createLanguageDecorator();
    public  PatternComponent createPatternDecorator();
    public  PatternComponent createPartDecorator();
}
