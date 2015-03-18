package ca.yorku.cse2311.tab2pdf.ui.component;

import ca.yorku.cse2311.tab2pdf.ui.support.JFrameTool;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Glib Sitiugin on 2015-03-07.
 */
public class ToolBar extends JToolBar {

    /**
     * Size of toolbar buttons
     */
    private static final Dimension BUTTON_SIZE = new Dimension(400, 200);

    /**
     * Toolbar elements
     */
    private final JButton OPEN_BUTTON = new JButton("Open");

    private final JButton SAVE_BUTTON = new JButton("Save");

    private final JButton EXPORT_BUTTON = new JButton("Export PDF");

    private final JButton SETTINGS_BUTTON = new JButton("Settings");

    private final JButton HELP_BUTTON = new JButton("Help");

    public JButton getHelpButton() {

        return HELP_BUTTON;
    }

    public JButton getExportButton() {

        return EXPORT_BUTTON;
    }

    public JButton getSaveButton() {

        return SAVE_BUTTON;
    }

    public JButton getOpenButton() {

        return OPEN_BUTTON;
    }

    public ToolBar() {

        super();

        // setup toolbar
        this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        this.setPreferredSize(JFrameTool.BAR_SIZE);
        this.setFloatable(false);

        setupButtons();

    }

    private void setupButtons() {

        // set button size
        setButtonSize(OPEN_BUTTON);
        setButtonSize(SAVE_BUTTON);
        setButtonSize(EXPORT_BUTTON);
        setButtonSize(SETTINGS_BUTTON);
        setButtonSize(HELP_BUTTON);

        // disable buttons which are not to be clicked yet
        //enableComponents(SAVE_BUTTON, false);
        //enableComponents(EXPORT_BUTTON, false);

        // add buttons to toolbar
        this.add(OPEN_BUTTON);
        this.add(createSeparator());
        this.add(SAVE_BUTTON);
        this.add(createSeparator());
        this.add(EXPORT_BUTTON);
        this.add(createSeparator());
        this.add(SETTINGS_BUTTON);
        this.add(createSeparator());
        this.add(HELP_BUTTON);
    }

    private void setButtonSize(JButton button) {

        button.setPreferredSize(BUTTON_SIZE);
        button.setMaximumSize(BUTTON_SIZE);
    }

    private static JSeparator createSeparator() {

        JSeparator separator = new JSeparator();
        separator.setOrientation(JSeparator.VERTICAL);

        return separator;
    }

}
