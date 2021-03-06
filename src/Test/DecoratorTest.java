package Test;

import datamodel.*;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class DecoratorTest
{

    /**
     * Tests on LatexDecoratorFactory's methods.
     *
     * TODO: probably should be renamed to LatexDecoratorFactoryTest.
     */
    @Test
    void shouldDecoratePatternLanguage()
    {
        Decorator decorated;
        LatexDecoratorFactory fc = new LatexDecoratorFactory();
        PatternLanguage patternLanguage = new PatternLanguage("PatternLanguage Name");

        decorated = fc.createLanguageDecorator(patternLanguage);

        assertEquals("\\title{PatternLanguage Name}", decorated.getBeginTag());
        assertEquals("\\maketitle", decorated.getEndTag());
    }

    @Test
    void shouldDecoratePattern()
    {
        Decorator decorated;
        LatexDecoratorFactory fc = new LatexDecoratorFactory();
        Pattern pattern = new Pattern("Pattern Name");

        decorated = fc.createPatternDecorator(pattern);

        assertEquals("\\section{Pattern Name}", decorated.getBeginTag());
        assertEquals("", decorated.getEndTag());
    }

    @Test
    void shouldDecoratePart()
    {
        Decorator decorated;
        LatexDecoratorFactory fc = new LatexDecoratorFactory();
        PatternPart part = new PatternPart("Part Name");

        decorated = fc.createPartDecorator(part);

        assertEquals("\\subsection{Part Name}", decorated.getBeginTag());
        assertEquals("", decorated.getEndTag());
    }

    @Test
    void shouldDetectOS() throws IOException {
        Decorator dc = new Decorator("","","");
//        dc.initPdfExport();
    }

}