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

	//TODO: Probably empty in order to be implemented in subclass.
	public void add(PatternComponent component){}

    //TODO: Probably empty in order to be implemented in subclass.
	public void remove(){}
	
	/**
	 * Creates a clone of a PatternComponent object.
     *     This is a Deep copy of this object because name is of type
     *     String which is immutable.
     *
     * I think it is best to use copy constructors or factory methods
     * instead of overriding clone().
     *
     * @return PatternComponent clone.
	 */
	@Override
	public PatternComponent clone()
	{
		try
		{
			return (PatternComponent) super.clone();
		}
		catch(CloneNotSupportedException e)
		{
			throw new AssertionError();
		}
	}

	/**
	 *	Saves a new name
     *
	 *	@param	name	the new name.
	 */
	//TODO: Doesn't follow UML 's definition.
	public void saveName(String name)
	{
		this.name = name;
	}

	//TODO: don't know what this is supposed to do.
	public void saveContents() {}

	/**
	 *	Only for testing
	 *	@return	components name
	 */
	public String getName()
	{
		return this.name;
	}
}