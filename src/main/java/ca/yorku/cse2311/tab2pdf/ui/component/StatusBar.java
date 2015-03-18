package ca.yorku.cse2311.tab2pdf.ui.component;

import ca.yorku.cse2311.tab2pdf.ui.support.JFrameTool;

import javax.swing.*;

/**
 * Created by Glib Sitiugin on 2015-03-07.
 */
public class StatusBar extends JPanel {

    private static final String STATUS_BAR_NAME = "Program Status";

    private static final String SOURCE_FILE_LABEL_NAME = "Source File: ";

    private static final String SYMBOLS_NUMBER_LABEL_NAME = "Symbols Number: ";

    public static final JLabel INPUT_FILE_PATH = new JLabel("No File Selected");

    public static final JLabel SYMBOLS_NUMBER = new JLabel("0");

    public static final JLabel HINT_LABEL = new JLabel(JFrameTool.EMPTY_HINT);

    /**
     * Constructs a new status bar
     */
    public StatusBar() {

        super();

        // setup new panel
        this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        this.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), STATUS_BAR_NAME));
        this.setPreferredSize(JFrameTool.BAR_SIZE);

        // handle display of input file
        this.add(new JLabel(SOURCE_FILE_LABEL_NAME));
        this.add(INPUT_FILE_PATH);

        // add separator
        this.add(new JPopupMenu.Separator());

        // handle display of symbols number in editor
        this.add(new JLabel(SYMBOLS_NUMBER_LABEL_NAME));
        this.add(SYMBOLS_NUMBER);

        // add separator
        //this.add(new JPopupMenu.Separator());

        // handle display of hints
        //this.add(HINT_LABEL);
    }


    public static void setInputFilePath(String path) {

        INPUT_FILE_PATH.setText(path);
    }

    public static void setSymbolsNumber(int number) {

        SYMBOLS_NUMBER.setText(String.valueOf(number));
    }

    public static void setHint(String hint) {

        HINT_LABEL.setText(hint);
    }
}
