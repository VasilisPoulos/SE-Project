package datamodel;

public class PatternLanguage {

    private String title;
    private Integer plCount; /* TODO: Should move this to an interface class!!! */

    public String getTitle() {
        return title;
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
        plCount = 1;

        if (title == null || title == "" || title.isEmpty()) {
          title = this.generateDefaultTitle(); // Should generate using a counter (e.g. PatternLanguage1, PatternLanguage2, ...
        }
        this.title = title;
    }

    /**
     * Generates a default pattern language title, based on a global counter of pattern languages
     * @return default title for the new pattern language
     */
    public String generateDefaultTitle() {
        String title = "Pattern Language #" + Integer.toString(plCount);

        plCount++;

        return title;
    }
}
