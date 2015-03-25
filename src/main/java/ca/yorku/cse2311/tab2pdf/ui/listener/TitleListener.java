package ca.yorku.cse2311.tab2pdf.ui.listener;

import ca.yorku.cse2311.tab2pdf.model.Title;
import ca.yorku.cse2311.tab2pdf.parser.TabParser;
import ca.yorku.cse2311.tab2pdf.ui.MainJFrame;
import ca.yorku.cse2311.tab2pdf.ui.component.EditorTab;

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
            editor.getEditor().setText(
                    editor.getText().replace(oldTitle.toString(), newTitle.toString())
            );

            // Fire an update event, and reset the caret to the first position
            window.update("Changed title to " + newTitle.getValue());
            window.getInputEditor().setCaretPosition(0);
        }
    }
}


