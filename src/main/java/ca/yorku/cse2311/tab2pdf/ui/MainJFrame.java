package ca.yorku.cse2311.tab2pdf.ui;

import ca.yorku.cse2311.tab2pdf.Arguments;
import ca.yorku.cse2311.tab2pdf.util.PdfCreator;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.text.JTextComponent;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
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

    public static final String EMPTY_FILE_PATH = "Type the file path, or select a file by clicking browse...";

    /**
     * Minimum width of inputFilePath/outputFilePath
     */
    //400
    public static final int FILE_FIELD_MIN_WIDTH = 100;

    /**
     * Max width of the input/output panels (file path text field + browse button)
     */
    //800
    public static final int FILE_PANEL_MAX_WIDTH = 200;

    /**
     * Minimum size of the window
     */
    public static final Dimension WINDOW_MIN_SIZE = new Dimension(0, 600);

    /**
     * Title of the GUI window
     */
    public static final String WINDOW_TITLE = "Tab2PDF";

    /**
     * Filters *.txt and *.text files
     */
    private static final FileFilter TEXT_FILE_FILTER = new FileNameExtensionFilter("Text Files (*.txt, *.text)", "txt", "text");

    /**
     * Filters *.tab files
     */
    private static final FileFilter TAB_FILE_FILTER = new FileNameExtensionFilter("Tab Files (*.tab)", "tab");

    /**
     * Filters *.pdf files
     */
    private static final FileFilter PDF_FILE_FILTER = new FileNameExtensionFilter("PDF Files (*.pdf)", "pdf");

    private final Logger LOG = Logger.getLogger(this.getClass().getName());

    /**
     * This ActionListener will fire when the Create PDF button is clicked
     */
    private final ActionListener CREATE_PDF_LISTENER = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {

            LOG.log(Level.INFO, e.paramString());

            new Thread(new PdfCreator(new Arguments(getInputFile(), getOutputFile()))).start();
        }
    };

    /**
     * The Tab File we will be converting to PDF
     */
    private File inputFile;

    /**
     * The PDF File to save output to
     */
    private File outputFile;

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
            fc.setCurrentDirectory(getInputFile().getParentFile()); //Starts chooser in current directory
            fc.setFileFilter(TEXT_FILE_FILTER);           // Allow *.txt files (default)
            fc.addChoosableFileFilter(TAB_FILE_FILTER);   // Allow *.tab files

            // open the file chooser, showing the open dialog
            if (JFileChooser.APPROVE_OPTION == fc.showOpenDialog(MainJFrame.this)) {

                // Set the file path in the text field to that of the users chosen file
                inputFilePath.setText(fc.getSelectedFile().getPath());
                setInputFile(fc.getSelectedFile());
            }
        }
    };

    /**
     * This Focus Listener will check for when the Input text box has been updated
     */
    private final FocusListener INPUT_FOCUS_LISTENER = new FocusListener() {
        @Override
        public void focusGained(FocusEvent e) {
            //Do Nothing
        }

        @Override
        public void focusLost(FocusEvent e) {
            setInputFile(new File(inputFilePath.getText()));
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
            fc.setCurrentDirectory(getOutputFile().getParentFile()); //Starts chooser in current directory
            fc.setFileFilter(PDF_FILE_FILTER);  // Allow *.pdf files (default)

            // open the file chooser, showing the save dialog
            if (JFileChooser.APPROVE_OPTION == fc.showSaveDialog(MainJFrame.this)) {

                // Set the file path in the text field to that of the users chosen file
                outputFilePath.setText(fc.getSelectedFile().getPath());
                setOutputFile(fc.getSelectedFile());
            }
        }
    };

    /**
     * This Focus Listener will check for when the Input text box has been updated
     */
    private final FocusListener OUTPUT_FOCUS_LISTENER = new FocusListener() {
        @Override
        public void focusGained(FocusEvent e) {
            //Do Nothing
        }

        @Override
        public void focusLost(FocusEvent e) {
            setInputFile(new File(outputFilePath.getText()));
        }
    };

    /**
     * Creates the main window of our application
     *
     * @param title The title of the window
     */
    private MainJFrame(String title) {

        super(title);

        //Puts the window in the upper left corner
        this.setLocation(0, 0);

        // This magically centers the window on the screen
        //this.setLocationRelativeTo(null);

        // This magically makes the window look native to your computer (it: Windows, or Mac looking)
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {/* Who cares if we can't set the look and feel? */}

        //This adds the interface panels to the frame
        addPanels();
    }

    private MainJFrame(String title, Arguments args) {

        this(title);

        inputFile = args.getInputFile();
        outputFile = args.getOutputFile();
        inputFilePath.setText((null != inputFile) ? inputFile.getAbsolutePath() : "");
        outputFilePath.setText((null != outputFile) ? outputFile.getAbsolutePath() : "");
    }

    /**
     * Adds interactive panels, such as Input/Output boxes, to the frame
     */
    private void addPanels() {
        //Determines the relative position of panels in the window
        LayoutManager layout = new GridBagLayout();
        this.setLayout(layout);

        GridBagConstraints constraints = new GridBagConstraints();

        //TODO: Title
        //Adds Control Panel to the window
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.anchor = GridBagConstraints.FIRST_LINE_END;
        this.getContentPane().add(controlPanel(), constraints);
        //Adds Input Editor to the window
        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.ipady = 480;
        constraints.ipadx = 640;
        this.getContentPane().add(inputEditor(), constraints);
        //Adds File Selection Panel to the window
        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.ipady = 0;
        constraints.ipadx = 0;
        constraints.anchor = GridBagConstraints.FIRST_LINE_START;
        this.getContentPane().add(fileSelectionPanel(), constraints);
    }

    /**
     * Creates and shows the main window of our application
     */
    public static void createAndShow() {createAndShow(WINDOW_TITLE, new Arguments());}

    public static void createAndShow(String title, Arguments args) {

        MainJFrame window = new MainJFrame(title, args); // create the window that holds our application
        window.pack(); // compress contents
        window.setMinimumSize(WINDOW_MIN_SIZE); // set minimum size
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); // exit the app when the JFrame closes
        window.setVisible(true);                // Show the window
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


    public File getInputFile() {

        return inputFile;
    }

    public void setInputFile(File inputFile) {

        this.inputFile = inputFile;
    }

    public File getOutputFile() {

        return outputFile;
    }

    public void setOutputFile(File outputFile) {

        this.outputFile = outputFile;
    }


    /**
     * This JPanel will host the input file chooser
     * See (for JPanels): http://docs.oracle.com/javase/tutorial/uiswing/components/panel.html
     *
     * @return A JPanel that will allow the user to select an input file
     */
    private JPanel fileInputPanel() {

        JPanel panel = new JPanel();
        BoxLayout layout = new BoxLayout(panel, BoxLayout.X_AXIS);
        panel.setLayout(layout);
        // Setup button
        JButton browseButton = new JButton(" Browse For Input File ");
        browseButton.setActionCommand("Browse For Input File");
        browseButton.addActionListener(INPUT_LISTENER);
        // Setup input file text field
        inputFilePath.setMinimumSize(new Dimension(FILE_FIELD_MIN_WIDTH, inputFilePath.getHeight()));
        inputFilePath.addFocusListener(INPUT_FOCUS_LISTENER);

        panel.add(inputFilePath);    // add the input file text field to the panel
        panel.add(browseButton);    // add the browse button to the panel

        return panel;
    }
    /**
     * This JPanel will host the input file chooser
     * See (for JPanels): http://docs.oracle.com/javase/tutorial/uiswing/components/panel.html
     *
     * @return A JPanel that will allow the user to select an input file
     */
    private JPanel fileOutputPanel() {

        JPanel panel = new JPanel();
        BoxLayout layout = new BoxLayout(panel, BoxLayout.X_AXIS);
        panel.setLayout(layout);
        // Setup button
        JButton browseButton = new JButton("Browse For Output File");
        browseButton.setActionCommand("Browse For Output File");
        browseButton.addActionListener(OUTPUT_LISTENER);
        // Setup input file text field
        outputFilePath.setMinimumSize(new Dimension(FILE_FIELD_MIN_WIDTH, outputFilePath.getHeight()));
        outputFilePath.addFocusListener(OUTPUT_FOCUS_LISTENER);

        panel.add(outputFilePath);  // add the output file text field to the panel
        panel.add(browseButton);    // add the browse button to the panel

        return panel;
    }

    private JPanel scrollBarPanel() {
        //TODO for me: make scrollbars active
        JPanel panel = new JPanel();
        BoxLayout layout = new BoxLayout(panel, BoxLayout.X_AXIS);
        panel.setLayout(layout);
        //Setup Scaling Bar
        JLabel scalingLabel = new JLabel("Scaling Factor:   ");
        JTextField scalingTextField = new JTextField();
        scalingTextField.setText("7.0");
        JScrollBar scalingScrollBar = new JScrollBar(JScrollBar.VERTICAL);
        panel.add(scalingLabel);
        panel.add(scalingTextField);
        panel.add(scalingScrollBar);
        //Setup Spacing Bar
        JLabel spacingLabel = new JLabel("Spacing Factor:   ");
        JTextField spacingTextField = new JTextField();
        spacingTextField.setText("1.0");
        JScrollBar spacingScrollBar = new JScrollBar(JScrollBar.VERTICAL);
        panel.add(spacingLabel);
        panel.add(spacingTextField);
        panel.add(spacingScrollBar);
        JLabel emptySpace = new JLabel("   ");
        panel.add(emptySpace);

        return panel;
    }


    /**
     * @return a panel containing a set of buttons
     */
    private JPanel controlPanel() {

        JPanel panel = new JPanel();
        BoxLayout layout = new BoxLayout(panel, BoxLayout.X_AXIS);
        panel.setLayout(layout);
        // Adds border to panel
        panel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Control Panel"));
        //Add ScrollBar panel
        panel.add(scrollBarPanel());
        //Add "Create Pdf" button
        JButton createPdfButton = new JButton("Create Pdf");
        createPdfButton.addActionListener(CREATE_PDF_LISTENER);
        panel.add(createPdfButton);
        //Add "Preview" button
        JButton previewButton = new JButton("Preview Pdf");
        previewButton.addActionListener(CREATE_PDF_LISTENER);
        panel.add(previewButton);

        return panel;
    }

    private JPanel fileSelectionPanel() {

        JPanel panel = new JPanel();
        BoxLayout layout = new BoxLayout(panel, BoxLayout.Y_AXIS);
        panel.setLayout(layout);
        // Adds border to panel
        panel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "File Selection Panel"));
        //Add Input/Output Selection panels
        panel.add(fileInputPanel());
        panel.add(fileOutputPanel());
        return panel;
    }
    /**
     * @return a panel containing a modifiable and scrollable text field.
     * -Input file contents are to be loaded in the text field.
     * -Output of the app is based on the contents of the text field.
     */
    private JPanel inputEditor() {

        //TODO for Varsha:implement this method
        //Don't mind other TODOs, they are for me

        //This determines how components are positioned relative to each other
        //Change if needed. Leave it like this if method works fine
        JPanel panel = new JPanel();
        BoxLayout layout = new BoxLayout(panel, BoxLayout.LINE_AXIS);
        panel.setLayout(layout);

        //This adds visible border to the panel
        panel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Input Editor"));
        //JTextArea editor = new JTextArea(); //This is what i suggest to use as a text field
        //panel.add(editor); // This is how you can add stuff to the panel

        /*
         * 1) Add text field to the panel
         * 2) Make sure it fits the size of the panel and does not change the size when you print stuff in it
         * 3) Add a vertical scroll bar to the text field
         * 4) When you select input file, its contents must be printed in the text field
         *    You'll probably need to work with INPUT_LISTENER to do this
         * 5) Since user can modify the text field, when you create Pdf, it must be created
         *    based on the text field, not the input file
         *    You'll probably need to work with CREATE_PDF_LISTENER to do this
         * 6) After you're done, send me the class and i'll continue working on it
         *
         * -  Listeners are already in the code, you don't need to create them
         * -  Use JFrame API to figure out how to do all this stuff
         * -  If you don't get how some of the code works, text me
         */

        return panel; //This returns the resulting panel for further use
    }

}
