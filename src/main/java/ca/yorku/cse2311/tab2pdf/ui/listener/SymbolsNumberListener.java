package ca.yorku.cse2311.tab2pdf.ui.listener;

import ca.yorku.cse2311.tab2pdf.ui.MainJFrame;
import ca.yorku.cse2311.tab2pdf.ui.component.EditorTab;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Created by Glib Sitiugin on 2015-04-09.
 */
public class SymbolsNumberListener implements KeyListener {

    private final MainJFrame window;

    public SymbolsNumberListener(MainJFrame window) {

        this.window = window;
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // do nothing
    }

    @Override
    public void keyPressed(KeyEvent e) {
        updateSymbolsNumber();
    }

    @Override
    public void keyReleased(KeyEvent e) {
        updateSymbolsNumber();
    }

    private void updateSymbolsNumber() {

        int symbolsNumber = window.getEditorTab().getEditor().getText().length();
        window.getStatusbar().setSymbolsNumber(symbolsNumber);
    }
}
