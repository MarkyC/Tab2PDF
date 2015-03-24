package ca.yorku.cse2311.tab2pdf.ui.listener;

import ca.yorku.cse2311.tab2pdf.ui.MainJFrame;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
        // do nothing
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int line = 0;
        String[] string = window.getEditorContents();
        Pattern i= Pattern.compile("(\\W|^)SUBTITLE[=](\\W|$)");
        Matcher matcher = i.matcher("SUBTITLE=");
        while (matcher.find()) {
            line++;
            String newTitle = "SUBTITLE=" + window.getSubtitleTextField().getText();
            String printableText =  Arrays.toString(string).replaceAll("[\\[\\]]", "").replaceAll(", ", "\n").replace(string[line], newTitle);
            window.getInputEditor().setText(printableText);
            window.getInputEditor().setCaretPosition(0);

        }

    }
}
