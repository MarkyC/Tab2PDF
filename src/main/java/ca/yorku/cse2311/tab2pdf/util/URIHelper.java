package ca.yorku.cse2311.tab2pdf.util;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class URIHelper {
	
	public URIHelper() {
		
	}
	
	/**
	 * Opens the URI in the URI object using the desktop's default browser
	 * @param URI uri
	 */
	public static void open(URI uri) throws IOException {
    	
		Desktop.getDesktop().browse(uri);
    	
    }
	public static void open(String link) throws IOException, URISyntaxException{
		
		open(new URI(link));
	}
	
	
}
