package ca.yorku.cse2311.tab2pdf.ui.listener;

import ca.yorku.cse2311.tab2pdf.model.Subtitle;
import ca.yorku.cse2311.tab2pdf.parser.TabParser;
import ca.yorku.cse2311.tab2pdf.ui.MainJFrame;
import ca.yorku.cse2311.tab2pdf.ui.component.EditorTab;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.logging.Logger;


/**
 * ${NAME}
 *
 *
 *
 * @author Varsha, Marco Cirillo
 * @since 2015-03-25
 */
public class SubtitleListener extends KeyAdapter {

    private final static Logger LOG = Logger.getLogger(SubtitleListener.class.getName());

    private final MainJFrame window;

    /**
     * @param window the window we are working with
     */
    public SubtitleListener(MainJFrame window) {

        super();

        this.window = window;
    }
    @Override
    public void keyReleased(KeyEvent e) {

        super.keyReleased(e);

        // Grab the old title fom the editor, new title from the text field
        // since this listener watches for changes on the text field
        EditorTab editor = window.getEditorTab();
        Subtitle oldSubtitle = TabParser.getSubtitle(editor.getTextAsList());
        Subtitle newSubtitle = new Subtitle(editor.getSubtitleField().getText());

        if ( !newSubtitle.equals(oldSubtitle) ) { // The subtitle has changed
            LOG.info(String.format("Subtitle changed from %s to %s", oldSubtitle.getValue(), newSubtitle.getValue()));

            // update the subtitle in the editor
            updateSubtitle(editor.getEditor(), oldSubtitle, newSubtitle);

            // Fire an update event, and reset the caret to the first position
            window.update("Changed title to " + newSubtitle.getValue());
            window.getInputEditor().setCaretPosition(0);
        }
    }

    /**
     * Updates the subtitle in the editor
     * @param editor        The editor to update
     * @param oldSubtitle   the old subtitle
     * @param newSubtitle   the new subtitle
     */
    public static void updateSubtitle(JTextPane editor, Subtitle oldSubtitle, Subtitle newSubtitle) {
        if (Subtitle.DEFAULT_SUBTITLE.equals(oldSubtitle.getValue())) {

            // Fixes a bug where the Tab says "SUBTITLE=", but our subtitle field says No Subtitle
            // This is because we map
            // "SUBTITLE=" (the empty string is the subtitle)
            // to "SUBTITLE=No Subtitle", so our tabs will have a default subtitle that is not empty
            editor.setText(
                    editor.getText().replaceAll("SUBTITLE=", newSubtitle.toString())
            );
        } else {

            // For every other subtitle (that isn't empty), we simply replace the old subtitle with the new subtitle
            editor.setText(
                    editor.getText().replace(oldSubtitle.toString(), newSubtitle.toString())
            );
        }
    }
}
