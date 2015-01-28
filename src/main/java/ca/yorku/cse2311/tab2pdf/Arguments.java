package ca.yorku.cse2311.tab2pdf;

import java.io.File;
import java.io.IOException;

/**
 * Arguments
 *
 * @author Marco
 * @since 2015-01-28
 */
public class Arguments {

    private File inputFile = null;
    private File outputFile = null;

    public Arguments() {    // Default Constructor does nothing
        super();
    }

    public Arguments(File inputFile, File outputFile) {
        this.inputFile = inputFile;
        this.outputFile = outputFile;
    }

    public File getInputFile() {
        return inputFile;
    }

    public void setInputFile(String filePath) throws IOException {

        setInputFile(new File(filePath));
    }

    public void setInputFile(File inputFile) throws IOException {

        this.inputFile = inputFile;
    }

    public File getOutputFile() {
        return outputFile;
    }

    public void setOutputFile(String outputFilePath) throws IOException {

        setOutputFile(new File(outputFilePath));
    }

    public void setOutputFile(File outputFile) throws IOException {

        this.outputFile = outputFile;
    }
}