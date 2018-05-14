package datamodel;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

class PatternPartTest {

    @Test
    void saveContents() throws IOException
    {
        /* create new pattern parts */
        PatternPart first = new PatternPart("First","First Contents");
        PatternPart second = new PatternPart("Second","Second Contents");

        Path firstFp = Paths.get("./first.txt");
        Path secondFp = Paths.get("./second.txt");
        /* save to .txt */
        first.saveContents(firstFp);
        second.saveContents(secondFp);

        /* read if written correctly */
        String read = Files.readAllLines(firstFp)
                .get(0);

        assertEquals("First Contents", read);

        read = Files.readAllLines(secondFp)
                .get(0);

        assertEquals("Second Contents", read);

        /* delete files created by test */
        Files.delete(firstFp);
        Files.delete(secondFp);
    }
}