package ca.yorku.cse2311.tab2pdf.ui.listener;

import ca.yorku.cse2311.tab2pdf.ui.MainJFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * SettingsListener
 *
 * Opens the settings window for the user to edit settings
 *
 * @author Glib Sitiugin, Marco Cirillo
 * @since 2015-03-19
 */
 public class SettingsListener implements ActionListener {

    private final MainJFrame window;

    /**
     * @param window    the window we are working with
     */
    public SettingsListener(MainJFrame window) {

        super();

        this.window = window;
    }


    @Override
    public void actionPerformed(ActionEvent e) {

        JFrame helpFrame = new JFrame("Settings");
        helpFrame.setSize(500, 500);
        JLabel label = new JLabel("Settings ideas: \n* Show/Hide toolbar text", JLabel.CENTER);
        helpFrame.add(label, BorderLayout.CENTER);
        helpFrame.setVisible(true);
    }
}
