package datamodel;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PatternLanguageTest
{
    @Test
    void shouldDecorateAllPatterns()
    {
        LatexDecoratorFactory fc = new LatexDecoratorFactory();
        Decorator decoratedObject;
        PatternLanguage patternLanguage = new PatternLanguage("PatternLanguage Name");
        Pattern pattern_1 = new Pattern("1");
        Pattern pattern_2 = new Pattern("2");
        Pattern pattern_3 = new Pattern("3");
        Pattern pattern_4 = new Pattern("4");
        patternLanguage.add(pattern_1);
        patternLanguage.add(pattern_2);
        patternLanguage.add(pattern_3);
        patternLanguage.add(pattern_4);

        patternLanguage.decorateComponents(fc);
        decoratedObject = (Decorator) patternLanguage.componentsList.get(0);
        assertEquals("\\section{1}",decoratedObject.getBeginTag());
        assertEquals("",decoratedObject.getEndTag());

        decoratedObject = (Decorator) patternLanguage.componentsList.get(1);
        assertEquals("\\section{2}",decoratedObject.getBeginTag());
        assertEquals("",decoratedObject.getEndTag());

        decoratedObject = (Decorator) patternLanguage.componentsList.get(2);
        assertEquals("\\section{3}",decoratedObject.getBeginTag());
        assertEquals("",decoratedObject.getEndTag());

        decoratedObject = (Decorator) patternLanguage.componentsList.get(3);
        assertEquals("\\section{4}",decoratedObject.getBeginTag());
        assertEquals("",decoratedObject.getEndTag());
    }


}
