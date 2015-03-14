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

    	LOGGER.log(Level.INFO, e.paramString());
        
        // create a new frame with an embedded user manual
        JFrame helpFrame = new JFrame("Help");
        helpFrame.setSize(500, 500);
        Container container = helpFrame.getContentPane();
        container.setLayout(new GridBagLayout());
        JButton manualButton = new JButton();
        manualButton.setText("<HTML>User Manual</HTML>");
        manualButton.setHorizontalAlignment(SwingConstants.LEFT);
        manualButton.setBorderPainted(false);
        manualButton.setOpaque(true);
        manualButton.setBackground(Color.WHITE);
        manualButton.addActionListener(new OpenUrlAction());
        
        
        
        
        container.add(manualButton);
        helpFrame.setVisible(true);
        

        
    }
 
    
}
