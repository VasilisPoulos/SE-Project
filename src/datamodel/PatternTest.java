package datamodel;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class PatternTest{

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
     * Make sure that cloning makes a new object.
     * If original components name changes, copy's name should stay
     * the same
     */
    @Test
    void shouldClone() {

        Pattern oldPattern = new Pattern("newPattern");
        PatternPart part = new PatternPart("Lorem ipsum");
        oldPattern.add(part);

        /* Test that clone works correctly */
        Pattern newPattern = oldPattern.clone();
        assertEquals(oldPattern.getComponentsList().get(0).getName(), newPattern.getComponentsList().get(0).getName());
        assertNotEquals(oldPattern.getComponentsList().get(0), newPattern.getComponentsList().get(0));


    }

    @Test
    void shouldMakeDeepCopy() {

        Pattern oldPattern = new Pattern("newPattern");
        PatternPart part = new PatternPart("Lorem ipsum");
        oldPattern.add(part);

        Pattern newPattern = oldPattern.clone();

        /* Test that clone has different components after changing */
        newPattern.getComponentsList().get(0).setName("dolor sit");
        assertNotEquals(oldPattern.getComponentsList().get(0), newPattern.getComponentsList().get(0));

        /* Test that clone has same size components before adding */
        assertEquals(oldPattern.getComponentsList().size(), newPattern.getComponentsList().size());

        /* Test that clone has different size after adding components */
        newPattern.add(new PatternPart("amet"));
        assertNotEquals(oldPattern.getComponentsList().size(), newPattern.getComponentsList().size());
    }
}