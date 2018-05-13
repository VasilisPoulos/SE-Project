package datamodel;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class PatternPartTest {

    @Test
    void saveContents() throws IOException
    {
        PatternPart pt = new PatternPart("New","Contents");
        pt.saveContents();
    }
}