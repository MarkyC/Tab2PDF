package ca.yorku.cse2311.tab2pdf.ui.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Logger;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

import ca.yorku.cse2311.tab2pdf.ui.MainJFrame;

/**
 * SaveFileAsListener
 *
 * Handles Save As... in the menu
 *
 * @since 2015-04-06
 * @author Varsha
 */
public class SaveFileAsListener implements ActionListener {

    private final static Logger LOG = Logger.getLogger(SaveFileListener.class.getName());

    private final FileFilter TEXT_FILE_FILTER = new FileNameExtensionFilter("Text Files (*.txt, *.text)", "txt", "text");

    private final MainJFrame window;

    public SaveFileAsListener(MainJFrame window) {
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

        else{
            JFileChooser fc = new JFileChooser(new File(window.getFile().getAbsolutePath()));
            fc.setDialogTitle("Save");
            fc.setFileFilter(TEXT_FILE_FILTER);
            int result = fc.showSaveDialog(null);
            if(result == JFileChooser.APPROVE_OPTION){
                File fi = addExtension(fc.getSelectedFile());
                try {
                    BufferedWriter writer = new BufferedWriter(new FileWriter(fi.getPath()));
                    writer.write(window.getEditorTab().getText());
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
    }
    
    private File addExtension(File out) {
        if (out.getName().endsWith(".txt")) {
            return out;
        } else {
            return new File(out.getAbsolutePath() + ".txt");
        }
        
    }
}




