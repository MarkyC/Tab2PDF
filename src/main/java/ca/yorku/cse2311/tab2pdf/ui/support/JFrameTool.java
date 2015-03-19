package ca.yorku.cse2311.tab2pdf.ui.support;

import ca.yorku.cse2311.tab2pdf.ui.component.StatusBar;

import javax.swing.*;
import java.awt.*;

/**
 * This class allows to access all JComponents of the frame
 *
 * Created by Glib Sitiugin on 2015-03-13.
 */
public final class JFrameTool {

    /**
     * Size of input editor panel
     */
    public static final Dimension TAB_SIZE = new Dimension(500, 500);

    public static final String EMPTY_FILE_PATH = "Type the file path, or select a file by clicking browse...";
    /**
     * Status panel hints text
     */
    public static final String EMPTY_HINT = "";

    public static final Dimension BAR_SIZE = new Dimension(1020, 50);

    public static JLabel getInputFilePath() {

        return StatusBar.INPUT_FILE_PATH;
    }

    public static void enableComponent(JComponent component, boolean isEnabled) {

        component.setEnabled(isEnabled);
    }
}
