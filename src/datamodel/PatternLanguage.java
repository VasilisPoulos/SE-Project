package datamodel;

public class PatternLanguage {

    private String title;
    private static Integer plCount = 0;

    public String getTitle() {
        return this.title;
    }

    /**
     * @constructor
     * Overloaded constructor with default parameters
     */
    public PatternLanguage() {
        this("");
    }

    /**
     * @constructor 
     * @param title the title of the new pattern language
     */
    public PatternLanguage(String title) {

        if (title == null || title.equals("") || title.isEmpty()) {
          title = this.generateDefaultTitle(); // Should generate using a counter (e.g. PatternLanguage1, PatternLanguage2, ...
        }
        this.title = title;
    }

    /**
     * Generates a default pattern language title, based on a global counter of pattern languages
     * @return default title for the new pattern language
     */
    public String generateDefaultTitle() {

        plCount++;
        String title = "Pattern Language #" + Integer.toString(plCount);
        return title;
    }
}
