package ca.yorku.cse2311.tab2pdf.ui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
        //TODO: make user manual work
        // create a new frame with an embedded user manual
        JFrame helpFrame = new JFrame("User Manual");
        helpFrame.setSize(500, 500);
        JLabel label = new JLabel("Will be implemented soon!", JLabel.CENTER);
        helpFrame.add(label);
        helpFrame.setVisible(true);
    }
}
