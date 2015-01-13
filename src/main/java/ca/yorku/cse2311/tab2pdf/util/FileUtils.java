package ca.yorku.cse2311.tab2pdf.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;

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
     * @throws Exception
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
     * @throws Exception
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
}
