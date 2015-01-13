package ca.yorku.cse2311.tab2pdf.util;

import java.io.*;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

/**
 * Files
 *
 * @author Marco
 * @since 2015-01-13
 */
public class FileUtils {

    public static File createTempFile(String filename, String extension) throws IOException {
        return Files.createTempFile(filename, extension).toFile();
    }

    /**
     * Tests if a File exists and can be read
     *
     * @param f the file to test
     *
     * @throws Exception if there's a problem reading the file
     */
    public static void testFileRead(File f) throws Exception {

        if (!f.exists()) {
            throw new FileNotFoundException("File does not exist: " + f.getAbsolutePath());
        } else if (!f.canRead()) {
            throw new IOException("File cannot be read: " + f.getAbsolutePath());
        }

    }

    /**
     * Tests if a File exists and can be read and written to
     *
     * @param f the file to test
     *
     * @throws Exception if there's a problem reading/writing to the file
     */
    public static void testFileReadWrite(File f) throws Exception {

        if (!f.exists()) {
            throw new FileNotFoundException("File does not exist: " + f.getAbsolutePath());
        } else if (!f.canRead()) {
            throw new IOException("File cannot be read: " + f.getAbsolutePath());
        } else if (!f.canWrite()) {
            throw new IOException("File cannot be written to: " + f.getAbsolutePath());
        }

    }

    /**
     * Reads a File into a List
     *
     * @param f the File to read
     * @return A List of Strings, each element is one line in the file
     * @throws IOException if there's a problem reading the file
     */
    public static List<String> readFile(File f) throws IOException {

        // Creates an empty list.
        List<String> lines = new ArrayList<>();

        // Opens and reads the file.
        BufferedReader in = new BufferedReader(new FileReader(f));

        // This is a standard Java idiom for reading a file line-by-line into a List
        String line = "";
        while ((line = in.readLine()) != null) {
            lines.add(line);
        }

        return lines;
    }
}
