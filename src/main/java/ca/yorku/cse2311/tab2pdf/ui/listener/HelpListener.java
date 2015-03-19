package ca.yorku.cse2311.tab2pdf.ui.listener;

import ca.yorku.cse2311.tab2pdf.ui.MainJFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * HelpListener
 *
 * Opens the User Manual for the user to peruse
 *
 * @author Glib Sitiugin, Anton Sitkovets, Marco Cirillo
 * @since 2015-03-19
 */
 public class HelpListener implements ActionListener {

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

        //TODO: make user manual work
        // create a new frame with an embedded user manual
        JFrame helpFrame = new JFrame("User Manual");
        helpFrame.setSize(500, 500);
        JLabel label = new JLabel("Will be implemented soon!", JLabel.CENTER);
        helpFrame.add(label);
        helpFrame.setVisible(true);
    }
}
