package datamodel;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PatternTest {

    /*
    @org.junit.jupiter.api.Test
    void shouldClonePattern()
    {
        PatternComponent component = new PatternComponent("Pattern");
        PatternComponent copy = component.clone();
        assertNotSame(component,copy); //not the Same Object
        assertEquals("Pattern",component.getName());
        assertEquals("Pattern",copy.getName());
        component.setName("New name");
        assertEquals("New name",component.getName());
        assertEquals("Pattern",copy.getName());
    }
    */

    /**
     * Check if clone makes a deep copy
     *      Make sure that cloning makes a new object.
     *      If original components name changes, copy's name should stay
     *      the same
     */
    @org.junit.jupiter.api.Test
    void shouldClone()
    {
        Pattern newPattern = new Pattern("newPattern");
        Pattern clone = newPattern.clone();
        assertNotSame(newPattern,clone); //not the Same Object

    }
}