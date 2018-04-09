package datamodel;

public class PatternPart extends PatternComponent
{
	public PatternPart(String name) {
		super(name);
	}

	/*
	*	This is an override
	*/
	public PatternComponent clone()
	{
		return null;
	}

	public void saveComtents()
	{
		return;
	}
}