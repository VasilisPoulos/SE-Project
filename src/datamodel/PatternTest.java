package datamodel;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class PatternTestS{

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

        Pattern newPattern = new Pattern("newPattern");


        PatternPart comp1 = new PatternPart("comp1", "");
        newPattern.add(comp1);
        PatternPart comp2 = new PatternPart("comp2", "");
        newPattern.add(comp2);
        Pattern clone = newPattern.clone();
        assertNotSame(newPattern, clone); //not the Same Object

        System.out.println(newPattern.getName());
        for (PatternComponent p : newPattern.componentsList) {
            System.out.println(p.getName());
        }

        System.out.println("\n");
        clone.setName("Cloned");
        PatternPart comp3 = new PatternPart("comp3", "");
        clone.add(comp3);

        System.out.println(clone.getName());
        for (PatternComponent p : clone.componentsList) {
            System.out.println(p.getName());
        }

        System.out.println("\n");
        System.out.println("Last iteration");

        System.out.println("\n");
        newPattern.componentsList.get(1).setName("try");
        System.out.println(newPattern.getName());
        for (PatternComponent p : newPattern.componentsList) {
            System.out.println(p.getName());
        }

        System.out.println(clone.getName());
        for (PatternComponent p : clone.componentsList) {
            System.out.println(p.getName());
        }
    }
}