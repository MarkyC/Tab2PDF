package ca.yorku.cse2311.tab2pdf;

import com.itextpdf.text.DocumentException;

import java.awt.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
 
/**
 * Gets the Title and the Author's name from the text file and puts it into a temp pdf.
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
 	
    /**
     * Creates a PDF file: hello.pdf and opens it.
     * @param    args    no arguments needed
     */
	public static void main(String[] args) {

    	    try {
	
   		     // Create a temporary file to hold our hello world PDF
    		 Path tempFile = Files.createTempFile(FILENAME, PDF_SUFFIX);
    		        
      	    // Run the example code
                // new TitleAndAuthor().createPdf(tempFile);

            // open the newly created PDF
             Desktop.getDesktop().open(tempFile.toFile());

        } catch (Exception e) {

            // We fucked up somewhere
            // This should probably be handled with a Thread.UncaughtExceptionHandler
            e.printStackTrace();
        }
    }
 
    /**
     * Creates a TEMP. PDF document and writes title and authors name.
     * @param filename the path to the new PDF document
     * @throws    DocumentException 
     * @throws    IOException 
     */
   /* public void createPdf(String filename)
    throws DocumentException, IOException {
        // step 1
        Document document = new Document();
        // step 2
        PdfWriter.getInstance(document, new FileOutputStream(filename));
        // step 3
        document.open();
        // step 4
       
        // creates an empty title and author array list to store title and author.
        ArrayList<String> lines = new ArrayList<String>();
        
        //Opens the user file.
		File file = new File("/Users/Deep/Desktop/text.txt");
		
		//calls the readFile method.
        lines = TitleAndAuthor.readFile(file);
        
        //Calls the getTitle method and store the return variable in title.
        String title = getTitle(lines);
        
        //Calls the getAuthor method and store the return variable in author.
        String author = getAuthor(lines);
        
        // Setting the font size and alignment of the title
        Paragraph write_title = new Paragraph(title, FontFactory.getFont(FontFactory.HELVETICA, 20, Font.NORMAL));
        write_title.setAlignment(Element.ALIGN_CENTER);
        document.add(write_title);
        
        // Setting the font size and alignment of the author
        Paragraph write_author = new Paragraph(author);
        write_author.setAlignment(Element.ALIGN_CENTER);
        document.add(write_author);
        
        document.close();
    }*/
    
    /**
     * Reads the text file stores the first 10 lines in an ArrayList.
     * @return    ArrayList filled with the lines in the text file.
     */
   /* public static ArrayList<String> readFile(File f){
        // Opens and reads the file.
    	FileReader reading = new FileReader(f);
		BufferedReader in = new BufferedReader(reading);
		
		//Creates an empty list.
	    ArrayList<String> lines = new ArrayList<String>();
		
	    //Stores the first 10 lines in ArrayList.
	    for(int i = 0; i < 10; i++){
	    	String element = in.readLine();
	    	lines.add(i, element);
		}
	    //Returns the ArrayList.
	    return lines; 
    }*/
    
   /**
    * Extracts the tile from the first line.
    * @param lines in the ArrayList.
    * @return Title
    */
    public static String getTitle(ArrayList<String> lines){
    	//Gets the first element in the list which is assumed to be the title
    	String title = lines.get(0);
    	
    	//Extracts just the title.
    	int length = title.length();
		title = title.substring(6, length);
		
		//Returns title.
		return title;
    }
    
    /**
     * Extracts the Authors name from the second line.
     * @param lines in the ArrayList.
     * @return Author's name
     */
    public static String getAuthor(ArrayList<String> lines){
    	//Gets the first element in the list which is assumed to be the title
    	String Author = lines.get(1);
    	
    	//Extracts just the title.
    	int length = Author.length();
		Author = Author.substring(9, length);
		
		//Returns title.
		return Author;
    }
}