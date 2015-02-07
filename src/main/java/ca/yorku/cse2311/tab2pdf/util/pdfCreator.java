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
        document.add(new Paragraph(tab.getTitle().getTitle()));     // The Tab's Title
        document.add(new Paragraph(tab.getSubtitle().getSubtitle()));  // The Tab's Subtitle

        stave(0, writer);


        float pageWidth = writer.getPageSize().getWidth();

        double spacing = 3 + tab.getSpacing().getSpacing();

        int stave = 0;

        int MARGIN = 30;

        int xBarPos = MARGIN;

        int lastStave = 0;
        int lastXPos = 0;
        int lastLine = 0;
        String lastString = " ";

        for (Bar bar : tab.getBars()) {

            double temp = (bar.getLine(0).getLine().size() - 1) * spacing + xBarPos;

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

            int xPos = xBarPos;

            for (int i = 0; i < bar.getLines().size(); ++i) {
                xPos = xBarPos;

                int lineNumber = i + 1;
                BarLine line = bar.getLine(i);

                for (IDrawable note : line.getLine()) {
                    //Draw the note
                    for (IDrawable longNoteType : longNotes) {
                        if (longNoteType.getClass() == note.getClass()) { //If it is a long draw we have to do something special
                            ((ILongDraw) note).drawLong(stave, lineNumber, xPos, writer, lastStave, lastLine, lastXPos, lastString);
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
                        lastXPos = xPos;
                        lastLine = lineNumber;
                        lastStave = stave;
                        lastString = note.toString();
                    }

                    xPos += spacing;

                }

            }
            xBarPos = xPos;
        }
        

        document.close();
    }
}
