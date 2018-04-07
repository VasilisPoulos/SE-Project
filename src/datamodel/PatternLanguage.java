package datamodel;
import gui.MainViewController;

public class PatternLanguage {

    private String title;

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
        Integer plCount = MainViewController.getPlCount();
        String title = "Pattern Language #" + Integer.toString(plCount);

        MainViewController.setPlCount(plCount++);

        return title;
    }
}
