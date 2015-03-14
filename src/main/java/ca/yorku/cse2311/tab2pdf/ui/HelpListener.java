package ca.yorku.cse2311.tab2pdf.ui;

import javax.swing.*;

import java.awt.Color;
import java.awt.Container;
import java.awt.Desktop;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URI;
import java.util.EventListener;
import java.util.logging.Level;

/**
 * Created by Glib Sitiugin on 2015-03-06.
 */
public class HelpListener extends JFrameListener implements EventListener, ActionListener {
	//public final URI uri = new URI("http://markyc.github.io/Tab2PDF/user-manual.pdf");
	
    /**
     * Constructor
     *
     * @param frame we are working with
     */
    public HelpListener(MainJFrame frame) {

        super(frame);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
    	JMenu helpMenu;
    	JButton manualButton ; 
    	JMenuItem userManual;
    	JRadioButtonMenuItem rbUserManual;
    	
    	LOGGER.log(Level.INFO, e.paramString());
        
        // create a new frame with an embedded user manual
        JFrame helpFrame = new JFrame("Help");
        helpFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        helpFrame.setSize(500, 500);
        Container container = helpFrame.getContentPane();
        container.setLayout(new GridBagLayout());
        JButton button = new JButton();
        button.setText("<HTML>Click the <FONT color=\"#000099\"><U>link</U></FONT>"
            + " to view the User Manual.</HTML>");
        button.setHorizontalAlignment(SwingConstants.LEFT);
        button.setBorderPainted(false);
        button.setOpaque(false);
        button.setBackground(Color.WHITE);
        
        //button.addActionListener(new OpenUrlAction());
        container.add(button);
        helpFrame.setVisible(true);
        
       // manualButton = new JButton("User Manual");
       // manualButton.setSize(JFrameData.BUTTON_SIZE);
        
       // helpFrame.add(manualButton);
        
    }
 
    public static void open(URI uri){
    	if (Desktop.isDesktopSupported()){
    		try{
    			Desktop.getDesktop().browse(uri);
    		}catch (IOException e){
    			//TODO
    		}
    	}else{
    		//TODO
    	}
    }
}
