package ca.yorku.cse2311.tab2pdf.ui.component;

import org.icepdf.ri.common.ComponentKeyBinding;
import org.icepdf.ri.common.SwingController;
import org.icepdf.ri.common.SwingViewBuilder;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Glib Sitiugin on 2015-03-07.
 */
public class PreviewTab extends JPanel {

    private static final String PREVIEW_PANEL_NAME = "Pdf Preview";

    public static final Dimension TAB_SIZE = new Dimension(500, 500);

    /**
     * Constructs a new preview tab
     */
    public PreviewTab() {
        //TODO: make the panel display current state of Pdf file to be created
        super();

        setupTab();
    }

    /**
     * Sets tab parameters, including size and border
     */
    private void setupTab() {

        // setup new panel
        this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        this.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), PREVIEW_PANEL_NAME));
        this.setPreferredSize(TAB_SIZE);
        this.setMaximumSize(TAB_SIZE);
    }

    public void update(String filePath) {

        // build a controller
        SwingController controller = new SwingController();

        // Build a SwingViewFactory configured with the controller
                SwingViewBuilder factory = new SwingViewBuilder(controller);

        // Use the factory to build a JPanel that is pre-configured
        //with a complete, active Viewer UI.
                JPanel viewerComponentPanel = factory.buildViewerPanel();

        // add copy keyboard command
                ComponentKeyBinding.install(controller, viewerComponentPanel);

        // add interactive mouse link annotation support via callback
                controller.getDocumentViewController().setAnnotationCallback(
                        new org.icepdf.ri.common.MyAnnotationCallback(
                                controller.getDocumentViewController()));

        this.add(viewerComponentPanel);

        // Open a PDF document to view
        controller.openDocument(filePath);
    }
}
