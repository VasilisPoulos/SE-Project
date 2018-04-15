package datamodel;

import java.util.HashMap;
import java.util.Map;

public class TemplateFactory
{

    private HashMap<String, Pattern> templatesList;

    /**
     *
     */
    public TemplateFactory()
    {

        templatesList = new HashMap<>();

        Pattern micro = new Pattern("Micro-Pattern");
        micro.add(new PatternPart("Name", "What shall this pattern be called by practitioners?"));
        micro.add(new PatternPart("Template", "Which template is followed for the pattern specification ?"));
        micro.add(new PatternPart("Problem", "What is motivating us to apply this pattern? "));
        micro.add(new PatternPart("Solution", " How do we solve the problem?"));

        Pattern inductive = new Pattern( "Inductive Mini-Pattern");
        inductive.add(new PatternPart("Name", "What shall this pattern be called by practitioners?"));
        inductive.add(new PatternPart("Template", "Which template is followed for the pattern specification ? "));
        inductive.add(new PatternPart("Context", " What are the assumed environment or a priori assumptions for applying this pattern?"));
        inductive.add(new PatternPart("Forces", "What are the different design motivations that must be balanced? ?"));
        inductive.add(new PatternPart("Solution", "How do we solve the problem? "));

        Pattern deductive = new Pattern( "Deductive Mini-Pattern");
        deductive.add(new PatternPart("Name", "What shall this pattern be called by practitioners?"));
        deductive.add(new PatternPart("Template", "Which template is followed for the pattern specification ? "));
        deductive.add(new PatternPart("Problem", "  What is motivating us to apply this pattern? "));
        deductive.add(new PatternPart("Solution", ": How do we solve the problem? ?"));
        deductive.add(new PatternPart("Benefits", " What are the potential positive outcomes of applying this pattern?  "));
        deductive.add(new PatternPart("Consequences", "What are potential shortcomings and consequences of applying this pattern? \n"));

        Pattern gof = new Pattern( "Gang-Of-Four Pattern");
        gof.add(new PatternPart("Name", "What is the pattern called? "));
        gof.add(new PatternPart("Template", " Which template is followed for the pattern specification ?  "));
        gof.add(new PatternPart("Pattern Classification", " Is the pattern creational, structural, or behavioral? "));
        gof.add(new PatternPart("Intent", "What problem does this pattern solve?"));
        gof.add(new PatternPart("Also Known As", " What are other names for this pattern? "));
        gof.add(new PatternPart("Motivation", "What is an example scenario for applying this pattern? "));
        gof.add(new PatternPart("Applicability", " When does this pattern apply? "));
        gof.add(new PatternPart("Structure", "Which are the classes of the objects in this pattern?"));
        gof.add(new PatternPart("Participants", " What are the objects that participate in this pattern? "));
        gof.add(new PatternPart("Collaborations", " How do these objects interoperate? "));
        gof.add(new PatternPart("Consequences", " What are the trade−offs of using this pattern? "));
        gof.add(new PatternPart("Implementation", "Which techniques or issues arise in applying this pattern? "));
        gof.add(new PatternPart("Sample Code", "What is an example of the pattern in source code?"));
        gof.add(new PatternPart("Known Uses", " What are some examples of real systems using this pattern? "));
        gof.add(new PatternPart("Related Patterns", " What other patterns from this pattern collection are related to this pattern?"));

        Pattern sop = new Pattern( "System Of Patterns Template");
        sop.add(new PatternPart("Name", "What is the pattern called? "));
        sop.add(new PatternPart("Template", " Which template is followed for the pattern specification ? "));
        sop.add(new PatternPart("Also Known As", " What are other names for this pattern? "));
        sop.add(new PatternPart("Example", "What is an example of the need for this pattern? "));
        sop.add(new PatternPart("Context", " When does this pattern apply? "));
        sop.add(new PatternPart("Problem", "What is the problem solved by this pattern?"));
        sop.add(new PatternPart("Solution", "What is the underlying principal underlying this pattern? "));
        sop.add(new PatternPart("Structure", "What objects are involved and related?"));
        sop.add(new PatternPart("Dynamics", " How do these objects collaborate? "));
        sop.add(new PatternPart("Implementation", "What are some guidelines for implementing this pattern? "));
        sop.add(new PatternPart("Example Resolved", "Show how the previous example is resolved using the pattern"));
        sop.add(new PatternPart("Variants", "What are important variations of this pattern? "));
        sop.add(new PatternPart("Known Uses", "What are real−world systems using this pattern? "));
        sop.add(new PatternPart("Consequences", " What are the benefits and liabilities of using this pattern? "));

        templatesList.put(micro.getName(), micro);
        templatesList.put(inductive.getName(), inductive);
        templatesList.put(deductive.getName(), deductive);
        templatesList.put(gof.getName(), gof);
        templatesList.put(sop.getName(), sop);

    }

    /**
     *
     *
     * @param templateName name of the template
     * @return Pattern based on template prototype
     */
    public PatternComponent createTemplate(String templateName)
    {
        return templatesList.get(templateName).clone();
    }

    public HashMap<String, Pattern> getTemplatesList() {
        return templatesList;
    }


}
