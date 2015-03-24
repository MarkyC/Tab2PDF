package ca.yorku.cse2311.tab2pdf.ui.listener;

import ca.yorku.cse2311.tab2pdf.model.Spacing;
import ca.yorku.cse2311.tab2pdf.parser.TabParser;
import ca.yorku.cse2311.tab2pdf.ui.MainJFrame;
import ca.yorku.cse2311.tab2pdf.ui.component.EditorTab;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.util.logging.Logger;


/**
 * SpacingListener
 *
 * Handles updates to the SPACING= attribute
 *
 * @author Varsha Ragavendran, Marco Cirillo
 * @since 2015-03-25
 */
public class SpacingListener implements ChangeListener {

    private final static Logger LOG = Logger.getLogger(SpacingListener.class.getName());

    private final MainJFrame window;

    /**
     * @param window the window we are working with
     */
    public SpacingListener(MainJFrame window) {

        super();

        this.window = window;
    }

    @Override
    public void stateChanged(ChangeEvent e) {

        // Grab the old spacing fom the editor, new spacing from the slider
        EditorTab editor = window.getEditorTab();
        Spacing oldSpacing = TabParser.getSpacing(editor.getTextAsList());
        Spacing newSpacing = new Spacing(editor.getSpacingValue());

        if ( !newSpacing.equals(oldSpacing) ) { // The spacing has changed
            LOG.info(String.format("spacing changed from %f to %f", oldSpacing.getSpacing(), newSpacing.getSpacing()));

            // update the spacing in the editor
            editor.getEditor().setText(
                    editor.getText().replaceAll("SPACING=(.*)", newSpacing.toString())
            );

            // Fire an update event, and reset the caret to the first position
            window.update("Changed spacing to " + newSpacing.getSpacing());
            window.getInputEditor().setCaretPosition(0);
        }
    }
}
