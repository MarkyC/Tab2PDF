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

    public static final String STATUS_BAR_NAME = "Status Bar";

    public static final JButton OPEN_BUTTON = new JButton("Open File");

    public static final JButton SAVE_BUTTON = new JButton("Save File");

    public static final JButton CONVERT_BUTTON = new JButton("Convert to Pdf");

    public static final JButton HELP_BUTTON = new JButton("Help");

    public static final JSlider SCALING_SLIDER = new JSlider(JSlider.HORIZONTAL, JFrameData.SCALING_SLIDER_MIN, JFrameData.SCALING_SLIDER_MAX, JFrameData.SCALING_SLIDER_INIT);

    public static final JSlider SPACING_SLIDER = new JSlider(JSlider.HORIZONTAL, JFrameData.SPACING_SLIDER_MIN, JFrameData.SPACING_SLIDER_MAX, JFrameData.SPACING_SLIDER_INIT);

    private static final JTextPane EDITOR = MainJFrame.inputEditor;

    private static JLabel inputFilePath = new JLabel("No File Selected");

    private static JLabel symbolsNumber = new JLabel("0");

    private static JProgressBar progressBar = new JProgressBar();


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

            case STATUS_BAR_NAME:
                super.add(statusBar());
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
        toolBar.setPreferredSize(JFrameData.BAR_SIZE);
        toolBar.setFloatable(false);

        // set button size
        setButtonSize(OPEN_BUTTON);
        setButtonSize(SAVE_BUTTON);
        setButtonSize(CONVERT_BUTTON);
        setButtonSize(HELP_BUTTON);

        // add buttons to toolbar
        toolBar.add(OPEN_BUTTON);
        toolBar.add(SAVE_BUTTON);
        toolBar.add(CONVERT_BUTTON);
        toolBar.add(HELP_BUTTON);

        // add sliders to toolbar
        setupSliders();
        toolBar.add(new JLabel("    Vertical Scaling:"));
        toolBar.add(SCALING_SLIDER);
        toolBar.add(new JLabel("    Horizontal Spacing:"));
        toolBar.add(SPACING_SLIDER);

        return toolBar;
    }

    /**
     * @return a panel containing input EDITOR
     */
    private JPanel editorPanel() {

        // create a new panel
        JPanel panel = newPanel(INPUT_EDITOR_NAME, true);

        // setup EDITOR
        panel.add(EDITOR);
        EDITOR.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 12));

        // setup scroll pane
        JScrollPane scrollPane = new JScrollPane(EDITOR);
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

    private JPanel statusBar() {

        //TODO: make progress bar work
        // create a new panel
        JPanel panel = newPanel(STATUS_BAR_NAME, true);
        panel.setPreferredSize(JFrameData.BAR_SIZE);

        setupProgressBar();
        panel.add(progressBar);
        panel.add(new JPopupMenu.Separator());
        panel.add(new JLabel("Source File: "));
        panel.add(inputFilePath);
        panel.add(new JPopupMenu.Separator());
        panel.add(new JLabel("Symbols Number: "));
        panel.add(symbolsNumber);

        return panel;
    }

    public static void setInputFilePath(String filePath) {

        inputFilePath.setText(filePath);
    }

    public static void setSymbolsNumber(int number) {

        symbolsNumber.setText(String.valueOf(number));
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

        // setup scaling slider
        SCALING_SLIDER.setMajorTickSpacing(5);
        SCALING_SLIDER.setMinorTickSpacing(1);
        SCALING_SLIDER.setPaintTicks(true);
        SCALING_SLIDER.setPaintLabels(true);

        // setup spacing slider
        SPACING_SLIDER.setMajorTickSpacing(3);
        SPACING_SLIDER.setMinorTickSpacing(1);
        SPACING_SLIDER.setPaintTicks(true);
        SPACING_SLIDER.setPaintLabels(true);
    }

    private void setupProgressBar() {

        //progressBar.setPreferredSize(JFrameData.PROGRESS_BAR_SIZE);
        progressBar.setMaximumSize(JFrameData.PROGRESS_BAR_SIZE);
    }

}
