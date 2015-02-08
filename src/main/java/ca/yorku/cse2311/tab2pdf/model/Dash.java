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

    boolean imaginary;

    public Dash() {
        this(false);
    }

    public Dash(boolean imaginary) {
        this.imaginary = imaginary;
    }

    public int leftPadding() {
        return 0;
    }

    public int rightPadding() {
        return 0;
    }

    public int size() {
        return 1;
    }

    @Override
    public String toString() {
        if (imaginary) {
            return "";
        }
        return "-";
    }

    @Override
    public boolean equals(Object obj) {

        return obj instanceof Dash;    // All Spaces are the same for now
    }

    public void draw(int staveNumber, int lineNumber, float xCoordinate, PdfWriter writer) {
        //Do Nothing
    }
}
