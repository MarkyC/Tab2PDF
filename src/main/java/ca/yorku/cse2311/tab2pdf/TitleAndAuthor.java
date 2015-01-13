import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
 
/**
 * First iText example: Hello World.
 */
public class TitleAndAuthor {
 
   /**
     * The name of the PDF file we will create
     */
    public static final String FILENAME = "hello";

   /**
     * PDF file suffix (*.pdf)
     */
    public static final String PDF_SUFFIX = ".pdf";
 	
    /** Path to the resulting PDF file. */
    public static final String RESULT
        = "/Users/Deep/Desktop/hello.pdf";
 
    /**
     * Creates a PDF file: hello.pdf and opens it.
     * @param    args    no arguments needed
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
     * Creates a PDF document and writes title and authors name.
     * @param filename the path to the new PDF document
     * @throws    DocumentException 
     * @throws    IOException 
     */
    public void createPdf(String filename)
	throws DocumentException, IOException {
        // step 1
        Document document = new Document();
        // step 2
        PdfWriter.getInstance(document, new FileOutputStream(filename));
        // step 3
        document.open();
        // step 4
       
        // Calls method to get the name of the title and author.
        String TitleAndAuthor = getTitleAndAuthor();
        String[] TitleAuthor = TitleAndAuthor.split("\n");
        
        // Setting the font size and alignment of the title
        Paragraph write_title = new Paragraph(TitleAuthor[0], FontFactory.getFont(FontFactory.HELVETICA, 20, Font.NORMAL));
        write_title.setAlignment(Element.ALIGN_CENTER);
        document.add(write_title);
        
        // Setting the font size and alignment of the author
        Paragraph write_author = new Paragraph(TitleAuthor[1]);
        write_author.setAlignment(Element.ALIGN_CENTER);
        document.add(write_author);
        
        document.close();
    }
    
    /**
     * Opens and reads the text file to extract the tile and the author.
     * @throws    FileNotFoundException
     * @throws    IOException 
     * @return    Author and Title as one string.
     */
    public String getTitleAndAuthor(){
    	try{
    		// Opens and reads the file.
    		File file = new File("/Users/Deep/Desktop/text.txt");
    		FileReader reading = new FileReader(file);
    		BufferedReader in = new BufferedReader(reading);
    		
    		// Reads the first line.
    		String Title = in.readLine();
    		int length = Title.length();
    		Title = Title.substring(6, length);
    		
    		// Reads the second line.
    		String Author = in.readLine();
    		length = Author.length();
		    Author = Author.substring(9, length);
    		
		    // returns the just the author and title name.
    		return Title + '\n' + Author;
			} catch (FileNotFoundException e){
				System.err.printf("File not found!" + e.getMessage());
			} catch (IOException e){
				System.err.printf("Can not read!" + e.getMessage());
			}
		return null;
}
}