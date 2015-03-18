package ca.yorku.cse2311.tab2pdf.ui.listener;

import ca.yorku.cse2311.tab2pdf.ui.MainJFrame;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.EventListener;

/**
 * Created by Glib Sitiugin on 2015-03-16.
 */
public class SubtitleListener extends AbstractListener implements EventListener, KeyListener {
    /**
     * Constructor
     *
     * @param frame we are working with
     */
    public SubtitleListener(MainJFrame frame) {
        super(frame);
    }

    @Override
    public void enableComponents() {

        // do nothing
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
    //TODO: implement this class

}
