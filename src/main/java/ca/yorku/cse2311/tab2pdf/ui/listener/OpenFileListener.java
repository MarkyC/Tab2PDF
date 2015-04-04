package ca.yorku.cse2311.tab2pdf.ui.listener;

import ca.yorku.cse2311.tab2pdf.ui.MainJFrame;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * OpenFileListener
 *
 * Handles opening a file in our application.
 *
 * File Types Supported: *.txt, *.text, *.tab
 *
 * @author Marco Cirillo, Glib Sitiugin
 * @since 2015-03-19
 */
public class OpenFileListener implements ActionListener {

    private final MainJFrame window;

    private final FileFilter TEXT_FILE_FILTER = new FileNameExtensionFilter("Text Files (*.txt, *.text)", "txt", "text");

    private final FileFilter TAB_FILE_FILTER = new FileNameExtensionFilter("Tab Files (*.tab)", "tab");

    public OpenFileListener(MainJFrame window) {
        this.window = window;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        JFileChooser fc = new JFileChooser();

        // Adds supported file types
        fc.addChoosableFileFilter(TEXT_FILE_FILTER);
        fc.addChoosableFileFilter(TAB_FILE_FILTER);
        fc.setFileFilter(TEXT_FILE_FILTER); // sets default File Filter

        // Open the file chooser, and open the file that the user chose
        if (JFileChooser.APPROVE_OPTION == fc.showOpenDialog(window)) {
            window.setFile(fc.getSelectedFile());
        }


    }
}
