package ca.yorku.cse2311.tab2pdf;

import ca.yorku.cse2311.tab2pdf.parser.TabParser;
import ca.yorku.cse2311.tab2pdf.util.FileUtils;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.logging.Level;
import java.util.logging.Logger;

import static ca.yorku.cse2311.tab2pdf.PdfHelper.*;

/**
 * First iText example: Hello World.
 */
public class Main {

    /**
     * The name of the PDF file we will create
     */
    public static final String FILENAME = "hello";
    /**
     * PDF file suffix (*.pdf)
     */
    public static final String PDF_SUFFIX = ".pdf";
    private final static Logger LOG = Logger.getLogger(Main.class.getName());
    public static File inputFile;
    public static File outputFile;

    /**
     * Prints program usage and exits
     */
    public static void printUsageExit() {

        System.out.println("Usage: java -jar Tab2Pdf.jar [(-i|--input) <input_file>] [(-o|--output) <output_file>]");
        System.out.println("Example: java -jar Tab2Pdf.jar -i someTab.txt -o somePdf.pdf");
        System.exit(0);

    }

    public static void parseArgs(String[] args) {

        for (int i = 0; i < args.length; i++) {

            String arg = args[i];

            switch (arg.charAt(0)) {

                case '-':

                    if ((1 < arg.length()) && ('-' == arg.charAt(1))) {
                        // This is a double-dash option: --input or --output

                        if ("-input".equals(arg.substring(1))) {   // Set the input file

                            setInputFile(args[i + 1]);


                        } else if ("-output".equals(arg.substring(1))) {

                            setOutputFile(args[i + 1]);

                        }

                    } else {
                        if ("i".equals(arg.substring(1))) {   // Set the input file

                            setInputFile(args[i + 1]);


                        } else if ("o".equals(arg.substring(1))) {

                            setOutputFile(args[i + 1]);

                        }
                    }
                    break;

            }
        }
    }

    public static void setInputFile(String arg) {

        try {

            inputFile = new File(arg);
            FileUtils.testFileRead(inputFile);

        } catch (ArrayIndexOutOfBoundsException ex) {
            System.out.println("No input file specified.");
            printUsageExit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            printUsageExit();
        }

    }

    public static void setOutputFile(String arg) {

        try {

            outputFile = new File(arg);
            FileUtils.testFileReadWrite(outputFile);

        } catch (ArrayIndexOutOfBoundsException ex) {
            System.out.println("No output file specified.");
            printUsageExit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            printUsageExit();
        }

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

        LOG.setLevel(Level.ALL);
        Arguments arguments = new Arguments();

        if (0 != args.length) {

            try {
                arguments.setInputFile(findInputFileInArgs(args));
            } catch (Exception e) {
                LOG.log(Level.WARNING, e.getMessage());
            }

            try {
                arguments.setOutputFile(findOutputFileInArgs(args));
            } catch (Exception e) {
                LOG.log(Level.WARNING, e.getMessage());
            }
        }

        //MainJFrame window =

        System.exit(0);

         /*   printUsageExit();

        } else {

            try {

            parseArgs(args);

            if (null == inputFile) {
                System.out.println("No input file specified");
                printUsageExit();
            }
        }*/

        try {

            // we will allow the output file to be null. This means the user just wants to generate a temporary PDF
            if (null == outputFile) {
                outputFile = FileUtils.createTempFile(FILENAME, PDF_SUFFIX);
            }

            // Run the example code
            new Main().createPdf(outputFile);

            // open the newly created PDF
            Desktop.getDesktop().open(outputFile);

        } catch (Exception e) {

            // We fucked up somewhere
            // This should probably be handled with a Thread.UncaughtExceptionHandler
            e.printStackTrace();
        }

    }

    /**
     * Creates a PDF document.
     *
     * @param file the File that will be the new PDF document
     * @throws DocumentException
     * @throws IOException
     */
    public void createPdf(File file) throws Exception {

        // We'll clean this up later! I don't like the overuse of statics here, and everywhere in this file
        java.util.List<String> lines = FileUtils.readFile(inputFile);

        // step 1
        Document document = new Document();
        // step 2
        PdfWriter writer = PdfWriter.getInstance(document, Files.newOutputStream(file.toPath()));
        // step 3
        document.open();
        // step 4
        //document.add(new Paragraph("Hello World!"));                // Hello World!
        document.add(new Paragraph(TabParser.getTitle(lines)));     // The Tab's Title
        document.add(new Paragraph(TabParser.getSubtitle(lines)));  // The Tab's Subtitle
        drawShapes(writer);

        for (int i = 0; i < 9; i++)
            stave(i, writer);

        // step 5
        document.close();
    }

    private void drawShapes(PdfWriter writer) {
        linesCircles(0, 50, writer);
        circlesLines(0, 550, writer);
        for(int i = 0; i <= 600 ; i += 100){
            thinLine(1, i, writer);
            thickLine(2, i, writer);
        }

    }
}