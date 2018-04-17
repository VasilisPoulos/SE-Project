package datamodel;

public class PatternPart extends PatternComponent

{
    private String contents;

    /**
     * Constructor.
     *
     * @param name - name of the pattern part
     * @param contents - contents/description of the pattern part
     */
    public PatternPart(String name, String contents) {
        super(name);
        this.contents = contents;
    }

    /**
     * Constructor.
     *
     * @param name - name of the pattern part
     */
    public PatternPart(String name) {
		super(name);
	}

    /** Return contents, passed to the constructor.  */
    @Override
    public String getContents() {
        return contents;
    }

    /** Set the contents field. */
    @Override
    public void setContents(String contents) {
        this.contents = contents;
    }

    //TODO: Write contents to output file (V2)
    /*
    public void saveContents(){
        return;
    }
    */

    /** Return string representation of the pattern part. */
    @Override
	public String toString() {
        return this.name + ":\n" + this.contents;
    }
}