package ca.yorku.cse2311.tab2pdf.ui.listener;

import ca.yorku.cse2311.tab2pdf.ui.MainJFrame;

import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * HelpListener
 *
 * Opens the User Manual for the user to peruse
 *
 * @author Anton Sitkovets
 * @since 2015-03-19
 */
 public class SampleInput_1Listener implements ActionListener {
	 private final String INPUT_FILE_1 = "https://wiki.eecs.yorku.ca/course_archive/2014-15/W/2311/_media/moonlightsonata.txt";
	 private final MainJFrame window;
    

    /**
     * @param window    the window we are working with
     */
    public SampleInput_1Listener(MainJFrame window) {

        super();

        this.window = window;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    	open(createURI(INPUT_FILE_1));
    	
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