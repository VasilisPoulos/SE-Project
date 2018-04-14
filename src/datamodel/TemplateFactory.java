package datamodel;

import java.util.HashMap;
import java.util.Map;

public class TemplateFactory
{

    private HashMap<String, Pattern> templatesList;

    /*
     * Keep track of how many patterns have been created.
     */
    private static int template_counter = 0;

    /**
     *
     */
    public TemplateFactory()
    {
//        templatesList = new Pattern[3];
        templatesList = new HashMap<>();
        Pattern micro = new Pattern("MicroPatternPrototype");
        // TODO: fix contents
        micro.add(new PatternPart("Name", "What shall this pattern be called by practitioners?"));
        micro.add(new PatternPart("Template", "What shall this pattern be called by practitioners?"));
        micro.add(new PatternPart("Problem", "What shall this pattern be called by practitioners?"));
        micro.add(new PatternPart("Solution", "What shall this pattern be called by practitioners?"));

        // TODO: Add other templates
        templatesList.put("MicroPattern", micro);

    }

    /**
     * TODO: Retrieves a prototype from the Map and clones it! Did I do it right?
     * @param templateName name of the template
     * @return Pattern based on template prototype
     */
    public PatternComponent createTemplate(String templateName)
    {
        return templatesList.get(templateName).clone();
//        return new Pattern(templateName);
    }

    public HashMap<String, Pattern> getTemplatesList() {
        return templatesList;
    }

    public void setTemplatesList(HashMap<String, Pattern> templatesList) {
        this.templatesList = templatesList;
    }

// TODO: Check if this is obsolete (we have a Map now)
//    /*
//     * USED FOR TESTING.
//     * Creates 3 template objects.
//     * @return the list of the objects created.
//     */
//    public Pattern[] templatesListIterator()
//    {
//        for (int i = 0; i < 3; i++)
//        {
//            templatesList[i] = createTemplate("default"+i);
//            template_counter++;
//        }
//        return templatesList;
//    }
}
