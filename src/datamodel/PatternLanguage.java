package datamodel;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class PatternLanguage extends PatternComposite
{
    private static Integer plCount = 0;

    private enum ParseType {
        PL_NAME,
        PATTERN_NAME,
        PART_NAME,
        PART_CONTENTS,
        EOF
    }

    public PatternLanguage()
    {
        super("Default");
    }
    /**
     * Overloaded constructor with default parameters
     *
     * @param name Pattern Language's name
     */
    public PatternLanguage(String name)
    {
        super(name);
        if (name == null || name.equals("") || name.isEmpty())
        {
            name = this.generateDefaultTitle(); // Should generate using a counter (e.g. Pattern Language #1, Pattern Language #2, ...)
        }
        this.name = name;
    }

    /**
     * @param filename the file containing the pattern language
     * @return the pattern language loaded from the file
     * @throws Exception if a non-valid pattern language is detected in the file
     * @throws IOException on file read error
     */
    public static PatternLanguage loadPatternLanguage(Path filename) throws Exception {
        List<String> data = new ArrayList<String>();
        Stream<String> lines = Files.lines(filename);
        lines
                .forEach(line -> data.add(line));
        lines.close();


        return parsePL(data);
    }

    /**
     * Parse the Pattern Language from its text file
     * @param data the lines of the file.
     * @return new Pattern Language object parsed from List of String objects
     * @throws Exception if a non-valid pattern language is detected in the file
     * @throws IOException on file read error
     */
    private static PatternLanguage parsePL(List<String> data) throws Exception {

        // Placeholder variables
        PatternLanguage newPl = new PatternLanguage();
        Pattern currentPattern = new Pattern("");
        PatternPart currentPart = new PatternPart("");

        // Flag to hold information about what we might parse next
        ParseType flag = ParseType.PL_NAME;

        for (String i: data) {

            if (i.isEmpty())
                continue;

            switch (flag) {
                case PL_NAME:
                    newPl.setName(i);
                    flag = ParseType.PATTERN_NAME;
                    break;
                case PATTERN_NAME:
                    currentPattern = new Pattern(i);
                    newPl.add(currentPattern);
                    flag = ParseType.PART_NAME;
                    break;
                case PART_NAME:
                    PatternPart part = new PatternPart(i);
                    currentPattern.add(part);
                    currentPart = part;
                    flag = ParseType.PART_CONTENTS;
                    break;
                case PART_CONTENTS:
                    currentPart.setContents(i);
                    String line1;
                    String line2;
                    // Check for EOF
                    if (data.indexOf(i) + 1 >= data.size()) {
                        flag = ParseType.EOF;
                        break;
                    }
                    else if (data.indexOf(i) + 2 >= data.size()) {
                        line1 = data.get(data.indexOf(i) + 1);
                        line2 = "";
                        flag = ParseType.EOF;
                    }
                    else {
                    line1 = data.get(data.indexOf(i) + 1);
                    line2 = data.get(data.indexOf(i) + 2);
                    }
                    if (line1.isEmpty()) {
                        if (line2.isEmpty())
                            flag = ParseType.PATTERN_NAME;
                        else
                            flag = ParseType.PART_NAME;
                    }
                    else {
                        throw new Exception("ERROR: File does not contain a valid Pattern Language.");
                    }
                    break;
                case EOF:
                    return newPl;
            }
        }
        return newPl;
    }


    @Override
    public void decorateComponents(LatexDecoratorFactory latexDecoratorFactory)
    {
        for (int i=0; i<componentsList.size(); i++)
        {
            componentsList.set(i, latexDecoratorFactory.createPatternDecorator(componentsList.get(i)));
        }
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
}
