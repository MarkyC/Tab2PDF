package ca.yorku.cse2311.tab2pdf;

import java.io.File;
import java.io.FileNotFoundException;
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

    public void setInputFile(File inputFile) throws IOException {

        if (!inputFile.exists()) {    // The input file does not exist

            throw new FileNotFoundException("File not found: " + inputFile.getAbsolutePath());

        } else if (!inputFile.canRead()) { // We can't read from the input file
            throw new IOException("Input file cannot be opened for reading: " + inputFile.getAbsolutePath());
        }

        this.inputFile = inputFile;
    }

    public void setInputFile(String filePath) throws IOException {
        setInputFile(new File(filePath));
    }

    public File getOutputFile() {
        return outputFile;
    }

    public void setOutputFile(File outputFile) throws IOException {

        if (!outputFile.exists()) {   // File does not exist

            throw new FileNotFoundException("File not found: " + outputFile.getAbsolutePath());

        } else if (!outputFile.canRead()) {   // File cannot be read

            throw new IOException("Output file cannot be opened for reading: " + outputFile.getAbsolutePath());

        } else if (!outputFile.canWrite()) {  // File cannot be written to

            throw new IOException("Output file cannot be opened for writing: " + outputFile.getAbsolutePath());

        }

        this.outputFile = outputFile;
    }

    public void setOutputFile(String outputFilePath) throws IOException {
        setOutputFile(new File(outputFilePath));
    }
}