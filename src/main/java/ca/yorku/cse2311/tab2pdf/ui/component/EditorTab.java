package ca.yorku.cse2311.tab2pdf.ui.component;

import ca.yorku.cse2311.tab2pdf.ui.support.JFrameTool;
import ca.yorku.cse2311.tab2pdf.util.FileUtils;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

/**
 * EditorTab
 *
 * This tab allows users to edit the guitar tabulature file
 *
 * @author Glib Sitiugin, Marco Cirllo
 * @since 2015-03-18
 */
 public class EditorTab extends JPanel {

    /**
     * Toolbar sliders data
     */
    private static final int SCALING_SLIDER_MIN = 5;

    private static final int SCALING_SLIDER_MAX = 15;

    private static final int SCALING_SLIDER_INIT = 7;

    private static final int SPACING_SLIDER_MIN = 2;

    private static final int SPACING_SLIDER_MAX = 8;

    private static final int SPACING_SLIDER_INIT = 5;

    private static final int SCALING_MAJOR_TRICK_SPACING = 5;

    private static final int SPACING_MAJOR_TRICK_SPACING = 3;

    private static final int MINOR_TRICK_SPACING = 1;

    /**
     * An instance of monospaced font for input editor
     */
    private final Font EDITOR_FONT = new Font(Font.MONOSPACED, Font.PLAIN, 12);

    //private final Font TITLE_FONT = new Font(Font.MONOSPACED, Font.BOLD, 16);

    /**
     * String constants
     */
    private static final String TITLE_PANEL_NAME = "Song Title";

    private static final String SUBTITLE_PANEL_NAME = "Song Subtitle";

    private static final String SCALING_PANEL_NAME = "Vertical Scaling";

    private static final String SPACING_PANEL_NAME = "Horizontal Spacing";

    private static final String EDITOR_PANEL_NAME = "Input Editor";

    private static final String SAVE_PANEL_NAME = "Save Panel";

    /**
     * Tab components
     */
    private final JTextPane EDITOR = new JTextPane();

    private final JTextField TITLE = new JTextField("Not Implemented");

    private final JTextField SUBTITLE = new JTextField("Not Implemented");

    private final JSlider SCALING_SLIDER = new JSlider(JSlider.HORIZONTAL, SCALING_SLIDER_MIN, SCALING_SLIDER_MAX, SCALING_SLIDER_INIT);

    private final JSlider SPACING_SLIDER = new JSlider(JSlider.HORIZONTAL, SPACING_SLIDER_MIN, SPACING_SLIDER_MAX, SPACING_SLIDER_INIT);

    public JTextPane getEditor() {

        return EDITOR;
    }

    public String getTitle() {

        return TITLE.getText();
    }

    public String getSubtitle() {

        return SUBTITLE.getText();
    }

    public Integer getScalingValue() {

        return SCALING_SLIDER.getValue();
    }

    public Integer getSpacingValue() {

        return SPACING_SLIDER.getValue();
    }

    public String getText() {
        return EDITOR.getText();
    }

    /**
     * Sets the file we are editting
     * @param f The new file we are editting
     */
    public void setFile(File f) throws IOException {
        this.getEditor().setText(FileUtils.readFile(f));
    }

    /**
     * Constructs a new Editor tab composed of control panel and input editor
     */
    public EditorTab() {

        super();

        // setup layout
        this.setLayout(new BorderLayout());
        //this.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), ""));

        // add components to the tab
        this.add(controlPanel(), BorderLayout.WEST);
        this.add(editorPanel(), BorderLayout.CENTER);

        // set size
        this.setPreferredSize(JFrameTool.TAB_SIZE);
    }

    /**
     * Returns control panel composed of title editor, subtitle editor, scaling slider and spacing slider
     * @return control panel
     */
    private JPanel controlPanel() {

        // setup new panel
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        // add title and subtitle editors
        panel.add(panel(TITLE_PANEL_NAME, TITLE));
        panel.add(panel(SUBTITLE_PANEL_NAME, SUBTITLE));

        //panel.add(createSeparator());

        // add sliders
        setupSliders();
        panel.add(panel(SCALING_PANEL_NAME, SCALING_SLIDER));
        panel.add(panel(SPACING_PANEL_NAME, SPACING_SLIDER));

        return panel;
    }

    /**
     * Sets slider parameters to needed values
     */
    private void setupSliders() {

        // setup scaling slider
        SCALING_SLIDER.setMajorTickSpacing(SCALING_MAJOR_TRICK_SPACING);
        SCALING_SLIDER.setMinorTickSpacing(MINOR_TRICK_SPACING);
        SCALING_SLIDER.setPaintTicks(true);
        SCALING_SLIDER.setPaintLabels(true);

        // setup spacing slider
        SPACING_SLIDER.setMajorTickSpacing(SPACING_MAJOR_TRICK_SPACING);
        SPACING_SLIDER.setMinorTickSpacing(MINOR_TRICK_SPACING);
        SPACING_SLIDER.setPaintTicks(true);
        SPACING_SLIDER.setPaintLabels(true);

    }

    /**
     * Returns the input editor panel composed of a single editor text pane
     * @return input editor panel
     */
    private JPanel editorPanel() {

        // setup new panel
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
        panel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), EDITOR_PANEL_NAME));

        panel.add(EDITOR);
        // setup input editor
        //this.add(EDITOR, BorderLayout.CENTER);
        EDITOR.setFont(EDITOR_FONT);
        JScrollPane scrollPane = new JScrollPane(EDITOR);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        panel.add(scrollPane);

        return panel;
    }

    /**
     * Creates a new JPanel with a border and adds component to it
     * To be used to add minor specific panels to control panel
     * @param title name of new panel
     * @param component component to be added to panel
     * @return resultant JPanel
     */
    private JPanel panel(String title, JComponent component) {

        // setup new panel based on panel name
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
        panel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), title));

        // add specified component to the panel
        panel.add(component);

        return panel;
    }


}
