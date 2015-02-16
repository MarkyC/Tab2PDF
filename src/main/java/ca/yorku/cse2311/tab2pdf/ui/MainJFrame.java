package ca.yorku.cse2311.tab2pdf.ui;

import ca.yorku.cse2311.tab2pdf.Arguments;
import ca.yorku.cse2311.tab2pdf.util.PdfCreator;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.*;
import java.util.*;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * MainJFrame
 * Handles the GUI of the application
 *
 * @author Marco, Glib, Varsha
 * @since 2015-01-21
 */
public class MainJFrame extends JFrame {

    public static JTextPane inputEditor = new JTextPane();

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
    private final ActionListener FILE_PREVIEW_LISTENER = new ActionListener() {
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
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setCurrentDirectory(getInputFile().getParentFile()); //Starts chooser in current directory
            fileChooser.setFileFilter(TEXT_FILE_FILTER);           // Allow *.txt files (default)
            fileChooser.addChoosableFileFilter(TAB_FILE_FILTER);   // Allow *.tab files

            // open the file chooser, showing the open dialog
            if (JFileChooser.APPROVE_OPTION == fileChooser.showOpenDialog(MainJFrame.this)) {

                // Set the file path in the text field to that of the users chosen file
                File selectedFile = fileChooser.getSelectedFile();
                inputFilePath.setText(selectedFile.getPath());

                //Load file contents into editor
                setInputFile(selectedFile);
                try {loadEditorFile(selectedFile);}
                catch (IOException e1) {e1.printStackTrace();}
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
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setCurrentDirectory(getOutputFile().getParentFile()); //Starts chooser in current directory
            fileChooser.setFileFilter(PDF_FILE_FILTER);  // Allow *.pdf files (default)

            // open the file chooser, showing the save dialog
            if (JFileChooser.APPROVE_OPTION == fileChooser.showSaveDialog(MainJFrame.this)) {

                // Set the file path in the text field to that of the users chosen file
                File selectedFile = fileChooser.getSelectedFile();
                outputFilePath.setText(selectedFile.getPath());
                setOutputFile(selectedFile);
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
     * Application main frame constructor
     *
     * @param title the window title
     */
    private MainJFrame(String title) {

        super(title);

        //Puts the window in the upper left corner
        this.setLocation(0, 0);

        //Makes the frame look native to your computer (it: Windows, or Mac looking)
        try {UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());}
        catch (Exception e) {e.printStackTrace();}

        //Determines the relative positions of the panels to be added
        this.setLayout(new BorderLayout());

        //Add panels to the frame
        this.getContentPane().add(topPanel(), BorderLayout.PAGE_START);
        this.getContentPane().add(leftPanel(), BorderLayout.LINE_START);
        this.getContentPane().add(centralPanel(), BorderLayout.CENTER);
    }

    /**
     * Application main frame constructor
     *
     * @param title the window title
     * @param args command line arguments
     */
    private MainJFrame(String title, Arguments args) {

        this(title);

        inputFile = args.getInputFile();
        outputFile = args.getOutputFile();
        inputFilePath.setText((null != inputFile) ? inputFile.getAbsolutePath() : "");
        outputFilePath.setText((null != outputFile) ? outputFile.getAbsolutePath() : "");
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
            public void run() {MainJFrame.createAndShow();}
        });
    }


    public File getInputFile() {return inputFile;}

    public void setInputFile(File inputFile) {this.inputFile = inputFile;}

    public File getOutputFile() {return outputFile;}

    public void setOutputFile(File outputFile) {this.outputFile = outputFile;}

    /**
     * Loads contents of the input file into input editor
     *
     * @param sourceFile input file whose contents are to be loaded
     */
    private void loadEditorFile(File sourceFile) throws IOException {

        //Setup file reader
        BufferedReader bufferedReader = new BufferedReader(new FileReader(sourceFile));
        StringBuilder stringBuilder = new StringBuilder();
        //TODO: check if line is not null
        String nextLine = bufferedReader.readLine();
        //Read file
        while (nextLine != null) {
            stringBuilder.append(nextLine);
            stringBuilder.append(System.lineSeparator());
            nextLine = bufferedReader.readLine();
        }

        //Put the resulting string into editor
        inputEditor.setText(stringBuilder.toString());
    }

    public static List<String> getEditorContents() {

        //Setup object to store editor contents
        List<String> editorContents = new ArrayList<>();

        String contents = inputEditor.getText();
        if (!contents.isEmpty()) {
            String lines[] = contents.split("\\r?\\n");

            //Store contents into object
            for (int i = 0; i < lines.length; i++)
                editorContents.add(i, lines[i]);
        }

        return editorContents;
    }

    /**
     * The IO primary panel which allows user to select input and output files
     *
     * @return primary panel to be displayed at the top of the frame
     */
    private JPanel topPanel() {

        //Set up panel
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setToolTipText("Select the input and output files for the program");

        //Add border to panel
        panel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "File Selection"));

        //Add IO Selection panels
        panel.add(fileInputPanel());
        panel.add(fileOutputPanel());

        return panel;
    }

    /**
     * The primary panel which allows for Pdf preview, saving the text file, and resizing Pdf components
     *
     * @return leftmost primary panel
     */
    private JPanel leftPanel() {

        //Setup panel
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        //Add button secondary panel
        panel.add(controlPanel());

        //Add Scaling/Spacing secondary panels
        panel.add(scalingPanel());
        panel.add(spacingPanel());

        return panel;
    }

    /**
     * The primary panel which allows to edit the input file
     *
     * @return primary panel containing a modifiable and scrollable text pane
     */
    private JPanel centralPanel() {

        //Setup panel
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.LINE_AXIS));
        panel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Input Editor"));
        panel.setMaximumSize(EDITOR_PANEL_SIZE);
        panel.setToolTipText("Edit the input file");

        //Setup text editor and add it to the panel
        panel.add(inputEditor);
        JScrollPane scrollPanel = new JScrollPane(inputEditor);
        scrollPanel.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        panel.add(scrollPanel);

        return panel;
    }

    /**
     * The secondary panel which belongs to the top primary panel hosts the input file chooser
     *
     * @return secondary panel which allows for browsing input files
     */
    private JPanel fileInputPanel() {

        //Setup panel
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));

        // Setup input file text field
        inputFilePath.addFocusListener(INPUT_FOCUS_LISTENER);

        // Setup browse button
        JButton browseButton = new JButton("Browse For Input File  ");
        browseButton.setActionCommand("Browse For Input File");
        browseButton.addActionListener(INPUT_LISTENER);

        //Add input file text field and browse button to the panel
        panel.add(inputFilePath);    // add the input file text field to the panel
        panel.add(browseButton);    // add the browse button to the panel

        return panel;
    }
    /**
     * The secondary panel which belongs to the top primary panel and hosts the output file chooser
     * @return secondary panel which allows for browsing output files
     */
    private JPanel fileOutputPanel() {

        //Setup panel
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));

        // Setup input file text field
        outputFilePath.addFocusListener(OUTPUT_FOCUS_LISTENER);

        // Setup browse button
        JButton browseButton = new JButton("Browse For Output File");
        browseButton.setActionCommand("Browse For Output File");
        browseButton.addActionListener(OUTPUT_LISTENER);

        //Add output file text field and browse button to the panel
        panel.add(outputFilePath);
        panel.add(browseButton);

        return panel;
    }

    /**
     * The secondary panel which belongs to the left primary panel
     * and contains buttons to preview the Pdf file and to save the input file
     *
     * @return secondary panel which allows to preview and save files
     */
    private JPanel controlPanel() {

        //Setup panel
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Control Panel"));
        panel.setToolTipText("Preview the Pdf file or save the changes to the input file");

        //Add "Create Pdf" button
        JButton previewButton = new JButton("File Preview   ");
        previewButton.addActionListener(FILE_PREVIEW_LISTENER);
        panel.add(previewButton);

        //Add "Save Changes" button
        JButton saveButton = new JButton("Save Changes");
        panel.add(saveButton);

        return panel;
    }

    /**
     * The secondary panel which belongs to the left primary panel
     * and contains a group of radio buttons to select the overall scale of the Pdf file
     *
     * @return secondary panel which allows to scale the Pdf file
     */
    private JPanel scalingPanel() {

        //Setup panel
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Scaling Panel"));
        panel.setToolTipText("Select the overall size of the Pdf elements");

        //Create radio buttons
        JRadioButtonMenuItem radioButtonExtraLarge = new JRadioButtonMenuItem("Large+", false);
        radioButtonExtraLarge.setToolTipText("Select the overall size of the Pdf elements");
        JRadioButtonMenuItem radioButtonLarge = new JRadioButtonMenuItem("Large", false);
        JRadioButtonMenuItem radioButtonMedium = new JRadioButtonMenuItem("Medium", true);
        JRadioButtonMenuItem radioButtonSmall = new JRadioButtonMenuItem("Small", false);
        JRadioButtonMenuItem radioButtonExtraSmall = new JRadioButtonMenuItem("Small-", false);

        //Group the buttons
        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(radioButtonExtraLarge);
        buttonGroup.add(radioButtonLarge);
        buttonGroup.add(radioButtonMedium);
        buttonGroup.add(radioButtonSmall);
        buttonGroup.add(radioButtonExtraSmall);

        //Add buttons to panel
        panel.add(radioButtonExtraLarge);
        panel.add(radioButtonLarge);
        panel.add(radioButtonMedium);
        panel.add(radioButtonSmall);
        panel.add(radioButtonExtraSmall);

        return panel;
    }

    /**
     * The secondary panel which belongs to the left primary panel
     * and contains a group of radio buttons to select the horizontal spacing between the Pdf file objects
     *
     * @return secondary panel which allows to select the Pdf file spacing
     */
    private JPanel spacingPanel() {

        //Setup panel
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Spacing Panel"));
        panel.setToolTipText("Select the horizontal spacing between the Pdf elements");

        //Create radio buttons
        JRadioButtonMenuItem radioButtonExtraLarge = new JRadioButtonMenuItem("Large+", false);
        JRadioButtonMenuItem radioButtonLarge = new JRadioButtonMenuItem("Large", false);
        JRadioButtonMenuItem radioButtonMedium = new JRadioButtonMenuItem("Medium", true);
        JRadioButtonMenuItem radioButtonSmall = new JRadioButtonMenuItem("Small", false);
        JRadioButtonMenuItem radioButtonExtraSmall = new JRadioButtonMenuItem("Small-", false);

        //Group the buttons
        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(radioButtonExtraLarge);
        buttonGroup.add(radioButtonLarge);
        buttonGroup.add(radioButtonMedium);
        buttonGroup.add(radioButtonSmall);
        buttonGroup.add(radioButtonExtraSmall);

        //Add buttons to panel
        panel.add(radioButtonExtraLarge);
        panel.add(radioButtonLarge);
        panel.add(radioButtonMedium);
        panel.add(radioButtonSmall);
        panel.add(radioButtonExtraSmall);

        return panel;
    }

}
