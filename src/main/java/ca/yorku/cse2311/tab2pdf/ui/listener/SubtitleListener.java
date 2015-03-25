package ca.yorku.cse2311.tab2pdf.ui.listener;

import ca.yorku.cse2311.tab2pdf.model.Subtitle;
import ca.yorku.cse2311.tab2pdf.parser.TabParser;
import ca.yorku.cse2311.tab2pdf.ui.MainJFrame;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Arrays;


/**
 * Created by Varsha on 15-03-24.
 */
public class SubtitleListener implements KeyListener {

    private final MainJFrame window;

    /**
     * @param window the window we are working with
     */
    public SubtitleListener(MainJFrame window) {

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
        Subtitle subtitle =  TabParser.getSubtitle(Arrays.asList(string));
        String newSubtitle =  window.getSubtitleTextField().getText();
        String printableText =  Arrays.toString(string).replaceAll("[\\[\\]]", "").replaceAll(", ", "\n").replace( subtitle.toString(), newSubtitle);
        window.getInputEditor().setText(printableText);
        window.getInputEditor().setCaretPosition(0);
    }

    @Override
    public void keyReleased(KeyEvent e) {
       //do nothing

    }
}
