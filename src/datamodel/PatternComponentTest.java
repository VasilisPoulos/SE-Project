package datamodel;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class PatternComponentTest {

    @Test
    void saveName()
    {
        PatternComponent pt = new PatternComponent("newPt");
        try{
            pt.saveName();
        }
        catch (IOException e) {
            System.out.println("Error Printing");
        }
    }
}