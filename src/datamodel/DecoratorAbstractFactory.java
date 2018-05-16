package datamodel;

public interface DecoratorAbstractFactory {

    public Decorator createLanguageDecorator(PatternLanguage patternLanguage);

    public PatternComponent createPatternDecorator(PatternComponent pattern);

    public PatternComponent createPartDecorator(PatternPart part);
}
