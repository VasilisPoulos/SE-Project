package PatternsEditor;

public class PatternLanguage {

    private String title;

    public PatternLanguage(String title) {
        if (title == null || title == "") {
          title = "defaultName"; // Should generate using a counter (e.g. PatternLanguage1, PatternLanguage2, ...
        }
        this.title = title;
    }
}
