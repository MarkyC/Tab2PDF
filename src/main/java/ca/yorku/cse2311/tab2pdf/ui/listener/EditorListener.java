package ca.yorku.cse2311.tab2pdf.ui.listener;

import ca.yorku.cse2311.tab2pdf.model.Spacing;
import ca.yorku.cse2311.tab2pdf.model.Subtitle;
import ca.yorku.cse2311.tab2pdf.model.Title;
import ca.yorku.cse2311.tab2pdf.parser.TabParser;
import ca.yorku.cse2311.tab2pdf.ui.MainJFrame;
import ca.yorku.cse2311.tab2pdf.ui.component.EditorTab;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.logging.Logger;

/**
 * EditorListener
 *
 * Listens for changes to the Tab
 *
 * @author Varsha Ragavendran , Glib Sitiugin, Marco Cirllo
 * @since 2015-03-25
 */
public  class EditorListener extends KeyAdapter {

    private final static Logger LOG = Logger.getLogger(EditorListener.class.getName());

    private final MainJFrame window;

    /**
     * @param window the window we are working with
     */
    public EditorListener(MainJFrame window) {

        super();

        this.window = window;
    }

    @Override
    public void keyReleased(KeyEvent e) {

        super.keyReleased(e);

        // update the title and subtitle if they are changed
        updateTitle();
        updateSubtitle();
        updateSpacing();
    }

    private void updateTitle() {

        EditorTab editor = window.getEditorTab();
        Title newTitle =  TabParser.getTitle(editor.getTextAsList());
        Title oldTitle = new Title(editor.getTitleField().getText());

        if ( !newTitle.equals(oldTitle) ) {
            LOG.info(String.format("Title changed from %s to %s", oldTitle.getValue(), newTitle.getValue()));

            // update the title in the title text field
            editor.getTitleField().setText(newTitle.getValue());

            // Fire an update event
            window.update("Changed title to " + newTitle.getValue());
        }
    }

    private void updateSubtitle() {

        EditorTab editor = window.getEditorTab();
        Subtitle newSubtitle =  TabParser.getSubtitle(editor.getTextAsList());
        Subtitle oldSubtitle = new Subtitle(editor.getSubtitleField().getText());

        if ( !newSubtitle.equals(oldSubtitle) ) {
            LOG.info(String.format("Subtitle changed from %s to %s", oldSubtitle.getValue(), newSubtitle.getValue()));

            // update the subtitle in the subtitle text field
            editor.getSubtitleField().setText(newSubtitle.getValue());

            // Fire an update event
            window.update("Changed subtitle to " + newSubtitle.getValue());
        }
    }

    private void updateSpacing() {
        EditorTab editor = window.getEditorTab();
        Spacing newSpacing =  TabParser.getSpacing(editor.getTextAsList());
        Spacing oldSpacing = new Spacing(editor.getSpacingValue());

        if ( !newSpacing.equals(oldSpacing) ) {
            LOG.info(String.format("Spacing changed from %f to %f", oldSpacing.getSpacing(), newSpacing.getSpacing()));

            // update the subtitle in the subtitle text field
            editor.getSpacingSlider().setValue((int) newSpacing.getSpacing());

            // Fire an update event
            window.update("Changed spacing to " + newSpacing.getSpacing());
        }
    }
}


