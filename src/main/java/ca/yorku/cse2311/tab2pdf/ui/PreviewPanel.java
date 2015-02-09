package ca.yorku.cse2311.tab2pdf.ui;

import javax.swing.*;

/**
 * Created by glibs_000 on 2015-03-07.
 */
public class PreviewPanel extends JPanel {

    public PreviewPanel() {
        //TODO: make the panel display current state of Pdf file to be created
        super();

        // setup new panel
        this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        this.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Preview Panel"));

        // set panel size
        this.setPreferredSize(JFrameData.VIEW_PANEL_SIZE);
        this.setMaximumSize(JFrameData.VIEW_PANEL_SIZE);
    }
}
