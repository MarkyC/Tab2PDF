package ca.yorku.cse2311.tab2pdf.ui.listener;

import ca.yorku.cse2311.tab2pdf.ui.MainJFrame;
import ca.yorku.cse2311.tab2pdf.ui.component.StatusBar;
import ca.yorku.cse2311.tab2pdf.ui.support.JFrameTool;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.EventListener;
import java.util.logging.Level;

/**
 * The listener fires when open button is clicked
 *
 * Created by Glib Sitiugin on 2015-03-06.
 */
public final class OpenFileListener extends AbstractListener implements EventListener, ActionListener {

    /**
     * Filters *.txt and *.text files
     */
    private final FileFilter TEXT_FILE_FILTER = new FileNameExtensionFilter("Text Files (*.txt, *.text)", "txt", "text");
    /**
     * Filters *.tab files
     */
    private final FileFilter TAB_FILE_FILTER = new FileNameExtensionFilter("Tab Files (*.tab)", "tab");

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
        //TODO: check if line is null
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

    /**
     * Unblocks components which can be used after the file is opened
     */
    @Override
    public void enableComponents() {

        JFrameTool.enableComponent(JFrameTool.getSavePdfButton(), true);
        JFrameTool.enableComponent(JFrameTool.getTitle(), true);
        JFrameTool.enableComponent(JFrameTool.getSubtitle(), true);
        JFrameTool.enableComponent(JFrameTool.getScalingSlider(), true);
        JFrameTool.enableComponent(JFrameTool.getSpacingSlider(), true);
        JFrameTool.enableComponent(JFrameTool.getConvertButton(), true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        LOGGER.log(Level.INFO, e.paramString());
        StatusBar.setHint("Open File Button Clicked");

        // setup file chooser
        JFileChooser fileChooser = new JFileChooser();
        //fileChooser.setCurrentDirectory(getInputFile().getParentFile()); //Starts chooser in current directory
        fileChooser.setFileFilter(TEXT_FILE_FILTER);           // Allow *.txt files (default)
        fileChooser.addChoosableFileFilter(TAB_FILE_FILTER);   // Allow *.tab files

        // open the file chooser, showing the open dialog
        if (JFileChooser.APPROVE_OPTION == fileChooser.showOpenDialog(super.getJFrame())) {

            // Set the file path in the text field to that of the users chosen file
            File selectedFile = fileChooser.getSelectedFile();
            setInputFile(selectedFile);

            //Load file contents into editor, change symbol number in toolbar
            StatusBar.setInputFilePath(selectedFile.getPath());
            try {loadEditorFile(selectedFile);}
            catch (IOException e1) {e1.printStackTrace();}
            StatusBar.setSymbolsNumber(JFrameTool.getSymbolsNumber());

            // unblock components which can be used now
            enableComponents();
        }

        // disable save button
        //ToolBar.enableComponents(ToolBar.SAVE_PDF_BUTTON, false);

        // enable JFrame components which can be used now
        //ToolBar.enableComponents(ToolBar.CONVERT_BUTTON, true);
        //ToolBar.enableComponents(InputEditorTab.SCALING_SLIDER, true);
        //ToolBar.enableComponents(InputEditorTab.SPACING_SLIDER, true);
    }
}

