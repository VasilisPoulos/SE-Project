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

        // TODO: fix contents
        Pattern micro = new Pattern("MicroPattern");
        micro.add(new PatternPart("Name", "What shall this pattern be called by practitioners?"));
        micro.add(new PatternPart("Template", "What shall this pattern be called by practitioners?"));
        micro.add(new PatternPart("Problem", "What shall this pattern be called by practitioners?"));
        micro.add(new PatternPart("Solution", "What shall this pattern be called by practitioners?"));

        Pattern inductive = new Pattern( "InductiveMiniPattern");
        inductive.add(new PatternPart("Name", "What shall this pattern be called by practitioners?"));
        inductive.add(new PatternPart("Template", "What shall this pattern be called by practitioners?"));
        inductive.add(new PatternPart("Context", " What are the assumed environment or a priori assumptions for applying this pattern?"));
        inductive.add(new PatternPart("Forces", "What are the different design motivations that must be balanced? ?"));
        inductive.add(new PatternPart("Solution", "How do we solve the problem? "));


        // TODO: Add other templates
        templatesList.put("MicroPattern", micro);
        templatesList.put("InductiveMiniPattern", inductive);

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
