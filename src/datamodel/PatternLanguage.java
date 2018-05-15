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

    public static PatternLanguage LoadPatternLanguage(Path filename) throws IOException {
        List<String> data = new ArrayList<String>();
        Stream<String> lines = Files.lines(filename);
        lines
                .filter(line -> !line.isEmpty())
                .forEach(line -> data.add(line));
        lines.close();

        return parsePL(data);
    }

    /**
     * TODO: Move to datamodel
     * Parse the Pattern Language from its text file
     * @param data the lines of the file.
     * @return new Pattern Language object parsed from List of String objects
     */
    private static PatternLanguage parsePL(List<String> data) {
        // Placeholder variables
        PatternLanguage newPl = new PatternLanguage();
        Pattern currentPattern = new Pattern("");
        PatternPart currentPart = new PatternPart("");

        // Flags for whether we are still parsing a name
        boolean plNameFlag = false;
        boolean patternNameFlag = false;
        boolean partNameFlag = false;
        boolean iterationFlag = false;

        // Variable to hold string up to now
        String currentStr = "";
        // Temporary variable
        String tempStr;

        for (String i: data) {

            if (i.substring(0,4).equals("--- ")) {
                // Set contents of Pattern Part
                currentPart.setContents(currentStr);
                currentStr = "";

                // Pattern Language name
                if (i.endsWith(" ---")) {
                    currentStr += i.substring(4, i.length() - 4);
                    //  Create new Pattern Language
                    newPl.setName(currentStr);

                    currentStr = "";
                    plNameFlag = false;
                }
                else {
                    currentStr += i.substring(4);
                    plNameFlag = true;
                    iterationFlag = true;
                }

            }
            else if (i.substring(0, 3).equals("-- ")) {
                // Set contents of Pattern Part
                currentPart.setContents(currentStr);
                currentStr = "";
                // Pattern name
                if (i.endsWith(" --")) {
                    currentStr += i.substring(3, i.length() - 3);
                    // Create Pattern and add to PL
                    Pattern pattern = new Pattern(currentStr);
                    newPl.add(pattern);
                    currentPattern = pattern;
                    currentStr = "";
                    patternNameFlag = false;
                }
                else {
                    currentStr += i.substring(3);
                    patternNameFlag = true;
                    iterationFlag = true;
                }
            }
            else if (i.substring(0, 2).equals("- ")) {
                // Set contents of Pattern Part
                currentPart.setContents(currentStr);
                currentStr = "";
                // Pattern Part name
                if (i.endsWith(" -")) {
                    currentStr += i.substring(2, i.length() - 2);
                    // Create Pattern Part and add to current Pattern
                    PatternPart part = new PatternPart(currentStr);
                    currentPattern.add(part);
                    currentPart = part;

                    currentStr = "";
                    partNameFlag = false;
                }
                else {
                    currentStr += i.substring(2);
                    partNameFlag = true;
                    iterationFlag = true;
                }
            }
            else {
                // Pattern Part contents
                currentStr += i;
            }

            /* Check if we aren't done reading this string */
            if (plNameFlag) {
                if (i.endsWith(" ---"))
                    currentStr += i.substring(0, i.length() - 4);
                else {
                    if (iterationFlag)
                        iterationFlag = false;
                    else
                        currentStr += i;
                }
                //  Create new Pattern Language
                newPl.setName(currentStr);

                currentStr = "";
                plNameFlag = false;
            }
            else if (patternNameFlag) {
                if (i.endsWith(" --"))
                    currentStr += i.substring(0, i.length() - 3);
                else {
                    if (iterationFlag)
                        iterationFlag = false;
                    else
                        currentStr += i;
                }
                // Create Pattern and add to PL
                Pattern pattern = new Pattern(currentStr);
                newPl.add(pattern);
                currentPattern = pattern;
                currentStr = "";
                patternNameFlag = false;
            }
            else if (partNameFlag) {
                if (i.endsWith(" -"))
                    currentStr += i.substring(0, i.length() - 2);
                else {
                    if (iterationFlag)
                        iterationFlag = false;
                    else
                        currentStr += i;
                }
                // Create Pattern Part and add to current Pattern
                PatternPart part = new PatternPart(currentStr);
                currentPattern.add(part);
                currentPart = part;

                currentStr = "";
                partNameFlag = false;
            }

        }
        // Set contents of Pattern Part
        currentPart.setContents(currentStr);

        return newPl;
    }


    @Override
    public void decorateComponents(DecoratorAbstractFactory decoratorFactory)
    {
        //TODO
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
