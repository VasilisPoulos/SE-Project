package Test;

import datamodel.PatternComponent;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

class PatternComponentTest {

    @Test
    void shouldSaveName() throws IOException {

        /* create new pattern components */
        PatternComponent first = new PatternComponent("First");
        PatternComponent second = new PatternComponent("Second");

        Path firstFp = Paths.get("./first.txt");
        Path secondFp = Paths.get("./second.txt");
        /* save name .txt */
        first.saveName(firstFp);
        second.saveName(secondFp);

        /* read if written correctly */
        String read = Files.readAllLines(firstFp)
                .get(0);

        assertEquals("First", read);

        read = Files.readAllLines(secondFp)
                .get(0);

        assertEquals("Second", read);

        /* delete files created by test */
        Files.delete(firstFp);
        Files.delete(secondFp);
    }
}