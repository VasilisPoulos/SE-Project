package Test;

import datamodel.*;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Comparator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class AcceptanceTests{
    /*The rest of the acceptance tests are in PatternCompositeTest*/

    /**
     * Acceptance Test [US1]
     */
    @Test
    void shouldMakePLObject()
    {
        /*Test with no given name*/
        PatternLanguage new_pl = new PatternLanguage();
        assertEquals("Default",new_pl.getName());


        /*Test with given name*/
        PatternLanguage named_pl = new PatternLanguage("TestName");
        assertEquals("TestName",named_pl.getName());
    }

    /**
     * Acceptance Test [US2]
     */
    @Test
    void shouldMakeTemplates()
    {
        /*Initialize template Factory*/
        TemplateFactory tf = new TemplateFactory();

        /*Micro-Pattern Test*/
        ArrayList<PatternComponent> microList = new ArrayList<>();
        microList.add(new PatternPart("Name", "What shall this pattern be called by practitioners?"));
        microList.add(new PatternPart("Template", "Which template is followed for the pattern specification?"));
        microList.add(new PatternPart("Problem", "What is motivating us to apply this pattern?"));
        microList.add(new PatternPart("Solution", "How do we solve the problem?"));

        Pattern micro = tf.createTemplate("Micro-Pattern");
        assertEquals("Micro-Pattern",micro.getName());

        for (int i = 0; i < micro.getComponentsList().size(); i++)
        {
            assertEquals(microList.get(i).toString(), micro.getComponentsList().get(i).toString());
        }

        /*Inductive Mini-Pattern Test*/
        ArrayList<PatternComponent> inductiveList = new ArrayList<>();
        inductiveList.add(new PatternPart("Name", "What shall this pattern be called by practitioners?"));
        inductiveList.add(new PatternPart("Template", "Which template is followed for the pattern specification?"));
        inductiveList.add(new PatternPart("Context", "What are the assumed environment or a priori assumptions for applying this pattern?"));
        inductiveList.add(new PatternPart("Forces", "What are the different design motivations that must be balanced?"));
        inductiveList.add(new PatternPart("Solution", "How do we solve the problem?"));

        Pattern inductive = tf.createTemplate("Inductive Mini-Pattern");
        assertEquals("Inductive Mini-Pattern",inductive.getName());

        for (int i = 0; i < inductive.getComponentsList().size(); i++)
        {
            assertEquals(inductiveList.get(i).toString(), inductive.getComponentsList().get(i).toString());
        }

        /*Deductive Mini-Pattern Test*/
        ArrayList<PatternComponent> deductiveList = new ArrayList<>();
        deductiveList.add(new PatternPart("Name", "What shall this pattern be called by practitioners?"));
        deductiveList.add(new PatternPart("Template", "Which template is followed for the pattern specification?"));
        deductiveList.add(new PatternPart("Problem", "What is motivating us to apply this pattern?"));
        deductiveList.add(new PatternPart("Solution", "How do we solve the problem?"));
        deductiveList.add(new PatternPart("Benefits", "What are the potential positive outcomes of applying this pattern?"));
        deductiveList.add(new PatternPart("Consequences", "What are potential shortcomings and consequences of applying this pattern?"));

        Pattern deductive = tf.createTemplate("Deductive Mini-Pattern");
        assertEquals("Deductive Mini-Pattern",deductive.getName());

        for (int i = 0; i < deductive.getComponentsList().size(); i++)
        {
            assertEquals(deductiveList.get(i).toString(), deductive.getComponentsList().get(i).toString());
        }

        /*Gang-Of-Four Pattern Test*/
        ArrayList<PatternComponent> gofList = new ArrayList<>();
        gofList.add(new PatternPart("Name", "What is the pattern called?"));
        gofList.add(new PatternPart("Template", "Which template is followed for the pattern specification?"));
        gofList.add(new PatternPart("Pattern Classification", "Is the pattern creational, structural, or behavioral?"));
        gofList.add(new PatternPart("Intent", "What problem does this pattern solve?"));
        gofList.add(new PatternPart("Also Known As", "What are other names for this pattern?"));
        gofList.add(new PatternPart("Motivation", "What is an example scenario for applying this pattern?"));
        gofList.add(new PatternPart("Applicability", "When does this pattern apply?"));
        gofList.add(new PatternPart("Structure", "Which are the classes of the objects in this pattern?"));
        gofList.add(new PatternPart("Participants", "What are the objects that participate in this pattern?"));
        gofList.add(new PatternPart("Collaborations", "How do these objects interoperate?"));
        gofList.add(new PatternPart("Consequences", "What are the trade-offs of using this pattern?"));
        gofList.add(new PatternPart("Implementation", "Which techniques or issues arise in applying this pattern?"));
        gofList.add(new PatternPart("Sample Code", "What is an example of the pattern in source code?"));
        gofList.add(new PatternPart("Known Uses", "What are some examples of real systems using this pattern?"));
        gofList.add(new PatternPart("Related Patterns", "What other patterns from this pattern collection are related to this pattern?"));

        Pattern gof = tf.createTemplate("Gang-Of-Four Pattern");
        assertEquals("Gang-Of-Four Pattern",gof.getName());

        for (int i = 0; i < gof.getComponentsList().size(); i++)
        {
            assertEquals(gofList.get(i).toString(), gof.getComponentsList().get(i).toString());
        }

        /*System Of Patterns Template Test*/
        ArrayList<PatternComponent> sopList = new ArrayList<>();
        sopList.add(new PatternPart("Name", "What is the pattern called?"));
        sopList.add(new PatternPart("Template", "Which template is followed for the pattern specification?"));
        sopList.add(new PatternPart("Also Known As", "What are other names for this pattern?"));
        sopList.add(new PatternPart("Example", "What is an example of the need for this pattern?"));
        sopList.add(new PatternPart("Context", "When does this pattern apply?"));
        sopList.add(new PatternPart("Problem", "What is the problem solved by this pattern?"));
        sopList.add(new PatternPart("Solution", "What is the underlying principal underlying this pattern?"));
        sopList.add(new PatternPart("Structure", "What objects are involved and related?"));
        sopList.add(new PatternPart("Dynamics", "How do these objects collaborate?"));
        sopList.add(new PatternPart("Implementation", "What are some guidelines for implementing this pattern?"));
        sopList.add(new PatternPart("Example Resolved", "Show how the previous example is resolved using the pattern"));
        sopList.add(new PatternPart("Variants", "What are important variations of this pattern?"));
        sopList.add(new PatternPart("Known Uses", "What are real-world systems using this pattern?"));
        sopList.add(new PatternPart("Consequences", "What are the benefits and liabilities of using this pattern?"));

        Pattern sop = tf.createTemplate("System Of Patterns Template");
        assertEquals("System Of Patterns Template",sop.getName());

        for (int i = 0; i < sop.getComponentsList().size(); i++)
        {
            assertEquals(sopList.get(i).toString(), sop.getComponentsList().get(i).toString());
        }
    }

    /**
     * Acceptance Test [US2]
     * Check if item was added in componentList.
     */
    @Test
    void add()
    {
        PatternLanguage composite = new PatternLanguage("TestComposite");
        PatternComponent component = new PatternComponent("Component");
        composite.add(component);
        assertEquals(composite.getComponentsList().get(0).getName(),"Component");
    }

    /**
     * Acceptance Test [US3]
     * Check if item was removed from componentList.
     */
    @Test
    void remove()
    {
        PatternLanguage composite = new PatternLanguage("TestComposite");
        PatternComponent component = new PatternComponent("Component");
        composite.add(component);
        composite.remove("Component");
        assertEquals(true,composite.getComponentsList().isEmpty());

    }


    /**
     * Acceptance Test [US4]
     */
    @Test
    void shouldSetContents()
    {
        Pattern new_pattern = new Pattern("Pattern");
        new_pattern.add(new PatternPart("qwer","asdf"));
        new_pattern.getComponentsList().get(0).setContents("qwer");
        assertEquals("qwer",new_pattern.getComponentsList().get(0).getContents());
    }

    /**
     * Acceptance Test [US5] & [US6]
     */
    @Test
    void shouldSaveAndLoad() throws Exception {
        TemplateFactory tf = new TemplateFactory();
        PatternLanguage pl = new PatternLanguage("pl");
        pl.add(tf.createTemplate("Micro-Pattern"));
        Path tmp = Paths.get("./.tmp/");

        if (Files.exists(tmp)){
            Files.walk(tmp)
                    .sorted(Comparator.reverseOrder())
                    .map(Path::toFile)
                    .forEach(File::delete);
        }

        if (!Files.exists(tmp))
            Files.createDirectory(tmp);

        Path filename = Paths.get("./.tmp/pl.txt");
        pl.saveName(filename);
        pl.saveContents(filename);

        PatternLanguage newPL = (PatternLanguage) PatternLanguage.loadPatternLanguage(filename);

        // Should have the same name, patterns, parts and contents...
        assertEquals(pl.toString(), newPL.toString());
        // ...but not be the same object!
        assertNotEquals(pl, newPL);

        if (Files.exists(tmp)){
            Files.walk(tmp)
                    .sorted(Comparator.reverseOrder())
                    .map(Path::toFile)
                    .forEach(File::delete);
        }
        Files.deleteIfExists(tmp);

    }

    /**
     * Acceptance Test [US6]
     */
    @Test
    void shouldThrowException() throws Exception {
        TemplateFactory tf = new TemplateFactory();
        PatternLanguage pl = new PatternLanguage("pl");
        pl.add(tf.createTemplate("Micro-Pattern"));
        Path tmp = Paths.get("./.tmp/");

        if (Files.exists(tmp)){
            Files.walk(tmp)
                    .sorted(Comparator.reverseOrder())
                    .map(Path::toFile)
                    .forEach(File::delete);
        }

        if (!Files.exists(tmp))
            Files.createDirectory(tmp);

        Path filename = Paths.get("./.tmp/pl.txt");
        pl.saveName(filename);
        String str = "asdf\nasfd\nasdf\n";
        byte[] bytes = str.getBytes();
        Files.write(filename, bytes, StandardOpenOption.APPEND);
        pl.saveContents(filename);

        try {
            PatternLanguage.loadPatternLanguage(filename);
            assert false;
        } catch (Exception e) {
            assert true;
            assert(e.getMessage().contains("ERROR: File does not contain a valid Pattern Language."));
        }
        
        if (Files.exists(tmp)){
            Files.walk(tmp)
                    .sorted(Comparator.reverseOrder())
                    .map(Path::toFile)
                    .forEach(File::delete);
        }
        Files.deleteIfExists(tmp);

    }

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

        decoratedObject = (Decorator) pattern.getComponentsList().get(0);
        assertEquals("\\subsection{1}",decoratedObject.getBeginTag());
        assertEquals("",decoratedObject.getEndTag());

        decoratedObject = (Decorator) pattern.getComponentsList().get(1);
        assertEquals("\\subsection{2}",decoratedObject.getBeginTag());
        assertEquals("",decoratedObject.getEndTag());

        decoratedObject = (Decorator) pattern.getComponentsList().get(2);
        assertEquals("\\subsection{3}",decoratedObject.getBeginTag());
        assertEquals("",decoratedObject.getEndTag());

        decoratedObject = (Decorator) pattern.getComponentsList().get(3);
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
        decoratedObject = (Decorator) patternLanguage.getComponentsList().get(0);
        assertEquals("\\section{1}",decoratedObject.getBeginTag());
        assertEquals("",decoratedObject.getEndTag());

        decoratedObject = (Decorator) patternLanguage.getComponentsList().get(1);
        assertEquals("\\section{2}",decoratedObject.getBeginTag());
        assertEquals("",decoratedObject.getEndTag());

        decoratedObject = (Decorator) patternLanguage.getComponentsList().get(2);
        assertEquals("\\section{3}",decoratedObject.getBeginTag());
        assertEquals("",decoratedObject.getEndTag());

        decoratedObject = (Decorator) patternLanguage.getComponentsList().get(3);
        assertEquals("\\section{4}",decoratedObject.getBeginTag());
        assertEquals("",decoratedObject.getEndTag());

        Decorator part_decorated = (Decorator) pattern_1.getComponentsList().get(0);
        assertEquals("\\subsection{part1}", part_decorated.getBeginTag());
    }

    /**
     * Acceptance test [US8] and [US9]
     */
    @Test
    void shouldSaveAndLoadDecoratedPL() throws Exception {
        TemplateFactory tf = new TemplateFactory();
        PatternLanguage pl = new PatternLanguage("pl");
        pl.add(tf.createTemplate("Micro-Pattern"));
        Path tmp = Paths.get("./.tmp/");

        if (Files.exists(tmp)){
            Files.walk(tmp)
                    .sorted(Comparator.reverseOrder())
                    .map(Path::toFile)
                    .forEach(File::delete);
        }

        if (!Files.exists(tmp))
            Files.createDirectory(tmp);

        Path filename = Paths.get("./.tmp/pl.txt");

        // Decorate PL
        LatexDecoratorFactory ldf =  new LatexDecoratorFactory();
        Decorator decoratedPL = ldf.createLanguageDecorator(pl);
        decoratedPL.decorateComponents(ldf);

        decoratedPL.saveDecorated(filename);

        PatternComposite newPL = PatternLanguage.loadPatternLanguage(filename);

        // Should have the same name, patterns, parts and contents...
        assertEquals(decoratedPL.toString(), newPL.toString());
        // ...but not be the same object!
        assertNotEquals(decoratedPL, newPL);

        if (Files.exists(tmp)){
            Files.walk(tmp)
                    .sorted(Comparator.reverseOrder())
                    .map(Path::toFile)
                    .forEach(File::delete);
        }
        Files.deleteIfExists(tmp);
    }

    /**
     * Acceptance Test [US9]
     */
    @Test
    void shouldThrowExceptionDecorated() throws Exception {
        TemplateFactory tf = new TemplateFactory();
        PatternLanguage pl = new PatternLanguage("pl");
        pl.add(tf.createTemplate("Micro-Pattern"));
        Path tmp = Paths.get("./.tmp/");

        if (Files.exists(tmp)){
            Files.walk(tmp)
                    .sorted(Comparator.reverseOrder())
                    .map(Path::toFile)
                    .forEach(File::delete);
        }

        if (!Files.exists(tmp))
            Files.createDirectory(tmp);

        Path filename = Paths.get("./.tmp/pl.txt");
        // Decorate PL
        LatexDecoratorFactory ldf =  new LatexDecoratorFactory();
        Decorator decoratedPL = ldf.createLanguageDecorator(pl);
        decoratedPL.decorateComponents(ldf);

        decoratedPL.saveDecorated(filename);

        String str = "asdf\nasfd\nasdf\n";
        byte[] bytes = str.getBytes();
        Files.write(filename, bytes, StandardOpenOption.APPEND);
        pl.saveContents(filename);

        try {
            PatternLanguage.loadPatternLanguage(filename);
            assert false;
        } catch (Exception e) {
            assert true;
            assert(e.getMessage().contains("ERROR: File does not contain a valid Pattern Language."));
        }

        if (Files.exists(tmp)){
            Files.walk(tmp)
                    .sorted(Comparator.reverseOrder())
                    .map(Path::toFile)
                    .forEach(File::delete);
        }
        Files.deleteIfExists(tmp);

    }


}