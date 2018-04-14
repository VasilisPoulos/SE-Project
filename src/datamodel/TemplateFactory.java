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
        Pattern micro = new Pattern("MicroPatternPrototype");
        micro.add(new PatternPart("Name", "What shall this pattern be called by practitioners?"));
        micro.add(new PatternPart("Template", "What shall this pattern be called by practitioners?"));
        micro.add(new PatternPart("Problem", "What shall this pattern be called by practitioners?"));
        micro.add(new PatternPart("Solution", "What shall this pattern be called by practitioners?"));

        // TODO: Add other templates
        templatesList.put("MicroPattern", micro);

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
