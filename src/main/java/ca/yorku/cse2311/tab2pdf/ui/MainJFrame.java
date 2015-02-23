package ca.yorku.cse2311.tab2pdf.ui;

import ca.yorku.cse2311.tab2pdf.Arguments;
import ca.yorku.cse2311.tab2pdf.PdfHelper;
import ca.yorku.cse2311.tab2pdf.util.PdfCreator;

import javax.swing.*;
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

    /**
     * Modifiable text pane to which contents of input file are loaded
     */
    public static JTextPane inputEditor = new JTextPane();

    /**
     * Buttons in scaling panel
     */
    private JRadioButtonMenuItem radioButtonScaleExtraLarge;
    private JRadioButtonMenuItem radioButtonScaleLarge;
    private JRadioButtonMenuItem radioButtonScaleMedium;
    private JRadioButtonMenuItem radioButtonScaleSmall;
    private JRadioButtonMenuItem radioButtonScaleExtraSmall;

    /**
     * Buttons in spacing panel
     */
    private JRadioButtonMenuItem radioButtonSpaceExtraLarge;
    private JRadioButtonMenuItem radioButtonSpaceLarge;
    private JRadioButtonMenuItem radioButtonSpaceMedium;
    private JRadioButtonMenuItem radioButtonSpaceSmall;
    private JRadioButtonMenuItem radioButtonSpaceExtraSmall;

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
     * This ActionListener will fire when the Preview button is clicked
     */
    private final ActionListener FILE_PREVIEW_LISTENER = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {

            LOG.log(Level.INFO, e.paramString());

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
            fileChooser.setCurrentDirectory(getInputFile().getParentFile()); //Starts chooser in current directory
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
            fileChooser.setCurrentDirectory(getOutputFile().getParentFile()); //Starts chooser in current directory
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
     * This action listener will listen for clicks to the radio buttons
     */
    private final ActionListener SCALING_LISTENER = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {

            LOG.log(Level.INFO, e.paramString());

            //If button is clicked change scale
            if (radioButtonScaleExtraSmall.isSelected())
                PdfHelper.setLineSpace(JFrameData.EXTRA_SMALL_SCALING);
            else if (radioButtonScaleSmall.isSelected())
                PdfHelper.setLineSpace(JFrameData.SMALL_SCALING);
            else if (radioButtonScaleMedium.isSelected())
                PdfHelper.setLineSpace(JFrameData.MEDIUM_SCALING);
            else if (radioButtonScaleLarge.isSelected())
                PdfHelper.setLineSpace(JFrameData.LARGE_SCALING);
            else if (radioButtonScaleExtraLarge.isSelected())
                PdfHelper.setLineSpace(JFrameData.EXTRA_LARGE_SCALING);
        }
    };

    /**
     * This action listener will listen for clicks to the radio buttons in the spacing panel
     */
    private final ActionListener SPACING_LISTENER = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {

            LOG.log(Level.INFO, e.paramString());

            String[] line = inputEditor.getText().split("\\n");
            String string = line[2];

            //If button is clicked change spacing
            if (radioButtonSpaceSmall.isSelected()) {
                line[2] = string.replace(string.charAt(8), '4');
                String printableText = Arrays.toString(line).replaceAll("[\\[\\]]", "").replaceAll(", ", "\n");
                inputEditor.setText(printableText);
            }
            else if(radioButtonSpaceMedium.isSelected()) {
                line[2] = string.replace(string.charAt(8), '5');
                String printableText = Arrays.toString(line).replaceAll("[\\[\\]]", "").replaceAll(", ", "\n");
                inputEditor.setText(printableText);
            }
            else if (radioButtonSpaceLarge.isSelected()){
                line[2] = string.replace(string.charAt(8), '6');
                String printableText = Arrays.toString(line).replaceAll("[\\[\\]]", "").replaceAll(", ", "\n");
                inputEditor.setText(printableText);
            }
            else if(radioButtonSpaceExtraSmall.isSelected()){
                line[2] = string.replace(string.charAt(8), '3');
                String printableText = Arrays.toString(line).replaceAll("[\\[\\]]", "").replaceAll(", ", "\n");
                inputEditor.setText(printableText);
            }
            else if(radioButtonSpaceExtraLarge.isSelected()){
                line[2] = string.replace(string.charAt(8), '7');
                String printableText = Arrays.toString(line).replaceAll("[\\[\\]]", "").replaceAll(", ", "\n");
                inputEditor.setText(printableText);
            }

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

        //Puts the window in the upper left corner
        this.setLocation(0, 0);

        //Makes the frame look native to your computer (it: Windows, or Mac looking)
        try {UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());}
        catch (Exception e) {e.printStackTrace();}

        //Determines the relative positions of the panels to be added
        this.setLayout(new BorderLayout());

        //Add primary panels to the frame
        Container container = this.getContentPane();
        container.add(topPanel(), BorderLayout.PAGE_START);
        container.add(leftPanel(), BorderLayout.LINE_START);
        container.add(centralPanel(), BorderLayout.CENTER);
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
        window.setMinimumSize(JFrameData.WINDOW_MIN_SIZE); // set minimum size
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
     * The primary panel which allows to edit the input file
     *
     * @return primary panel containing a modifiable and scrollable text pane
     */
    private JPanel centralPanel() {

        //Setup panel
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.LINE_AXIS));
        panel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), JFrameData.EDITOR_PANEL_NAME));
        panel.setMaximumSize(JFrameData.EDITOR_PANEL_SIZE);
        panel.setToolTipText(JFrameData.EDITOR_TOOLTIP_TEXT);

        //Setup text editor and add it to the panel
        inputEditor.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 12));
        panel.add(inputEditor);
        JScrollPane scrollPanel = new JScrollPane(inputEditor);
        scrollPanel.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        panel.add(scrollPanel);

        return panel;
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
        panel.setToolTipText(JFrameData.IO_TOOLTIP_TEXT);

        //Add border to panel
        panel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), JFrameData.IO_PANEL_NAME));

        //Add IO Selection panels
        panel.add(fileInputPanel());
        panel.add(fileOutputPanel());

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

        //Setup input file text field
        inputFilePath.addFocusListener(INPUT_FOCUS_LISTENER);

        //Setup browse button
        JButton browseButton = new JButton(JFrameData.INPUT_BUTTON_NAME);
        browseButton.setActionCommand(JFrameData.INPUT_BUTTON_NAME);
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

        //Setup input file text field
        outputFilePath.addFocusListener(OUTPUT_FOCUS_LISTENER);

        //Setup browse button
        JButton browseButton = new JButton(JFrameData.OUTPUT_BUTTON_NAME);
        browseButton.setActionCommand(JFrameData.OUTPUT_BUTTON_NAME);
        browseButton.addActionListener(OUTPUT_LISTENER);

        //Add output file text field and browse button to the panel
        panel.add(outputFilePath);
        panel.add(browseButton);

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
     * The secondary panel which belongs to the left primary panel
     * and contains buttons to preview the Pdf file and to save the input file
     *
     * @return secondary panel which allows to preview and save files
     */
    private JPanel controlPanel() {

        //Setup panel
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), JFrameData.CONTROL_PANEL_NAME));
        panel.setToolTipText(JFrameData.CONVERT_TOOLTIP_TEXT);

        //Add "Create Pdf" button
        JButton convertButton = new JButton(JFrameData.CONVERT_BUTTON_NAME);
        convertButton.addActionListener(FILE_PREVIEW_LISTENER);
        panel.add(convertButton);

        //Add "Save Changes" button
        JButton saveButton = new JButton(JFrameData.SAVE_BUTTON_NAME);
        saveButton.addActionListener(SAVE_LISTENER);
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
        panel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), JFrameData.SCALING_PANEL_NAME));
        panel.setToolTipText(JFrameData.SCALING_TOOLTIP_TEXT);

        //Setup buttons
        createScalingButtons(panel);

        return panel;
    }

    /**
     * Creates 5 radio buttons which will determine the scale of the Pdf file
     *
     * @param panel panel to which buttons will be added
     */
    private void createScalingButtons(JPanel panel) {

        //Create radio buttons
        radioButtonScaleExtraLarge = new JRadioButtonMenuItem(JFrameData.EXTRA_LARGE_BUTTON_NAME, false);
        radioButtonScaleLarge = new JRadioButtonMenuItem(JFrameData.LARGE_BUTTON_NAME, false);
        radioButtonScaleMedium = new JRadioButtonMenuItem(JFrameData.MEDIUM_BUTTON_NAME, true);
        radioButtonScaleSmall = new JRadioButtonMenuItem(JFrameData.SMALL_BUTTON_NAME, false);
        radioButtonScaleExtraSmall = new JRadioButtonMenuItem(JFrameData.EXTRA_SMALL_BUTTON_NAME, false);

        //Group the buttons
        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(radioButtonScaleExtraLarge);
        buttonGroup.add(radioButtonScaleLarge);
        buttonGroup.add(radioButtonScaleMedium);
        buttonGroup.add(radioButtonScaleSmall);
        buttonGroup.add(radioButtonScaleExtraSmall);

        //Add action listeners
        radioButtonScaleExtraLarge.addActionListener(SCALING_LISTENER);
        radioButtonScaleLarge.addActionListener(SCALING_LISTENER);
        radioButtonScaleMedium.addActionListener(SCALING_LISTENER);
        radioButtonScaleSmall.addActionListener(SCALING_LISTENER);
        radioButtonScaleExtraSmall.addActionListener(SCALING_LISTENER);

        //Add buttons to panel
        panel.add(radioButtonScaleExtraLarge);
        panel.add(radioButtonScaleLarge);
        panel.add(radioButtonScaleMedium);
        panel.add(radioButtonScaleSmall);
        panel.add(radioButtonScaleExtraSmall);
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
        panel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), JFrameData.SPACING_PANEL_NAME));
        panel.setToolTipText(JFrameData.SPACING_TOOLTIP_TEXT);

        //Setup buttons
        createSpacingButtons(panel);

        return panel;
    }

    /**
     * Creates 5 radio buttons which will determine the spacing of the Pdf file
     *
     * @param panel panel to which buttons will be added
     */
    private void createSpacingButtons(JPanel panel) {

        //Create radio buttons
        radioButtonSpaceExtraLarge = new JRadioButtonMenuItem(JFrameData.EXTRA_LARGE_BUTTON_NAME, false);
        radioButtonSpaceLarge = new JRadioButtonMenuItem(JFrameData.LARGE_BUTTON_NAME, false);
        radioButtonSpaceMedium = new JRadioButtonMenuItem(JFrameData.MEDIUM_BUTTON_NAME, true);
        radioButtonSpaceSmall = new JRadioButtonMenuItem(JFrameData.SMALL_BUTTON_NAME, false);
        radioButtonSpaceExtraSmall = new JRadioButtonMenuItem(JFrameData.EXTRA_SMALL_BUTTON_NAME, false);

        //Group the buttons
        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(radioButtonSpaceExtraLarge);
        buttonGroup.add(radioButtonSpaceLarge);
        buttonGroup.add(radioButtonSpaceMedium);
        buttonGroup.add(radioButtonSpaceSmall);
        buttonGroup.add(radioButtonSpaceExtraSmall);

        //Add action listeners
        radioButtonSpaceExtraLarge.addActionListener(SPACING_LISTENER);
        radioButtonSpaceLarge.addActionListener(SPACING_LISTENER);
        radioButtonSpaceMedium.addActionListener(SPACING_LISTENER);
        radioButtonSpaceSmall.addActionListener(SPACING_LISTENER);
        radioButtonSpaceExtraSmall.addActionListener(SPACING_LISTENER);

        //Add buttons to panel
        panel.add(radioButtonSpaceExtraLarge);
        panel.add(radioButtonSpaceLarge);
        panel.add(radioButtonSpaceMedium);
        panel.add(radioButtonSpaceSmall);
        panel.add(radioButtonSpaceExtraSmall);
    }

}
