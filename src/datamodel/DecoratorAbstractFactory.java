package datamodel;

public interface DecoratorAbstractFactory {
    public PatternComponent createLanguageDecorator();

    public PatternComponent createPatternDecorator();

    public PatternComponent createPartDecorator();
}
