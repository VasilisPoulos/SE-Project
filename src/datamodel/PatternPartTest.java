package datamodel;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class PatternPartTest {

    @Test
    void saveContents() throws IOException
    {
        PatternPart first = new PatternPart("First","First Contents");
        PatternPart second = new PatternPart("Second","Second Contents");
        first.saveContents();
        second.saveContents();
    }
}