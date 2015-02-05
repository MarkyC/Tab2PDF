package ca.yorku.cse2311.tab2pdf.util;

import ca.yorku.cse2311.tab2pdf.Arguments;
import ca.yorku.cse2311.tab2pdf.PdfHelper;
import ca.yorku.cse2311.tab2pdf.model.Bar;
import ca.yorku.cse2311.tab2pdf.model.BarLine;
import ca.yorku.cse2311.tab2pdf.model.IDrawable;
import ca.yorku.cse2311.tab2pdf.model.Tab;
import ca.yorku.cse2311.tab2pdf.parser.TabParser;
import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import java.awt.*;
import java.io.File;
import java.nio.file.Files;
import java.util.logging.Logger;

import static ca.yorku.cse2311.tab2pdf.PdfHelper.stave;

/**
 * pdfCreator
 * Handles the creaton of the pdf file
 *
 * @author Brody Atto
 * @since 2015-02-4
 */
public class PdfCreator implements Runnable {

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

        double spacing = 5 + tab.getSpacing().getSpacing();

        int stave = 0;

        int MARGIN = 50;

        int xBarPos = MARGIN;

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

                    //  (int staveNumber, int lineNumber, int xCoordinate, PdfWriter writer)
                    note.draw(stave, lineNumber, xPos, writer);
                    xPos += spacing;

                }

            }
            xBarPos = xPos;
        }
        

        document.close();
    }
}
