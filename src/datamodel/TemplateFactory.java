package datamodel;

import java.util.HashMap;
import java.util.Map;

public class TemplateFactory
{
    /*
     * List of default patterns [3] that will be supported.
     * TODO: application should create 3 (one for each template) Pattern objects (prototypes) at the very beginning.
     * TODO: Then we create patterns by *cloning* these prototypes
     * TODO: Should be a Map of (templateName, prototype)
     *
     */
    private HashMap<String, Pattern> templatesList;
//    private Pattern[] templatesList;

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
        templatesList = new HashMap<String, Pattern>();
        templatesList.put("MicroPattern", new Pattern("MicroPatternPrototype"));
        templatesList.put("MediumPattern", new Pattern("MediumPatternPrototype"));
        templatesList.put("MegaPattern", new Pattern("MegaPatternPrototype"));

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
