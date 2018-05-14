package datamodel;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

class PatternComponentTest {

    @Test
    void shouldSaveName() throws IOException {

        /* create new pattern components */
        PatternComponent first = new PatternComponent("First");
        PatternComponent second = new PatternComponent("Second");

        /* save name .txt */
        first.saveName();
        second.saveName();

        /* read if written correctly */
        String read = Files.readAllLines(Paths.get("./out/savedFiles/PatternComponent/First.txt"))
                .get(0);

        assertEquals("First", read);

        read = Files.readAllLines(Paths.get("./out/savedFiles/PatternComponent/Second.txt"))
                .get(0);

        assertEquals("Second", read);

        /* delete files created by test */
        Files.delete(Paths.get("./out/savedFiles/PatternComponent/First.txt"));
        Files.delete(Paths.get("./out/savedFiles/PatternComponent/Second.txt"));
    }
}