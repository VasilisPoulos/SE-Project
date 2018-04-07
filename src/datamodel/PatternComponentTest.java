package datamodel;

import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;

class PatternComponentTest {

    @org.junit.jupiter.api.Test
    void shouldClonePattern()
    {
        PatternComponent component = new PatternComponent();
        PatternComponent copy = component.clone();
        assertNotSame(component,copy);
    }

    @org.junit.jupiter.api.Test
    void add() {
    }

    @org.junit.jupiter.api.Test
    void remove() {
    }

    @org.junit.jupiter.api.Test
    void saveName() {
    }

    @org.junit.jupiter.api.Test
    void saveContents() {
    }

    @org.junit.jupiter.api.Test
    void getName() {
    }

    @org.junit.jupiter.api.Test
    void main() {
    }
}