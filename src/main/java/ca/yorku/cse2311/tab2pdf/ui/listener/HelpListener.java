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
 * @author Glib Sitiugin, Anton Sitkovets, Marco Cirillo
 * @since 2015-03-19
 */
 public class HelpListener implements ActionListener {
	private final String USER_MANUAL_LINK = "http://markyc.github.io/Tab2PDF/user-manual.pdf";
    private final MainJFrame window;
    

    /**
     * @param window    the window we are working with
     */
    public HelpListener(MainJFrame window) {

        super();

        this.window = window;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    	
    	new OpenUrl(USER_MANUAL_LINK);
    	
    	
    }
	
    
}
