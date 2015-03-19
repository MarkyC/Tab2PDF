package ca.yorku.cse2311.tab2pdf.ui;

import ca.yorku.cse2311.tab2pdf.Arguments;
import ca.yorku.cse2311.tab2pdf.PdfHelper;
import ca.yorku.cse2311.tab2pdf.ui.component.EditorTab;
import ca.yorku.cse2311.tab2pdf.ui.component.PreviewTab;
import ca.yorku.cse2311.tab2pdf.ui.component.StatusBar;
import ca.yorku.cse2311.tab2pdf.ui.component.ToolBar;
import ca.yorku.cse2311.tab2pdf.ui.listener.HelpListener;
import ca.yorku.cse2311.tab2pdf.ui.listener.OpenFileListener;
import ca.yorku.cse2311.tab2pdf.util.PdfCreator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
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

        LOG.info("Opening file: "+file.getAbsolutePath());

        try { // Put this file in the editor
            this.EDITOR_TAB.setFile(file);
            this.file = file;
            update(String.format("Opened %s", file.getName()));
        } catch (IOException e) {
            LOG.severe(e.getMessage());
            // TODO: Show error dialog explaining we could not open the file
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

        // determines the relative positions of the panels to be added
        this.setLayout(new BorderLayout());

        // add primary panels to the frame
        Container container = this.getContentPane();
        container.add(this.TOOLBAR = new ToolBar(), BorderLayout.NORTH);

        JTabbedPane pane = tabbedPane("Switch to Text File Editor", this.EDITOR_TAB = new EditorTab(), "Switch to Pdf Preview Panel", new PreviewTab());
        container.add(pane, BorderLayout.CENTER);
        container.add(this.STATUS_BAR = new StatusBar(), BorderLayout.SOUTH);
        // add listeners to the toolbar elements
        addListeners();

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

        // add action listeners to the toolbar buttons
        this.TOOLBAR.getOpenButton().addActionListener(new OpenFileListener(this));
        this.TOOLBAR.getSaveButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {

                try {
                    BufferedWriter writer = new BufferedWriter(new FileWriter(file));
                    writer.write(EDITOR_TAB.getText());
                    writer.close();
                } catch (IOException e) {
                    LOG.severe(e.getMessage());
                    // TODO: Show "could not save file, make sure it is not opened in another process" dialog
                }
            }
        });
        this.TOOLBAR.getExportButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {

                try {
                    new PdfCreator(
                            new PdfHelper(EDITOR_TAB.getSpacingValue(), EDITOR_TAB.getScalingValue()),
                            getEditorContents()
                    );
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        this.TOOLBAR.getHelpButton().addActionListener(new HelpListener(this));

        // add key listener to the input editor
        // the listener is needed to update symbols number in status panel
        this.EDITOR_TAB.getEditor().addKeyListener(new KeyAdapter() {
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

    public void update(String status) {

        this.STATUS_BAR.setHint(status);

        if (null != file) {
            this.STATUS_BAR.setInputFilePath(file.getAbsolutePath());   // The file we're currently editing
            this.STATUS_BAR.setSymbolsNumber(this.EDITOR_TAB.getEditor().getText().length()); // Size of the file

            // If a file is loaded, we should enable certain stuff
            this.TOOLBAR.getSaveButton().setEnabled(true);
            this.TOOLBAR.getExportButton().setEnabled(true);
            this.EDITOR_TAB.setEnabled(true);
        } else {
            blockComponents();
        }
    }

    /**
     * Stores contents of input editor in a list line by line and returns the list
     * Method is needed to supply a list to parse
     *
     * @return list containing the contents of input editor
     */
    public List<String> getEditorContents() {

        // TODO: refactor this method into EditorTab.getEditorText()

        //Setup object to store editor contents
        List<String> editorContents = new ArrayList<>();

        String contents = this.EDITOR_TAB.getEditor().getText();
        if (!contents.isEmpty()) {
            String lines[] = contents.split("\\r?\\n");

            //Store contents into object
            for (int i = 0; i < lines.length; i++)
                editorContents.add(i, lines[i]);
        }
        return editorContents;
    }

    private JTabbedPane tabbedPane(String tab1Name, JComponent tab1, String tab2Name, JComponent tab2) {

        JTabbedPane pane = new JTabbedPane();

        pane.addTab(tab1Name, tab1);
        pane.addTab(tab2Name, tab2);

        return pane;

    }

    /**
     * Disables components which are not supposed to be used yet
     */
    private void blockComponents() {

        this.TOOLBAR.getSaveButton().setEnabled(false);
        this.TOOLBAR.getExportButton().setEnabled(false);

        this.EDITOR_TAB.setEnabled(false);
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
