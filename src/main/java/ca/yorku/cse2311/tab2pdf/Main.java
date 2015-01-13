package ca.yorku.cse2311.tab2pdf;

import ca.yorku.cse2311.tab2pdf.util.FileUtils;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

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

    /**
     * Creates a PDF file that says Hello World and opens it
     *
     * @param args no arguments needed
     */
    public static void main(String[] args) {

        if (0 == args.length) {

            printUsageExit();
        } else {

            parseArgs(args);

            if (null == inputFile) {
                System.out.println("No input file specified");
                printUsageExit();
            }
        }

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
    public void createPdf(java.io.File file)
            throws DocumentException, IOException {
        // step 1
        Document document = new Document();
        // step 2
        PdfWriter writer = PdfWriter.getInstance(document, Files.newOutputStream(file.toPath()));
        // step 3
        document.open();
        // step 4
        document.add(new Paragraph("Hello World!"));
        drawShapes(writer);
        // step 5
        document.close();
    }

    private void drawShapes(PdfWriter writer) {
        //Draws 2 lines
        PdfHelper.line(100, 100, 200, 100, 1.5f, writer);
        PdfHelper.line(100, 110, 200, 110, 1, writer);

        //Draws 2 Circles
        PdfHelper.circle(200, 100, 2.5f, true, writer); //Filled Circle
        PdfHelper.circle(100, 100, 3.5f, false, writer); //Hollow circle
    }
}