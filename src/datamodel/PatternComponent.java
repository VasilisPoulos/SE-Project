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
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

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

    //TODO: implement if needed

    public String getContents() {return null;}

    public void setContents(String contents) {}

    public void setContents(String contents, int x){}


    /**
     * Writes the name of a PatternComponent to a text file
     *
     * @param fp The filepath
     *
     * @throws IOException on failure to write to the file
     */
    public void saveName(Path fp) throws IOException {
        boolean fileExists = Files.exists(fp);
        String str;
        if(fileExists) {
            if (this.getClass() == Decorator.class) {

                if (((PatternComposite) this).getComponentsList().get(0) instanceof Pattern)
                    str = "\n" + ((Decorator) this).getBeginTag() + "\n\n";
                else if (((PatternComposite) this).getComponentsList().get(0) instanceof PatternLanguage)
                    str = ((Decorator) this).getBeginTag() + "\n\n";
                else
                    str = ((Decorator) this).getBeginTag() + "\n";
                str = str.replace("#", "\\#");
                str = str.replace("%", "\\%");

            }
            else {
                if (this instanceof Pattern) {
                    str = "\n" + this.name + "\n\n";
                }
                else if (this instanceof PatternLanguage) {
                    str = this.name + "\n\n";
                }
                else {
                    str = this.name + "\n";
                }
            }
            // Append to the file
            byte[] bytes = str.getBytes();
            Files.write(fp, bytes, StandardOpenOption.APPEND);
        }
        // We need to create the file
        else {
            str = this.name + "\n\n";
            byte[] bytes = str.getBytes();
            Files.write(fp, bytes, StandardOpenOption.CREATE);
        }

    }

    public void saveContents(Path fp) throws IOException {
        return;
    }

	public void add(PatternComponent component){}

	public void remove(){}

    public PatternComponent getChild(){
        return new PatternComponent("");
    }
}