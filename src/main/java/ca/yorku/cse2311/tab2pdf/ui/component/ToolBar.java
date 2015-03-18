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
    public static final JButton OPEN_BUTTON = new JButton("Open Text File");

    public static final JButton SAVE_PDF_BUTTON = new JButton("Save Pdf File");

    public static final JButton CONVERT_BUTTON = new JButton("Convert to Pdf");

    public static final JButton SETTINGS_BUTTON = new JButton("Settings");

    public static final JButton HELP_BUTTON = new JButton("Help");


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
        setButtonSize(SAVE_PDF_BUTTON);
        setButtonSize(CONVERT_BUTTON);
        setButtonSize(SETTINGS_BUTTON);
        setButtonSize(HELP_BUTTON);

        // disable buttons which are not to be clicked yet
        //enableComponents(SAVE_PDF_BUTTON, false);
        //enableComponents(CONVERT_BUTTON, false);

        // add buttons to toolbar
        this.add(OPEN_BUTTON);
        this.add(createSeparator());
        this.add(SAVE_PDF_BUTTON);
        this.add(createSeparator());
        this.add(CONVERT_BUTTON);
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
