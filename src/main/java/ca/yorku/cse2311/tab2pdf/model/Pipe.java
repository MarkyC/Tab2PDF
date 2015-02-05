package ca.yorku.cse2311.tab2pdf.model;

import com.itextpdf.text.pdf.PdfWriter;

import static ca.yorku.cse2311.tab2pdf.PdfHelper.thinLine;

/**
 * Pipe
 *
 * Represents a Pipe ('|') in Musical Notation. A Pipe separates Standard Bars of music
 * It's the leftmost thing in the image on this page: http://en.wikipedia.org/wiki/Bar_%28music%29
 *
 * @author Brody
 * @since 2015-01-12
 */
public class Pipe implements ITabNotation {

    @Override
    public String toString() {
        return "|";
    }

    @Override
    public boolean equals(Object obj) {

        return obj instanceof Pipe;
    }

    public void draw(int staveNumber, int lineNumber, int xCoordinate, PdfWriter writer) {
        thinLine(staveNumber, xCoordinate, writer);
    }
}
