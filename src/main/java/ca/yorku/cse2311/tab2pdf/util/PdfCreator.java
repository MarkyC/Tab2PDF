package ca.yorku.cse2311.tab2pdf.util;

import ca.yorku.cse2311.tab2pdf.Arguments;
import ca.yorku.cse2311.tab2pdf.PdfHelper;
import ca.yorku.cse2311.tab2pdf.model.*;
import ca.yorku.cse2311.tab2pdf.parser.TabParser;
import com.itextpdf.text.Document;
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
            Tab tab = TabParser.parse(FileUtils.readFile(args.getInputFile()));
            createPdf(tab, args.getOutputFile());
            Desktop.getDesktop().open(args.getOutputFile());

        } catch (Exception e) {
            e.printStackTrace();
            LOG.severe(e.getMessage());
        }
    }

    public void createPdf(Tab tab, File out) throws Exception {

        // step 1
        Document document = new Document();
        // step 2
        PdfWriter writer = PdfWriter.getInstance(document, Files.newOutputStream(out.toPath()));
        // step 3
        document.open();
        // step 4
        //document.add(new Paragraph("Hello World!"));                // Hello World!
        document.add(new Paragraph(tab.getTitle().getValue()));     // The Tab's Title
        document.add(new Paragraph(tab.getSubtitle().getValue()));  // The Tab's Subtitle

        stave(0, writer);


        float pageWidth = writer.getPageSize().getWidth();

        float spacing = tab.getSpacing().getSpacing();

        int stave = 0;

        int MARGIN = 30;

        float xBarPos = MARGIN;

        int numLines = tab.getBars().get(0).getNumLines() + 1;

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

        for (Bar bar : tab.getBars()) {

            double temp = (bar.getLine(0).getLine().size() - 1) * spacing + xBarPos + MARGIN;

            if (temp > pageWidth) {
                stave++;
                xBarPos = MARGIN;

                temp = PdfHelper.determineYCoordinate(stave + 1) + (6) * PdfHelper.getLineSpace();
                if (temp < 0) {
                    writer.setPageEmpty(false);
                    document.newPage();

                    stave = -1;
                }

                stave(stave, writer);
            }

            float xPos = xBarPos;

            //add start of bar
            if (bar.getBeginRepeat()) {
                PdfHelper.startRepeat(stave, xPos, spacing, writer);
                xBarPos += spacing;
            } else {
                new Pipe().draw(stave, 1, xPos, writer);
            }
            xBarPos += spacing;

            for (int i = 0; i < bar.getLines().size(); ++i) {
                xPos = xBarPos;

                int lineNumber = i + 1;
                BarLine line = bar.getLine(i);

                for (IDrawable note : line.getLine()) {
                    //Draw the note
                    for (IDrawable longNoteType : longNotes) {
                        if (longNoteType.getClass() == note.getClass()) { //If it is a long draw we have to do something special
                            ((ILongDraw) note).drawLong(stave, lineNumber, xPos, writer, lastStave[i], lastLine[i], lastXPos[i], lastString[i]);
                        } else {
                            note.draw(stave, lineNumber, xPos, writer);
                        }
                    }

                    //Saves the location of the last note
                    boolean ignore = false;
                    for (IDrawable ignoreType : ignoreNotes) {
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

                    xPos += spacing;

                }

            }

            //add end of bar
            if (bar.getEndRepeat()) {
                xPos += spacing;
                PdfHelper.endRepeat(stave, xPos, spacing, writer);
            } else {
                new Pipe().draw(stave, 1, xPos, writer);
            }

            xBarPos = xPos;
        }
        

        document.close();
    }
}
