package ca.yorku.cse2311.tab2pdf.ui;

import javax.swing.*;

/**
 * Created by Glib Sitiugin on 2015-03-07.
 */
public class ToolBar extends JToolBar {

    public static final JButton OPEN_BUTTON = new JButton("Open File");

    public static final JButton SAVE_BUTTON = new JButton("Save File");

    public static final JButton CONVERT_BUTTON = new JButton("Convert to Pdf");

    public static final JButton HELP_BUTTON = new JButton("Help");

    public static final JSlider SCALING_SLIDER = new JSlider(JSlider.HORIZONTAL, JFrameData.SCALING_SLIDER_MIN, JFrameData.SCALING_SLIDER_MAX, JFrameData.SCALING_SLIDER_INIT);

    public static final JSlider SPACING_SLIDER = new JSlider(JSlider.HORIZONTAL, JFrameData.SPACING_SLIDER_MIN, JFrameData.SPACING_SLIDER_MAX, JFrameData.SPACING_SLIDER_INIT);

    public ToolBar() {

        super();

        // setup toolbar
        this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        this.setPreferredSize(JFrameData.BAR_SIZE);
        this.setFloatable(false);

        // set button size
        setButtonSize(OPEN_BUTTON);
        setButtonSize(SAVE_BUTTON);
        setButtonSize(CONVERT_BUTTON);
        setButtonSize(HELP_BUTTON);

        // add buttons to toolbar
        this.add(OPEN_BUTTON);
        this.add(SAVE_BUTTON);
        this.add(CONVERT_BUTTON);
        this.add(HELP_BUTTON);

        // add sliders to toolbar
        setupSliders();
        this.add(new JLabel("    Vertical Scaling:"));
        this.add(SCALING_SLIDER);
        this.add(new JLabel("    Horizontal Spacing:"));
        this.add(SPACING_SLIDER);
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
}
