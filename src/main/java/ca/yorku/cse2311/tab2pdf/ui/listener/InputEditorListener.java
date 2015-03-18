package ca.yorku.cse2311.tab2pdf.ui.listener;

import ca.yorku.cse2311.tab2pdf.ui.MainJFrame;
import ca.yorku.cse2311.tab2pdf.ui.component.StatusBar;
import ca.yorku.cse2311.tab2pdf.ui.support.JFrameTool;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.EventListener;
import java.util.logging.Level;

/**
 * The listener fires when editor contents are modified
 *
 * Created by Glib Sitiugin on 2015-03-07.
 */
public class InputEditorListener extends AbstractListener implements EventListener, KeyListener {

    /**
     * Constructor
     *
     * @param frame we are working with
     */
    public InputEditorListener(MainJFrame frame) {
        super(frame);
    }



    private void doAction() {

        // update symbols number in the status bar
        StatusBar.setSymbolsNumber(JFrameTool.getSymbolsNumber());

        // enable save button
        enableComponents();
    }

    @Override
    public void enableComponents() {

        JFrameTool.enableComponent(JFrameTool.getSaveTextFileButon(), true);
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // do nothing
    }

    @Override
    public void keyPressed(KeyEvent e) {

        LOGGER.log(Level.INFO, e.paramString());
        StatusBar.setHint("Key Pressed");

        doAction();
    }

    @Override
    public void keyReleased(KeyEvent e) {

        LOGGER.log(Level.INFO, e.paramString());
        StatusBar.setHint("Key Released");

        doAction();
    }
}
