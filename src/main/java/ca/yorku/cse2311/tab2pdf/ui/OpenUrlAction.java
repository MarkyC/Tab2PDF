package ca.yorku.cse2311.tab2pdf.ui;

import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class OpenUrlAction implements ActionListener
{
	private final String LINK = "http://markyc.github.io/Tab2PDF/user-manual.pdf";
	
	/**
	 * Creates a new URI object with the link of the User Manual web page and opens the page using the user's browser.
	 */
	public void actionPerformed(ActionEvent e){
		open(createURI(LINK));
		
	}
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
