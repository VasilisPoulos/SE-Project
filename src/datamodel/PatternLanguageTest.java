package datamodel;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PatternLanguageTest
{
    /**
     * [US7 - part1]
     *
     * Makes sure all pattern parts are decorated by
     * decorateComponents in Pattern.
     */
    @Test
    void shouldDecorateAllParts()
    {
        LatexDecoratorFactory fc = new LatexDecoratorFactory();
        Decorator decoratedObject;
        Pattern pattern = new Pattern("Pattern Name");
        PatternPart part_1 = new PatternPart("1");
        PatternPart part_2 = new PatternPart("2");
        PatternPart part_3 = new PatternPart("3");
        PatternPart part_4 = new PatternPart("4");
        pattern.add(part_1);
        pattern.add(part_2);
        pattern.add(part_3);
        pattern.add(part_4);

        pattern.decorateComponents(fc);
        decoratedObject = (Decorator) pattern.componentsList.get(0);
        assertEquals("\\subsection{1}",decoratedObject.getBeginTag());
        assertEquals("",decoratedObject.getEndTag());

        decoratedObject = (Decorator) pattern.componentsList.get(1);
        assertEquals("\\subsection{2}",decoratedObject.getBeginTag());
        assertEquals("",decoratedObject.getEndTag());

        decoratedObject = (Decorator) pattern.componentsList.get(2);
        assertEquals("\\subsection{3}",decoratedObject.getBeginTag());
        assertEquals("",decoratedObject.getEndTag());

        decoratedObject = (Decorator) pattern.componentsList.get(3);
        assertEquals("\\subsection{4}",decoratedObject.getBeginTag());
        assertEquals("",decoratedObject.getEndTag());
    }

    /**
     * [US7 - part2]
     *
     * Makes sure all patterns and their parts are decorated by
     * decorateComponents in Pattern Language.
     */
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

        PatternPart part1 = new PatternPart("part1");
        pattern_1.add(part1);

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

        Decorator part_decorated = (Decorator) pattern_1.componentsList.get(0);
        assertEquals("\\subsection{part1}", part_decorated.getBeginTag());
    }
}
