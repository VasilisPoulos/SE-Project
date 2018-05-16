package datamodel;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

public class Decorator extends PatternComposite {

    private String beginTag;
    private String endTag;

    /**
     * Constructor
     * @param name name of the Decorator
     * @param beginTag the begin tag for the corresponding element
     * @param endTag the end tag for the corresponding element
     */
    public Decorator(String name, String beginTag, String endTag)
    {
        super(name);
        this.beginTag = beginTag;
        this.endTag = endTag;
    }

    /**
     * Saves a decorated Pattern Language to the disk
     * @param fp the file path we want to save to
     * @throws IOException on file write error
     */
    public void saveDecorated(Path fp) throws IOException {

        String str;
        // Add \documentclass{article}, create or append depending on whether file exists
        str = "\\documentclass{article}\n\n";
        byte[] bytes = str.getBytes();
        if (Files.exists(fp)) {
            Files.write(fp, bytes, StandardOpenOption.APPEND);
        }
        else {
            Files.write(fp, bytes, StandardOpenOption.CREATE);
        }
        // Add title
        this.saveName(fp);

        // Add \begin{document}
        str = "\\begin{document}\n\n";
        bytes = str.getBytes();
        Files.write(fp, bytes, StandardOpenOption.APPEND);

        // Add \maketitle to show title and date
        str = "\\maketitle\n\n";
        bytes = str.getBytes();
        Files.write(fp, bytes, StandardOpenOption.APPEND);


        // Add contents
        this.saveContents(fp);

        //Add \end{document}
        str = "\\end{document}\n\n";
        bytes = str.getBytes();
        Files.write(fp, bytes, StandardOpenOption.APPEND);
    }

    /**
     * @return the begin tag of the decorated component
     */
    public String getBeginTag() {
        return beginTag;
    }

    /**
     * @return the end tag of the decorated component
     */
    public String getEndTag() {
        return endTag;
    }

    @Override
    public void decorateComponents(LatexDecoratorFactory latexDecoratorFactory)
    {
    }
}
