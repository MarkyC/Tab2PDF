package ca.yorku.cse2311.tab2pdf;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfWriter;

import java.awt.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Deprecated
public class EECS2311 {

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
			new EECS2311().createPdf(tempFile);

			// Open the newly created PDF
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
	public void createPdf(Path path) throws DocumentException, IOException {

		Document document = new Document();
		PdfWriter writer = PdfWriter.getInstance(document, Files.newOutputStream(path));
		document.open();
		
		//Stave test example
		for (int i = 1; i < 10; i++)
			PdfHelper.stave(i, writer);
		
		//Lines and circles example
		for (int i = 0; i < 600; i +=100) 
			PdfHelper.thinLine(3, i, writer);
		
		for (int i = 200; i <= 400; i +=50)
			PdfHelper.thickLine(1, i, writer);
		
		PdfHelper.linesCircles(1, 50, writer);
		PdfHelper.circlesLines(2, 200, writer);
		
		PdfHelper.doubleFigure(2, 350, writer);
		PdfHelper.doubleFigure(2, 400, writer);

		
		document.close();
	}
}