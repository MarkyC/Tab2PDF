package ca.yorku.cse2311.tab2pdf;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import java.awt.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

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

    /**
     * Creates a PDF file that says Hello World and opens it
     *
     * @param args no arguments needed
     */
    public static void main(String[] args) {

        try {

            // Create a temporary file to hold our hello world PDF
            Path tempFile = Files.createTempFile(FILENAME, PDF_SUFFIX);

            // Run the example code
            new Main().createPdf(tempFile);

            // open the newly created PDF
            Desktop.getDesktop().open(tempFile.toFile());

        } catch (Exception e) {

            // We fucked up somewhere
            // This should probably be handled with a Thread.UncaughtExceptionHandler
            e.printStackTrace();
        }
    }

    /**
     * Creates a PDF document.
     *
     * @param path the path to the new PDF document
     * @throws DocumentException
     * @throws IOException
     */
    public void createPdf(Path path)
            throws DocumentException, IOException {
        // step 1
        Document document = new Document();
        // step 2
        PdfWriter writer = PdfWriter.getInstance(document, Files.newOutputStream(path));
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