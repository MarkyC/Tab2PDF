package ca.yorku.cse2311.tab2pdf.ui;

import ca.yorku.cse2311.tab2pdf.Arguments;
import ca.yorku.cse2311.tab2pdf.ui.component.*;
import ca.yorku.cse2311.tab2pdf.ui.listener.*;
import ca.yorku.cse2311.tab2pdf.ui.support.JFrameTool;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;

/**
 * MainJFrame
 * Handles the GUI of the application
 *
 * @author Marco, Glib, Varsha
 * @since 2015-01-21
 */
public class MainJFrame extends JFrame {

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

        // setup size
        //this.setPreferredSize(JFrameData.SCREEN_SIZE);

        // setup location and make its size fixed
        this.setLocation(0, 0);
        //this.setResizable(false);

        // makes the frame look native to your computer (it: Windows, or Mac looking)
        try {UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());}
        catch (Exception e) {e.printStackTrace();}

        // determines the relative positions of the panels to be added
        this.setLayout(new BorderLayout());

        // add primary panels to the frame
        Container container = this.getContentPane();
        container.add(new ToolBar(), BorderLayout.NORTH);
        //container.add(new InputEditorTab(), BorderLayout.LINE_START);
        //container.add(new PreviewTab(), BorderLayout.LINE_END);

        JTabbedPane pane = tabbedPane("Switch to Text File Editor", new InputEditorTab(), "Switch to Pdf Preview Panel", new PreviewTab());
        container.add(pane, BorderLayout.CENTER);
        container.add(new StatusBar(), BorderLayout.SOUTH);
        // add listeners to the toolbar elements
        addListeners();

        blockComponents();
    }

    /**
     * Main frame constructor which is used when command line arguments are supplied
     *
     * @param title the window title
     * @param args command line arguments
     */
    private MainJFrame(String title, Arguments args) {

        this(title);

        AbstractListener.setInputFile(args.getInputFile());
        AbstractListener.setOutputFile(args.getOutputFile());
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
        JFrameTool.getOpenButton().addActionListener(new OpenFileListener(this));
        JFrameTool.getSaveTextFileButon().addActionListener(new SaveTextFileListener(this));
        JFrameTool.getConvertButton().addActionListener(new ConvertToPdfListener(this));
        JFrameTool.getHelpButton().addActionListener(new HelpListener(this));

        // add change listeners to toolbar sliders
        JFrameTool.getScalingSlider().addChangeListener(new ScalingSliderListener(this));
        JFrameTool.getSpacingSlider().addChangeListener(new SpacingSliderListener(this));

        // add key listener to the input editor
        // the listener is needed to update symbols number in status panel
        JFrameTool.getEditor().addKeyListener(new InputEditorListener(this));
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

        String contents = JFrameTool.getEditor().getText();
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
    private static void blockComponents() {

        JFrameTool.enableComponent(JFrameTool.getSavePdfButton(), false);
        JFrameTool.enableComponent(JFrameTool.getConvertButton(), false);
        JFrameTool.enableComponent(JFrameTool.getTitle(), false);
        JFrameTool.enableComponent(JFrameTool.getSubtitle(), false);
        JFrameTool.enableComponent(JFrameTool.getScalingSlider(), false);
        JFrameTool.enableComponent(JFrameTool.getSpacingSlider(), false);
        JFrameTool.enableComponent(JFrameTool.getSaveTextFileButon(), false);
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
