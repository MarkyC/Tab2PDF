package ca.yorku.cse2311.tab2pdf.ui.support;

import ca.yorku.cse2311.tab2pdf.ui.component.InputEditorTab;
import ca.yorku.cse2311.tab2pdf.ui.component.StatusBar;
import ca.yorku.cse2311.tab2pdf.ui.component.ToolBar;

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

    public static JTextPane getEditor() {

        return InputEditorTab.EDITOR;
    }

    public static JTextField getTitle() {

        return InputEditorTab.TITLE;
    }


    public static JTextField getSubtitle() {

        return InputEditorTab.SUBTITLE;
    }

    public static JSlider getScalingSlider() {

        return InputEditorTab.SCALING_SLIDER;
    }

    public static JSlider getSpacingSlider() {

        return InputEditorTab.SPACING_SLIDER;
    }

    public static JButton getSaveTextFileButon() {

        return InputEditorTab.SAVE_TEXT_FILE_BUTTON;
    }

    /**
     * @return the number of symbols in the input editor
     */
    public static int getSymbolsNumber() {

        return InputEditorTab.EDITOR.getText().length();
    }

    public static JLabel getInputFilePath() {

        return StatusBar.INPUT_FILE_PATH;
    }

    public static JButton getOpenButton() {

        return ToolBar.OPEN_BUTTON;
    }

    public static JButton getSavePdfButton() {

        return ToolBar.SAVE_PDF_BUTTON;
    }

    public static JButton getConvertButton() {

        return ToolBar.CONVERT_BUTTON;
    }

    public static JButton getSettingsButton() {

        return ToolBar.SETTINGS_BUTTON;
    }

    public static JButton getHelpButton() {

        return ToolBar.HELP_BUTTON;
    }


    public static void enableComponent(JComponent component, boolean isEnabled) {

        component.setEnabled(isEnabled);
    }
}
