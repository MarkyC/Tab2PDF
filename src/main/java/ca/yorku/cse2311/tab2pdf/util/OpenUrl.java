package ca.yorku.cse2311.tab2pdf.util;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class OpenUrl
{
private String link;
	
	public OpenUrl(String link){
		this.link = link;
		open(createURI(this.link));
		
	}
	
	/**
	 * Creates a new URI object with the link of the User Manual web page and opens the page using the user's browser.
	 */
	
	/**
	 * Opens the URI in the URI object using the desktop's default browser
	 * @param URI uri
	 */
	public static void open(URI uri){
    	if (Desktop.isDesktopSupported()){
    		try{
    			Desktop.getDesktop().browse(uri);
    		}catch (IOException e){
    			e.printStackTrace();
    		}
    	}else{
    		//TODO
    	}
    }
	
	public URI createURI(String link){
		URI uri;
		try{
			uri = new URI(link);
			
		}catch (URISyntaxException m){
			uri= null;
			m.printStackTrace();
		}
		return uri;
	}
}
