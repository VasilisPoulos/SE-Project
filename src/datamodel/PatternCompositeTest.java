package datamodel;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PatternCompositeTest {

    /**
     * Check if item was added in componentList.
     */
    @org.junit.jupiter.api.Test
    void add()
    {
        PatternComposite composite = new PatternComposite("TestComposite");
        PatternComponent component = new PatternComponent("Component");
        composite.add(component);
        assertEquals(composite.componentList.get(0).getName(),"Component");
    }

    /**
     * Check if item was removed from componentList.
     */
    @org.junit.jupiter.api.Test
    void remove()
    {
        PatternComposite composite = new PatternComposite("TestComposite");
        PatternComponent component = new PatternComponent("Component");
        composite.add(component);
        composite.remove("Component");
        assertEquals(true,composite.componentList.isEmpty());
    }
}