package ca.yorku.cse2311.tab2pdf.ui;

import ca.yorku.cse2311.tab2pdf.Arguments;
import ca.yorku.cse2311.tab2pdf.PdfHelper;
import ca.yorku.cse2311.tab2pdf.util.PdfCreator;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.*;
import java.nio.file.Files;
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

    /**
     * Modifiable text pane to which contents of input file are loaded
     */
    public static JTextPane inputEditor = new JTextPane();

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
    private JTextField inputFilePath = new JTextField(JFrameData.EMPTY_FILE_PATH);

    private final Logger LOG = Logger.getLogger(this.getClass().getName());

    /**
     * This ActionListener will fire when the Help button is clicked
     */
    public final ActionListener HELP_LISTENER = new ActionListener() {

        @Override
        public void actionPerformed(ActionEvent e) {

            LOG.log(Level.INFO, e.paramString());

            JFrame helpFrame = new JFrame("User Manual");
            helpFrame.setSize(500, 500);
            JLabel label = new JLabel("Will be implemented soon!", JLabel.CENTER);
            helpFrame.add(label);
            helpFrame.setVisible(true);
        }

    };

    /**
     * This ActionListener will fire when the Preview button is clicked
     */
    public final ActionListener CONVERT_LISTENER = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {

            LOG.log(Level.INFO, e.paramString());

            if ( (null == getInputFile()) || !getInputFile().exists() || !getInputFile().canRead() ) {
                JOptionPane.showMessageDialog(MainJFrame.this, "Input file does not exist or cannot be read.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if ( null == getOutputFile() ) {
                //JOptionPane.showMessageDialog(MainJFrame.this, "No Output File Specified. A temporary file will be created.", "Error", JOptionPane.ERROR_MESSAGE);
                try {
                    setOutputFile(Files.createTempFile("tab2pdf-", ".pdf").toFile());
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(MainJFrame.this, "Could not create temporary file. Application will now close", "Error", JOptionPane.ERROR_MESSAGE);
                    System.exit(-1);
                }
            }

            new Thread(new PdfCreator(new Arguments(getInputFile(), getOutputFile()))).start();
        }
    };

    /**
     * This ActionListener will fire when the Save button is clicked
     */
    private final ActionListener SAVE_LISTENER = new ActionListener() {

        @Override
        public void actionPerformed(ActionEvent e) {

            LOG.log(Level.INFO, e.paramString());

            if ( (null == getInputFile()) || !getInputFile().exists() || !getInputFile().canRead() ) {
                JOptionPane.showMessageDialog(MainJFrame.this, "Input file does not exist or cannot be read.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if ( null == getOutputFile() ) {
                JOptionPane.showMessageDialog(MainJFrame.this, "No Output File Specified. A temporary file will be created.", "Error", JOptionPane.ERROR_MESSAGE);
                try {
                    setOutputFile(Files.createTempFile("tab2pdf-", ".pdf").toFile());
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(MainJFrame.this, "Could not create temporary file. Application will now close", "Error", JOptionPane.ERROR_MESSAGE);
                    System.exit(-1);
                }
            }

            try {saveFile();}
            catch (IOException e1) {e1.printStackTrace();}
        }
    };

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
            //fileChooser.setCurrentDirectory(getInputFile().getParentFile()); //Starts chooser in current directory
            fileChooser.setFileFilter(JFrameData.TEXT_FILE_FILTER);           // Allow *.txt files (default)
            fileChooser.addChoosableFileFilter(JFrameData.TAB_FILE_FILTER);   // Allow *.tab files

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
    private JTextField outputFilePath = new JTextField(JFrameData.EMPTY_FILE_PATH);

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
            if (null != getOutputFile()) fileChooser.setCurrentDirectory(getOutputFile().getParentFile()); //Starts chooser in current directory
            fileChooser.setFileFilter(JFrameData.PDF_FILE_FILTER);  // Allow *.pdf files (default)

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
     * This action listener will listen for scaling slider actions
     */
    private final ChangeListener SCALING_LISTENER = new ChangeListener() {

        @Override
        public void stateChanged(ChangeEvent e) {

            LOG.log(Level.INFO, e.toString());

            PdfHelper.setLineSpace(JFramePanel.verticalSlider.getValue());
        }
    };

    /**
     * This action listener will listen for spacing slider actions
     */
    private final ChangeListener SPACING_LISTENER = new ChangeListener() {

        @Override
        public void stateChanged(ChangeEvent e) {

            LOG.log(Level.INFO, e.toString());

            // get editor lines
            String[] line = inputEditor.getText().split("\\n");
            String string = line[2];

            line[2] = string.replace(string.charAt(8), (char) JFramePanel.horizontalSlider.getValue());
            String printableText = Arrays.toString(line).replaceAll("[\\[\\]]", "").replaceAll(", ", "\n");
            inputEditor.setText(printableText);

            //Scroll back to the top
            inputEditor.setCaretPosition(0);
        }
    };

    /**
     * Application main frame constructor
     *
     * @param title the window title
     */
    private MainJFrame(String title) {

        super(title);

        // setup location and make its size fixed
        this.setLocation(0, 0);
        this.setResizable(false);

        //Makes the frame look native to your computer (it: Windows, or Mac looking)
        try {UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());}
        catch (Exception e) {e.printStackTrace();}

        //Determines the relative positions of the panels to be added
        this.setLayout(new BorderLayout());

        //Add primary panels to the frame
        Container container = this.getContentPane();
        container.add(new JFramePanel(JFramePanel.TOOLBAR_PANEL_NAME), BorderLayout.PAGE_START);
        container.add(new JFramePanel(JFramePanel.INPUT_EDITOR_NAME), BorderLayout.LINE_START);
        container.add(new JFramePanel(JFramePanel.PREVIEW_PANEL_NAME), BorderLayout.LINE_END);

        // add listeners to the toolbar elements
        addListeners();
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
    public static void createAndShow() {createAndShow(JFrameData.WINDOW_TITLE, new Arguments());}

    public static void createAndShow(String title, Arguments args) {

        MainJFrame window = new MainJFrame(title, args); // create the window that holds our application
        window.pack(); // compress contents
        //window.setMinimumSize(JFrameData.WINDOW_MIN_SIZE); // set minimum size
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); // exit the app when the JFrame closes
        window.setVisible(true);                // Show the window
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

        //Scroll back to the top
        inputEditor.setCaretPosition(0);
    }

    /**
     * Saves contents of input editor back into input file
     *
     * @throws IOException when input file path is incorrect
     */
    private void saveFile() throws IOException {

        BufferedWriter writer = new BufferedWriter(new FileWriter(inputFile));
        writer.write(inputEditor.getText());
        writer.close();
    }

    /**
     * Stores contents of inout editor in a list line by line and returns the list
     *
     * @return list containing the contents of input editor
     */
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
     * Adds ActionListeners to the toolbar elements
     */
    public void addListeners(){

        // add action listeners to buttons
        JFramePanel.openButton.addActionListener(INPUT_LISTENER);
        JFramePanel.saveButton.addActionListener(SAVE_LISTENER);
        JFramePanel.convertButton.addActionListener(CONVERT_LISTENER);
        JFramePanel.helpButton.addActionListener(HELP_LISTENER);

        // add change listeners to sliders
        JFramePanel.verticalSlider.addChangeListener(SCALING_LISTENER);
        JFramePanel.horizontalSlider.addChangeListener(SPACING_LISTENER);
    }

    public static void main(String[] args) {

        // Temporary stub to test GUI code
        // Right click this file and click Run As > Java Application
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {MainJFrame.createAndShow();}
        });
    }
}
