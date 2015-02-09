package ca.yorku.cse2311.tab2pdf.ui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.EventListener;
import java.util.logging.Level;

/**
 * Created by glibs_000 on 2015-03-06.
 */
public class SaveFileListener extends JFrameListener implements EventListener, ActionListener {

    public SaveFileListener(MainJFrame frame) {

        super(frame);
    }

    private void saveFile() throws IOException {

        BufferedWriter writer = new BufferedWriter(new FileWriter(getInputFile()));
        writer.write(getEditor().getText());
        writer.close();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        LOGGER.log(Level.INFO, e.paramString());

        //TODO: open the dialog window which asks if user actually wants to override the input file
        if ( (null == getInputFile()) || !getInputFile().exists() || !getInputFile().canRead() ) {
            JOptionPane.showMessageDialog(super.getJFrame(), "Input file does not exist or cannot be read.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // try to save input editor contents back into input file
        try { saveFile(); }
        catch (IOException e1) { e1.printStackTrace(); }
    }
}
