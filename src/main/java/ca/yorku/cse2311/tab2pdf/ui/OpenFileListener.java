package ca.yorku.cse2311.tab2pdf.ui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.EventListener;
import java.util.logging.Level;

/**
 * Created by Glib Sitiugin on 2015-03-06.
 */
public final class OpenFileListener extends JFrameListener implements EventListener, ActionListener {


    public OpenFileListener(MainJFrame frame) {

        super(frame);
    }


    /**
     * Loads the input file contents in the input editor
     *
     * @param sourceFile a file to read from
     * @throws IOException when file can not be read
     */
    private void loadEditorFile(File sourceFile) throws IOException {

        //Setup file reader
        BufferedReader bufferedReader = new BufferedReader(new FileReader(sourceFile));
        StringBuilder stringBuilder = new StringBuilder();
        //TODO: check if line is not null
        String nextLine = bufferedReader.readLine();
        //Read file
        while (nextLine != null) {
            stringBuilder.append(nextLine);
            stringBuilder.append(System.lineSeparator());
            nextLine = bufferedReader.readLine();
        }

        //Put the resulting string into editor
        getEditor().setText(stringBuilder.toString());

        //Scroll back to the top
        getEditor().setCaretPosition(0);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        LOGGER.log(Level.INFO, e.paramString());

        // setup file chooser
        JFileChooser fileChooser = new JFileChooser();
        //fileChooser.setCurrentDirectory(getInputFile().getParentFile()); //Starts chooser in current directory
        fileChooser.setFileFilter(JFrameData.TEXT_FILE_FILTER);           // Allow *.txt files (default)
        fileChooser.addChoosableFileFilter(JFrameData.TAB_FILE_FILTER);   // Allow *.tab files

        // open the file chooser, showing the open dialog
        if (JFileChooser.APPROVE_OPTION == fileChooser.showOpenDialog(super.getJFrame())) {

            // Set the file path in the text field to that of the users chosen file
            File selectedFile = fileChooser.getSelectedFile();
            JFrameListener.setInputFile(selectedFile);

            //Load file contents into editor, change symbol number in toolbar
            StatusBar.updateStatusBarPath(selectedFile.getPath());

            try {loadEditorFile(selectedFile);}
            catch (IOException e1) {e1.printStackTrace();}
            StatusBar.setSymbolsNumber(StatusBar.getSymbolsNumber());
        }
    }
}
