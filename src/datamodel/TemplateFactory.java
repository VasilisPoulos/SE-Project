package datamodel;

public class TemplateFactory
{
    /*
     * List of default patterns [3] that will be supported.
     */
    private Pattern[] templateList;

    /*
     * Keep track of how many patterns have been created.
     */
    private static int template_counter = 0;

    TemplateFactory()
    {
        templateList = new Pattern[3];
    }

    public Pattern createTemplate(String templateName)
    {
        return new Pattern(templateName);
    }


    /*
     * USED FOR TESTING.
     * Creates 3 template objects.
     * @return the list of the objects created.
     */
    public Pattern[] templateListIterator()
    {
        for (int i = 0; i < 3; i++)
        {
            templateList[i] = createTemplate("default"+i);
            template_counter++;
        }
        return templateList;
    }
}
