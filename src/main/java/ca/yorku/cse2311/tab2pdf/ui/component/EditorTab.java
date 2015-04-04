package ca.yorku.cse2311.tab2pdf.ui.component;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.List;

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
    private final int SCALING_SLIDER_MIN = 5;
    private final int SCALING_SLIDER_MAX = 15;
    private final int SCALING_SLIDER_INIT = 7;
    private final int SPACING_SLIDER_MIN = 2;
    private final int SPACING_SLIDER_MAX = 10;
    private final int SPACING_SLIDER_INIT = 5;
    //private final int SCALING_MAJOR_TRICK_SPACING = 5;
    //private final int SPACING_MAJOR_TRICK_SPACING = 3;
    //private final int MINOR_TRICK_SPACING = 1;
    private final String SLIDER_LEFT_INDEX = "Narrow";
    private final String SLIDER_RIGHT_INDEX = "Wide";

    /**
     * Input elements data
     */
    public final String TITLE_PATTERN = "TITLE=";
    public final String SUBTITLE_PATTERN = "SUBTITLE=";
    public final String SPACING_PATTERN = "SPACING=";
    public final String SCALING_PATTERN = "SCALING=";

    /**
     * An frame of monospaced font for input editor
     */
    private final Font EDITOR_FONT = new Font(Font.MONOSPACED, Font.PLAIN, 12);

    /**
     * String constants
     */
    private static final String TITLE_PANEL_NAME = "Song Title";

    private static final String SUBTITLE_PANEL_NAME = "Song Subtitle";

    private static final String SCALING_PANEL_NAME = "Vertical Scaling";

    private static final String SPACING_PANEL_NAME = "Horizontal Spacing";

    private static final String EDITOR_PANEL_NAME = "Input Editor";

    private static final String SAVE_PANEL_NAME = "Save Panel";

    public static final Dimension TAB_SIZE = new Dimension(500, 500);

    /**
     * Tab components
     */
    private final JTextPane EDITOR = new JTextPane();

    private final JTextField TITLE = new JTextField("");

    private final JTextField SUBTITLE = new JTextField("");

    private final JSlider SCALING_SLIDER = new JSlider(JSlider.HORIZONTAL, SCALING_SLIDER_MIN, SCALING_SLIDER_MAX, SCALING_SLIDER_INIT);

    private final JSlider SPACING_SLIDER = new JSlider(JSlider.HORIZONTAL, SPACING_SLIDER_MIN, SPACING_SLIDER_MAX, SPACING_SLIDER_INIT);

    public JTextPane getEditor() {

        return EDITOR;
    }

    public JSlider getScalingSlider(){
        return SCALING_SLIDER;
    }
    public JSlider getSpacingSlider(){
        return SPACING_SLIDER;
    }

    public String getTitle() {

        return TITLE.getText();
    }

    public void setTitle(String title) {

        TITLE.setText(title);

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

    public JTextField getTitleField() {
        return TITLE;
    }
    public JTextField getSubtitleField(){
        return SUBTITLE;
    }

    public String getText() {
        return EDITOR.getText();
    }

    public String[] getTextAsArray() {
        return getText().split("\\r?\\n");
    }

    public List<String> getTextAsList() {
        return Arrays.asList(getTextAsArray());
    }

    /**
     * Sets the file we are editing
     * @param f The new file we are editing
     */
    public void setFile(File f) throws IOException {

        //this.getEditor().setText(FileUtils.readFile(f));

        // set control panel components to default values
        this.TITLE.setText("");
        this.SUBTITLE.setText("");
        this.SPACING_SLIDER.setValue(5);
        this.SCALING_SLIDER.setValue(7);

        BufferedReader reader = new BufferedReader(new FileReader(f));
        try {
            StringBuilder builder = new StringBuilder();
            String line = reader.readLine();

            while (line != null) {

                // decide what to do with input line
                // (either store to the left panel or put into editor)
                parseInputLine(builder, line);
                // read next line
                line = reader.readLine();
            }
            // set editor text
            this.getEditor().setText(builder.toString());
        }
        finally {
            reader.close();
        }

    }

    /**
     * Depending on input line either puts it to control panel or into editor
     * @param builder String Builder we are working with
     * @param line String from the input file
     */
    private void parseInputLine(StringBuilder builder, String line) {

        // if the line is subtitle line set subtitle
        if (line.contains(SUBTITLE_PATTERN)) {
            String subtitle = line.replace(SUBTITLE_PATTERN, "");
            this.SUBTITLE.setText(subtitle);
        }
        // if the line is title line set title
        else if (line.contains(TITLE_PATTERN)) {
            String title = line.replace(TITLE_PATTERN, "");
            this.TITLE.setText(title);
        }
        // if the line is spacing line set spacing
        else if (line.contains(SPACING_PATTERN)) {
            String spacing = line.replace(SPACING_PATTERN, "");
            this.SPACING_SLIDER.setValue(Integer.parseInt(spacing));
        }
        // if the line is scaling line set scaling
        else if (line.contains(SCALING_PATTERN)) {
            String scaling = line.replace(SCALING_PATTERN, "");
            this.SCALING_SLIDER.setValue(Integer.parseInt(scaling));
        }
        // otherwise put the line into editor
        else {
            builder.append(line);
            builder.append(System.lineSeparator());
        }
    }

    /**
     * Constructs a new Editor tab composed of control panel and input editor
     */
    public EditorTab() {

        super();

        // setup layout
        this.setLayout(new BorderLayout());

        // add components to the tab
        this.add(controlPanel(), BorderLayout.WEST);
        this.add(editorPanel(), BorderLayout.CENTER);

        // set size
        this.setPreferredSize(TAB_SIZE);
    }

    /**
     * Returns control panel composed of title editor, subtitle editor, scaling slider and spacing slider
     * @return control panel
     */
    private JPanel controlPanel() {

        // setup new panel
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        // prevent JTextFields from scaling vertically
        TITLE.setMaximumSize(new Dimension(
                (int) TITLE.getMaximumSize().getWidth(),
                (int) TITLE.getPreferredSize().getHeight()
        ));
        SUBTITLE.setMaximumSize(new Dimension(
                (int) TITLE.getMaximumSize().getWidth(),
                (int) TITLE.getPreferredSize().getHeight()
        ));

        // add title and subtitle editors
        panel.add(panel(TITLE_PANEL_NAME, TITLE));
        panel.add(panel(SUBTITLE_PANEL_NAME, SUBTITLE));

        //panel.add(createSeparator());

        // add sliders
        setupSliders();
        panel.add(panel(SPACING_PANEL_NAME, SPACING_SLIDER));
        panel.add(panel(SCALING_PANEL_NAME, SCALING_SLIDER));

        panel.add(Box.createVerticalGlue());

        return panel;
    }

    /**
     * Sets slider parameters to needed values
     */
    private void setupSliders() {

        // setup spacing slider
        /*SPACING_SLIDER.setMajorTickSpacing(SPACING_MAJOR_TRICK_SPACING);
        SPACING_SLIDER.setMinorTickSpacing(MINOR_TRICK_SPACING);
        SPACING_SLIDER.setPaintTicks(true);*/
        SPACING_SLIDER.setPaintLabels(true);
        Dictionary<Integer, JComponent> spacingLabels = new Hashtable<Integer, JComponent>();
        spacingLabels.put(SPACING_SLIDER_MIN + 1, new JLabel(SLIDER_LEFT_INDEX));
        spacingLabels.put(SPACING_SLIDER_MAX - 1, new JLabel(SLIDER_RIGHT_INDEX));
        SPACING_SLIDER.setLabelTable(spacingLabels);

        // setup scaling slider
        /*SCALING_SLIDER.setMajorTickSpacing(SCALING_MAJOR_TRICK_SPACING);
        SCALING_SLIDER.setMinorTickSpacing(MINOR_TRICK_SPACING);
        SCALING_SLIDER.setPaintTicks(true);*/
        SCALING_SLIDER.setPaintLabels(true);
        Dictionary<Integer, JComponent> scalingLabels = new Hashtable<Integer, JComponent>();
        scalingLabels.put(SCALING_SLIDER_MIN + 1, new JLabel(SLIDER_LEFT_INDEX));
        scalingLabels.put(SCALING_SLIDER_MAX - 1, new JLabel(SLIDER_RIGHT_INDEX));
        SCALING_SLIDER.setLabelTable(scalingLabels);
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

    /**
     * TODO: put a string like "click open to choose a file first"
     * @param enabled
     */
    @Override
    public void setEnabled(boolean enabled) {

        super.setEnabled(enabled);

        getTitleField().setEnabled(enabled);
        getSubtitleField().setEnabled(enabled);
        getSpacingSlider().setEnabled(enabled);
        getScalingSlider().setEnabled(enabled);
    }
}
