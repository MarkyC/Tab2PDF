package ca.yorku.cse2311.tab2pdf.ui.listener;

import ca.yorku.cse2311.tab2pdf.ui.MainJFrame;
import ca.yorku.cse2311.tab2pdf.ui.component.InputEditorTab;
import ca.yorku.cse2311.tab2pdf.ui.component.StatusBar;
import ca.yorku.cse2311.tab2pdf.ui.support.JFrameTool;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.util.Arrays;
import java.util.EventListener;
import java.util.logging.Level;

/**
 * Created by Glib Sitiugin on 2015-03-07.
 */
public class SpacingSliderListener extends AbstractListener implements EventListener, ChangeListener {
    /**
     * Constructor
     *
     * @param frame we are working with
     */
    public SpacingSliderListener(MainJFrame frame) {
        super(frame);
    }

    @Override
    public void enableComponents() {

        // do nothing
    }

    @Override
    public void stateChanged(ChangeEvent e) {

        LOGGER.log(Level.INFO, String.valueOf(InputEditorTab.SPACING_SLIDER.getValue()));
        StatusBar.setHint("Spacing Slider State Changed to: " + InputEditorTab.SPACING_SLIDER.getValue());

        // get editor lines
        String[] line = getEditor().getText().split("\\n");

        //TODO: make method work when no spacing is shown
        line[2] = "SPACING=" + JFrameTool.getSpacingSlider().getValue();

        String printableText = Arrays.toString(line).replaceAll("[\\[\\]]", "").replaceAll(", ", "\n");
        getEditor().setText(printableText);

        //Scroll back to the top
        getEditor().setCaretPosition(0);

        // enable save button
        //ToolBar.enableComponents(JFrameTool.getSavePdfButton(), true);
    }
}
