package ca.yorku.cse2311.tab2pdf.ui.component;

import ca.yorku.cse2311.tab2pdf.ui.support.JFrameTool;

import javax.swing.*;

/**
 * Created by Glib Sitiugin on 2015-03-07.
 */
public class PreviewTab extends JPanel {

    private static final String PREVIEW_PANEL_NAME = "Pdf Preview";

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
        this.setPreferredSize(JFrameTool.TAB_SIZE);
        this.setMaximumSize(JFrameTool.TAB_SIZE);
    }
}
