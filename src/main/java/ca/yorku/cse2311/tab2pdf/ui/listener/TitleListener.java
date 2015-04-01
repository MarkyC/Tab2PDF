package ca.yorku.cse2311.tab2pdf.ui.listener;

import ca.yorku.cse2311.tab2pdf.model.Title;
import ca.yorku.cse2311.tab2pdf.parser.TabParser;
import ca.yorku.cse2311.tab2pdf.ui.MainJFrame;
import ca.yorku.cse2311.tab2pdf.ui.component.EditorTab;
import ca.yorku.cse2311.tab2pdf.util.Strings;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.logging.Logger;

/**
 * TitleListener
 *
 * Listens for changes to the Tab's title (TITLE=) attribute
 *
 * @author Varsha Ragavendran , Glib Sitiugin, Marco Cirllo
 * @since 2015-03-25
 */
public class TitleListener extends KeyAdapter {

    private final static Logger LOG = Logger.getLogger(TitleListener.class.getName());

    private final MainJFrame window;

    /**
     * @param window the window we are working with
     */
    public TitleListener(MainJFrame window) {

        super();

        this.window = window;
    }

    @Override
    public void keyReleased(KeyEvent e) {

        super.keyReleased(e);

        // Grab the old title fom the editor, new title from the text field
        // since this listener watches for changes on the text field
        EditorTab editor = window.getEditorTab();
        Title oldTitle =  TabParser.getTitle(editor.getTextAsList());
        Title newTitle = new Title(editor.getTitleField().getText());

        if ( !newTitle.equals(oldTitle) ) { // The title has changed
            LOG.info(String.format("Title changed from %s to %s", oldTitle.getValue(), newTitle.getValue()));

            // update the title in the editor
            updateTitle(editor.getEditor(), oldTitle, newTitle);

            // Fire an update event, and reset the caret to the first position
            window.update("Changed title to " + newTitle.getValue());
            window.getInputEditor().setCaretPosition(0);
        }
    }

    /**
     * Updates the title in the editor
     * @param editor    the editor to update
     * @param oldTitle  the old title
     * @param newTitle  the new title
     */
    public static void updateTitle(JTextPane editor, Title oldTitle, Title newTitle) {
        if (Title.DEFAULT_TITLE.equals(oldTitle.getValue())) {

            // Fixes a bug where the Tab says "TITLE=", but our title field says No Title
            // This is because we map
            // "TITLE=" (the empty string is the title)
            // to "TITLE=No Title", so our tabs will have a default title that is not empty
            //
            // We use this for loop instead of String.replaceAll() since I could not find a way
            // to match TITLE= but not SUBTITLE= without splitting the input line-by-line

            String[] lines = editor.getText().split("\\r?\\n");
            for (int i = 0; i < lines.length; i++) {
                String line = lines[i];
                if (line.matches("^TITLE=")) {
                    // we found the title line, update it, and finish the loop
                    lines[i] = line.replaceAll("^TITLE=", newTitle.toString());
                    break;
                }
            }

            // update the editor with the new title
            editor.setText( Strings.join(lines) );
        } else {

            // For every other title (that isn't empty), we simply replace the old title with the new title
            editor.setText(
                    editor.getText().replace(oldTitle.toString(), newTitle.toString())
            );
        }
    }
}


