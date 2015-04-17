package ca.yorku.cse2311.tab2pdf.ui.component;

import javax.imageio.ImageIO;
import javax.swing.*;

import ca.yorku.cse2311.tab2pdf.ui.listener.AboutListener;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
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
public class AboutJFrame extends JFrame implements MouseListener {

    private final static Logger LOG = Logger.getLogger(AboutJFrame.class.getName());

    private static final String[][] CONTRIBUTORS = {
            // Name                          Email                       Picture1				Picture2
            {"Brody Atto",              "brodyatto@gmail.com",          "about/brody.jpg"		, ""},
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
    
    private boolean isStoic = false;

    public AboutJFrame() throws HeadlessException {

        super("About Tab2Pdf");
        
        String[] smilePictures = new String[CONTRIBUTORS[3].length];

        getContentPane().setLayout(new GridLayout(3, 2));
        
        for (String[] row : CONTRIBUTORS) {
            getContentPane().add(createRow(row[0], row[1], row[2])); 
            this.isStoic = false;
        }
        for (int i = 0; i < CONTRIBUTORS[3].length ; i++){
        	smilePictures[i] = CONTRIBUTORS[3][i];
        }
    }

    private static JPanel createRow(String name, String email, String image) {
        JPanel result = new JPanel();

        result.setLayout(new GridBagLayout());

        JButton pic = new JButton("Failed to load image");
        try {   // attempt to load the image of this contributor
            pic = new JButton(new ImageIcon(ImageIO.read(ClassLoader.getSystemResource(image))));
        } catch (IOException e) {
            LOG.log(Level.WARNING, "Could not set about picture "+image, e);
        }

        // Set size of picture, so they look uniform
        pic.setMinimumSize(PICTURE_SIZE);
        pic.setPreferredSize(PICTURE_SIZE);

        // Add picture
        result.add(pic, PIC_CONSTRAINTS);

        // Add name
        JLabel nameLabel = new JLabel(name);
        nameLabel.setVerticalAlignment(SwingConstants.BOTTOM);
        result.add(nameLabel, NAME_CONSTRAINTS);

        // Add email
        JLabel emailLabel = new JLabel(email);
        emailLabel.setVerticalAlignment(SwingConstants.TOP);
        result.add(emailLabel, EMAIL_CONSTRAINTS);
        
//        MouseListener mouseClicked = new MouseListener{
//        	@Override
//        	public void mouseClicked(MouseEvent e) {
//        		// TODO Auto-generated method stub
//        		
//        	}
//        };
		pic.addMouseListener(new MouseAdapter() { 
	          public void mousePressed(MouseEvent e) { 
	             
	            } 
	          });
        return result;
    }

	@Override
	public void mouseClicked(MouseEvent e) {
		
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}
