package ca.yorku.cse2311.tab2pdf.ui.listener;

import ca.yorku.cse2311.tab2pdf.ui.MainJFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

/**
 * ExitListener
 *
 * Exits the application
 *
 * @author Marco Cirillo
 * @since 2015-03-19
 */
 public class ExitListener implements ActionListener, WindowListener {

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

    @Override
    public void windowClosing(WindowEvent e) {
        if (null != window.getFile() && window.getEditorTab().isDirty()) {

            // there is an open file with unsaved changes
            if (JOptionPane.YES_OPTION ==  JOptionPane.showConfirmDialog(
                    window,
                    "You have unsaved changes. Do you want to save them?",
                    "Unsaved Changes",
                    JOptionPane.YES_NO_OPTION
            )) {    // The user asked to save their work, so we save it before opening the new tab
                new SaveFileListener(window).actionPerformed(new ActionEvent(window, 0, "save"));
            }
        }
    }

    // Unused listeners below

    @Override
    public void windowOpened(WindowEvent e) { }

    @Override
    public void windowClosed(WindowEvent e) { }

    @Override
    public void windowIconified(WindowEvent e) { }

    @Override
    public void windowDeiconified(WindowEvent e) { }

    @Override
    public void windowActivated(WindowEvent e) { }

    @Override
    public void windowDeactivated(WindowEvent e) { }
}
