package ca.yorku.cse2311.tab2pdf;

import ca.yorku.cse2311.tab2pdf.ui.MainJFrame;

import javax.swing.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Main.java
 * Entry Point of Our Application
 */
public class Main {

    /**
     * The name of the PDF file we will create
     */
    public static final String FILENAME = "tab2pdf-";

    /**
     * PDF file suffix (*.pdf)
     */
    public static final String PDF_SUFFIX = ".pdf";

    private final static Logger LOG = Logger.getLogger(Main.class.getName());

    /**
     * Creates a PDF file that says Hello World and opens it
     *
     * @param args no arguments needed
     */
    public static void main(String[] args) {

        // Set Logging First!
        LOG.setLevel(Level.ALL);

        // This starts our GUI
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {

                try {
                    MainJFrame.createAndShow();
                } catch (Exception e) {

                    // Our GUI messed up somehow, log it
                    LOG.severe(e.getMessage());
                }
            }
        });
    }
}