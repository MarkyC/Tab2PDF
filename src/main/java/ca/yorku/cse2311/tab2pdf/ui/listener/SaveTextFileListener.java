package ca.yorku.cse2311.tab2pdf.ui.listener;

import ca.yorku.cse2311.tab2pdf.ui.MainJFrame;
import ca.yorku.cse2311.tab2pdf.ui.component.StatusBar;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.EventListener;
import java.util.logging.Level;

/**
 * Created by Glib Sitiugin on 2015-03-06.
 */
public class SaveTextFileListener extends AbstractListener implements EventListener, ActionListener {

    private static final JButton OK_BUTTON = new JButton("OK");

    private static final JButton CANCEL_BUTTON = new JButton("Cancel");

    public SaveTextFileListener(MainJFrame frame) {

        super(frame);
    }

    @Override
    public void enableComponents() {

        // do nothing
    }

    private void saveFile() throws IOException {

        BufferedWriter writer = new BufferedWriter(new FileWriter(getInputFile()));
        writer.write(getEditor().getText());
        writer.close();
    }

    /**
     * @return the frame that asks user if he really wants to override the inut file
     */
    private static JFrame questionFrame() {

        //TODO: Finish the frame
        // setup new frame
        JFrame frame = new JFrame();
        frame.setLayout(new BorderLayout());

        //frame.setPreferredSize(JFrameTool.TAB_SIZE);
        //frame.setMaximumSize(JFrameTool.TAB_SIZE);

        frame.add(new JLabel("Saving changes will overwrite the old file. Continue?"), BorderLayout.NORTH);
        frame.add(OK_BUTTON, BorderLayout.EAST);
        frame.add(CANCEL_BUTTON, BorderLayout.WEST);


        return frame;

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        LOGGER.log(Level.INFO, e.paramString());
        StatusBar.setHint("Save File Button Clicked");


        //JFrame frame = questionFrame();
        //frame.setVisible(true);

        if ( (null == getInputFile()) || !getInputFile().exists() || !getInputFile().canRead() ) {
            JOptionPane.showMessageDialog(super.getJFrame(), "Input file does not exist or cannot be read.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // try to save input editor contents back into input file
        try { saveFile(); }
        catch (IOException e1) { e1.printStackTrace(); }
    }


}
