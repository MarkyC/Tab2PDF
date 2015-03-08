package ca.yorku.cse2311.tab2pdf.ui;

import javax.swing.*;
import java.awt.*;

/**
 * Created by glibs_000 on 2015-03-07.
 */
public class EditorPanel extends JPanel {

    public static final JTextPane EDITOR = new JTextPane();

    public EditorPanel() {

        super();

        // setup new panel
        this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        this.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Input Editor"));

        // setup EDITOR
        this.add(EDITOR);
        EDITOR.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 12));

        // setup scroll pane
        JScrollPane scrollPane = new JScrollPane(EDITOR);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        this.add(scrollPane);

        // set max size
        this.setPreferredSize(JFrameData.VIEW_PANEL_SIZE);
    }

}
