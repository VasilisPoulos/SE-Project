package datamodel;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

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
     * Make sure that cloning makes a new object.
     * If original components name changes, copy's name should stay
     * the same
     */
    @org.junit.jupiter.api.Test
    void shouldClone() {
        ArrayList<PatternComponent> newPatternList;
        ArrayList<PatternComponent> cloneList;

        Pattern newPattern = new Pattern("newPattern");
        Pattern clone = newPattern.clone();
        assertNotSame(newPattern, clone); //not the Same Object

        newPatternList = newPattern.getComponentsList();
        PatternComponent comp1 = new PatternComponent("comp1");
        newPattern.add(comp1);
        PatternComponent comp2 = new PatternComponent("comp2");
        newPattern.add(comp2);
        for (PatternComponent p : newPatternList) {
            System.out.println(p.getName());
        }

        PatternComponent comp3 = new PatternComponent("comp3");
        clone.add(comp3);
        cloneList = clone.getComponentsList();
        for (PatternComponent p : cloneList) {
            System.out.println(p.getName());
        }

        System.out.println("Last iteration");
        for (PatternComponent p : newPatternList) {
            System.out.println(p.getName());
        }
    }
}