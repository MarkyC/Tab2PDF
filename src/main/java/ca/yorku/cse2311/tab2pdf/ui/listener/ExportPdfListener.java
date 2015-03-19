package ca.yorku.cse2311.tab2pdf.ui.listener;

import ca.yorku.cse2311.tab2pdf.PdfHelper;
import ca.yorku.cse2311.tab2pdf.ui.MainJFrame;
import ca.yorku.cse2311.tab2pdf.util.PdfCreator;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Arrays;
import java.util.logging.Logger;

/**
 * ExportPdfListener
 *
 * Exports the tab to a PDF
 *
 * @author Marco Cirillo, Glib Sitiugin
 * @since 2015-03-19
 */
public class ExportPdfListener implements ActionListener {

    private final static Logger LOG = Logger.getLogger(ExportPdfListener.class.getName());


    private final MainJFrame window;

    public ExportPdfListener(MainJFrame window) {
        this.window = window;
    }

    @Override
    public void actionPerformed(ActionEvent evt) {


        try {

            JFileChooser fc = new JFileChooser();
            fc.setDialogTitle("Export to PDF");
            if (JFileChooser.APPROVE_OPTION == fc.showSaveDialog(window)) {
                // User has chosen a file to save
                File out = fc.getSelectedFile();
                LOG.info("Exporting PDF to "+out.getAbsolutePath());
                new Thread(new PdfCreator(
                        new PdfHelper(
                                createPdfFile(out),
                                window.getEditorTab().getSpacingValue(),
                                window.getEditorTab().getScalingValue()
                        ),
                        Arrays.asList(window.getEditorTab().getText().split("\\r?\\n"))
                )).start();
            }
        } catch (Exception e) {
            LOG.severe(e.getMessage());
            JOptionPane.showMessageDialog(
                    window,
                    "Could not export PDF: " + e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    private File createPdfFile(File out) {
        if (out.getName().endsWith(".pdf")) {
            return out;
        } else {
            return new File(out.getAbsolutePath() + ".pdf");
        }
    }
}
