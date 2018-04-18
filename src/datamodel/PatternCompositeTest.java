package datamodel;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PatternCompositeTest {

    /**
     * Acceptance Test [US2]
     * Check if item was added in componentList.
     */
    @org.junit.jupiter.api.Test
    void add()
    {
        PatternLanguage composite = new PatternLanguage("TestComposite");
        PatternComponent component = new PatternComponent("Component");
        composite.add(component);
        assertEquals(composite.getComponentsList().get(0).getName(),"Component");
    }

    /**
     * Acceptance Test [US3]
     * Check if item was removed from componentList.
     */
    @org.junit.jupiter.api.Test
    void remove()
    {
        PatternLanguage composite = new PatternLanguage("TestComposite");
        PatternComponent component = new PatternComponent("Component");
        composite.add(component);
        composite.remove("Component");
        assertEquals(true,composite.getComponentsList().isEmpty());

    }
}