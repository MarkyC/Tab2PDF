package ca.yorku.cse2311.tab2pdf.model;

import com.itextpdf.text.pdf.PdfWriter;

/**
 * Dash
 *
 * Represents a dash ('-') in musical notation
 *
 * @author Marco
 * @since 2015-01-12
 */
public class Dash implements ITabNotation {

    @Override
    public String toString() {
        return "-";
    }

    @Override
    public boolean equals(Object obj) {

        return obj instanceof Dash;    // All Spaces are the same for now
    }

    public void Draw(int staveNumber, int lineNumber, int xCoordinate, PdfWriter writer) {
        //Do Nothing
    }
}
