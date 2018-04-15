package datamodel;

import static org.junit.jupiter.api.Assertions.*;

class TemplateFactoryTest {

    @org.junit.jupiter.api.Test
    void shouldMakeTemplates() {

        TemplateFactory tf = new TemplateFactory();
        assertNotEquals(tf.getTemplatesList().isEmpty(), 0);
    }

    @org.junit.jupiter.api.Test
    void shouldMakeCopy() {

        TemplateFactory tf = new TemplateFactory();
        PatternComponent newPattern = tf.createTemplate("Micro-Pattern");
        PatternComponent oldPattern = tf.getTemplatesList().get("Micro-Pattern");

        newPattern.setName("asdf");
        oldPattern.setName("qwer");

        assertNotEquals(oldPattern.getName(), newPattern.getName());

    }

    @org.junit.jupiter.api.Test
    void shouldMakeDeepCopy() {
        TemplateFactory tf = new TemplateFactory();
        Pattern newPattern = (Pattern) tf.createTemplate("Micro-Pattern");

        PatternPart newPart = (PatternPart) newPattern.getComponentsList().get(0);
        PatternPart oldPart = (PatternPart) tf.getTemplatesList().get("Micro-Pattern").getComponentsList().get(0);

        newPart.setName("Lorem ipsum");
        oldPart.setName("dolor sit amet");

        assertNotEquals(newPart.getName(), oldPart.getName());
    }

}