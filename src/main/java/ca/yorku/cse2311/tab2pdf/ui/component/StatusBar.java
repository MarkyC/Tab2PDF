package ca.yorku.cse2311.tab2pdf.ui.component;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Glib Sitiugin on 2015-03-07.
 */
public class StatusBar extends JPanel {

    private static final String STATUS_BAR_NAME = "Program Status";

    private static final String SOURCE_FILE_LABEL_NAME = "Source File: ";

    private static final String SYMBOLS_NUMBER_LABEL_NAME = "Symbols Number: ";

    public static final JLabel INPUT_FILE_PATH = new JLabel("No File Selected");

    public static final JLabel SYMBOLS_NUMBER = new JLabel("0");

    public static final JLabel HINT_LABEL = new JLabel("");

    public static final Dimension BAR_SIZE = new Dimension(1020, 50);

    /**
     * Constructs a new status bar
     */
    public StatusBar() {

        super();

        // setup new panel
        this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        this.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), STATUS_BAR_NAME));
        this.setPreferredSize(BAR_SIZE);

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


    public void setInputFilePath(String path) {

        INPUT_FILE_PATH.setText(path);
    }

    public void setSymbolsNumber(int number) {

        SYMBOLS_NUMBER.setText(String.valueOf(number));
    }

    public void setHint(String hint) {

        HINT_LABEL.setText(hint);
    }
}
