package datamodel;
/*
 * 	PatternComponent class that defines the basic methods of the composite
 *	structure, along with default TRIVIAL IMPLEMENTATIONS.
 *	   |	think this means: define methods, some may do nothing and that's
 *	   | 	because we need child classes to inherit them.
 *	   |
 *	   |    "Trivial" typically refers to an implementation that demonstrates
 *	   |    the relevant functionality and no more.
 *	
 *	Random notes on packages:
 * 	CreatePackage-Compile  	javac -d . demo.java
 *	Run						java mypack.demo
 */

public class PatternComponent implements Cloneable
{
	protected String name;

	public PatternComponent (String name)
    {
        this.name = name;
    }

	/** @return	components name */
	public String getName() {
		return this.name;
	}

    public void setName(String name) {
        this.name = name;
    }

    public String getContents() {return null;}

    public void setContents(String contents) {}

    public void setContents(String contents, int x){}

    //TODO: Should write this.name to file
    public void saveName(){}

    //TODO: Should write contents to file
    public void saveContents() {}

    //TODO: Probably empty in order to be implemented in subclass.
	public void add(PatternComponent component){}

    //TODO: Probably empty in order to be implemented in subclass.
	public void remove(){}

    // TODO
    public PatternComponent getChild(){
        return new PatternComponent("");
    }
}