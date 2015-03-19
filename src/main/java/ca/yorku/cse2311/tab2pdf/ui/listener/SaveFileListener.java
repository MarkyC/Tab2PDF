package ca.yorku.cse2311.tab2pdf.ui.listener;

import ca.yorku.cse2311.tab2pdf.ui.MainJFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Logger;

/**
 * SaveFileListener
 *
 * Saves the tab file
 *
 * @author Marco Cirillo, Glib Sitiugin
 * @since 2015-03-19
 */
public class SaveFileListener implements ActionListener {

    private final static Logger LOG = Logger.getLogger(SaveFileListener.class.getName());


    private final MainJFrame window;

    public SaveFileListener(MainJFrame window) {
        this.window = window;
    }

    @Override
    public void actionPerformed(ActionEvent evt) {

        if (null == window.getFile()) {
            JOptionPane.showMessageDialog(
                    window,
                    "There is no file opened, please open a file first",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }

        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(window.getFile()));
            writer.write(window.getEditorText());
            writer.close();
        } catch (IOException e) {
            LOG.severe(e.getMessage());
            JOptionPane.showMessageDialog(
                    window,
                    "Could not save file: "+e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }

    }
}
