package datamodel;

public class LatexDecoratorFactory implements DecoratorAbstractFactory {


    /**
     *  @return decorated Pattern Language object
     */
    public Decorator createLanguageDecorator(PatternLanguage patternLanguage)
    {
        Decorator decoratedPatternLanguage = new Decorator(
                "",
                "\\title{"+patternLanguage.getName()+"}",
                "\\maketitle");
        decoratedPatternLanguage.componentsList.add(patternLanguage);
        return decoratedPatternLanguage;
    }


    /**
     * @return decorated Pattern object
     */
    public  Decorator createPatternDecorator(PatternComponent pattern)
    {
        Decorator decoratedPattern = new Decorator(
                "",
                "\\section{"+pattern.getName()+"}",
                "");
        decoratedPattern.componentsList.add(pattern);
        return decoratedPattern;
    }


    /**
     * @return decorated Pattern Part object
     */
    public  Decorator createPartDecorator(PatternPart part)
    {
        Decorator decoratedPart = new Decorator(
                "",
                "\\subsection{"+part.getName()+"}",
                "");
        decoratedPart.componentsList.add(part);
        return decoratedPart;
    }
}

