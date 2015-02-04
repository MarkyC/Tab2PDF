package ca.yorku.cse2311.tab2pdf;

import ca.yorku.cse2311.tab2pdf.ui.MainJFrame;
import ca.yorku.cse2311.tab2pdf.util.FileUtils;

import javax.swing.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Main.java
 * Entry Point of Our Application
 */
public class Main {

    /**
     * The name of the PDF file we will create
     */
    public static final String FILENAME = "tab2pdf-";

    /**
     * PDF file suffix (*.pdf)
     */
    public static final String PDF_SUFFIX = ".pdf";

    private final static Logger LOG = Logger.getLogger(Main.class.getName());

    /**
     * Prints program usage and exits
     */
    public static void printUsage() {

        System.out.println("Usage: java -jar Tab2Pdf.jar [(-i|--input) <input_file>] [(-o|--output) <output_file>]");
        System.out.println("Example: java -jar Tab2Pdf.jar -i someTab.txt -o somePdf.pdf");
    }

    private static String findInputFileInArgs(String[] args) throws Exception {

        for (int i = 0; i < args.length; i++) {

            String arg = args[i];

            switch (arg.charAt(0)) {

                case '-':

                    if ((1 < arg.length()) && ('-' == arg.charAt(1))) {
                        // This is a double-dash option: --input or --output

                        if ("-input".equals(arg.substring(1))) {   // Set the input file

                            if (args.length > i + 1) {
                                return args[i + 1];
                            }
                        }

                    } else {
                        if ("i".equals(arg.substring(1))) {   // Set the input file

                            if (args.length > i + 1) {
                                return args[i + 1];
                            }
                        }
                    }
                    break;

            }
        }

        throw new Exception("No input file found");
    }

    private static String findOutputFileInArgs(String[] args) throws Exception {

        for (int i = 0; i < args.length; i++) {

            String arg = args[i];

            switch (arg.charAt(0)) {

                case '-':

                    if ((1 < arg.length()) && ('-' == arg.charAt(1))) {
                        // This is a double-dash option: --input or --output

                        if ("-output".equals(arg.substring(1))) {

                            if (args.length > i + 1) {
                                return args[i + 1];
                            }
                        }

                    } else {
                        if ("o".equals(arg.substring(1))) {

                            if (args.length > i + 1) {
                                return args[i + 1];
                            }
                        }
                    }
                    break;

            }
        }

        throw new Exception("No output file found");
    }

    /**
     * Creates a PDF file that says Hello World and opens it
     *
     * @param args no arguments needed
     */
    public static void main(String[] args) {

        // Set Logging First!
        LOG.setLevel(Level.ALL);

        // Holds the command line arguments for this app
        final Arguments arguments = new Arguments();

        // if the user gave arguments via the command line when they started the app
        // parse them for input (-i, --input) and output (-o, --output) Files
        if (0 != args.length) {

            try {
                arguments.setInputFile(findInputFileInArgs(args));
            } catch (Exception e) {
                LOG.warning(e.getMessage());
            }

            try {
                arguments.setOutputFile(FileUtils.createTempFile(FILENAME, PDF_SUFFIX));
                arguments.setOutputFile(findOutputFileInArgs(args));    // if this fails, the output is a temp file
            } catch (Exception e) {
                LOG.warning(e.getMessage());
            }
        } else {

            // No Args given? Show them the usage.
            printUsage();
        }

        // This starts our GUI
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {

                try {
                    MainJFrame.createAndShow("Tab2PDF", arguments);
                } catch (Exception e) {

                    // Our GUI messed up somehow, log it
                    LOG.severe(e.getMessage());
                }
            }
        });
    }
}