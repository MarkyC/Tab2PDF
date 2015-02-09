package ca.yorku.cse2311.tab2pdf.ui;

import javax.swing.*;

/**
 * Created by glibs_000 on 2015-03-07.
 */
public class StatusBar extends JPanel {

    private static JLabel inputFilePath = new JLabel("No File Selected");

    private static JLabel symbolsNumber = new JLabel("0");

    private static JLabel hintLabel = new JLabel(JFrameData.EMPTY_HINT);

    public StatusBar() {

        super();

        // setup new panel
        this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        this.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Preview Panel"));
        this.setPreferredSize(JFrameData.BAR_SIZE);

        this.add(new JLabel("Source File: "));
        this.add(inputFilePath);
        this.add(new JPopupMenu.Separator());
        this.add(new JLabel("Symbols Number: "));
        this.add(symbolsNumber);
        this.add(new JPopupMenu.Separator());
        this.add(hintLabel);
    }

    public static int getSymbolsNumber() {

        return EditorPanel.EDITOR.getText().length();
    }


    public static void setSymbolsNumber(int number) {

        symbolsNumber.setText(String.valueOf(number));
    }

    public static void updateStatusBarPath(String path) {

        inputFilePath.setText(path);
    }
}
