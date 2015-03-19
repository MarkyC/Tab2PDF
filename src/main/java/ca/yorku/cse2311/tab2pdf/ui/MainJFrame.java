package ca.yorku.cse2311.tab2pdf.ui;

import ca.yorku.cse2311.tab2pdf.Arguments;
import ca.yorku.cse2311.tab2pdf.ui.component.*;
import ca.yorku.cse2311.tab2pdf.ui.component.MenuBar;
import ca.yorku.cse2311.tab2pdf.ui.listener.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;

/**
 * MainJFrame
 * Handles the GUI of the application
 *
 * @author Marco, Glib, Varsha
 * @since 2015-01-21
 */
public class MainJFrame extends JFrame {

    private final static Logger LOG = Logger.getLogger(MainJFrame.class.getName());

    private final ToolBar TOOLBAR;

    private final EditorTab EDITOR_TAB;

    private final StatusBar STATUS_BAR;

    private final MenuBar MENU_BAR;

    private final OpenFileListener OPEN_FILE_LISTENER = new OpenFileListener(this);
    private final SaveFileListener SAVE_FILE_LISTENER = new SaveFileListener(this);
    private final ExportPdfListener EXPORT_PDF_LISTENER = new ExportPdfListener(this);
    private final SettingsListener SETTINGS_LISTENER = new SettingsListener(this);
    private final HelpListener HELP_LISTENER = new HelpListener(this);
    private final AboutListener ABOUT_LISTENER = new AboutListener(this);

    /**
     * The tab we are editing
     */
    private File file;

    /**
     * @return the tab we are editing
     */
    public File getFile() {

        return file;
    }

    /**
     * sets the tab file we are editing
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

    }

    /**
     * Title of the GUI window
     */
    public static final String WINDOW_TITLE = "Tab2PDF";

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
        try {UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());}
        catch (Exception e) {e.printStackTrace();}

        this.setJMenuBar(this.MENU_BAR = new MenuBar());

        // add primary panels to the frame
        Container container = this.getContentPane();
        container.add(this.TOOLBAR = new ToolBar(), BorderLayout.NORTH);

        JTabbedPane pane = tabbedPane("Editor", this.EDITOR_TAB = new EditorTab(), "Preview", new PreviewTab());
        container.add(pane, BorderLayout.CENTER);
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
        //window.setMinimumSize(JFrameData.WINDOW_MIN_SIZE); // set minimum size
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); // exit the app when the JFrame closes
        window.setVisible(true);                // Show the window
    }

    /**
     * Adds Event Listeners to the JFrame components
     */
    public void addListeners() {

        // add action listeners to the toolbar and menu buttons
        this.TOOLBAR.getOpenButton().addActionListener(OPEN_FILE_LISTENER);
        this.MENU_BAR.getOpenMenuItem().addActionListener(OPEN_FILE_LISTENER);

        this.TOOLBAR.getSaveButton().addActionListener(SAVE_FILE_LISTENER);
        this.MENU_BAR.getSaveMenuItem().addActionListener(SAVE_FILE_LISTENER);

        this.TOOLBAR.getExportButton().addActionListener(EXPORT_PDF_LISTENER);
        this.MENU_BAR.getExportMenuItem().addActionListener(EXPORT_PDF_LISTENER);

        this.TOOLBAR.getSettingsButton().addActionListener(SETTINGS_LISTENER);
        this.MENU_BAR.getSettingsMenuItem().addActionListener(SETTINGS_LISTENER);

        this.TOOLBAR.getHelpButton().addActionListener(HELP_LISTENER);
        this.MENU_BAR.getUserManualMenuItem().addActionListener(HELP_LISTENER);

        this.MENU_BAR.getAboutMenuItem().addActionListener(ABOUT_LISTENER);

        this.MENU_BAR.getExitMenuItem().addActionListener(new ExitListener(this));

        // add key listener to the input editor
        // the listener is needed to update symbols number in status panel
        getEditorTab().getEditor().addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {

                update();
                super.keyPressed(e);
            }
        });
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

        this.STATUS_BAR.setHint(status);

        if (null != file) {
            this.STATUS_BAR.setInputFilePath(file.getAbsolutePath());   // The file we're currently editing
            this.STATUS_BAR.setSymbolsNumber(getEditorTab().getText().length()); // Size of the file

            // If a file is loaded, we should enable certain stuff
            this.TOOLBAR.getSaveButton().setEnabled(true);
            this.MENU_BAR.getSaveMenuItem().setEnabled(true);
            this.TOOLBAR.getExportButton().setEnabled(true);
            this.MENU_BAR.getExportMenuItem().setEnabled(true);
            getEditorTab().setEnabled(true);
        } else {
            blockComponents();
        }
    }

    public EditorTab getEditorTab() {

        return EDITOR_TAB;
    }

    private JTabbedPane tabbedPane(String tab1Name, JComponent tab1, String tab2Name, JComponent tab2) {

        final JTabbedPane pane = new JTabbedPane();

        pane.addTab(String.format(
                "<html><body><table width='150'><tr><td align='center'>%s</td></tr></table></body></html>",
                tab1Name
                ),
            tab1
        );
        pane.addTab(String.format(
                "<html><body><table width='150'><tr><td align='center'>%s</td></tr></table></body></html>",
                tab2Name
                ),
            tab2
        );

        return pane;

    }

    /**
     * Disables components which are not supposed to be used yet
     */
    private void blockComponents() {

        this.TOOLBAR.getSaveButton().setEnabled(false);
        this.MENU_BAR.getSaveMenuItem().setEnabled(false);
        this.TOOLBAR.getExportButton().setEnabled(false);
        this.MENU_BAR.getExportMenuItem().setEnabled(false);

        getEditorTab().setEnabled(false);
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
}
