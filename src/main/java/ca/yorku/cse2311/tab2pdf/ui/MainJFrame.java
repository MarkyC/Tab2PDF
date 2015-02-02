package ca.yorku.cse2311.tab2pdf.ui;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * MainJFrame
 * Handles the GUI of the application
 *
 * @author Marco
 * @since 2015-01-21
 */
public class MainJFrame extends JFrame {

    public static final String BROWSE = "Browse";

    public static final String EMPTY_FILE_PATH = "Type the file path, or select a file by clicking browse...";

    public static final String CREATE_PDF = "Create PDF";

    /**
     * Border around the Input file panel
     */
    public static final TitledBorder INPUT_PANEL_BORDER = BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Input File");

    /**
     * Border around the output file panel
     */
    public static final TitledBorder OUTPUT_PANEL_BORDER = BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Output File");

    /**
     * Minimum width of inputFilePath/outputFilePath
     */
    public static final int FILE_FIELD_MIN_WIDTH = 400;

    /**
     * Max width of the input/output panels (file path text field + browse button)
     */
    public static final int FILE_PANEL_MAX_WIDTH = 800;

    /**
     * Minimum size of the window
     */
    public static final Dimension WINDOW_MIN_SIZE = new Dimension(400, 200);

    /**
     * Title of the GUI window
     */
    public static final String WINDOW_TITLE = "Tab2PDF";

    /**
     * Filters *.txt and *.text files
     */
    private static final FileFilter TEXT_FILE_FILTER = new FileNameExtensionFilter("Text Files", "txt", "text");

    /**
     * Filters *.tab files
     */
    private static final FileFilter TAB_FILE_FILTER = new FileNameExtensionFilter("Tab Files", "tab");

    /**
     * Filters *.pdf files
     */
    private static final FileFilter PDF_FILE_FILTER = new FileNameExtensionFilter("PDF Files", "pdf");

    private final Logger LOG = Logger.getLogger(this.getClass().getName());

    /**
     * The Tab File we will be converting to PDF
     */
    private File inputFile;

    /**
     * The PDF File to save output to
     */
    private File outputFile;

    /**
     * This ActionListener will fire when the Create PDF button is clicked
     */
    private final ActionListener CREATE_PDF_LISTENER = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {

            LOG.log(Level.INFO, e.paramString());

            JOptionPane.showMessageDialog(
                    MainJFrame.this,
                    "input=" + inputFile.getAbsolutePath()
                            + "\noutput=" + outputFile.getAbsolutePath(),
                    "Create PDF",
                    JOptionPane.INFORMATION_MESSAGE
            );
        }
    };

    /**
     * Will show the path to the tab file
     */
    private JTextField inputFilePath = new JTextField(EMPTY_FILE_PATH);

    /**
     * This ActionListener will listen for clicks to the inputFileButton
     * It will open a file chooser when inputFileButton is clicked
     */
    private final ActionListener INPUT_LISTENER = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {

            LOG.log(Level.INFO, e.paramString());

            // setup file chooser
            JFileChooser fc = new JFileChooser();
            fc.setFileFilter(TEXT_FILE_FILTER);           // Allow *.txt files (default)
            fc.addChoosableFileFilter(TAB_FILE_FILTER);   // Allow *.tab files

            // open the file chooser, showing the open dialog
            if (JFileChooser.APPROVE_OPTION == fc.showOpenDialog(MainJFrame.this)) {

                // Set the file path in the text field to that of the users chosen file
                inputFilePath.setText(fc.getSelectedFile().getPath());
            }
        }
    };

    /**
     * Will show the path to the PDF file
     */
    private JTextField outputFilePath = new JTextField(EMPTY_FILE_PATH);

    /**
     * This ActionListener will listen for clicks to the outputFileButton
     * It will open a file chooser when outputFileButton is clicked
     */
    private final ActionListener OUTPUT_LISTENER = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {

            LOG.log(Level.INFO, e.paramString());

            // setup file chooser
            JFileChooser fc = new JFileChooser();
            fc.setFileFilter(PDF_FILE_FILTER);  // Allow *.pdf files (default)

            // open the file chooser, showing the save dialog
            if (JFileChooser.APPROVE_OPTION == fc.showSaveDialog(MainJFrame.this)) {

                // Set the file path in the text field to that of the users chosen file
                outputFilePath.setText(fc.getSelectedFile().getPath());
            }
        }
    };

    /**
     * Creates the main window of our application
     *
     * @param title The title of the window
     */
    public MainJFrame(String title) {

        super(title);

        // This magically centers the window on the screen
        this.setLocationRelativeTo(null);

        // This magically makes the window look native to your computer (it: Windows, or Mac looking)
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {/* Who cares if we can't set the look and feel? */}

        // This magically makes everything vertical
        LayoutManager layout = new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS);
        this.setLayout(layout);

        this.getContentPane().add(createFileInputPanel());
        this.getContentPane().add(Box.createGlue());
        this.getContentPane().add(createFileOutputPanel());
        this.getContentPane().add(Box.createGlue());
        this.getContentPane().add(createPdfButtonPanel());
    }

    /**
     * Creates and shows the main window of our application
     */
    public static void createAndShow() {

        MainJFrame window = new MainJFrame(WINDOW_TITLE);  // create the window that holds our application
        window.pack();                              // compress the window
        window.setMinimumSize(WINDOW_MIN_SIZE);     // set min window size
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); // exit the app when the JFrame closes
        window.setVisible(true);                    // Show the window
    }

    public static void main(String[] args) {

        // Temporary stub to test GUI code
        // Right click this file and click Run As > Java Application
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {

                MainJFrame.createAndShow();
            }
        });
    }

    /**
     * This JPanel will host the input file chooser
     * See (for JPanels): http://docs.oracle.com/javase/tutorial/uiswing/components/panel.html
     *
     * @return A JPanel that will allow the user to select an input file
     */
    private JPanel createFileInputPanel() {

        JPanel panel = new JPanel();

        BoxLayout layout = new BoxLayout(panel, BoxLayout.X_AXIS);
        panel.setLayout(layout);

        // Add border to panel
        panel.setBorder(INPUT_PANEL_BORDER);

        // Setup button
        JButton browseButton = new JButton(BROWSE);
        browseButton.addActionListener(INPUT_LISTENER);

        // Setup input file text field
        inputFilePath.setMinimumSize(new Dimension(FILE_FIELD_MIN_WIDTH, inputFilePath.getHeight()));

        panel.add(inputFilePath);    // add the input file text field to the panel
        panel.add(browseButton);    // add the browse button to the panel

        panel.setMaximumSize(new Dimension(FILE_PANEL_MAX_WIDTH, panel.getHeight()));

        return panel;
    }

    /**
     * This JPanel will host the input file chooser
     * See (for JPanels): http://docs.oracle.com/javase/tutorial/uiswing/components/panel.html
     *
     * @return A JPanel that will allow the user to select an input file
     */
    private JPanel createFileOutputPanel() {

        JPanel panel = new JPanel();

        BoxLayout layout = new BoxLayout(panel, BoxLayout.X_AXIS);
        panel.setLayout(layout);

        // Add border to panel
        panel.setBorder(OUTPUT_PANEL_BORDER);

        // Setup button
        JButton browseButton = new JButton(BROWSE);
        browseButton.addActionListener(OUTPUT_LISTENER);

        // Setup input file text field
        outputFilePath.setMinimumSize(new Dimension(FILE_FIELD_MIN_WIDTH, outputFilePath.getHeight()));

        panel.add(outputFilePath);  // add the output file text field to the panel
        panel.add(browseButton);    // add the browse button to the panel

        panel.setMaximumSize(new Dimension(FILE_PANEL_MAX_WIDTH, panel.getHeight()));

        return panel;
    }

    /**
     * @return A JPanel with a Create PDF button
     */
    private JPanel createPdfButtonPanel() {

        JPanel panel = new JPanel();

        JButton createPdfButton = new JButton(CREATE_PDF);
        createPdfButton.addActionListener(CREATE_PDF_LISTENER);

        panel.add(createPdfButton);
        return panel;
    }

}