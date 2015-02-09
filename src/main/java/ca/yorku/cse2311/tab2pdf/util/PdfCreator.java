package ca.yorku.cse2311.tab2pdf.util;

import ca.yorku.cse2311.tab2pdf.Arguments;
import ca.yorku.cse2311.tab2pdf.PdfHelper;
import ca.yorku.cse2311.tab2pdf.model.*;
import ca.yorku.cse2311.tab2pdf.parser.TabParser;
import ca.yorku.cse2311.tab2pdf.ui.MainJFrame;
import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import java.awt.*;
import java.io.File;
import java.nio.file.Files;
import java.util.LinkedList;
import java.util.logging.Logger;

import static ca.yorku.cse2311.tab2pdf.PdfHelper.stave;

/**
 * pdfCreator
 * Handles the creation of the pdf file
 *
 * @author Brody Atto
 * @since 2015-02-4
 */
public class PdfCreator implements Runnable {

    private static java.util.List<ILongDraw> longNotes = new LinkedList<>();

    static {
        longNotes.add(new PullOff(new Note()));
        longNotes.add(new HammerOn(new Note()));
    }

    private static java.util.List<IDrawable> ignoreNotes = new LinkedList<>();

    static {
        ignoreNotes.add(new Dash());
        ignoreNotes.add(new Pipe());
        ignoreNotes.add(new DoubleBar());
        //ignoreNotes.add()
    }

    private static java.util.List<IDrawable> bars = new LinkedList<>();

    static {
        bars.add(new Pipe());
        bars.add(new DoubleBar());
        //bars.add()
    }
    private final Logger LOG = Logger.getLogger(this.getClass().getName());
    private Arguments args;


    public PdfCreator(Arguments args) {
        this.args = args;
    }

    public void run() {

        try {
            //Tab tab = TabParser.parse(FileUtils.readFile(args.getInputFile()));
            Tab tab = TabParser.parse(MainJFrame.getEditorContents());
            createPdf(tab, args.getOutputFile());
            Desktop.getDesktop().open(args.getOutputFile());

        } catch (Exception e) {
            e.printStackTrace();
            LOG.severe(e.getMessage());
        }
    }

    public void createPdf(Tab tab, File out) throws Exception {

        // Create document
        Document document = new Document();
        // get writer
        PdfWriter writer = PdfWriter.getInstance(document, Files.newOutputStream(out.toPath()));
        // open document
        document.open();

        //Title
        Paragraph title = new Paragraph(
                tab.getTitle().getValue(), new Font(Font.FontFamily.TIMES_ROMAN, 24f));
        title.setAlignment(Paragraph.ALIGN_CENTER);
        document.add(title);     // The Tab's Title

        //Add subtitle
        Paragraph subTitle = new Paragraph(tab.getSubtitle().getValue());
        subTitle.setAlignment(Paragraph.ALIGN_CENTER);
        document.add(subTitle);     // The Tab's Subtitle

        //print first stave
        stave(0, writer);

        //Initialising variables

        float pageWidth = writer.getPageSize().getWidth();

        float spacing = tab.getSpacing().getSpacing();

        //The current stave
        int stave = 0;

        //Pages Margin
        int MARGIN = 30;

        //Position of the bar (left edge)
        float xBarPos = MARGIN;

        //The number of lines in the bar (default is 6)
        int numLines = tab.getBars().get(0).getNumLines() + 1;

        //These values are hear to keep track of the last note for long draws (ex. hammer on)
        int[] lastStave = new int[numLines];
        float[] lastXPos = new float[numLines];
        int[] lastLine = new int[numLines];
        String[] lastString = new String[numLines];

        for (int j = 0; j < numLines; j++) {
            lastStave[j] = 0;
            lastXPos[j] = 0f;
            lastLine[j] = 0;
            lastString[j] = " ";
        }

        //Goes through each bar
        for (Bar bar : tab.getBars()) {

            double projectedWidth = (bar.getLine(0).getLine().size() - 1) * spacing + xBarPos + MARGIN;
            if (projectedWidth > pageWidth) { //if teh bar would go off the page make a new stave
                stave++;
                xBarPos = MARGIN;

                double projectedHeight = PdfHelper.determineYCoordinate(stave + 1) + (6) * PdfHelper.getLineSpace();
                if (projectedHeight < 0) { // if the stave would go of the page make a new page and reset the positions
                    writer.setPageEmpty(false);
                    document.newPage();

                    stave = -1;
                }

                stave(stave, writer);
            }

            //reset x position to be used by the next loop
            float xPos = xBarPos;

            //add start of bar
            if (bar.getBeginRepeat()) {
                PdfHelper.startRepeat(stave, xPos, spacing, writer);
                xBarPos += spacing; //Double bars get an extra space
            } else {
                new Pipe().draw(stave, 1, xPos, writer);
            }
            xBarPos += spacing; //Add a space after the bar

            //iterate through each line in the bar
            for (int i = 0; i < bar.getLines().size(); ++i) {
                xPos = xBarPos; //resets the value again. the earlier reset was to grantee the value was initialised

                int lineNumber = i + 1; //the line we are working on
                BarLine line = bar.getLine(i);

                //Now we are iterating through each of the symbols
                for (IDrawable note : line.getLine()) {
                    //Draw the note
                    for (IDrawable longNoteType : longNotes) {
                        if (longNoteType.getClass() == note.getClass()) { //If it is a long draw we have to do something special
                            ((ILongDraw) note).drawLong(stave, lineNumber, xPos, writer, lastStave[i], lastLine[i], lastXPos[i], lastString[i]); //They get the information of the last symbol on the line
                        } else {
                            note.draw(stave, lineNumber, xPos, writer); //if not it is just a normal draw
                        }
                    }

                    //Saves the location of the last symbol
                    boolean ignore = false;
                    for (IDrawable ignoreType : ignoreNotes) { //only if it is not on the ignor list. (Ex: Dash, Pipe)
                        if (ignoreType.getClass().isInstance(note)) {
                            ignore = true;
                        }
                    }
                    if (!ignore) {
                        lastXPos[i] = xPos;
                        lastLine[i] = lineNumber;
                        lastStave[i] = stave;
                        lastString[i] = note.toString();
                    }

                    xPos += spacing; //adds the space for the symbol

                }

            }

            //add end of bar
            if (bar.getEndRepeat()) {
                xPos += spacing;
                PdfHelper.endRepeat(stave, xPos, spacing, writer);
                if (bar.getRepeat() != 1) {
                    PdfHelper.drawRepeat(stave, 0, xPos, bar.getRepeat(), writer);
                }
            } else {
                new Pipe().draw(stave, 1, xPos, writer);
            }

            xBarPos = xPos;
        }

        //Document is finished
        document.close();
    }
}
