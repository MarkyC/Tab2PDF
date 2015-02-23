package ca.yorku.cse2311.tab2pdf.ui;

import ca.yorku.cse2311.tab2pdf.PdfHelper;

import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.Serializable;

/**
 * JFrameData
 * Stores constants which define the frame
 *
 * @author Marco, Glib, Varsha
 * @since 2015-02-21
 */
public final class JFrameData {

    public static final String EMPTY_FILE_PATH = "Type the file path, or select a file by clicking browse...";

    /**
     * Minimum size of the window
     */
    public static final Dimension WINDOW_MIN_SIZE = new Dimension(800, 600);

    /**
     * Size of input editor panel
     */
    public static final Dimension EDITOR_PANEL_SIZE = new Dimension(640, 480);

    /**
     * Title of the GUI window
     */
    public static final String WINDOW_TITLE = "Tab2PDF";

    /**
     * Scaling constants of the Pdf file
     */
    public static final int MEDIUM_SCALING = PdfHelper.getLineSpace();

    public static final int EXTRA_SMALL_SCALING = MEDIUM_SCALING - 4;

    public static final int SMALL_SCALING = MEDIUM_SCALING - 2;

    public static final int LARGE_SCALING = MEDIUM_SCALING + 2;

    public static final int EXTRA_LARGE_SCALING = MEDIUM_SCALING + 4;

    /**
     * Radio button names
     */
    public static final String EXTRA_LARGE_BUTTON_NAME = "Large+";

    public static final String LARGE_BUTTON_NAME = "Large";

    public static final String MEDIUM_BUTTON_NAME = "Medium";

    public static final String SMALL_BUTTON_NAME = "Small";

    public static final String EXTRA_SMALL_BUTTON_NAME = "Small-";

    /**
     * Filters *.txt and *.text files
     */
    public static final FileFilter TEXT_FILE_FILTER = new FileNameExtensionFilter("Text Files (*.txt, *.text)", "txt", "text");
    /**
     * Filters *.tab files
     */
    public static final FileFilter TAB_FILE_FILTER = new FileNameExtensionFilter("Tab Files (*.tab)", "tab");
    /**
     * Filters *.pdf files
     */
    public static final FileFilter PDF_FILE_FILTER = new FileNameExtensionFilter("PDF Files (*.pdf)", "pdf");

    public static final String EDITOR_PANEL_NAME = "Input Editor";

    public static final String IO_PANEL_NAME = "File Selection";

    public static final String CONTROL_PANEL_NAME = "Control Panel";

    public static final String SCALING_PANEL_NAME = "Scaling Panel";

    public static final String SPACING_PANEL_NAME = "Spacing Panel";

    public static final String INPUT_BUTTON_NAME = "Browse For Input File  ";

    public static final String OUTPUT_BUTTON_NAME = "Browse For Output File";

    public static final String CONVERT_BUTTON_NAME = "Convert to Pdf";

    public static final String SAVE_BUTTON_NAME = "Save Changes";

    public static final String EDITOR_TOOLTIP_TEXT = "Edit the input file";

    public static final String IO_TOOLTIP_TEXT = "Select the input and output files for the program";

    public static final String CONVERT_TOOLTIP_TEXT = "Preview the Pdf file or save the changes to the input file";

    public static final String SCALING_TOOLTIP_TEXT = "Select the overall size of the Pdf elements";

    public static final String SPACING_TOOLTIP_TEXT = "Select the horizontal spacing between the Pdf elements";

}