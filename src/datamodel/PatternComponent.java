package datamodel;

/*
 * 	PatternComponent class that defines the basic methods of the composite
 *	structure, along with default TRIVIAL IMPLEMENTATIONS.
 *	   |	think this means: define methods, some may do nothing and that's
 *	   | 	because we need child classes to inherit them.
 *	   |
 *	   |    "Trivial" typically refers to an implementation that demonstrates
 *	   |    the relevant functionality and no more.
 */

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class PatternComponent implements Cloneable
{
	protected String name;

	public PatternComponent (String name)
    {
        this.name = name;
    }

	/**
     * @return	components name
     */
	public String getName() {
		return this.name;
	}

    public void setName(String name) {
        this.name = name;
    }

    public String getContents() {return null;}

    public void setContents(String contents) {}

    public void setContents(String contents, int x){}


    /*
     * Saves PatternComponents name to a txt file
     *
     * Assumes file's path -> TODO: Make this work even if a folder is missing ?
     *
     */
    public void saveName() throws IOException {
        Files.write(Paths.get("./out/savedFiles/PatternComponent.txt"), getName().getBytes());
    }

    //TODO: Should write contents to file (V2)
    public void saveContents() throws IOException {}

	public void add(PatternComponent component){}

	public void remove(){}

    public PatternComponent getChild(){
        return new PatternComponent("");
    }
}