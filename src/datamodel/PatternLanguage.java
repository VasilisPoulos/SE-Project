package datamodel;

public class PatternLanguage {

    private String title;
    private Integer plCount; /* TODO: Should move this to an interface class!!! */

    public String getTitle() {
        return title;
    }

    public PatternLanguage(String title) {
        plCount = 1;

        if (title == null || title == "" || title.isEmpty()) {
          title = this.generateDefaultTitle(); // Should generate using a counter (e.g. PatternLanguage1, PatternLanguage2, ...
        }
        this.title = title;
    }

    public String generateDefaultTitle() {
        plCount++;
        String title = "Pattern Language #" + Integer.toString(plCount);
        return title;
    }
}
