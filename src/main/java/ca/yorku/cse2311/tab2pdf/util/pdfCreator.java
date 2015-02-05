package ca.yorku.cse2311.tab2pdf.util;

import ca.yorku.cse2311.tab2pdf.Arguments;
import ca.yorku.cse2311.tab2pdf.model.*;
import ca.yorku.cse2311.tab2pdf.parser.TabParser;
import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import java.awt.*;
import java.io.File;
import java.nio.file.Files;
import java.util.logging.Logger;

import static ca.yorku.cse2311.tab2pdf.PdfHelper.*;

/**
 * pdfCreator
 * Handles the creaton of the pdf file
 *
 * @author Brody Atto
 * @since 2015-02-4
 */
public class pdfCreator implements Runnable {

    private final Logger LOG = Logger.getLogger(this.getClass().getName());
    private Arguments args;

    public pdfCreator(Arguments args) {
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

        stave(1, writer);


        double spaceing = 5 + tab.getSpacing().getSpacing();


        for (Bar bar : tab.getBars()) {
            for (int i = 0; i < bar.getLines().size(); ++i) {

                int lineNumber = i + 1;
                BarLine line = bar.getLine(i);
                int xPos = 50;
                for (ITabNotation note : line.getLine()) {

                    // TODO: What design pattern to use here?
                    if (note instanceof Pipe) {
                        thinLine(1, xPos, writer);
                        xPos += 10;
                    } else if (note instanceof Dash) {
                        xPos += 10;
                    } else if (note instanceof Note) {
                        int actualNote = Integer.parseInt(((Note) note).getNote());
                        drawDigit(1, lineNumber, xPos, actualNote, writer);
                        xPos += 10;
                    } else {
                        LOG.warning("Could not draw symbol " + note.getClass().getSimpleName());
                    }
                }

            }
        }
        

        document.close();
    }
}
