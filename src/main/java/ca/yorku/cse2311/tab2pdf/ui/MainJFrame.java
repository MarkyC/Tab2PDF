package ca.yorku.cse2311.tab2pdf.ui;

import ca.yorku.cse2311.tab2pdf.Arguments;
import ca.yorku.cse2311.tab2pdf.model.Tab;
import ca.yorku.cse2311.tab2pdf.parser.TabParser;
import ca.yorku.cse2311.tab2pdf.ui.component.*;
import ca.yorku.cse2311.tab2pdf.ui.component.MenuBar;
import ca.yorku.cse2311.tab2pdf.ui.listener.*;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * MainJFrame
 * Handles the GUI of the application
 *
 * @author Marco Cirillo, Glib Sitiugin, Varsha Ragavendran
 * @since 2015-01-21
 */
public class MainJFrame extends JFrame {

    /**
     * Title of the GUI window
     */
    public static final String WINDOW_TITLE = "Tab2PDF";

    private final static Logger LOG = Logger.getLogger(MainJFrame.class.getName());

    private final ToolBar TOOLBAR;

    private final EditorTab EDITOR_TAB;

    private final PreviewTab PREVIEW_TAB;

    private final StatusBar STATUS_BAR;

    private final MenuBar MENU_BAR;

    private final JTabbedPane TABBED_PANE;

    private final OpenFileListener OPEN_FILE_LISTENER = new OpenFileListener(this);

    private final SaveFileListener SAVE_FILE_LISTENER = new SaveFileListener(this);

    private final ExportPdfListener EXPORT_PDF_LISTENER = new ExportPdfListener(this);

    private final HelpListener HELP_LISTENER = new HelpListener(this);

    private final AboutListener ABOUT_LISTENER = new AboutListener(this);

    private final SampleInput1Listener SAMPLE_1_LISTENER = new SampleInput1Listener(this);

    private final SampleInput2Listener SAMPLE_2_LISTENER = new SampleInput2Listener(this);

    /**
     * The tab we are editing
     */
    private File file;

    /**
     * Main frame constructor
     *
     * @param title the window title
     */
    private MainJFrame(String title) {

        super(title);

        // setup location and make its size fixed
        this.setLocation(0, 0);

        // makes the frame look native to your computer (ie: Windows, or Mac looking)
        try { UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName()); }
        catch (Exception e) { LOG.log(Level.WARNING, "Could not set look and feel", e); }

        this.setJMenuBar(this.MENU_BAR = new MenuBar());

        // add primary panels to the frame
        Container container = this.getContentPane();
        container.add(this.TOOLBAR = new ToolBar(), BorderLayout.NORTH);

        // Add tabs for this window
        // Not to be confused with guitar tabs, these tabs are for the Editor and Preview panes.
        this.TABBED_PANE = new JTabbedPane();
        TABBED_PANE.addTab(formatTabName("Editor"), this.EDITOR_TAB = new EditorTab());
        TABBED_PANE.addTab(formatTabName("Preview"), this.PREVIEW_TAB = new PreviewTab());
        container.add(TABBED_PANE, BorderLayout.CENTER);

        // Add status bar
        container.add(this.STATUS_BAR = new StatusBar(), BorderLayout.SOUTH);

        // add listeners to the toolbar elements
        addListeners();

        // update GUI, we're ready for primetime!
        update();
    }

    /**
     * Main frame constructor which is used when command line arguments are supplied
     *
     * @param title the window title
     * @param args command line arguments
     */
    private MainJFrame(String title, Arguments args) {

        this(title);
    }

    /**
     * Creates and shows the main window of our application
     */
    public static void createAndShow() {createAndShow(WINDOW_TITLE, new Arguments());}

    public static void createAndShow(String title, Arguments args) {

        MainJFrame window = new MainJFrame(title, args); // create the window that holds our application
        window.pack(); // compress contents
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); // exit the app when the JFrame closes
        window.setTitle(title);
        window.setVisible(true);                // Show the window

    }

    /**
     * Temporary piece of code to test GUI
     * @param args program arguments
     */
    public static void main(String[] args) {

        SwingUtilities.invokeLater(new Runnable() {
            public void run() {

                MainJFrame.createAndShow();
            }
        });
    }

    /**
     * @return the tab we are editing
     */
    public File getFile() {

        return file;
    }

    /**
     * sets the tab file we are editing:
     * * load it in the editor
     * * update title/subtitle/spacing/scaling with values from the tab
     *
     * @param file the new tab file to set
     */
    public void setFile(File file) {

        try { // Put this file in the editor
            getEditorTab().setFile(file);
            this.file = file;
            update(String.format("Opened %s", file.getName()));
        } catch (IOException e) {
            LOG.severe(e.getMessage());
            String filePath = null == file ? "" : file.getAbsolutePath();
            JOptionPane.showMessageDialog(
                    MainJFrame.this,
                    String.format("Could not open file %s. Error: %s", filePath, e.getMessage()),
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }

        getEditorTab().getEditor().setCaretPosition(0); // bring the editor to the top

        // Refresh the preview tab, so the user is show the newly loaded tab
        // Fixes #65: converted Pdf file is not shown in the preview panel until you switch to editor panel and back
        new PreviewTabListener(this).stateChanged(new ChangeEvent(getTabbedPane()));
    }

    /**
     * Adds Event Listeners to the JFrame components
     */
    public void addListeners() {

        // add action listeners to the toolbar and menu buttons
        getToolbar().getOpenButton().addActionListener(OPEN_FILE_LISTENER);
        getMenubar().getOpenMenuItem().addActionListener(OPEN_FILE_LISTENER);

        getToolbar().getSaveButton().addActionListener(SAVE_FILE_LISTENER);
        getMenubar().getSaveMenuItem().addActionListener(SAVE_FILE_LISTENER);

        getToolbar().getExportButton().addActionListener(EXPORT_PDF_LISTENER);
        getMenubar().getExportMenuItem().addActionListener(EXPORT_PDF_LISTENER);

        getToolbar().getHelpButton().addActionListener(HELP_LISTENER);
        getMenubar().getUserManualMenuItem().addActionListener(HELP_LISTENER);

        getMenubar().getSample1MenuItem().addActionListener(SAMPLE_1_LISTENER);
        getMenubar().getSample2MenuItem().addActionListener(SAMPLE_2_LISTENER);

        getMenubar().getAboutMenuItem().addActionListener(ABOUT_LISTENER);

        getMenubar().getExitMenuItem().addActionListener(new ExitListener(this));

        getTabbedPane().addChangeListener(new PreviewTabListener(this));
    }

    public void update() {

        update("Welcome to Tab2Pdf");
    }

    /**
     * This makes the GUI tick. It's updated after events like
     *
     * * key press
     * * file opening
     * * file saved
     * * PDF exported
     *
     * @param status    The new status to put at the bottom
     */
    public void update(String status) {

        getStatusbar().setHint(status);

        if (null != file) {

            getStatusbar().setInputFilePath(file.getAbsolutePath());   // The file we're currently editing
            getStatusbar().setSymbolsNumber(getEditorTab().getText().length()); // Size of the file

            // If a file is loaded, we should enable everything
            setEnabled(true);

        } else {
            setEnabled(false);
        }
    }

    public final EditorTab getEditorTab() {

        return EDITOR_TAB;
    }

    public final ToolBar getToolbar() {
        return TOOLBAR;
    }

    public final MenuBar getMenubar() {
        return MENU_BAR;
    }

    public final StatusBar getStatusbar() {
        return STATUS_BAR;
    }

    public final JTabbedPane getTabbedPane() {
        return TABBED_PANE;
    }

    public final PreviewTab getPreviewTab() {
        return PREVIEW_TAB;
    }

    @Override
    public void setEnabled(boolean enabled) {

        // don't call super here, since it would actually disable this window
        // instead, we delegate to the toolbar, menubar, and editor's setEnabled() methods
        getToolbar().setEnabled(enabled);
        getMenubar().setEnabled(enabled);
        getEditorTab().setEnabled(enabled);
    }

    private static String formatTabName(String name) {
        return String.format(
                "<html><body><table width='150'><tr><td align='center'>%s</td></tr></table></body></html>",
                name
        );
    }

    public String getTitle() {
        return EDITOR_TAB.getTitle();
    }

    public String getSubtitle() {
        return EDITOR_TAB.getSubtitle();
    }

    public Integer getSpacingValue() {
        return EDITOR_TAB.getSpacingValue();
    }

    public Integer getScalingValue() {
        return EDITOR_TAB.getScalingValue();
    }

    /**
     * @return  The current tab that is open for editing
     */
    public Tab getTab() {

        Tab tab = TabParser.parse(getEditorTab().getTextAsList());
        tab.setTitle(getTitle());
        tab.setSubtitle(getSubtitle());
        tab.setSpacing(getSpacingValue());
        tab.setScaling(getScalingValue());
        return tab;
    }
}
