package ca.yorku.cse2311.tab2pdf.ui.component;

import javax.imageio.ImageIO;
import javax.swing.*;


import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * AboutJFrame
 *
 * @author Marco
 * @since 2015-04-04
 */
public class AboutJFrame extends JFrame {

    private final static Logger LOG = Logger.getLogger(AboutJFrame.class.getName());

    private static final String[][] CONTRIBUTORS = {
            // Name                          Email                       Picture1				Picture2
            {"Brody Atto",              "brodyatto@gmail.com",          "about/brody.jpg"		, "about/brody2.jpg"},
            {"Marco Pietro Cirillo",    "cirillom@my.yorku.ca",         "about/marco1.png"		, "about/marco2.png"},
            {"Deep Patel",              "deep0410@my.yorku.ca",         "about/deep1.png"		, "about/deep2.png"},
            {"Varsha Ragavendran",      "varsha_raghav@hotmail.com",    "about/varsha1.JPG"		, "about/varsha2.JPG"},
            {"Glib Sitiguin",           "situgin@yorku.ca",             "about/glib1.jpg"		, "about/glib2.jpg"},
            {"Anton Sitkovets",         "antosi@my.yorku.ca",           "about/anton1.png"		, "about/anton2.png"},
    };

    public static final GridBagConstraints PIC_CONSTRAINTS = new GridBagConstraints(0, 0, 1, 2, 1.0, 1.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0);

    public static final GridBagConstraints NAME_CONSTRAINTS = new GridBagConstraints(1, 0, 1, 1, 1.0, 1.0, GridBagConstraints.SOUTH, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0);

    public static final GridBagConstraints EMAIL_CONSTRAINTS = new GridBagConstraints(1, 1, 1, 1, 1.0, 1.0, GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0);

    public static final Dimension PICTURE_SIZE = new Dimension(150, 150);
    

    public AboutJFrame() throws HeadlessException {

        super("About Tab2Pdf");

        getContentPane().setLayout(new GridLayout(3, 2));
        
        for (String[] row : CONTRIBUTORS) {
            getContentPane().add(createRow(row[0], row[1], row[2], row[3])); 
            
        }
     
    }

    private static JPanel createRow(String name, String email, String image1, String image2) {
        JPanel result = new JPanel();
        final JLabel j;
        final String firstImage = image1;
        final String secondImage = image2;

        result.setLayout(new GridBagLayout());

        JLabel stoicFace = new JLabel("Failed to load image");
        //JLabel happyFace = new JLabel("Failed to laod image");
        try {   // attempt to load the image of this contributor
            stoicFace = new JLabel(new ImageIcon(ImageIO.read(ClassLoader.getSystemResource(image1))));
            //happyFace = new JLabel(new ImageIcon(ImageIO.read(ClassLoader.getSystemResource(image2))));
        } catch (IOException e) {
            LOG.log(Level.WARNING, "Could not set about picture "+image1, e);
            //LOG.log(Level.WARNING, "Could not set about picture "+image2, e);

        }
        
        // Set size of picture, so they look uniform
        stoicFace.setMinimumSize(PICTURE_SIZE);
        stoicFace.setPreferredSize(PICTURE_SIZE);

        // Add name
        JLabel nameLabel = new JLabel(name);
        nameLabel.setVerticalAlignment(SwingConstants.BOTTOM);
        result.add(nameLabel, NAME_CONSTRAINTS);

        // Add email
        JLabel emailLabel = new JLabel(email);
        emailLabel.setVerticalAlignment(SwingConstants.TOP);
        result.add(emailLabel, EMAIL_CONSTRAINTS);
        
        j = stoicFace;
        
		j.addMouseListener(new MouseListener() { 
			
            public void mouseReleased(MouseEvent arg0) {}           
            
            public void mousePressed(MouseEvent arg0) {}            
            
            public void mouseExited(MouseEvent arg0) { 
            	try {   // attempt to load the image of this contributor
                    j.setIcon(new ImageIcon(ImageIO.read(ClassLoader.getSystemResource(firstImage))));
                } catch (IOException e) {
                    LOG.log(Level.WARNING, "Could not set about picture "+firstImage, e);
                }
            }           
            
            public void mouseEntered(MouseEvent arg0) {
                
                try {   // attempt to load the image of this contributor
                    j.setIcon(new ImageIcon(ImageIO.read(ClassLoader.getSystemResource(secondImage))));
                } catch (IOException e) {
                    LOG.log(Level.WARNING, "Could not set about picture "+secondImage, e);
                }
                
            	
            }           
           
            public void mouseClicked(MouseEvent arg0) {} } 
			  
		);
		// Add picture
        result.add(stoicFace, PIC_CONSTRAINTS);
        
		return result;
    }
}
