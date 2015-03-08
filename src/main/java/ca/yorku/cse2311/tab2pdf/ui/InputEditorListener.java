package ca.yorku.cse2311.tab2pdf.ui;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.EventListener;
import java.util.logging.Level;

/**
 * Created by Glib Sitiugi on 2015-03-07.
 */
public class InputEditorListener extends JFrameListener implements EventListener, KeyListener {

    /**
     * Constructor
     *
     * @param frame we are working with
     */
    public InputEditorListener(MainJFrame frame) {
        super(frame);
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // do nothing
    }

    @Override
    public void keyPressed(KeyEvent e) {

        LOGGER.log(Level.INFO, e.paramString());

        // update symbols number in the status bar
        StatusBar.setSymbolsNumber(StatusBar.getSymbolsNumber());
    }

    @Override
    public void keyReleased(KeyEvent e) {

        LOGGER.log(Level.INFO, e.paramString());

        // update symbols number in the status bar
        StatusBar.setSymbolsNumber(StatusBar.getSymbolsNumber());
    }
}
