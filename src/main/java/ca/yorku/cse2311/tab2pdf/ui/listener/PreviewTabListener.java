package ca.yorku.cse2311.tab2pdf.ui.listener;

import com.itextpdf.text.DocumentException;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import ca.yorku.cse2311.tab2pdf.model.Tab;
import ca.yorku.cse2311.tab2pdf.pdf.PdfCreator;
import ca.yorku.cse2311.tab2pdf.pdf.PdfHelper;
import ca.yorku.cse2311.tab2pdf.ui.MainJFrame;
import ca.yorku.cse2311.tab2pdf.ui.component.PreviewTab;
import ca.yorku.cse2311.tab2pdf.util.FileUtils;

/**
 * PreviewTabListener
 *
 * @author Marco
 * @since 2015-04-01
 */
public class PreviewTabListener implements ChangeListener {

    private final Logger LOG = Logger.getLogger(this.getClass().getName());

    private final MainJFrame window;

    public PreviewTabListener(MainJFrame window) {

        this.window = window;
    }

    private static PdfCreator buildPdfCreator(
            final MainJFrame window
            , final PreviewTab tab
            , Tab input
            , File output
    ) throws IOException, DocumentException {

        return new PdfCreator(
                new PdfHelper(
                        output,
                        window.getEditorTab().getSpacingValue(),
                        window.getEditorTab().getScalingValue()
                ),
                input,
                buildCallback(window, tab));

    }

    private static PdfCreator.Callback buildCallback(final MainJFrame window, final PreviewTab tab) {

        return new PdfCreator.Callback() {
            @Override
            public void onCallback(boolean success, Object output) {

                if (success && output instanceof File) {

                    // Update the tab renderer
                    tab.update(((File) output).getAbsolutePath());

                } else if (!success && output instanceof Exception) {

                    // we failed to convert the tab to PDF with an Exception
                    JOptionPane.showMessageDialog(
                            window,
                            "Could not render tab",
                            "Error",
                            JOptionPane.ERROR_MESSAGE
                    );
                }
            }
        };
    }

    @Override
    public void stateChanged(ChangeEvent e) {

        if (((JTabbedPane) e.getSource()).getSelectedComponent() instanceof PreviewTab) {

            // user has selected the preview tab
            final PreviewTab tab = (PreviewTab) ((JTabbedPane) e.getSource()).getSelectedComponent();

            final String text = window.getEditorTab().getText();
            if (text.isEmpty()) {
                return; // nothing to do here, the user has no tab in the editor (or the tab is empty)
            }

            // Non-empty tab here, let's get ready to parse it
            final List<String> input = Arrays.asList(text.split("\\r?\\n"));

            try {
                final File output = FileUtils.createTempFile("tab2pdf", ".pdf");
                LOG.info("Previewing File: " + output.getAbsolutePath());

                // Convert the file for the preview
                new Thread(buildPdfCreator(window, tab, window.getTab(), output)).start();
            } catch (Exception e1) {
                LOG.log(Level.SEVERE, "Could not convert tab to pdf: " + e1.getMessage(), e1);
            }
        }

    }

}
