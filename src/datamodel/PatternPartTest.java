package datamodel;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

class PatternPartTest {

    @Test
    void saveContents() throws IOException
    {
        /* create new patterns */
        PatternPart first = new PatternPart("First","First Contents");
        PatternPart second = new PatternPart("Second","Second Contents");

        /* save to .txt */
        first.saveContents();
        second.saveContents();

        /* read if written correctly */
        String read = Files.readAllLines(Paths.get("./out/savedFiles/PatternPartContents/First-Contents.txt"))
                .get(0);

        assertEquals("First Contents", read);

        read = Files.readAllLines(Paths.get("./out/savedFiles/PatternPartContents/Second-Contents.txt"))
                .get(0);

        assertEquals("Second Contents", read);

        /* delete files created by test */
        Files.delete(Paths.get("./out/savedFiles/PatternPartContents/First-Contents.txt"));
        Files.delete(Paths.get("./out/savedFiles/PatternPartContents/Second-Contents.txt"));
    }
}