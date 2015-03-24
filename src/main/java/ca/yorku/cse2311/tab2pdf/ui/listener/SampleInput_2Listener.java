package ca.yorku.cse2311.tab2pdf.ui.listener;

import ca.yorku.cse2311.tab2pdf.ui.MainJFrame;
import ca.yorku.cse2311.tab2pdf.util.OpenUrl;

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
 public class SampleInput_2Listener implements ActionListener {
	 private final String INPUT_FILE_2 = "https://wiki.eecs.yorku.ca/course_archive/2014-15/W/2311/_media/rememberingrain.txt";
	 private final MainJFrame window;
    

    /**
     * @param window    the window we are working with
     */
    public SampleInput_2Listener(MainJFrame window) {

        super();

        this.window = window;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    	new OpenUrl(INPUT_FILE_2);
    	
    }
   
    
}