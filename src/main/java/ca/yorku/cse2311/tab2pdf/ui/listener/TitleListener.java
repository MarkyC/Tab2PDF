package ca.yorku.cse2311.tab2pdf.ui.listener;

import ca.yorku.cse2311.tab2pdf.model.Title;
import ca.yorku.cse2311.tab2pdf.parser.TabParser;
import ca.yorku.cse2311.tab2pdf.ui.MainJFrame;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Arrays;


/**
 * Created by Varsha Ragavendran , Glib Sitiugin
 */
public  class TitleListener implements KeyListener {

    private final MainJFrame window;
    //public static final Pattern TOKEN_PATTERN = Pattern.compile("^TITLE=(.+)", Pattern.CASE_INSENSITIVE);

    /**
     * @param window the window we are working with
     */
    public TitleListener(MainJFrame window) {

        super();

        this.window = window;
    }


    @Override
    public void keyTyped(KeyEvent e) {
        // do nothing
    }

    @Override
    public void keyPressed(KeyEvent e) {

        String[] string = window.getEditorContents();
        Title title =  TabParser.getTitle(Arrays.asList(string));
        String newTitle = window.getTitleTextField().getText();
        String printableText =  Arrays.toString(string).replaceAll("[\\[\\]]", "").replaceAll(", ", "\n").replace( title.toString(), newTitle);
        window.getInputEditor().setText(printableText);
        window.getInputEditor().setCaretPosition(0);

    }

    @Override
    public void keyReleased(KeyEvent e) {
       // do nothing

    }


}


