package ca.yorku.cse2311.tab2pdf.ui.listener;

import ca.yorku.cse2311.tab2pdf.ui.MainJFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

/**
 * ExitListener
 *
 * Exits the application
 *
 * @author Marco Cirillo
 * @since 2015-03-19
 */
 public class ExitListener implements ActionListener {

    private final MainJFrame window;

    /**
     * @param window    the window we are working with
     */
    public ExitListener(MainJFrame window) {

        super();

        this.window = window;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        window.dispatchEvent(new WindowEvent(window, WindowEvent.WINDOW_CLOSING));
    }
}
