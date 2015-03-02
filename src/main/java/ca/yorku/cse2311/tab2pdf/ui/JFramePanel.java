package ca.yorku.cse2311.tab2pdf.ui;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Glib Sitiugin on 2015-02-25.
 */
public class JFramePanel extends JPanel {

    public static final String TOOLBAR_PANEL_NAME = "Tool Bar";

    public static final String INPUT_EDITOR_NAME = "Input Editor";

    public static final String PREVIEW_PANEL_NAME = "Pdf Preview";

    public static JButton openButton = new JButton("Open File");

    public static JButton saveButton = new JButton("Save File");

    public static JButton convertButton = new JButton("Convert to Pdf");

    public static JButton helpButton = new JButton("Help");

    public static JSlider verticalSlider = new JSlider(JSlider.HORIZONTAL, JFrameData.SLIDER_MIN, JFrameData.SLIDER_MAX, JFrameData.SLIDER_INIT);

    public static JSlider horizontalSlider = new JSlider(JSlider.HORIZONTAL, JFrameData.SLIDER_MIN, JFrameData.SLIDER_MAX, JFrameData.SLIDER_INIT);

    private JTextPane editor = MainJFrame.inputEditor;


    /**
     * Constructs a new primary panel based on provided name
     *
     * @param name name of panel to be constructed (should be one of class _NAME constants)
     */
    public JFramePanel(String name) {

        // construct JPanel
        super();

        // setup appropriate panel based on name provided
        switch (name) {

            case TOOLBAR_PANEL_NAME:
                super.add(toolBar());
                break;

            case INPUT_EDITOR_NAME:
                super.add(editorPanel());
                break;

            case PREVIEW_PANEL_NAME:
                super.add(previewPanel());
                break;
        }
    }

    /**
     * @return a toolbar panel which is to be placed on the top of JFrame
     */
    private JToolBar toolBar() {

        // setup toolbar
        JToolBar toolBar = new JToolBar();
        toolBar.setLayout(new BoxLayout(toolBar, BoxLayout.X_AXIS));
        toolBar.setPreferredSize(JFrameData.TOOLBAR_SIZE);
        toolBar.setFloatable(false);

        // set button size
        setButtonSize(openButton);
        setButtonSize(saveButton);
        setButtonSize(convertButton);
        setButtonSize(helpButton);

        // add buttons to toolbar
        toolBar.add(openButton);
        toolBar.add(saveButton);
        toolBar.add(convertButton);
        toolBar.add(helpButton);

        // add sliders to toolbar
        setupSliders();
        toolBar.add(new JLabel("    Vertical Scaling:"));
        toolBar.add(verticalSlider);
        toolBar.add(new JLabel("    Horizontal Spacing:"));
        toolBar.add(horizontalSlider);

        return toolBar;
    }

    /**
     * @return a panel containing input editor
     */
    private JPanel editorPanel() {

        // create a new panel
        JPanel panel = newPanel(INPUT_EDITOR_NAME, true);

        // setup editor
        panel.add(editor);
        editor.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 12));

        // setup scroll pane
        JScrollPane scrollPane = new JScrollPane(editor);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        panel.add(scrollPane);

        // set max size
        panel.setPreferredSize(JFrameData.VIEW_PANEL_SIZE);

        return panel;
    }

    /**
     * @return a panel containing a preview image of Pdf file
     */
    private JPanel previewPanel() {

        // create a new panel
        JPanel panel = newPanel(PREVIEW_PANEL_NAME, true);

        // set panel size
        panel.setPreferredSize(JFrameData.VIEW_PANEL_SIZE);
        panel.setMaximumSize(JFrameData.VIEW_PANEL_SIZE);

        //TODO: make the panel display current state of Pdf file to be created

        return panel;
    }

    /**
     * Instantiates a new JPanel with a given name and border
     * @param name name of a panel to be constructed
     * @param isVisible setting this parameter to true will create a visible border for the panel
     * @return an empty JPanel
     */
    private JPanel newPanel(String name, boolean isVisible) {

        // setup new panel
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));

        // add visible border if isVisible is set
        if (isVisible)
            panel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), name));

        return panel;
    }

    private void setButtonSize(JButton button) {

        button.setPreferredSize(JFrameData.BUTTON_SIZE);
        button.setMaximumSize(JFrameData.BUTTON_SIZE);
    }

    private void setupSliders() {

        // setup vertical slider
        verticalSlider.setMajorTickSpacing(5);
        verticalSlider.setMinorTickSpacing(1);
        verticalSlider.setPaintTicks(true);
        verticalSlider.setPaintLabels(true);

        // setup horizontal slider
        horizontalSlider.setMajorTickSpacing(5);
        horizontalSlider.setMinorTickSpacing(1);
        horizontalSlider.setPaintTicks(true);
        horizontalSlider.setPaintLabels(true);
    }
}
