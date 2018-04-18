package datamodel;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AcceptanceTests{
    /*The rest of the acceptance tests are in PatternCompositeTest*/

    /**
     * Acceptance Test [US1]
     */
    @org.junit.jupiter.api.Test
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
    @org.junit.jupiter.api.Test
    void shouldMakeTemplates()
    {
        /*Initialize template Factory*/
        TemplateFactory tf = new TemplateFactory();

        /*Micro-Pattern Test*/
        ArrayList<PatternComponent> microList = new ArrayList<>();
        microList.add(new PatternPart("Name", "What shall this pattern be called by practitioners?"));
        microList.add(new PatternPart("Template", "Which template is followed for the pattern specification ?"));
        microList.add(new PatternPart("Problem", "What is motivating us to apply this pattern? "));
        microList.add(new PatternPart("Solution", " How do we solve the problem?"));

        Pattern micro = tf.createTemplate("Micro-Pattern");
        assertEquals("Micro-Pattern",micro.getName());

        for (int i = 0; i < micro.componentsList.size(); i++)
        {
            assertEquals(microList.get(i).toString(), micro.componentsList.get(i).toString());
        }

        /*Inductive Mini-Pattern Test*/
        ArrayList<PatternComponent> inductiveList = new ArrayList<>();
        inductiveList.add(new PatternPart("Name", "What shall this pattern be called by practitioners?"));
        inductiveList.add(new PatternPart("Template", "Which template is followed for the pattern specification ? "));
        inductiveList.add(new PatternPart("Context", " What are the assumed environment or a priori assumptions for applying this pattern?"));
        inductiveList.add(new PatternPart("Forces", "What are the different design motivations that must be balanced? ?"));
        inductiveList.add(new PatternPart("Solution", "How do we solve the problem? "));

        Pattern inductive = tf.createTemplate("Inductive Mini-Pattern");
        assertEquals("Inductive Mini-Pattern",inductive.getName());

        for (int i = 0; i < inductive.componentsList.size(); i++)
        {
            assertEquals(inductiveList.get(i).toString(), inductive.componentsList.get(i).toString());
        }

        /*Deductive Mini-Pattern Test*/
        ArrayList<PatternComponent> deductiveList = new ArrayList<>();
        deductiveList.add(new PatternPart("Name", "What shall this pattern be called by practitioners?"));
        deductiveList.add(new PatternPart("Template", "Which template is followed for the pattern specification ? "));
        deductiveList.add(new PatternPart("Problem", "  What is motivating us to apply this pattern? "));
        deductiveList.add(new PatternPart("Solution", ": How do we solve the problem? ?"));
        deductiveList.add(new PatternPart("Benefits", " What are the potential positive outcomes of applying this pattern?  "));
        deductiveList.add(new PatternPart("Consequences", "What are potential shortcomings and consequences of applying this pattern? \n"));

        Pattern deductive = tf.createTemplate("Deductive Mini-Pattern");
        assertEquals("Deductive Mini-Pattern",deductive.getName());

        for (int i = 0; i < deductive.componentsList.size(); i++)
        {
            assertEquals(deductiveList.get(i).toString(), deductive.componentsList.get(i).toString());
        }

        /*Gang-Of-Four Pattern Test*/
        ArrayList<PatternComponent> gofList = new ArrayList<>();
        gofList.add(new PatternPart("Name", "What is the pattern called? "));
        gofList.add(new PatternPart("Template", " Which template is followed for the pattern specification ?  "));
        gofList.add(new PatternPart("Pattern Classification", " Is the pattern creational, structural, or behavioral? "));
        gofList.add(new PatternPart("Intent", "What problem does this pattern solve?"));
        gofList.add(new PatternPart("Also Known As", " What are other names for this pattern? "));
        gofList.add(new PatternPart("Motivation", "What is an example scenario for applying this pattern? "));
        gofList.add(new PatternPart("Applicability", " When does this pattern apply? "));
        gofList.add(new PatternPart("Structure", "Which are the classes of the objects in this pattern?"));
        gofList.add(new PatternPart("Participants", " What are the objects that participate in this pattern? "));
        gofList.add(new PatternPart("Collaborations", " How do these objects interoperate? "));
        gofList.add(new PatternPart("Consequences", " What are the trade-offs of using this pattern? "));
        gofList.add(new PatternPart("Implementation", "Which techniques or issues arise in applying this pattern? "));
        gofList.add(new PatternPart("Sample Code", "What is an example of the pattern in source code?"));
        gofList.add(new PatternPart("Known Uses", " What are some examples of real systems using this pattern? "));
        gofList.add(new PatternPart("Related Patterns", " What other patterns from this pattern collection are related to this pattern?"));

        Pattern gof = tf.createTemplate("Gang-Of-Four Pattern");
        assertEquals("Gang-Of-Four Pattern",gof.getName());

        for (int i = 0; i < gof.componentsList.size(); i++)
        {
            assertEquals(gofList.get(i).toString(), gof.componentsList.get(i).toString());
        }

        /*System Of Patterns Template Test*/
        ArrayList<PatternComponent> sopList = new ArrayList<>();
        sopList.add(new PatternPart("Name", "What is the pattern called? "));
        sopList.add(new PatternPart("Template", " Which template is followed for the pattern specification ? "));
        sopList.add(new PatternPart("Also Known As", " What are other names for this pattern? "));
        sopList.add(new PatternPart("Example", "What is an example of the need for this pattern? "));
        sopList.add(new PatternPart("Context", " When does this pattern apply? "));
        sopList.add(new PatternPart("Problem", "What is the problem solved by this pattern?"));
        sopList.add(new PatternPart("Solution", "What is the underlying principal underlying this pattern? "));
        sopList.add(new PatternPart("Structure", "What objects are involved and related?"));
        sopList.add(new PatternPart("Dynamics", " How do these objects collaborate? "));
        sopList.add(new PatternPart("Implementation", "What are some guidelines for implementing this pattern? "));
        sopList.add(new PatternPart("Example Resolved", "Show how the previous example is resolved using the pattern"));
        sopList.add(new PatternPart("Variants", "What are important variations of this pattern? "));
        sopList.add(new PatternPart("Known Uses", "What are real-world systems using this pattern? "));
        sopList.add(new PatternPart("Consequences", " What are the benefits and liabilities of using this pattern? "));

        Pattern sop = tf.createTemplate("System Of Patterns Template");
        assertEquals("System Of Patterns Template",sop.getName());

        for (int i = 0; i < sop.componentsList.size(); i++)
        {
            assertEquals(sopList.get(i).toString(), sop.componentsList.get(i).toString());
        }
    }

    /**
     * Acceptance Test [US2]
     * Check if item was added in componentList.
     */
    @org.junit.jupiter.api.Test
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
    @org.junit.jupiter.api.Test
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
    @org.junit.jupiter.api.Test
    void shouldSetContents()
    {
        Pattern new_pattern = new Pattern("Pattern");
        new_pattern.add(new PatternPart("qwer","asdf"));
        new_pattern.componentsList.get(0).setContents("qwer");
        assertEquals("qwer",new_pattern.componentsList.get(0).getContents());
    }

}