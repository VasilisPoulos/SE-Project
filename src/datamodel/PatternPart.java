package datamodel;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

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

    /**
     * Save Contents in a txt file
     *
     *  Each object has it's own file name.
     *  Using Java 7 utility class Files.
     *
     *  @see http://www.baeldung.com/java-write-to-file
     *
     *  TODO: Maybe check if file exists first.
     *  for improvements:
     *  @see https://docs.oracle.com/javase/7/docs/api/java/io/File.html
     */
    public void saveContents() throws IOException {
        Files.write(Paths.get("./out/savedFiles/PatternPartContents/"+getName()+"-Contents.txt"),
                getContents().getBytes());
    }

    /** Return string representation of the pattern part. */
    @Override
	public String toString() {
        return this.name + ":\n" + this.contents;
    }
}