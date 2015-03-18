package ca.yorku.cse2311.tab2pdf.ui.listener;

import ca.yorku.cse2311.tab2pdf.Arguments;
import ca.yorku.cse2311.tab2pdf.ui.MainJFrame;
import ca.yorku.cse2311.tab2pdf.ui.component.StatusBar;
import ca.yorku.cse2311.tab2pdf.util.PdfCreator;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.nio.file.Files;
import java.util.EventListener;
import java.util.logging.Level;

/**
 * Created by Glib Sitiugin on 2015-03-06.
 */
public class ConvertToPdfListener extends AbstractListener implements EventListener, ActionListener {

    private int tempFileNumber = 0;

    public ConvertToPdfListener(MainJFrame frame) {

        super(frame);
    }

    @Override
    public void enableComponents() {
        // do nothing
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        LOGGER.log(Level.INFO, e.paramString());
        StatusBar.setHint("Convert Button Clicked");

        // check if the file can be read
        if ( (null == getInputFile()) || !getInputFile().exists() || !getInputFile().canRead() ) {
            JOptionPane.showMessageDialog(getJFrame(), "Input file does not exist or cannot be read.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // create a temporary file for output PDF
        try { setOutputFile(Files.createTempFile("tab2pdf-" + tempFileNumber, ".pdf").toFile()); }
        catch (IOException ex) {
                JOptionPane.showMessageDialog(getJFrame(), "Could not create temporary file. Application will now close", "Error", JOptionPane.ERROR_MESSAGE);
                System.exit(-1);
            }

        // increment temporary file number for future use
        tempFileNumber++;

        // start PDF creator in a new thread
        new Thread(new PdfCreator(new Arguments(getInputFile(), getOutputFile()))).start();
    }
}
