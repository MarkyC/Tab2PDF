package ca.yorku.cse2311.tab2pdf.ui;

import ca.yorku.cse2311.tab2pdf.PdfHelper;

import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.Serializable;
import java.util.Dictionary;
import java.util.Enumeration;

/**
 * JFrameData
 * Stores constants which define the frame
 *
 * @author Marco, Glib, Varsha
 * @since 2015-02-21
 */
public final class JFrameData {

    /**
     * Slider data
     */
    static final int SCALING_SLIDER_MIN = 5;

    static final int SCALING_SLIDER_MAX = 15;

    static final int SCALING_SLIDER_INIT = 7;

    static final int SPACING_SLIDER_MIN = 2;

    static final int SPACING_SLIDER_MAX = 8;

    static final int SPACING_SLIDER_INIT = 5;


    public static final String EMPTY_FILE_PATH = "Type the file path, or select a file by clicking browse...";

    public static final Dimension BUTTON_SIZE = new Dimension(170, 170);

    /**
     * Minimum size of the window
     */
    public static final Dimension WINDOW_MIN_SIZE = new Dimension(800, 600);

    /**
     * Size of input editor panel
     */
    public static final Dimension VIEW_PANEL_SIZE = new Dimension(500, 500);

    public static final Dimension BAR_SIZE = new Dimension(1020, 50);

    public static final Dimension PROGRESS_BAR_SIZE = new Dimension(200, 50);


    /**
     * Title of the GUI window
     */
    public static final String WINDOW_TITLE = "Tab2PDF";

    /**
     * Scaling constants of the Pdf file
     */
    public static final int MEDIUM_SCALING = PdfHelper.getLineSpace();

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

    public static final String EDITOR_TOOLTIP_TEXT = "Edit the input file";

    public static final String IO_TOOLTIP_TEXT = "Select the input and output files for the program";

    public static final String CONVERT_TOOLTIP_TEXT = "Preview the Pdf file or save the changes to the input file";

    public static final String SCALING_TOOLTIP_TEXT = "Select the overall size of the Pdf elements";

    public static final String SPACING_TOOLTIP_TEXT = "Select the horizontal spacing between the Pdf elements";



}