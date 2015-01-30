package ca.yorku.cse2311.tab2pdf.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * MainJFrame
 * <p/>
 * Normally it's bad practice to extend JFrame ("prefer composition over inheritance"), but this is fine for now
 *
 * @author Marco
 * @since 2015-01-21
 */
public class MainJFrame extends JFrame {

    // to use this, see: http://docs.oracle.com/javase/tutorial/uiswing/components/filechooser.html
    // When you create this with "new JFileChooser" the file chooser will open
    private JFileChooser inputFileChooser;
    private JFileChooser outputFileChooser;

    // to use this, see: http://docs.oracle.com/javase/tutorial/uiswing/components/textfield.html
    // This text field will display to the user the path of the file they selected
    private JTextField inputFilePath;
    /**
     * This ActionListener will listen for clicks to the inputFileButton
     * It will open a file chooser when inputFileButton is clicked
     */
    private final ActionListener inputActionListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {

            System.out.println("Input Button Clicked: " + e.paramString());

            // TODO: open the JFileChooser here

            // TODO: (as a bonus...) restrict input to only *.txt files...
            // To do this, you will have to look up file filters
            // See: http://docs.oracle.com/javase/tutorial/uiswing/components/filechooser.html#filters

            // TODO: set the text of inputFilePath to the path of the File that was chosen by the inputFileChooser
            inputFilePath.setText("TODO: set the text here to the path of the file that was opened, ex: ~/deep/samples/moonlightsonata.txt");
        }
    };
    private JTextField outputFilePath;
    /**
     * This ActionListener will listen for clicks to the outputFileButton
     * It will open a file chooser when outputFileButton is clicked
     */
    private final ActionListener outputActionListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {

            System.out.println("Output Button Clicked: " + e.paramString());

            // TODO: open the JFileChooser here

            // TODO: (as a bonus...) restrict input to only *.txt files...
            // To do this, you will have to look up file filters
            // See: http://docs.oracle.com/javase/tutorial/uiswing/components/filechooser.html#filters

            // TODO: set the text of outputFilePath to the path of the File that was chosen by the outputFileChooser
            // I'm not sure what happens when this file doesn't exist.
            // If you have to choose a file that exists, allow the outputFileChooser to choose a directory
            // and name the output file to the same name as the input File, with a PDF extension
            // So, if input File is moonlightsonata.txt, output file is moonlightsonata.txt.pdf
            // We can make this pretty later
            outputFilePath.setText("TODO: set the text here to the path of the file that the PDF will be saved to, ex: ~/deep/moonlightsonata.txt.pdf");
        }
    };
    // To use this, see: http://docs.oracle.com/javase/tutorial/uiswing/components/button.html
    // When the user clicks these buttons, a file chooser will open and allow them to select a file
    private JButton inputFileButton;
    private JButton outputFileButton;

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


        // EDIT BELOW THIS

        // Add the file input and output panels to our window
        // The default layout of JFrame's is BorderLayout
        // see: http://docs.oracle.com/javase/tutorial/uiswing/layout/visual.html
        this.getContentPane().add(createFileInputPanel(), BorderLayout.CENTER);
        this.getContentPane().add(createFileOutputPanel(), BorderLayout.CENTER);
    }

    /**
     * Creates and shows the main window of our application
     */
    public static void createAndShow(String title) {

        // see: http://docs.oracle.com/javase/tutorial/uiswing/components/toplevel.html
        MainJFrame window = new MainJFrame(title);   // create the window that holds our application

        // See here for more info: http://www.java2s.com/Tutorial/Java/0240__Swing/DisplayaJFrameinstance.htm
        window.setSize(700, 500);   // TODO: remove hardcoded numbers here
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); // exit the app when the JFrame closes
        window.setVisible(true);    // Show the window
    }

    /**
     * This JPanel will host the input file chooser
     * See (for JPanels): http://docs.oracle.com/javase/tutorial/uiswing/components/panel.html
     *
     * @return A JPanel that will allow the user to select an input file
     */
    private JPanel createFileInputPanel() {
        JPanel panel = new JPanel();

        // TODO: Add a Border to this Jpanel, with the title "Input File"
        // See: http://docs.oracle.com/javase/tutorial/uiswing/components/border.html
        // You will create a TitledBorder that uses EtchedBorder or LineBorder as its Border
        // also see: http://docs.oracle.com/javase/8/docs/api/javax/swing/BorderFactory.html#createTitledBorder-javax.swing.border.Border-java.lang.String-

        // TODO: Make these hardcoded Strings/Integers constant
        // like this (at the top of the file): private static final String BROWSE = "Browse";
        inputFileButton = new JButton("Browse");
        inputFileButton.addActionListener(inputActionListener);
        inputFilePath = new JTextField("Select a file by clicking browse...");

        // TODO: resize this stuff so that it looks nicer (see setPreferredSize())

        panel.add(inputFilePath);    // add the input file text field to the panel
        panel.add(inputFileButton);    // add the browse button to the panel

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

        // TODO: Add a Border to this Jpanel, with the title "Output File"
        // See: http://docs.oracle.com/javase/tutorial/uiswing/components/border.html
        // You will create a TitledBorder that uses EtchedBorder or LineBorder as its Border
        // also see: http://docs.oracle.com/javase/8/docs/api/javax/swing/BorderFactory.html#createTitledBorder-javax.swing.border.Border-java.lang.String-

        // TODO: Make these hardcoded Strings/Integers constant
        // like this (at the top of the file): private static final String BROWSE = "Browse";
        outputFileButton = new JButton("Browse");
        outputFileButton.addActionListener(outputActionListener);
        outputFilePath = new JTextField("Select a file by clicking browse...");

        // TODO: resize this stuff so that it looks nicer (see setPreferredSize())

        panel.add(outputFilePath);    // add the output file text field to the panel
        panel.add(outputFileButton);    // add the browse button to the panel

        return panel;
    }
}
