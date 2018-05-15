package datamodel;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;

public abstract class PatternComposite extends PatternComponent {

    private Integer count;      // so no two patters are created with the same name

    /**
     * ArrayList of components.
     */
    protected ArrayList<PatternComponent> componentsList;

    /**
     * Calling parent constructor, initializing array.
     *
     * @param name Name of the composite
     */
    public PatternComposite(String name)
    {
        super(name);
        this.count = 0;
        this.componentsList = new ArrayList<>();
    }

    @Override
    public void setContents(String contents) {
        super.setContents(contents);
    }



    @Override
    public void saveContents(Path fp) throws IOException {

        boolean fileExists = Files.exists(fp);
        if(fileExists) {
            // Iterate through the list and write the names and contents of each element to the text file
            for (PatternComponent i: this.componentsList) {
                i.saveName(fp);
                i.saveContents(fp);
            }
        }
        else {
                System.out.println("Error: File \"" + fp.toString() + "\" not found.");
                throw new IOException(fp.toString());
        }

    }

    /**
     * Adds a PatternComponent item in ArrayList
     *
     * @param component new component added to the list
     */
    @Override
    public void add(PatternComponent component)
    {
        for (PatternComponent i: this.componentsList) {
            if (i.getName().equals(component.getName())) {
                this.count++;
                component.setName(component.getName() + "-" + Integer.toString(this.count));
            }
        }
        this.componentsList.add(component);
    }

    /**
     * Removes a PatternComponent item from the ArrayList
     *     if component List contains the item requested, remove it.
     *
     * @param patternComponentTitle pattern components name
     */
    public void remove(String patternComponentTitle)
    {
        this.componentsList.removeIf((PatternComponent p) -> p.getName().equals(patternComponentTitle));

    }

    /*
     *  Inherited by every subclass
     */
    public abstract void decorateComponents(DecoratorAbstractFactory decoratorFactory);

    @Override
    public PatternComponent getChild() {
        return super.getChild();
    }

    @Override
    public String toString() {
        String list="";
        for(int i=0;i<componentsList.size();i++){
            list += componentsList.get(i).toString() + '\n';
        }
        return list;
    }

    /**
     * For testing purposes.
     * @return patternComponent's componentsList
     */
    public ArrayList<PatternComponent> getComponentsList() {
        return componentsList;
    }
}
