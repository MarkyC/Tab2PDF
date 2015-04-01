package ca.yorku.cse2311.tab2pdf.util;

import ca.yorku.cse2311.tab2pdf.PdfHelper;
import ca.yorku.cse2311.tab2pdf.model.*;
import ca.yorku.cse2311.tab2pdf.parser.TabParser;
import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * pdfCreator
 * Handles the creation of the pdf file
 *
 * @author Brody Atto
 * @since 2015-02-4
 */
public class PdfCreator implements Runnable {

    private final PdfHelper helper;

    private final List<String> lines;

    private final Callback callback;

    private List<? extends ILongDraw> longNotes = Collections.unmodifiableList(Arrays.asList(
            new PullOff(new Note()),
            new HammerOn(new Note())
    ));

    private static List<? extends IDrawable> ignoreNotes = Collections.unmodifiableList(Arrays.asList(
            new Dash(),
            new Pipe(),
            new DoubleBar()
    ));

    private static List<? extends IDrawable> bars = Collections.unmodifiableList(Arrays.asList(
            new Pipe(),
            new DoubleBar()
    ));

    private final Logger LOG = Logger.getLogger(this.getClass().getName());

    public PdfCreator(PdfHelper helper, List<String> lines, Callback callback) {

        this.helper = helper;
        this.lines = lines;
        this.callback = callback;
    }

    public void run() {

        try {

            // attempt to create a PDF from the parsed tab
            createPdf(TabParser.parse(lines));

            // notify our observers by giving them the output PDF File
            LOG.info("PDF created successfully, running Callback...");
            callback.onCallback(true, helper.getFile());

        } catch (Exception e) {
            LOG.log(Level.SEVERE, "Could not convert tab: "+e.getMessage(), e);
            callback.onCallback(false, e);
        }
    }

    public void createPdf(Tab tab) throws Exception {

        // Create document
        Document document = this.helper.getDocument();
        // get writer
        PdfWriter writer = this.helper.getWriter();
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
        helper.stave(0);

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

                double projectedHeight = helper.determineYCoordinate(stave + 1) + (6) * helper.getLineSpace();
                if (projectedHeight < 0) { // if the stave would go of the page make a new page and reset the positions
                    writer.setPageEmpty(false);
                    document.newPage();

                    stave = -1;
                }

                helper.stave(stave);
            }

            //reset x position to be used by the next loop
            float xPos = xBarPos;

            //add start of bar
            if (bar.getBeginRepeat()) {
                helper.startRepeat(stave, xPos, spacing);
                xBarPos += spacing; //Double bars get an extra space
            } else {
                new Pipe().draw(helper, stave, 1, xPos);
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
                            ((ILongDraw) note).drawLong(helper, stave, lineNumber, xPos, lastStave[i], lastLine[i], lastXPos[i], lastString[i]); //They get the information of the last symbol on the line
                        } else {
                            note.draw(helper, stave, lineNumber, xPos); //if not it is just a normal draw
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
                helper.endRepeat(stave, xPos, spacing);
                if (bar.getRepeat() != 1) {
                    helper.drawRepeat(stave, 0, xPos, bar.getRepeat());
                }
            } else {
                new Pipe().draw(helper, stave, 1, xPos);
            }

            xBarPos = xPos;
        }

        //Document is finished
        document.close();
    }

    /**
     * Used to provide a callback to execute when this Runnable has finished
     */
    public interface Callback {
        public void onCallback(boolean success, Object output);
    }
}
