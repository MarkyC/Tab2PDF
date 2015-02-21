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
     * Default scaling of the Pdf file
     */
    public static final int MEDIUM_SCALING = PdfHelper.getLineSpace();

    public static final int EXTRA_SMALL_SCALING = MEDIUM_SCALING - 4;

    public static final int SMALL_SCALING = MEDIUM_SCALING - 2;

    public static final int LARGE_SCALING = MEDIUM_SCALING + 2;

    public static final int EXTRA_LARGE_SCALING = MEDIUM_SCALING + 4;

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
}