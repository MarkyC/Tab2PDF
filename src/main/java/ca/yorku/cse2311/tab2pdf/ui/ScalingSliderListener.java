package ca.yorku.cse2311.tab2pdf.ui;

import ca.yorku.cse2311.tab2pdf.PdfHelper;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.event.ActionListener;
import java.util.EventListener;
import java.util.logging.Level;

/**
 * Created by Glib Sitiugin on 2015-03-07.
 */
public class ScalingSliderListener extends JFrameListener implements EventListener, ChangeListener {

    /**
     * Constructor
     *
     * @param frame we are working with
     */
    public ScalingSliderListener(MainJFrame frame) {

        super(frame);
    }

    @Override
    public void stateChanged(ChangeEvent e) {

        LOGGER.log(Level.INFO, String.valueOf(ToolBar.SCALING_SLIDER.getValue()));

        PdfHelper.setLineSpace(ToolBar.SCALING_SLIDER.getValue());
    }
}
