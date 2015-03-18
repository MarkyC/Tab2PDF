package ca.yorku.cse2311.tab2pdf.ui.listener;

import ca.yorku.cse2311.tab2pdf.PdfHelper;
import ca.yorku.cse2311.tab2pdf.ui.MainJFrame;
import ca.yorku.cse2311.tab2pdf.ui.component.InputEditorTab;
import ca.yorku.cse2311.tab2pdf.ui.component.StatusBar;
import ca.yorku.cse2311.tab2pdf.ui.support.JFrameTool;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.util.EventListener;
import java.util.logging.Level;

/**
 * Created by Glib Sitiugin on 2015-03-07.
 */
public class ScalingSliderListener extends AbstractListener implements EventListener, ChangeListener {

    /**
     * Constructor
     *
     * @param frame we are working with
     */
    public ScalingSliderListener(MainJFrame frame) {

        super(frame);
    }

    @Override
    public void enableComponents() {

        // do nothing
    }

    @Override
    public void stateChanged(ChangeEvent e) {

        LOGGER.log(Level.INFO, String.valueOf(InputEditorTab.SCALING_SLIDER.getValue()));
        StatusBar.setHint("Scaling Slider State Changed to: " + InputEditorTab.SCALING_SLIDER.getValue());

        PdfHelper.setLineSpace(JFrameTool.getScalingSlider().getValue());
    }
}
