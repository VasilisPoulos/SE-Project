package datamodel;

public class TemplateFactory
{
    private Pattern[] templateList;
    private static int template_counter = 0;

    TemplateFactory()
    {
        templateList = new Pattern[3];
    }

    public Pattern createTemplate(String templateName)
    {
        return new Pattern(templateName);
    }

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
