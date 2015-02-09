package ca.yorku.cse2311.tab2pdf.ui;

import ca.yorku.cse2311.tab2pdf.Arguments;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.*;
import java.util.List;

import static ca.yorku.cse2311.tab2pdf.ui.JFrameData.WINDOW_TITLE;

/**
 * MainJFrame
 * Handles the GUI of the application
 *
 * @author Marco, Glib, Varsha
 * @since 2015-01-21
 */
public class MainJFrame extends JFrame {

    /**
     * Main frame constructor
     *
     * @param title the window title
     */
    private MainJFrame(String title) {

        super(title);

        // setup location and make its size fixed
        this.setLocation(0, 0);
        this.setResizable(false);

        // makes the frame look native to your computer (it: Windows, or Mac looking)
        try {UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());}
        catch (Exception e) {e.printStackTrace();}

        // determines the relative positions of the panels to be added
        this.setLayout(new BorderLayout());

        // add primary panels to the frame
        Container container = this.getContentPane();
        container.add(new ToolBar(), BorderLayout.PAGE_START);
        container.add(new EditorPanel(), BorderLayout.LINE_START);
        container.add(new PreviewPanel(), BorderLayout.LINE_END);
        container.add(new StatusBar(), BorderLayout.PAGE_END);

        // add listeners to the toolbar elements
        addListeners();
    }

    /**
     * Main frame constructor which is used when command line arguments are supplied
     *
     * @param title the window title
     * @param args command line arguments
     */
    private MainJFrame(String title, Arguments args) {

        this(title);

        JFrameListener.setInputFile(args.getInputFile());
        JFrameListener.setOutputFile(args.getOutputFile());
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
        ToolBar.OPEN_BUTTON.addActionListener(new OpenFileListener(this));
        ToolBar.SAVE_BUTTON.addActionListener(new SaveFileListener(this));
        ToolBar.CONVERT_BUTTON.addActionListener(new ConvertToPdfListener(this));
        ToolBar.HELP_BUTTON.addActionListener(new HelpListener(this));

        // add change listeners to toolbar sliders
        ToolBar.SCALING_SLIDER.addChangeListener(new ScalingSliderListener(this));
        ToolBar.SPACING_SLIDER.addChangeListener(new SpacingSliderListener(this));

        // add key listener to the input editor
        // the listener is needed to update symbols number in status panel
        EditorPanel.EDITOR.addKeyListener(new InputEditorListener(this));
    }

    /**
     * Stores contents of input editor in a list line by line and returns the list
     * Method is needed to supply a list to parse
     *
     * @return list containing the contents of input editor
     */
    public static List<String> getEditorContents() {

        //Setup object to store editor contents
        List<String> editorContents = new ArrayList<>();

        String contents = EditorPanel.EDITOR.getText();
        if (!contents.isEmpty()) {
            String lines[] = contents.split("\\r?\\n");

            //Store contents into object
            for (int i = 0; i < lines.length; i++)
                editorContents.add(i, lines[i]);
        }
        return editorContents;
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
