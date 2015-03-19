package ca.yorku.cse2311.tab2pdf.ui.listener;

import ca.yorku.cse2311.tab2pdf.ui.MainJFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * OpenFileListener
 *
 * @author Marco Cirillo, Glib Sitiugin
 * @since 2015-03-19
 */
public class OpenFileListener implements ActionListener {

    private final MainJFrame window;

    public OpenFileListener(MainJFrame window) {
        this.window = window;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        JFileChooser fc = new JFileChooser();
        int returnVal = fc.showOpenDialog(window);

        if (returnVal == JFileChooser.APPROVE_OPTION) {
            window.setFile(fc.getSelectedFile());
        }

    }
}
