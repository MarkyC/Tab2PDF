package ca.yorku.cse2311.tab2pdf.ui.listener;

import ca.yorku.cse2311.tab2pdf.ui.MainJFrame;
import ca.yorku.cse2311.tab2pdf.ui.component.AboutJFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * AboutListener
 *
 * Opens the About Us Window
 *
 * @author Glib Sitiugin, Anton Sitkovets, Marco Cirillo, Deep Patel
 * @since 2015-03-19
 */
public class AboutListener implements ActionListener{

    private final MainJFrame window;

    /**
     * @param window    the window we are working with
     */
    public AboutListener (MainJFrame window) {

        super();
        this.window = window;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JFrame aboutWindow = new AboutJFrame();
        aboutWindow.pack();
        aboutWindow.setResizable(false);
        aboutWindow.setVisible(true);
    }
}
