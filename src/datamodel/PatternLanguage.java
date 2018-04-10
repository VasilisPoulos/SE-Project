package datamodel;

public class PatternLanguage extends PatternComposite
{
    private static Integer plCount = 0;

    /**
     * @constructor
     * Overloaded constructor with default parameters
     */
    public String getTitle() {
        return super.getName();
    }

    public PatternLanguage(String name)
    {
        super(name);
        if (name == null || name.equals("") || name.isEmpty())
        {
            name = this.generateDefaultTitle(); // Should generate using a counter (e.g. PatternLanguage1, PatternLanguage2, ...
        }
        this.name = name;
    }

    /**
     * Generates a default pattern language title, based on a global counter of pattern languages
     * @return default title for the new pattern language
     */
    public String generateDefaultTitle()
    {
        plCount++;
        String title = "Pattern Language #" + Integer.toString(plCount);
        return title;
    }

    //TODO: Release 2.0
    //public void decorateComponents()
}
