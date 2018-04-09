package datamodel;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PatternCompositeTest {

    @org.junit.jupiter.api.Test
    void add()
    {
        PatternComposite composite = new PatternComposite("TestComposite");
        PatternComponent component = new PatternComponent("Component");
        composite.add(component);
        assertEquals(composite.componentList.get(0).getName(),"Component");
    }
}