package datamodel;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

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
     */
    public void saveContents(Path fp) throws IOException {
        boolean fileExists = Files.exists(fp);
        // Add 2 newlines, one to escape from the current line and another for readability
        String str = this.contents + "\n\n";
        byte[] bytes = str.getBytes();
        if(fileExists) {
            // Append to the file
            Files.write(fp, bytes, StandardOpenOption.APPEND);
        }
        else {
            System.out.println("Error: File \"" + fp.toString() + "\" not found.");
            throw new IOException(fp.toString());
        }
    }

    /** Return string representation of the pattern part. */
    @Override
	public String toString() {
        return this.name + ":\n" + this.contents;
    }
}