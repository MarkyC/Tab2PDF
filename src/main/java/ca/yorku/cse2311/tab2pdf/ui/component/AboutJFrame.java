package ca.yorku.cse2311.tab2pdf.ui.component;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
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
            // Name                          Email                       Picture
            {"Brody Atto",              "brodyatto@gmail.com",          "about/brody.jpg"},
            {"Marco Pietro Cirillo",    "cirillom@my.yorku.ca",         "about/marco.jpeg"},
            {"Deep Patel",              "deep0410@my.yorku.ca",         "about/Deep.jpg"},
            {"Varsha Ragavendran",      "varsha_raghav@hotmail.com",    "about/Varsha.jpg"},
            {"Glib Sitiguin",           "situgin@yorku.ca",             "about/glib.jpg"},
            {"Anton Sitkovets",         "antosi@my.yorku.ca",           "about/anton.jpg"},
    };

    public static final GridBagConstraints PIC_CONSTRAINTS = new GridBagConstraints(0, 0, 1, 2, 1.0, 1.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0);

    public static final GridBagConstraints NAME_CONSTRAINTS = new GridBagConstraints(1, 0, 1, 1, 1.0, 1.0, GridBagConstraints.SOUTH, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0);

    public static final GridBagConstraints EMAIL_CONSTRAINTS = new GridBagConstraints(1, 1, 1, 1, 1.0, 1.0, GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0);

    public static final Dimension PICTURE_SIZE = new Dimension(150, 150);

    public AboutJFrame() throws HeadlessException {

        super("About Tab2Pdf");

        getContentPane().setLayout(new GridLayout(3, 2));

        for (String[] row : CONTRIBUTORS) {
            getContentPane().add(createRow(row[0], row[1], row[2]));
        }
    }

    private static JPanel createRow(String name, String email, String image) {
        JPanel result = new JPanel();

        result.setLayout(new GridBagLayout());

        JLabel pic = new JLabel("Failed to load image");
        try {   // attempt to load the image of this contributor
            pic = new JLabel(new ImageIcon(ImageIO.read(ClassLoader.getSystemResource(image))));
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

        return result;
    }
}
